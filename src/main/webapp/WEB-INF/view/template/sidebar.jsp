<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message" />
<div class="sidebar content-box" style="display: block;">
	<ul class="nav">
		<!-- Main menu -->
		<li class="current"><a href="/"><i
				class="glyphicon glyphicon-home"></i><fmt:message key="sidebar.home"></fmt:message></a></li>
		<li><c:if test="${currentUser.role.id==2 }">
		<form action="add-user" method="post">
		<fmt:message key="sidebar.addUser" var="addNewUser"></fmt:message>
		<input type="hidden" name="command" value="adduser">
		<input type="submit" value="${addNewUser}">
		</form>
			</c:if></li>
		<li><c:if test="${currentUser.role.id==1 }">
				<a href="?command=create-payment"><fmt:message key="sidebar.createPayment"></fmt:message></a>
			</c:if></li>
	</ul>
</div>