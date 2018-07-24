<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">

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



				<c:choose>

					<c:when test="${existusers == null}">
						<!--如果 -->

					
					<li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a></li>
					<li><a href="${pageContext.request.contextPath}/UserServlet?method=registerUI">注册</a></li>
						</c:when>
					<c:otherwise>
						<!--否则 -->					
						<li><a href="#">${existusers.name}</a></li>
						<li><a href="${pageContext.request.contextPath}/UserServlet?method=quit">退出</a></li>
					</c:otherwise>

				</c:choose>


				<li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
						<li><a href="${pageContext.request.contextPath}/jsp/order_list.jsp">我的订单</a></li>
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
							<a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
							 <c:forEach items="${clist}" var="item">
								<li><a href="${pageContext.request.contextPath}/ProductServlet?method=plistbycid&page=1&cid=${item.cid}">${item.cname}</a></li>
								</c:forEach>
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
    