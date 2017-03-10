<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="dt" uri="/WEB-INF/dateCustomTag.tld" %>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message" />
<!DOCTYPE html>
<html lang="${language}">
<head>
<title><fmt:message key="login.title"></fmt:message></title>

<meta http-equiv="Content-Language" content="en" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<!-- styles -->
<link type="text/css" href="resources/css/styles.css" rel="stylesheet">
</head>
<body class="login-bg">
	<div class="header">
		<form class="language_form">
			<select id="language" name="language" onchange="submit()">
				<option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
				<option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
				<option value="ua" ${language == 'ua' ? 'selected' : ''}>Ua</option>
			</select>
		</form>
		<div class="container">
			<div class="row">
				<div class="col-md-12"></div>
			</div>
		</div>
	</div>

	<div class="page-content container">
		<div class="row"><h4><dt:date/></h4>
			<div class="col-md-4 col-md-offset-4">
				<div class="login-wrapper">
					<div class="box">
						<div class="content-wrap">
							<h6>
								<fmt:message key="login.signin"></fmt:message>
							</h6>
							<fmt:message key="login.placeholder.phone" var="phone"></fmt:message>
							<fmt:message key="login.placeholder.password" var="password"></fmt:message>
							<fmt:message key="login.submit" var="submit"></fmt:message>
							<form action="home" method="POST">
							<label><c:if test="${loginError}">Entered login or password not found</c:if></label>
							<label><c:if test="${phoneError}">Enter phone right</c:if></label>
								<input type="hidden" name="command" value="login"> <input
									class="form-control" name="login" type="text"
									placeholder="${phone}" value="${phoneValue}"> 
									<input class="form-control"
									name="password" type="password" placeholder="${password}">
								<div class="action">
									<input class="btn btn-primary signup" type="submit"
										value="${submit}">
								</div>
							</form>
						</div>
					</div>
					 <div class="already">
			            <p><fmt:message key="login.dontHaveAcc"></fmt:message></p>
			            <a href="?command=registration"><fmt:message key="login.signup"></fmt:message></a>
			        </div>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/js/custom.js"></script>
</body>
</html>