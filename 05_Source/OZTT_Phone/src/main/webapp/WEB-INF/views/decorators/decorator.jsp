<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title><sitemesh:write property='title' /></title>
  <%@ include file="../commoncssHead.jsp"%>
  <%@ include file="../commonjsFooter.jsp"%>
  <sitemesh:write property='head' />
</head>
<!-- Head END -->
<script>
	function toShopCart(){
		var currentUserId = $("#currentUserId").val();
		if (currentUserId == null || currentUserId.length == 0) {
			location.href = "${ctx}/login/init"
		} else {
			location.href = "${ctx}/shopcart/init"
		}
	}
</script>

<!-- Body BEGIN -->
<body id="container">
	<sitemesh:write property='body' />

    <!-- BEGIN FOOTER -->
    <div class="main-nav" id="main-nav-id">
		<a href="${ctx}/main/init" class="main-nav-item main-nav-home main-nav-active">
			<img alt="home" src="${ctx}/images/main.png">
			<span><fmt:message key="DECORATOR_MAIN"/></span>
		</a>
		<a href="${ctx}/category/init" class="main-nav-item main-nav-cat ">
			<img alt="category" src="${ctx}/images/category.png">
			<span><fmt:message key="DECORATOR_CATEGORY"/></span>
		</a>
		<a href="#" onclick="toShopCart()" class="main-nav-item main-nav-cart ">
			<img alt="shopcart" src="${ctx}/images/shopcart.png">
			<span><fmt:message key="DECORATOR_SHOPCART"/></span>
		</a>
		<a href="${ctx}/user/init" class="main-nav-item main-nav-profile ">
			<img alt="me" src="${ctx}/images/me.png">
			<span><fmt:message key="DECORATOR_ME"/></span>
		</a>
	</div>
    <!-- END FOOTER -->
    <input type="hidden" value="${currentUserId}" id="currentUserId">
    
    <script type="text/javascript">
    var currentPath = window.location.pathname;
	if (currentPath.indexOf("login/init") > 0) {
		$("#main-nav-id").css("display","none");
	}
    
    
    </script>
</body>
<!-- END BODY -->
</html>