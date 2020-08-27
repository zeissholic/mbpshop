package me.ijpark.shop.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import me.ijpark.shop.dao.*;
import me.ijpark.shop.model.Cart;


@WebServlet("/")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartDao cartDAO;

	public void init() {
		cartDAO = new CartDaoImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				//System.out.println("new");
				break;
			case "/insert":
				insertCart(request, response);
				//System.out.println("insert");
				break;
			case "/delete":
				deleteCart(request, response);
				//System.out.println("delete");
				break;
			case "/edit":
				showEditForm(request, response);
				//System.out.println("edit");
				break;
			case "/update":
				updateCart(request, response);
				//System.out.println("update");
				break;
			case "/list":
				listCart(request, response);
				System.out.println("list");
				break;
			case "/healthcheck":
				healthCheck(request, response);
				//System.out.println("list");
				break;
			default:
				//System.out.println("CARTS");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCart(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		String username = request.getSession().getAttribute("user").toString();
		
		if (username == null || username.equals("")) {
			
			response.sendRedirect("/shop/login");
		} else { 
		
			List<Cart> listCart = cartDAO.selectAllCarts(username);
			request.setAttribute("listCart", listCart);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cart/cart-list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private void healthCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("common/healthcheck.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("cart/cart-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Cart existingCart = cartDAO.selectCart(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cart/cart-form.jsp");
		request.setAttribute("cart", existingCart);
		dispatcher.forward(request, response);

	}

	private void insertCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		String itemnm = request.getParameter("itemnm");
		String username = request.getSession().getAttribute("user").toString();
		
		//System.out.println(request.getParameter("itemnm"));
		//System.out.println(request.getParameter("price"));
		
		float price = Float.parseFloat(request.getParameter("price"));
		//System.out.println(request.getParameter("targetDate"));
		//DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		//LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"),df);
	
		//System.out.println(LocalDate.now());
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		
		String ipaddr = getClientIp(request);
		
		Cart newCart = new Cart(itemnm, username, price, LocalDate.now(), isDone, ipaddr);
		//Cart newCart = new Cart(itemnm, username, price, targetDate, isDone);
		cartDAO.insertCart(newCart);
		response.sendRedirect("/shop/list");
	}

	private void updateCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		String itemnm = request.getParameter("itemnm");
		String username = request.getSession().getAttribute("user").toString();
		float price = Float.parseFloat(request.getParameter("price"));
		//DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
		
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		Cart updateCart = new Cart(id, itemnm, username, price, targetDate, isDone);
		
		cartDAO.updateCart(updateCart);
		
		response.sendRedirect("/shop/list");
	}

	private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		cartDAO.deleteCart(id);
		response.sendRedirect("/shop/list");
	}
	
	private static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
