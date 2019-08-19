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
<!-- ControllerPath = "demo/orders/orders/ordersController/toOrdersManage" -->
<title>管理</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/h5component/common/h5uiinclude-css.jsp">
<jsp:param value="<%=importlibs%>" name="importlibs"/>
</jsp:include>
</head>
<body>
<div id="tableToolbar" class="toolbar">
	<div class="toolbar-left">
		<sec:accesscontrollist hasPermission="3" domainObject="formdialog_orders_button_add" permissionDes="添加">
  	<a id="orders_insert" href="javascript:void(0)" class="btn btn-primary form-tool-btn btn-sm" role="button" title="添加"><i class="fa fa-plus"></i> 添加</a>
	</sec:accesscontrollist>
	<sec:accesscontrollist hasPermission="3" domainObject="formdialog_orders_button_edit" permissionDes="编辑">
	<a id="orders_modify" href="javascript:void(0)" class="btn btn-primary form-tool-btn btn-sm" role="button" title="编辑"><i class="fa fa-file-text-o"></i> 编辑</a>
	</sec:accesscontrollist>
	<sec:accesscontrollist hasPermission="3" domainObject="formdialog_orders_button_delete" permissionDes="删除">
	<a id="orders_del" href="javascript:void(0)" class="btn btn-primary form-tool-btn btn-sm" role="button" title="删除"><i class="fa fa-trash-o"></i> 删除</a>
	</sec:accesscontrollist>
		</div>
    <div class="toolbar-right">
	    <div class="input-group form-tool-search">
     		<input type="text" name="orders_keyWord" id="orders_keyWord" style="width:240px;" class="form-control input-sm" placeholder="请输入查询条件">
     		<label id="orders_searchPart" class="icon icon-search form-tool-searchicon"></label>
   		</div>
   		<div class="input-group-btn form-tool-searchbtn">
   			<a id="orders_searchAll" href="javascript:void(0)" class="btn btn-defaul btn-sm" role="button" >高级查询 <span class="caret"></span></a>
   		</div>
    </div>
</div>					
<table id="ordersjqGrid"></table>
<div id="jqGridPager"></div>
</body>
<!-- 高级查询 -->
<div id="searchDialog" style="overflow: auto;display: none">
	<form id="form" style="padding: 10px;">
    	   <table class="form_commonTable">
			    <tr>
																						  						   							 							 						   						   						   																							  						   							 								<th width="10%">订单编码:</th>
										    								 <td width="39%">
	    								 <input title="订单编码" class="form-control input-sm" type="text" name="code" id="code" />
	    								 </td>
																								 						   						   						   																																		  						   							 								<th width="10%">商品价格:</th>
																			<td width="39%">
																				     												<div class="input-group input-group-sm spinner" data-trigger="spinner">
												  <input  class="form-control"  type="text" name="goodsPrice" id="goodsPrice"  data-min="-<%=Math.pow(10,6)-Math.pow(10,-2)%>" data-max="<%=Math.pow(10,6)-Math.pow(10,-2)%>" data-step="1" data-precision="2">
												  <span class="input-group-addon">
												    <a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-triangle-top"></i></a>
												    <a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-triangle-bottom"></i></a>
												  </span>
												</div>	
																					  										</td>
																										</tr>
									<tr>
															 						   						   						   																							  						   							 								<th width="10%">商品颜色:</th>
										    								 <td width="39%">
	    								 <input title="商品颜色" class="form-control input-sm" type="text" name="goodsColor" id="goodsColor" />
	    								 </td>
																								 						   						   						   																							  						   							 								<th width="10%">商品型号:</th>
										    								 <td width="39%">
	    								 <input title="商品型号" class="form-control input-sm" type="text" name="goodsSize" id="goodsSize" />
	    								 </td>
																										</tr>
									<tr>
															 						   						   						   																							  						   							 								<th width="10%">购买人id:</th>
										    								 <td width="39%">
	    								 <input title="购买人id" class="form-control input-sm" type="text" name="buyerId" id="buyerId" />
	    								 </td>
																								 						   						   						   																							  						   							 								<th width="10%">购买人名称:</th>
										    								 <td width="39%">
	    								 <input title="购买人名称" class="form-control input-sm" type="text" name="buyerName" id="buyerName" />
	    								 </td>
																										</tr>
									<tr>
															 						   						   						   																																		  						   							 								<th width="10%">订单状态:</th>
										    								 <td width="39%">
	    								 <input title="订单状态" class="form-control input-sm" type="text" name="status" id="status" />
	    								 </td>
																								 						   						   						   																							  						   							 									<th width="10%">支付时间(从):</th>
    									<td width="39%">
    									<div class="input-group input-group-sm">
							                <input class="form-control time-picker"  type="text" name="payTimeBegin" id="payTimeBegin"/><span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
							            </div>
    									</td>
																				 </tr>
										 <tr>
									        									<th width="10%">支付时间(至):</th>
    									<td width="39%">
    									<div class="input-group input-group-sm">
							                <input class="form-control time-picker"  type="text" name="payTimeEnd" id="payTimeEnd"/><span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
							            </div>
    									</td>	

																		
													   						   						   																							  						   							 									<th width="10%">取消时间(从):</th>
    									<td width="39%">
    									<div class="input-group input-group-sm">
							                <input class="form-control time-picker"  type="text" name="cancelTimeBegin" id="cancelTimeBegin"/><span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
							            </div>
    									</td>
																				 </tr>
										 <tr>
									        									<th width="10%">取消时间(至):</th>
    									<td width="39%">
    									<div class="input-group input-group-sm">
							                <input class="form-control time-picker"  type="text" name="cancelTimeEnd" id="cancelTimeEnd"/><span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
							            </div>
    									</td>	

																		
													   						   						   																																																																																																																																																																																																																																																																																																												 </tr>
    	</table>
    </form>
