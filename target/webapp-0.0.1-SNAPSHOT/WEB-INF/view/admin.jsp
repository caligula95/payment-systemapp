<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Admin page</title>
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
<body>
	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<!-- Logo -->
					<div class="logo">
						<h1></h1>
					</div>
				</div>
				<div class="col-md-5">
					<div class="row">
						<div class="col-lg-12"></div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="navbar navbar-inverse" role="banner">
						<nav
							class="collapse navbar-collapse bs-navbar-collapse navbar-right"
							role="navigation">
							<ul class="nav navbar-nav">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown">${role}<b class="caret"></b></a>
									<ul class="dropdown-menu animated fadeInUp">
										<li><a href="profile.html">Profile</a></li>
										<li><a href="login.html">Logout</a></li>
									</ul></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="page-content">
		<div class="row">
			<div class="col-md-2">
				<div class="sidebar content-box" style="display: block;">
					<ul class="nav">
						<!-- Main menu -->
						<li class="current"><a href="index.html"><i
								class="glyphicon glyphicon-home"></i> Dashboard</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-md-6">
						<div class="content-box-large">
							<div class="panel-heading">
								<div class="panel-title">All users</div>

								<div class="panel-options">
									<a href="#" data-rel="collapse"><i
										class="glyphicon glyphicon-refresh"></i></a> <a href="#"
										data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<table border="3" width="100%" cellpadding="4" cellpacing="3">
									<th>Name</th>
									<th>Surname</th>
									<th>Email</th>
									<th></th>
									<th></th>
									<c:forEach items="${users}" var="user">
										<tr align="center">
											<td>${user.name}</td>
											<td>${user.surname}</td>
											<td>${email}</td>
											<td><a class="btn btn-primary href="/edit/${user.userId}">Edit user</a></td>
											<td><a class="btn btn-primary href="/showcardinfo/${user.userId}">Show user accounts</a></td>
										</tr>
										</c:forEach>
								</table>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row">
							<div class="col-md-12">
								<div class="content-box-header">
									<div class="panel-title">New vs Returning Visitors</div>

									<div class="panel-options">
										<a href="#" data-rel="collapse"><i
											class="glyphicon glyphicon-refresh"></i></a> <a href="#"
											data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
									</div>
								</div>
								<div class="content-box-large box-with-header"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="content-box-header">
									<div class="panel-title">New vs Returning Visitors</div>

									<div class="panel-options">
										<a href="#" data-rel="collapse"><i
											class="glyphicon glyphicon-refresh"></i></a> <a href="#"
											data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
									</div>
								</div>
								<div class="content-box-large box-with-header"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 panel-warning">
						<div class="content-box-header panel-heading">
							<div class="panel-title ">New vs Returning Visitors</div>

							<div class="panel-options">
								<a href="#" data-rel="collapse"><i
									class="glyphicon glyphicon-refresh"></i></a> <a href="#"
									data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
							</div>
						</div>
						<div class="content-box-large box-with-header"></div>
					</div>
				</div>

				<div class="content-box-large"></div>
			</div>
		</div>
	</div>

	<footer>
		<div class="container">

			<div class="copy text-center">
				Copyright 2016 <a href='#'></a>
			</div>

		</div>
	</footer>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/js/custom.js"></script>
</body>
</html>