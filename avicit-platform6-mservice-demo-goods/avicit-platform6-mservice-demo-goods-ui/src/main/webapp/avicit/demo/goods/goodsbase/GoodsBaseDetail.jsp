<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@ taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<% 
String importlibs = "common,form,fileupload";
%>
<!DOCTYPE html>
<HTML>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<HEAD>
<title>详细</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- ControllerPath = "demo/goods/goodsbase/goodsBaseController/operation/Detail/id" -->
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/h5component/common/h5uiinclude-css.jsp">
<jsp:param value="<%=importlibs%>" name="importlibs"/>
</jsp:include>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',split:true,border:false">
		<form id='form'>
										   				<input type="hidden" name="id" value="<c:out  value='${goodsBaseDTO.id}'/>" />		    
			   		    								   		    																																																																																																																																																																				<table class="form_commonTable">
				 <tr>
																	 																	 													<th width="10%">
						    						  	<label for="code">商品编码:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="code"  id="code" readonly="readonly" value="<c:out value='${goodsBaseDTO.code}'/>"/>
													   </td>
																														   									 																	 													<th width="10%">
						    						  	<label for="name">商品名称:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="name"  id="name" readonly="readonly" value="<c:out value='${goodsBaseDTO.name}'/>"/>
													   </td>
															</tr>
								<tr>
																														   									 																	 													<th width="10%">
						    						  	<label for="price">价格:</label></th>
						    							<td width="39%">
														  									 									 		<div class="input-group input-group-sm spinner" data-trigger="spinner">
										  <input  class="form-control"  type="text" name="price" id="price" readonly="readonly" value="<c:out  value='${goodsBaseDTO.price}'/>" data-min="-<%=Math.pow(10,6)-Math.pow(10,-2)%>" data-max="<%=Math.pow(10,6)-Math.pow(10,-2)%>" data-step="1" data-precision="2">
										  <span class="input-group-addon">
										    <a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-triangle-top"></i></a>
										    <a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-triangle-bottom"></i></a>
										  </span>
										</div>
								 									 													   </td>
																														   									 																	 													<th width="10%">
						    						  	<label for="descriptionShort">商品简介:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="descriptionShort"  id="descriptionShort" readonly="readonly" value="<c:out value='${goodsBaseDTO.descriptionShort}'/>"/>
													   </td>
															</tr>
								<tr>
																														   									 																	 													<th width="10%">
						    						  	<label for="thumb">缩略图:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="thumb"  id="thumb" readonly="readonly" value="<c:out value='${goodsBaseDTO.thumb}'/>"/>
													   </td>
																														   									 																	 													<th width="10%">
						    						  	<label for="status">商品状态:</label></th>
						    							<td width="39%">
														    <input class="form-control input-sm" type="text" name="status"  id="status" readonly="readonly" value="<c:out value='${goodsBaseDTO.status}'/>"/>
													   </td>
															</tr>
								<tr>
																														   									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 									 						</tr>
						<tr>
						  <th><label for="attachment">附件</label></th>
							<td colspan="<%=2 * 2 - 1 %>">
								<div id="attachment"></div>
							</td>
						</tr>
				  </table>
		</form>
</div>
<jsp:include page="/avicit/platform6/h5component/common/h5uiinclude-js.jsp">
<jsp:param value="<%=importlibs%>" name="importlibs"/>
</jsp:include>
<script type="text/javascript">
//关闭Dialog
function closeForm(){
	parent.goodsBase.closeDialog();
}
//加载完后初始化
$(document).ready(function () {
	//初始化附件上传组件
   $('#attachment').uploaderExt({
		formId: '${goodsBaseDTO.id}',
		allowAdd: false,
		allowDelete: false
   });
	//返回按钮绑定事件
	$('#returnButton').bind('click', function(){
		closeForm();
	});
	//form控件禁用
	setFormDisabled();
	$(document).keydown(function(event){  
		event.returnValue = false;
		return false;
	});  
});
</script>
</body>
</html>