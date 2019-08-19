<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% 
String importlibs = "common,table,form";	
%>
<!DOCTYPE html>
<html>
<head>
<!-- ControllerPath = "demo/orders/orders/ordersController/operation/Edit/id" -->
<title>详细</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/h5component/common/h5uiinclude-css.jsp">
<jsp:param value="<%=importlibs%>" name="importlibs"/>
</jsp:include>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false">
		<form id='form'>
						<input type="hidden" name="version" value="<c:out  value='${ordersDTO.version}'/>"/>
															<input type="hidden" name="id" value="<c:out  value='${ordersDTO.id}'/>"/>
																																																																																																																																																																																																																																																																																																																																																										 <table class="form_commonTable">
				<tr>
																																											 																			    																	<th width="10%" style="word-break:break-all;word-warp:break-word;">
								    								  	<label for="code">订单编码:</label></th>
								    									<td width="39%">
																		    <input class="form-control input-sm" type="text" name="code"  id="code" readonly="readonly" value="<c:out  value='${ordersDTO.code}'/>"/>
																	   </td>
																																								   													 												 																			    																	<th width="10%" style="word-break:break-all;word-warp:break-word;">
								    								  	<label for="goodsPrice">商品价格:</label></th>
								    									<td width="39%">
																		  										 										 		<div class="input-group input-group-sm spinner" data-trigger="spinner">
											  <input  class="form-control"  type="text" name="goodsPrice" id="goodsPrice" readonly="readonly" value="<c:out  value='${ordersDTO.goodsPrice}'/>" data-min="-<%=Math.pow(10,6)-Math.pow(10,-2)%>" data-max="<%=Math.pow(10,6)-Math.pow(10,-2)%>" data-step="1" data-precision="2">
											  <span class="input-group-addon">
											    <a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-triangle-top"></i></a>
											    <a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-triangle-bottom"></i></a>
											  </span>
											</div>
									 										 																	   </td>
																			</tr>
										<tr>
																																								   													 																			    																	<th width="10%" style="word-break:break-all;word-warp:break-word;">
								    								  	<label for="goodsColor">商品颜色:</label></th>
								    									<td width="39%">
																		    <input class="form-control input-sm" type="text" name="goodsColor"  id="goodsColor" readonly="readonly" value="<c:out  value='${ordersDTO.goodsColor}'/>"/>
																	   </td>
																																								   													 																			    																	<th width="10%" style="word-break:break-all;word-warp:break-word;">
								    								  	<label for="goodsSize">商品型号:</label></th>
								    									<td width="39%">
																		    <input class="form-control input-sm" type="text" name="goodsSize"  id="goodsSize" readonly="readonly" value="<c:out  value='${ordersDTO.goodsSize}'/>"/>
																	   </td>
																			</tr>
										<tr>
																																								   													 																			    																	<th width="10%" style="word-break:break-all;word-warp:break-word;">
								    								  	<label for="buyerId">购买人id:</label></th>
								    									<td width="39%">
																		    <input class="form-control input-sm" type="text" name="buyerId"  id="buyerId" readonly="readonly" value="<c:out  value='${ordersDTO.buyerId}'/>"/>
																	   </td>
																																								   													 																			    																	<th width="10%" style="word-break:break-all;word-warp:break-word;">
								    								  	<label for="buyerName">购买人名称:</label></th>
								    									<td width="39%">
																		    <input class="form-control input-sm" type="text" name="buyerName"  id="buyerName" readonly="readonly" value="<c:out  value='${ordersDTO.buyerName}'/>"/>
																	   </td>
																			</tr>
										<tr>
																																								   													 																			    																	<th width="10%" style="word-break:break-all;word-warp:break-word;">
								    								  	<label for="remark">备注:</label></th>
								    									<td width="39%">
																		    <input class="form-control input-sm" type="text" name="remark"  id="remark" readonly="readonly" value="<c:out  value='${ordersDTO.remark}'/>"/>
																	   </td>
																																								   													 																			    																	<th width="10%" style="word-break:break-all;word-warp:break-word;">
								    								  	<label for="status">订单状态:</label></th>
								    									<td width="39%">
																		    <input class="form-control input-sm" type="text" name="status"  id="status" readonly="readonly" value="<c:out  value='${ordersDTO.status}'/>"/>
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
	<script type="text/javascript">
		document.ready = function () {
		parent.orders.formValidate($('#form'));
	};
	//form控件禁用
	setFormDisabled();
	$(document).keydown(function(event){  
		event.returnValue = false;
		return false;
	});  
	</script>
</body>
</html>