<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>XXX网络商城</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
 <script type="text/javascript">
	$(function(){
			$.post("CategoryServlet",{method:"findCategoryByCid"},function(msg){
				var CategoryList = $.parseJSON(msg);//json字符串转json数组对象
				$.each(CategoryList,function(i,category){
					$("#nav_ul").append("<li><a href='ProductServlet?method=plistbycid&page=1&cid="+category.cid+"'>"+category.cname+"</a></li>");
				});	
		        	});

	})
</script>
	<body>


			<!--
            	描述：菜单栏
            -->
			<div class="container-fluid">
				<div class="col-md-4">
					<h1 style="color: #28A4C9; font-weight: bold;">蓝&nbsp;&nbsp;桥</h1>
				</div>
				<div class="col-md-5">
				</div>
				<div class="col-md-3" align="right" style="padding-top:20px;line-height: 35px;">
					<ol class="list-inline">
						<c:if test="${empty username}">
							<li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a></li>
							<li><a href="${pageContext.request.contextPath}/UserServlet?method=registerUI">注册</a></li>
						</c:if>
						
						<c:if test="${!empty username}">
							欢迎，${username}
							<li><a href="${pageContext.request.contextPath}/UserServlet?method=logout">退出</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/order_list.jsp">我的订单</a></li>
						</c:if>

					</ol>
				</div>
			</div>
			
			
			
			<!--
            	描述：导航条
            -->
			<div class="container-fluid">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="${pageContext.request.contextPath}/UserServlet?method=indexUI">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav" id="nav_ul">
								
							</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>

			
	</body>

</html>