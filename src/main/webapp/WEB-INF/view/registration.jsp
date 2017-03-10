<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="registration.title"></fmt:message></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- styles -->
<link href="resources/css/styles.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-bg">
	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<!-- Logo -->
					<div class="logo">
						<h1></h1>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="page-content container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-wrapper">
					<div class="box">
						<div class="content-wrap">
							<fmt:message key="registration.phone" var="phone"></fmt:message>
							<fmt:message key="registration.email" var="email"></fmt:message>
							<fmt:message key="registration.password" var="password"></fmt:message>
							<fmt:message key="registration.confirmation" var="confirmation"></fmt:message>
							<h6>
								<fmt:message key="registration.signup"></fmt:message>
								<c:if test="${errorMessage}">User not found or is already registered</c:if>
							</h6>
							<form method="post">
							<label for="phone"><c:if test="${phoneError}">Enter phone (10 sumbols) for example (0...)</c:if></label>
								<input id="phone" name="phone" class="form-control" type="text"
									placeholder="${phone}*" value="${phoneValue}"> 
									<label></label>
									<input name="email"
									class="form-control" type="email" placeholder="${email}" value="${emailValue}">
									<label><c:if test="${passwordError}">Enter password not less than 5 symbols </c:if></label>
								<input name="password" class="form-control" type="password"
									placeholder="${password}*"> 
									<label><c:if test="${passwordConfirmationError}">Don't match entered password</c:if></label>
									<input	name="passwordConfirmation" class="form-control"
									type="password" placeholder="${confirmation}*">
								<div class="action">
									<button type="submit" class="btn btn-primary signup">
										<fmt:message key="registration.button"></fmt:message>
									</button>
								</div>
							</form>
						</div>
					</div>
					<div class="already">
						<p>
							<fmt:message key="registration.haveAccountAlready"></fmt:message>
						</p>
						<a href="/"><fmt:message key="registration.login"></fmt:message></a>
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