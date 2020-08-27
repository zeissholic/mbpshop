<header>
	<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: #b0b0b0">
		<div>
			<a href="/shop/list" class="navbar-brand"> Octank Shopping Mall (AWS v2.1)</a>
		</div>

		<ul class="navbar-nav navbar-collapse justify-content-end">
			
			<% if (session.getAttribute("user") == null || session.getAttribute("user").equals("")) { %> 
				<li class="nav-link"><a href="/shop/login" class="nav-link">Login</a></li>
				<li><a href="/shop/register" class="nav-link">Signup</a></li>
			<% } else { %>
				<b><%=session.getAttribute("user")%></b>&nbsp;&nbsp;logged in 
				<ul class="navbar-nav navbar-collapse justify-content-end">
					<li><a href="/shop/logout" class="nav-link">Logout</a></li>
				</ul>
			<% }%> 
			
			
		</ul>
	</nav>
</header>