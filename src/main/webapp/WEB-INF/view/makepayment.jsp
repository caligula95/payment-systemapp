<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="message" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="makepayment.title"></fmt:message></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- styles -->
<link href="resources/css/styles.css" rel="stylesheet">
<link href="resources/css/forms.css" rel="stylesheet">
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
						<div class="content-box-large">
							<div class="panel-heading">
								<div class="panel-title">
									<fmt:message key="makepayment.createNewPayment"></fmt:message>
								</div>
							</div>
							<div class="panel-body">
								<form class="form-horizontal" role="form" method="POST">
									<div class="form-group">
										<label for="account" class="col-sm-2 control-label"><fmt:message
												key="makepayment.selectAccountYouWantToSendFrom"></fmt:message></label>
										<div class="col-sm-10">
											<select name="account">
												<option><fmt:message
														key="makepayment.selectAccount"></fmt:message></option>
												<c:forEach items="${accounts}" var="account">
													<option value="${account.accountId}">${account.number}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="surname" class="col-sm-2 control-label"><fmt:message
												key="makepayment.enterNumber"></fmt:message></label>
										<div class="col-sm-10">
										<fmt:message key="home.account.number" var="number"></fmt:message>
											<input type="text" name="number" class="form-control"
												id="number" placeholder="${number}" value="${numberValue}">
										</div>
									</div>
									<div class="form-group">
										<label for="summa" class="col-sm-2 control-label"><fmt:message
												key="makepayment.enterSum"></fmt:message></label>
										<div class="col-sm-10">
										<fmt:message key="home.payments.summa" var="summa"></fmt:message>
											<input type="text" name="summa" class="form-control"
												id="summa" placeholder="${summa}" value="${summaValue}">
										</div>
									</div>
									<div class="form-group">
										<label for="appointment" class="col-sm-2 control-label"><fmt:message
												key="makepayment.appointment"></fmt:message></label>
										<div class="col-sm-10">
											<textarea name="appointment" id="appointment" rows="5"
												cols="50">${appointmentValue}</textarea>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-primary">
												<fmt:message key="makepayment.ok"></fmt:message>
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="template/footer.jsp" />
</body>
</html>