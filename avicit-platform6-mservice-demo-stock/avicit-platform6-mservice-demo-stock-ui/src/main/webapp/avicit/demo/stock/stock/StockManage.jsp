<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<% 
String importlibs = "common,table,form";	
%>
<!DOCTYPE html>
<html>
<head>
<!-- ControllerPath = "demo/stock/stock/stockController/toStockManage" -->
<title>管理</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/h5component/common/h5uiinclude-css.jsp">
<jsp:param value="<%=importlibs%>" name="importlibs"/>
</jsp:include>
</head>
<body>
<div id="tableToolbar" class="toolbar">
	<div class="toolbar-left">
		<sec:accesscontrollist hasPermission="3" domainObject="formdialog_stock_button_add" permissionDes="添加">
  	<a id="stock_insert" href="javascript:void(0)" class="btn btn-primary form-tool-btn btn-sm" role="button" title="添加"><i class="fa fa-plus"></i> 添加</a>
	</sec:accesscontrollist>
	<sec:accesscontrollist hasPermission="3" domainObject="formdialog_stock_button_edit" permissionDes="编辑">
	<a id="stock_modify" href="javascript:void(0)" class="btn btn-primary form-tool-btn btn-sm" role="button" title="编辑"><i class="fa fa-file-text-o"></i> 编辑</a>
	</sec:accesscontrollist>
	<sec:accesscontrollist hasPermission="3" domainObject="formdialog_stock_button_delete" permissionDes="删除">
	<a id="stock_del" href="javascript:void(0)" class="btn btn-primary form-tool-btn btn-sm" role="button" title="删除"><i class="fa fa-trash-o"></i> 删除</a>
	</sec:accesscontrollist>
		</div>
    <div class="toolbar-right">
	    <div class="input-group form-tool-search">
     		<input type="text" name="stock_keyWord" id="stock_keyWord" style="width:240px;" class="form-control input-sm" placeholder="请输入查询条件">
     		<label id="stock_searchPart" class="icon icon-search form-tool-searchicon"></label>
   		</div>
   		<div class="input-group-btn form-tool-searchbtn">
   			<a id="stock_searchAll" href="javascript:void(0)" class="btn btn-defaul btn-sm" role="button" >高级查询 <span class="caret"></span></a>
   		</div>
    </div>
</div>					
<table id="stockjqGrid"></table>
<div id="jqGridPager"></div>
</body>
<!-- 高级查询 -->
<div id="searchDialog" style="overflow: auto;display: none">
	<form id="form" style="padding: 10px;">
    	   <table class="form_commonTable">
			    <tr>
																						  						   							 							 						   						   						   																																		  						   							 								<th width="10%">库存数量:</th>
																			<td width="39%">
																				     												<div class="input-group input-group-sm spinner" data-trigger="spinner">
												  <input  class="form-control"  type="text" name="num" id="num"  data-min="-<%=Math.pow(10,12)-Math.pow(10,-0)%>" data-max="<%=Math.pow(10,12)-Math.pow(10,-0)%>" data-step="1" data-precision="0">
												  <span class="input-group-addon">
												    <a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-triangle-top"></i></a>
												    <a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-triangle-bottom"></i></a>
												  </span>
												</div>	
																					  										</td>
																								 						   						   						   																																																																																																																																																																																																																																																																																																												 </tr>
    	</table>
    </form>
</div>
<jsp:include page="/avicit/platform6/h5component/common/h5uiinclude-js.jsp">
<jsp:param value="<%=importlibs%>" name="importlibs"/>
</jsp:include>
<script src="avicit/demo/stock/stock/js/Stock.js" type="text/javascript"></script>
<script type="text/javascript">
var stock;
function formatValue(cellvalue, options, rowObject) {
		return '<a href="javascript:void(0);" onclick="stock.detail(\''+rowObject.id+'\');">'+cellvalue+'</a>';
 }
function formatDateForHref(cellvalue, options, rowObject){
	var thisDate = format(cellvalue);
	return '<a href="javascript:void(0);" onclick="stock.detail(\''+rowObject.id+'\');">'+thisDate+'</a>';
}
		
$(document).ready(function () {
	var dataGridColModel =  [
																{ label: 'id', name: 'id', key: true, width: 75, hidden:true }
																					  																		,{ label: '库存数量', name: 'num', width: 150}
																																																																																																				];
	var searchNames = new Array();
	var searchTips = new Array();
						  		  										  																																																																																		var searchC = searchTips.length==2?'或' + searchTips[1] : "";
	$('#stock_keyWord').attr('placeholder','请输入' + searchTips[0] + searchC);
	stock= new Stock('stockjqGrid','${url}','searchDialog','form','stock_keyWord',searchNames,dataGridColModel);
	//添加按钮绑定事件
	$('#stock_insert').bind('click', function(){
		stock.insert();
	});
	//编辑按钮绑定事件
	$('#stock_modify').bind('click', function(){
		stock.modify();
	});
	//删除按钮绑定事件
	$('#stock_del').bind('click', function(){  
		stock.del();
	});
	//查询按钮绑定事件
	$('#stock_searchPart').bind('click', function(){
		stock.searchByKeyWord();
	});
	//打开高级查询框
	$('#stock_searchAll').bind('click', function(){
		stock.openSearchForm(this);
	});
																																																																																																																		
});

</script>
</html>