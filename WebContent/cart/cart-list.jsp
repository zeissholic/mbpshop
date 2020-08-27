<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>MBP Shopping mall</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
<br>
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
		<div class="container">
			<h3 class="text-center">Recent Orders</h3>
			<hr>
			<div class="container text-left">
				<a href="/shop/list" class="btn btn-success">Refresh List</a>
				<a href="/shop/new" class="btn btn-success">New Order</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead class="thead-dark">
					<tr>
						<th class="text-center">Item Name</th>
						<th class="text-center">Price($)</th>
						<th class="text-center">Purchase Date</th>
						<th class="text-center">Status</th>
						<th class="text-center">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="total" value="${0}"/>
					<c:forEach var="cart" items="${listCart}">

						<tr>
							<td><c:out value="${cart.itemnm}" /></td>
							<td class="text-right"><fmt:formatNumber value='${cart.price}' pattern="#,##0.00" /></td>
							<td class="text-center"><c:out value="${cart.targetDate}" /></td>
							<td class="text-center">
								<c:choose>
								    <c:when test="${cart.status=='true'}">
								        Checkout
								        <br />
								    </c:when>    
								    <c:otherwise>
								        Cart 
								        <br />
								    </c:otherwise>
								</c:choose>
							</td>

							<td class="text-center"><a href="edit?id=<c:out value='${cart.id}' />">modify</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${cart.id}' />">delete</a></td>


						</tr>
						<c:set var="total" value="${total + cart.price}" />
					</c:forEach>
						<tr>
							<td class="text-center">Total</td>
							<td class="text-right"><fmt:formatNumber value='${total}' pattern="#,##0.00" /></td>
							<td colspan=3>(checkout and cart)</td>
						</tr>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
