﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page  import= "com.lanqiao.shop.domain.*"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>购物车</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			
			.container .row div {
				/* position:relative;
	 float:left; */
			}
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
	</head>
<script type="text/javascript">
	function updateCart(obj){
		//var reg =/^[0-9] $/;
		var upid = obj.getAttribute("upid");
		var count = obj.value;
		var subupid = 'sub'+upid;

		/* if(!/^[1-9]+ $/.test(2)){
			alert('输入有误！');
			return false;
		} */
		  $.post("CartServlet?method=updateCart",{pid:upid,count:count},function(msg){
			  var updateProduct = $.parseJSON(msg);//json字符串转json数组对象
			
				var subtotal  = updateProduct[0]['map'][upid]['subtotal'];
				var total  = updateProduct[0]['total'];
				
				$('#points').html(total);
				$('#total').html(total);
				$('#'+subupid).html(subtotal);//jq选择器竟然可以拼接。。
		 });
		  
		
	}

</script>
	<body>
	<%@ include file="header.jsp" %>


		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin:5px 0;">订单详情</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
							<c:forEach items="${cart.map}" var="map" > 
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="pid" value="22">
									<img src="${pageContext.request.contextPath}/${map.value.product.pimage}" width="70" height="60">
								</td>
								<td width="30%">
									<a target="_blank" href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${map.value.product.pid}">${map.value.product.pname}</a>
								</td>								
								<td width="20%">
									${map.value.product.shop_price}
								</td>
								<td width="10%">																	<!--竟然可以自定义属性  -->
									<input type="text" name="count" id="count" value="${map.value.count}" upid="${map.value.product.pid}" maxlength="4" size="10" onblur="updateCart(this)"/>
								</td>
								<td width="15%">					<!--竟然可以动态拼接绑定id -->
									<span class="subtotal" id="sub${map.value.product.pid}">${map.value.subtotal}</span>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/CartServlet?method=delCart&pid=${map.value.product.pid}" class="delete">删除</a>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div style="margin-right:130px;">
				<div style="text-align:right;">
					<em style="color:#ff6600;">
				登录后确认是否享有优惠&nbsp;&nbsp;
			</em> 赠送积分: <em style="color:#ff6600;"><span id="points"><c:out value="${cart.total}"></c:out></span></em>&nbsp; 商品金额: <strong style="color:#ff6600;"><span id="total"><c:out value="${cart.total}"></c:out></span></strong>
				</div>
				<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
					<a href="${pageContext.request.contextPath}/CartServlet?method=clearCart" id="clear" class="clear">清空购物车</a>
					<a href="${pageContext.request.contextPath}/OrderServlet?method=saveOrder">
						<%--提交表单 --%>
						
						<input type="submit" width="100" value="提交订单" name="submit" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
					</a>
				</div>
			</div>

		</div>

		<%@ include file="foot.jsp" %>

	</body>

</html>