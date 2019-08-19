<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<% 
String importlibs = "common,table,form";	
%>
<!DOCTYPE html>
<html>
<head>
<!-- ControllerPath = "demo/orders/orders/ordersController/operation/Add/null" -->
<title>添加</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/h5component/common/h5uiinclude-css.jsp">
<jsp:param value="<%=importlibs%>" name="importlibs"/>
</jsp:include>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false">
		<form id='form'>
			<input type="hidden" name="id" />
			<table class="form_commonTable">
				<tr>
																																		 																	 													<th width="10%">
						    						  	<label for="code">订单编码:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="code"  id="code" />
													   </td>
																														   									 									 																	 													<th width="10%">
						    						  	<label for="goodsPrice">商品价格:</label></th>
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
																														   									 																	 													<th width="10%">
						    						  	<label for="goodsColor">商品颜色:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="goodsColor"  id="goodsColor" />
													   </td>
																														   									 																	 													<th width="10%">
						    						  	<label for="goodsSize">商品型号:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="goodsSize"  id="goodsSize" />
													   </td>
															</tr>
								<tr>
																														   									 																	 													<th width="10%">
						    						  	<label for="buyerId">购买人id:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="buyerId"  id="buyerId" />
													   </td>
																														   									 																	 													<th width="10%">
						    						  	<label for="buyerName">购买人名称:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="buyerName"  id="buyerName" />
													   </td>
															</tr>
								<tr>
																														   									 																	 													<th width="10%">
						    						  	<label for="remark">备注:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="remark"  id="remark" />
													   </td>
																														   									 																	 													<th width="10%">
						    						  	<label for="status">订单状态:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="status"  id="status" />
													   </td>
															</tr>
								<tr>
																														   									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 						</tr>
					</table>
			</form>
	</div>
	<div data-options="region:'south',border:false" style="height: 60px;">
		<div id="toolbar"
			class="datagrid-toolbar datagrid-toolbar-extend foot-formopera">
			<table class="tableForm" style="border:0;cellspacing:1;width:100%">
				<tr>
					<td width="50%" style="padding-right:4%;" align="right">
						<a href="javascript:void(0)" class="btn btn-primary form-tool-btn typeb btn-sm" role="button" title="保存" id="orders_saveForm">保存</a>
						<a href="javascript:void(0)" class="btn btn-grey form-tool-btn btn-sm" role="button" title="返回" id="orders_closeForm">返回</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<jsp:include page="/avicit/platform6/h5component/common/h5uiinclude-js.jsp">
	<jsp:param value="<%=importlibs%>" name="importlibs"/>
	</jsp:include>
	<script type="text/javascript">
			function closeForm(){
			parent.orders.closeDialog("insert");
		}
		function saveForm(){
			var isValidate = $("#form").validate();
	        if (!isValidate.checkForm()) {
	            isValidate.showErrors();
	            return false;
	        }
	        //限制保存按钮多次点击
  		 	$('#orders_saveForm').addClass('disabled').unbind("click");	
			parent.orders.save($('#form'),"insert");
		}
	
		$(document).ready(function () {
			$('.date-picker').datepicker();
			$('.time-picker').datetimepicker({
				oneLine:true,//单行显示时分秒
				closeText:'确定',//关闭按钮文案
				showButtonPanel:true,//是否展示功能按钮面板
				showSecond:false,//是否可以选择秒，默认否
				beforeShow: function(selectedDate) {
					if($('#'+selectedDate.id).val()==""){
						$(this).datetimepicker("setDate", new Date());
						$('#'+selectedDate.id).val('');
					}
				}
			});
			
			parent.orders.formValidate($('#form'));
			//保存按钮绑定事件
			$('#orders_saveForm').bind('click', function(){
				saveForm();
			}); 
			//返回按钮绑定事件
			$('#orders_closeForm').bind('click', function(){
				closeForm();
			});
			
																																																																																																																																																																																																																																																															
			$('.date-picker').on('keydown',nullInput);
			$('.time-picker').on('keydown',nullInput);
		});
	</script>
</body>
</html>