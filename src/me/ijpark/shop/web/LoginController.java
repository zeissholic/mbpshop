package me.ijpark.shop.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import me.ijpark.shop.dao.LoginDao;
import me.ijpark.shop.model.LoginBean;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;

	public void init() {
		loginDao = new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/logout":
				HttpSession session = request.getSession();
				session.invalidate();
				response.sendRedirect("/shop/login/login.jsp");
				//System.out.println("new");
				break;
			default:
				//System.out.println("CARTS");
				response.sendRedirect("/shop/login/login.jsp");
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}	
			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authenticate(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		try {
			if (loginDao.validate(loginBean)) {
				
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				
				//RequestDispatcher dispatcher = request.getRequestDispatcher("/cart/cart-list.jsp");
				//dispatcher.forward(request, response);
				response.sendRedirect("/shop/list");

			} else {
				response.sendRedirect("/shop/login/login.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
