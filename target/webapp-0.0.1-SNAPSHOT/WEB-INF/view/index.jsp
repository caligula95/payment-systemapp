<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>   
<html>
  <head>
    <title>Bootstrap Admin Theme v3</title>
    <meta http-equiv = "Content-Language" content = "en"/>
<meta http-equiv = "Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- styles -->
    <link type="text/css" href="/resources/css/styles.css" rel="stylesheet" >
  </head>
  <body class="login-bg">
  	<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-12">
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
			                <h6>Sign In</h6>
			                <form action="login" method="POST">
			                <input type="hidden" name="command" value="login">
			                <input class="form-control" name="login" type="text" placeholder="Login">
			                <input class="form-control" name="password" type="password" placeholder="Password">
			                <div class="action">
			                <input class="btn btn-primary signup" type="submit" value="Login"> 
			                </div>   
			                </form>             
			            </div>
			        </div>

			        <div class="already">
			            <p>Don't have an account yet?</p>
			            <a href="signup.html">Sign Up</a>
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