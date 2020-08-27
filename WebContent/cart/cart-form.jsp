<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script>
	function setPrice(item, price) {
		document.getElementById("itemnm").value  = item;
		document.getElementById("price").value  = price;
	}
	
	
  </script>

</head>

</head>
<body>

	<jsp:include page="../common/header.jsp"></jsp:include>
	
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${cart != null}">
					<form action="/shop/update" method="post">
				</c:if>
				<c:if test="${cart == null}">
					<form action="/shop/insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${cart != null}">
            			Edit Order
            		</c:if>
						<c:if test="${cart == null}">
            			New Order
            		</c:if>
					</h2>
				</caption>

				<c:if test="${cart != null}">
					<input type="hidden" name="id" value="<c:out value='${cart.id}' />" />
				</c:if>

<div class="dropdown">
  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
    Please choose items you want to buy
  </button><br><br>
  <div class="dropdown-menu">
  	<h5 class="dropdown-header">New Items</h5>
    <a class="dropdown-item" href="javascript:setPrice('Apple iPhone 11 Pro Max', 680);">Apple iPhone 11 Pro Max ($680)</a>
    <a class="dropdown-item" href="javascript:setPrice('Apple Watch 5', 380);">Apple Watch 5 ($380)</a>
    <a class="dropdown-item" href="javascript:setPrice('Apple iMac 27inch', 1299);">Apple iMac 27inch ($1,299)</a>
    <a class="dropdown-item" href="javascript:setPrice('Apple Mac Pro', 4999);">Apple Mac Pro ($4,999)</a>
  	<h5 class="dropdown-header">Accessories</h5>
    <a class="dropdown-item" href="javascript:setPrice('Apple iPhone Lightening cable', 29);">Apple iPhone Lightening cable ($29)</a>
    <a class="dropdown-item" href="javascript:setPrice('Apple Magic Mouse II', 70);">Apple Magic Mouse II ($70)</a>
    <a class="dropdown-item" href="javascript:setPrice('Apple Airpods Pro', 249);">Apple Airpods Pro ($249)</a>
  </div>
</div>


				<fieldset class="form-group">
					<label>Item Name</label> <input type="text"
						value="<c:out value='${cart.itemnm}' />" class="form-control"
						name="itemnm" id="itemnm" required="required" minlength="5" readonly>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Price</label> <input type="text"
						value="<c:out value='${cart.price}' />" class="form-control"
						name="price" id="price" readonly>
				</fieldset>

				<fieldset class="form-group">
					<label>Cart Status</label> <select class="form-control"
						name="isDone">
						<option value="false">Cart</option>
						<option value="true">Checkout</option>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Purchase Date</label> <input type="date"
						value="<c:out value='${cart.targetDate}' />" class="form-control"
						name="targetDate" readonly >
				</fieldset>

				<button type="submit" class="btn btn-success">Complete Order</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
