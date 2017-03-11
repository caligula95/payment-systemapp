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
<title><fmt:message key="admin.title"></fmt:message></title>
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
					<div class="col-md-12 panel-warning">
					<form action="all-users" method="post">
					<fmt:message key="admin.showAllUsers" var="showAllUsers"></fmt:message>
					<input type="hidden" name="command" value="showAllUsers">
					<input type="submit" value="${showAllUsers }">
					</form>
						<c:if test="${showUsers }">
							<div class="content-box-header panel-heading">
								<div class="panel-title ">
									<fmt:message key="admin.allUsers"></fmt:message>
								</div>

							</div>

							<div class="content-box-large box-with-header">
								<table border="1" width="100%" cellpadding="4" cellpacing="3">
									<th><fmt:message key="admin.name"></fmt:message></th>
									<th><fmt:message key="admin.surname"></fmt:message></th>
									<th><fmt:message key="admin.email"></fmt:message></th>
									<th><fmt:message key="admin.phone"></fmt:message></th>
									<th></th>
									<th></th>
									<c:forEach items="${users}" var="user">
										<tr align="center">
											<td>${user.name}</td>
											<td>${user.surname}</td>
											<td>${user.email}</td>
											<td>${user.phone}</td>
											<td><a href="#my_modal" data-toggle="modal"
												data-user-id="${user.userId}"><fmt:message
														key="admin.addAccount"></fmt:message></a></td>
											<td><c:url
													value="?command=showuserinfo&userId=${user.userId}"
													var="cardInfo"></c:url> <a href="${cardInfo}"><fmt:message
														key="admin.showUserAccounts"></fmt:message></a></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</c:if>

						<c:if test="${showUser}">
							<div class="content-box-header panel-heading">
								<div class="panel-title ">
									<fmt:message key="admin.allUsers"></fmt:message>
								</div>

							</div>

							<div class="content-box-large box-with-header">
								<table border="1" width="100%" cellpadding="4" cellpacing="3">
									<th><fmt:message key="admin.name"></fmt:message></th>
									<th><fmt:message key="admin.surname"></fmt:message></th>
									<th><fmt:message key="admin.email"></fmt:message></th>
									<th><fmt:message key="admin.phone"></fmt:message></th>
									<th></th>
									<th></th>
									<tr align="center">
										<td>${user.name}</td>
										<td>${user.surname}</td>
										<td>${user.email}</td>
										<td>${user.phone}</td>
										<td><a href="#my_modal" data-toggle="modal"
											data-user-id="${user.userId}"><fmt:message
													key="admin.addAccount"></fmt:message></a></td>
										<td><c:url
												value="?command=showuserinfo&userId=${user.userId}"
												var="cardInfo"></c:url> <a href="${cardInfo}"><fmt:message
													key="admin.showUserAccounts"></fmt:message></a></td>
									</tr>
								</table>
							</div>
						</c:if>
					</div>
				</div>
				<div class="row"></div>
			</div>
		</div>
	</div>
	<jsp:include page="template/footer.jsp" />

	<div class="modal" id="my_modal">

		<div class="modal-dialog">
			<div class="modal-content">
				<form id="user" name="user" action="accounts" method="post">
					<div class="modal-header">
					<input type="hidden" name="command" value="addAccount">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only"><fmt:message
									key="home.modal.close"></fmt:message></span>
						</button>
						<h4 class="modal-title">
							<fmt:message key="admin.modal.addaccount"></fmt:message>
						</h4>
					</div>
					<div class="modal-body">
						<p>
							<fmt:message key="admin.modal.enterNumber"></fmt:message>
						</p>
						<input type="hidden" name="userId" value="" />
						<label id="numberLabel"></label>
						 <input id="number" type="text"	name="number">
						<p>
							<br>
							<br>
							<fmt:message key="admin.modal.enterBeginningSum"></fmt:message>
						</p>
						<input id="balance" type="text" name="balance" value="0">
					</div>
					<div class="modal-footer">
						<button id="send" class="btn btn-primary">
							<fmt:message key="home.modal.send"></fmt:message>
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<fmt:message key="home.modal.close"></fmt:message>
						</button>
					</div>
				</form>

		<script type="text/javascript">
			(function() {
				var form = document.querySelector('#user');
				var searchInput = document.querySelector('#number');
				var searchBalance = document.querySelector('#balance');
				
				form.addEventListener('submit', function(event) {
					var letters = /[0-9]{20}/;
					if (!searchInput.value.match(letters)) {
						alert('Enter only numbers 20 digits');
						event.preventDefault();
					}
					else {
						alert("OK");
					}
				});
			})();
		</script>

			</div>
		</div>
	</div>


	<script type="text/javascript">
		$('#my_modal').on('show.bs.modal', function(e) {
			var userId = $(e.relatedTarget).data('user-id');
			$(e.currentTarget).find('input[name="userId"]').val(userId);
		});
	</script>
</body>
</html>