</div>
<jsp:include page="/avicit/platform6/h5component/common/h5uiinclude-js.jsp">
<jsp:param value="<%=importlibs%>" name="importlibs"/>
</jsp:include>
<script src="avicit/demo/orders/orders/js/Orders.js" type="text/javascript"></script>
<script type="text/javascript">
var orders;
function formatValue(cellvalue, options, rowObject) {
		return '<a href="javascript:void(0);" onclick="orders.detail(\''+rowObject.id+'\');">'+cellvalue+'</a>';
 }
function formatDateForHref(cellvalue, options, rowObject){
	var thisDate = format(cellvalue);
	return '<a href="javascript:void(0);" onclick="orders.detail(\''+rowObject.id+'\');">'+thisDate+'</a>';
}
		
$(document).ready(function () {
	var dataGridColModel =  [
																{ label: 'id', name: 'id', key: true, width: 75, hidden:true }
																		  																	,{ label: '订单编码', name: 'code', width: 150,formatter:formatValue}
																															  																		,{ label: '商品价格', name: 'goodsPrice', width: 150}
																													  																		,{ label: '商品颜色', name: 'goodsColor', width: 150}
																													  																		,{ label: '商品型号', name: 'goodsSize', width: 150}
																																  																		,{ label: '购买人名称', name: 'buyerName', width: 150}
																																  																		,{ label: '订单状态', name: 'status', width: 150}
																													  																		,{ label: '支付时间', name: 'payTime', width: 150}
																													  																		,{ label: '取消时间', name: 'cancelTime', width: 150}
																																																																																																				];
	var searchNames = new Array();
	var searchTips = new Array();
						  		  							  		         searchNames.push("code");
    searchTips.push("订单编码");
		 	 		  										  							  		         searchNames.push("goodsColor");
    searchTips.push("商品颜色");
		 	 		  							  		     		  							  		     		  							  		     		  										  		     		  							  							  																																																																																		var searchC = searchTips.length==2?'或' + searchTips[1] : "";
	$('#orders_keyWord').attr('placeholder','请输入' + searchTips[0] + searchC);
	orders= new Orders('ordersjqGrid','${url}','searchDialog','form','orders_keyWord',searchNames,dataGridColModel);
	//添加按钮绑定事件
	$('#orders_insert').bind('click', function(){
		orders.insert();
	});
	//编辑按钮绑定事件
	$('#orders_modify').bind('click', function(){
		orders.modify();
	});
	//删除按钮绑定事件
	$('#orders_del').bind('click', function(){  
		orders.del();
	});
	//查询按钮绑定事件
	$('#orders_searchPart').bind('click', function(){
		orders.searchByKeyWord();
	});
	//打开高级查询框
	$('#orders_searchAll').bind('click', function(){
		orders.openSearchForm(this);
	});
																																																																																																																																																																																																																																																																													
});

</script>
</html>