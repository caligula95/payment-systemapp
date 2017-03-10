<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<style>
.center {
	margin: auto;
	width: 60%;
	border: 3px solid #73AD21;
	padding: 10px;
}
</style>
</head>
<body>
	<div class="center">
	<h1>Error occurred</h1>
		<%-- if was gotten error 404 or 500--%>
		<c:set var="code"
			value="${requestScope['javax.servlet.error.status_code']}"
			scope="page" />
		<c:if test="${code == '404'}">
			<c:set var="errorMessage" value="page not found" scope="page" />
		</c:if>
		<c:if test="${code == '500'}">
			<c:set var="errorMessage" value="internal server error" scope="page" />
		</c:if>
		<br>
			<span id="error_detail_message"> 
			     <c:if test="${not empty code}">
					<h1>Error number: <c:out value="${code}" /></h1><br><br>
				</c:if> 
			     <c:if test="${not empty errorMessage}">
			        <br>
						<c:out value="${errorMessage}" />
				</c:if>
			</span>
			<a href="/webapp">Back</a>
	</div>

</body>
</html>