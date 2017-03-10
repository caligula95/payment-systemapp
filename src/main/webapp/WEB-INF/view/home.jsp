<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message" />
<html>
<head>
<title><fmt:message key="home.title"></fmt:message></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- styles -->
<link href="resources/css/styles.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<jsp:include page="template/header.jsp" />

	<div class="page-content">
		<div class="row">
			<div class="col-md-2">
				<jsp:include page="template/sidebar.jsp" />
			</div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-md-6">
						<a href="?command=showAccounts"><fmt:message key="home.showAccounts"></fmt:message></a>
						<c:if test="${showAccouts}">
							<div class="content-box-large">
								<div class="panel-heading">
									<div class="panel-title">
										<fmt:message key="home.allaccounts"></fmt:message>
									</div>

								</div>
								<fmt:message key="home.account.number" var="number"></fmt:message>
								<fmt:message key="home.account.status" var="status"></fmt:message>
								<fmt:message key="home.account.status.active" var="statusActive"></fmt:message>
								<fmt:message key="home.account.status.blocked"
									var="statusBlocked"></fmt:message>
								<fmt:message key="home.account.button.block" var="block"></fmt:message>
								<fmt:message key="home.account.button.showInfo" var="showInfo"></fmt:message>

								<div class="panel-body">
									<table border="1" width="100%" cellpadding="4" cellpacing="3">
										<th>${number}</th>
										<th><fmt:message key="home.account.balance"></fmt:message></th>
										<th>${status}</th>
										<th><fmt:message key="userCards.action"></fmt:message></th>
										<th></th>
										
										<c:forEach items="${accounts}" var="account">
											<tr align="center">
												<td>${account.number}</td>
												<td>${account.balance}</td>
												<td><c:if test="${!account.isBlocked}">${statusActive }</c:if>
													<c:if test="${account.isBlocked}">${statusBlocked }</c:if></td>
												<td><c:if test="${!account.isBlocked}">
														<a
															href="?command=blockaccount&accountId=${account.accountId}">${block }</a>
													</c:if></td>
												<td><a
													href="?command=showinfo&accountId=${account.accountId}">${showInfo }</a></td>
												
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						</c:if>
					</div>

					<div class="col-md-6">
						<div class="row">
							<div class="col-md-12">
								<c:if test="${showAccouts}">
									<div class="content-box-header">
										<div class="panel-title">
											<fmt:message key="home.cards"></fmt:message>
										</div>
									</div>

									<div class="content-box-large box-with-header">
										<table border="1" width="100%" cellpadding="4" cellpacing="3">
											<th>${number}</th>
											<th><fmt:message key="home.card.cvv"></fmt:message></th>
											<th><fmt:message key="home.card.date"></fmt:message></th>
											<th>${status }</th>
											<th><fmt:message key="userCards.action"></fmt:message></th>
											
											<c:forEach items="${cards}" var="card">
												<tr align="center">
													<td>${card.number}</td>
													<td>${card.cvv}</td>
													<td>${card.validity}</td>
													<td><c:if test="${card.isActive}">${statusActive}</c:if>
														<c:if test="${!card.isActive}">${statusBlocked }</c:if></td>
													<td><c:if test="${card.isActive}">
															<a href="?command=blockCard&cardNumber=${card.number}">${block }</a>
														</c:if></td>
												
												</tr>
											</c:forEach>
										</table>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 panel-warning">
					<c:if test="${showAccouts}">
						<div class="content-box-header panel-heading">
							<div class="panel-title ">
								<fmt:message key="home.payments"></fmt:message>
							</div>
						</div>
						<div class="content-box-large box-with-header">
							<table border="1" width="100%" cellpadding="4" cellpacing="3">
								<th><fmt:message key="home.payments.receiverCard"></fmt:message></th>
								<th><fmt:message key="home.payments.appointment"></fmt:message></th>
								<th><fmt:message key="home.payments.date"></fmt:message></th>
								<th><fmt:message key="home.payments.summa"></fmt:message></th>
								<th>${status }</th>
								<th></th>
								<c:forEach items="${payments}" var="payment">
									<tr align="center">
										<td>${payment.cardNumber}</td>
										<td>${payment.appointment}</td>
										<td>${payment.date}</td>
										<td>${payment.sum }</td>
										<td><c:if test="${payment.condition}">
												<fmt:message key="home.payments.success"></fmt:message>
											</c:if> <c:if test="${!payment.condition}">
												<fmt:message key="home.payments.error"></fmt:message>
											</c:if></td>
										<td><a
											href="?command=repeatPayment&paymentId=${payment.paymentId}"><fmt:message
													key="home.payments.repeat"></fmt:message></a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="template/footer.jsp" />
</body>
</html>