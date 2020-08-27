<%@ page import="java.net.*" %> 
<%
	InetAddress inet = InetAddress.getLocalHost();
	String svrIP = inet.getHostAddress();
%>
<style>
.footer {
    position: fixed;
    bottom: 0;
    width:100%;
    height: 40px;
    background-color: #C0C0C0;
}

</style>

<footer class="footer font-small black">
	<ul class="navbar-nav navbar-collapse justify-content-end">
		<li>Server IP: <b><%=svrIP %></b></li>
	</ul>
</footer>
<!-- Footer -->

