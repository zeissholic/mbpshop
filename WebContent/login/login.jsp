<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<% if (session.getAttribute("user") == null || session.getAttribute("user").equals("")) { %> 

<!DOCTYPE html>
<html>
<head>
<title>MBP3 Shopping Mall</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
		<form action="/shop/login" method="post">
			<div class="form-group">
				<img src="https://www.ijpark.me/images/logo_1.png"/>
			</div>
			<div class="form-group">
				<label for="uname">User Name:</label> <input type="text"
					class="form-control" id="username" placeholder="User Name"
					name="username" required>
			</div>

			<div class="form-group">
				<label for="uname">Password:</label> <input type="password"
					class="form-control" id="password" placeholder="Password"
					name="password" required>
			</div>


			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>

<%  }  else {  response.sendRedirect("/shop/list"); } %>