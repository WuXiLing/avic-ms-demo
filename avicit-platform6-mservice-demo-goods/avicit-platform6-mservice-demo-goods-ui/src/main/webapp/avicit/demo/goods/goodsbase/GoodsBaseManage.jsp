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
<!-- ControllerPath = "demo/goods/goodsbase/goodsBaseController/toGoodsBaseManage" -->
<title>管理</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/h5component/common/h5uiinclude-css.jsp">
<jsp:param value="<%=importlibs%>" name="importlibs"/>
</jsp:include>
</head>
<body>
<div id="tableToolbar" class="toolbar">
	<div class="toolbar-left">
		<sec:accesscontrollist hasPermission="3" domainObject="formdialog_goodsBase_button_add" permissionDes="添加">
  	<a id="goodsBase_insert" href="javascript:;" class="btn btn-primary form-tool-btn btn-sm" role="button" title="添加"><i class="fa fa-plus"></i> 添加</a>
	</sec:accesscontrollist>
	<sec:accesscontrollist hasPermission="3" domainObject="formdialog_goodsBase_button_edit" permissionDes="编辑">
	<a id="goodsBase_modify" href="javascript:;" class="btn btn-primary form-tool-btn btn-sm" role="button" title="编辑"><i class="fa fa-file-text-o"></i> 编辑</a>
	</sec:accesscontrollist>
	<sec:accesscontrollist hasPermission="3" domainObject="formdialog_goodsBase_button_delete" permissionDes="删除">
	<a id="goodsBase_del" href="javascript:;" class="btn btn-primary form-tool-btn btn-sm" role="button" title="删除"><i class="fa fa-trash-o"></i> 删除</a>
	</sec:accesscontrollist>
		</div>
    <div class="toolbar-right">
	    <div class="input-group form-tool-search">
     		<input type="text" name="goodsBase_keyWord" id="goodsBase_keyWord" style="width:240px;" class="form-control input-sm" placeholder="请输入查询条件">
     		<label id="goodsBase_searchPart" class="icon icon-search form-tool-searchicon"></label>
   		</div>
   		<div class="input-group-btn form-tool-searchbtn">
   			<a id="goodsBase_searchAll" href="javascript:;" class="btn btn-defaul btn-sm" role="button" >高级查询 <span class="caret"></span></a>
   		</div>
    </div>
</div>					
<table id="goodsBasejqGrid"></table>
<div id="jqGridPager"></div>
</body>
<!-- 高级查询 -->
<div id="searchDialog" style="overflow: auto;display: none">
	<form id="form" style="padding: 10px;">
    	   <table class="form_commonTable">
			    <tr>
																																	  						   							 								<th width="10%" >商品编码:</th>
								<td width="39%">
										    								 <input title="商品编码" class="form-control input-sm" type="text" name="code" id="code" />
																		</td>
															 						   						   						   																							  						   							 								<th width="10%" >商品名称:</th>
								<td width="39%">
										    								 <input title="商品名称" class="form-control input-sm" type="text" name="name" id="name" />
																		</td>
																	</tr>
									<tr>
															 						   						   						   																							  						   							 								<th width="10%" >价格:</th>
								<td width="39%">
																			  										     												<div class="input-group input-group-sm spinner" data-trigger="spinner">
												  <input  class="form-control"  type="text" name="price" id="price"  data-min="-<%=Math.pow(10,6)-Math.pow(10,-2)%>" data-max="<%=Math.pow(10,6)-Math.pow(10,-2)%>" data-step="1" data-precision="2">
												  <span class="input-group-addon">
												    <a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-triangle-top"></i></a>
												    <a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-triangle-bottom"></i></a>
												  </span>
												</div>	
																					  																		</td>
															 						   						   						   																							  						   							 								<th width="10%" >商品简介:</th>
								<td width="39%">
										    								 <input title="商品简介" class="form-control input-sm" type="text" name="descriptionShort" id="descriptionShort" />
																		</td>
																	</tr>
									<tr>
															 						   						   						   																							  						   							 								<th width="10%" >缩略图:</th>
								<td width="39%">
										    								 <input title="缩略图" class="form-control input-sm" type="text" name="thumb" id="thumb" />
																		</td>
															 						   						   						   																							  						   							 								<th width="10%" >商品状态:</th>
								<td width="39%">
										    								 <input title="商品状态" class="form-control input-sm" type="text" name="status" id="status" />
																		</td>
																	</tr>
									<tr>
															 						   						   						   																																																																																																																																																																																																																																																																																																												 </tr>
    	</table>
    </form>
</div>
<jsp:include page="/avicit/platform6/h5component/common/h5uiinclude-js.jsp">
<jsp:param value="<%=importlibs%>" name="importlibs"/>
</jsp:include>
<script src="avicit/demo/goods/goodsbase/js/GoodsBase.js" type="text/javascript"></script>
<script type="text/javascript">
var goodsBase;
function formatValue(cellvalue, options, rowObject) {
		return '<a href="javascript:void(0);" onclick="goodsBase.detail(\''+rowObject.id+'\');">'+cellvalue+'</a>';
 }
function formatDateForHref(cellvalue, options, rowObject){
	var thisDate = format(cellvalue);
	return '<a href="javascript:void(0);" onclick="goodsBase.detail(\''+rowObject.id+'\');">'+thisDate+'</a>';
}
		
$(document).ready(function() {
	var dataGridColModel =  [
																{ label: 'id', name: 'id', key: true, width: 75, hidden:true }
																		  														    ,{ label: '商品编码', name: 'code', width: 150,formatter:formatValue}
																												  															    ,{ label: '商品名称', name: 'name', width: 150}
																													  															    ,{ label: '价格', name: 'price', width: 150}
																													  															    ,{ label: '商品简介', name: 'descriptionShort', width: 150}
																													  															    ,{ label: '缩略图', name: 'thumb', width: 150}
																													  															    ,{ label: '商品状态', name: 'status', width: 150}
																																																																																																				];
	var searchNames = new Array();
	var searchTips = new Array();
									  		        searchNames.push("code");
   searchTips.push("商品编码");
		 	 		  							  		        searchNames.push("name");
   searchTips.push("商品名称");
		 	 		  							  							  		     		  							  							  		     		  																																																																																		var searchC = searchTips.length==2?'或' + searchTips[1] : "";
	$('#goodsBase_keyWord').attr('placeholder','请输入' + searchTips[0] + searchC);
	goodsBase= new GoodsBase('goodsBasejqGrid','${url}','searchDialog','form','goodsBase_keyWord',searchNames,dataGridColModel);
	//添加按钮绑定事件
	$('#goodsBase_insert').bind('click', function(){
		goodsBase.insert();
	});
	//编辑按钮绑定事件
	$('#goodsBase_modify').bind('click', function(){
		goodsBase.modify();
	});
	//删除按钮绑定事件
	$('#goodsBase_del').bind('click', function(){  
		goodsBase.del();
	});
	//查询按钮绑定事件
	$('#goodsBase_searchPart').bind('click', function() {
		goodsBase.searchByKeyWord();
	});
	//查询框回车事件
	$('#goodsBase_keyWord').bind('keyup',function(e){
		if(e.keyCode == 13){
			goodsBase.searchByKeyWord();
		}
	});
	//打开高级查询框
	$('#goodsBase_searchAll').bind('click', function(){
		goodsBase.openSearchForm(this);
	});
																																																																																																																																																																																																					});

</script>
</html>