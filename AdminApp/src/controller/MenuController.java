package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AdminBean;

@WebServlet("/menu")
public class MenuController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4531900018971648876L;

	public MenuController() {
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
		if (adminBean == null || !adminBean.isLoggedIn()) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		if(action == null || action.equals("") || action.equals("home")) {
			String address = "/WEB-INF/pages/menu.jsp"; // Path to mainpage.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		else if(action.equals("logout")) {
			session.invalidate();
			response.sendRedirect(request.getContextPath());
		}
		else if(action.equals("difficulty")) {
			String address = "/difficulty";
			response.sendRedirect(request.getContextPath() + address);
		}
		else if(action.equals("adviser")) {
			String address = "/adviser";
			response.sendRedirect(request.getContextPath() + address);
		}
		else if(action.equals("regular")) {
			String address = "/regular";
			response.sendRedirect(request.getContextPath() + address);
		}
		else if(action.equals("attribute")) {
			String address = "/attribute";
			response.sendRedirect(request.getContextPath() + address);
		}
		else if(action.equals("category")) {
			String address = "/category";
			response.sendRedirect(request.getContextPath() + address);
		}
		else if(action.equals("exercise")) {
			String address = "/exercise";
			response.sendRedirect(request.getContextPath() + address);
		}
		else if(action.equals("intensity")) {
			String address = "/intensity";
			response.sendRedirect(request.getContextPath() + address);
		}
		else {
			String address = "/WEB-INF/pages/404.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
