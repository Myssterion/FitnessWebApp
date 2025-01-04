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
import bean.RegularUserBean;
import model.RegularUser;

@WebServlet("/regular")
public class RegularController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6783664354726701428L;

	public RegularController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
		if (adminBean == null || !adminBean.isLoggedIn()) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		String action = request.getParameter("action");
		if(action == null) {
			RegularUserBean regularUserBean = new RegularUserBean();
			session.setAttribute("regularUserBean",regularUserBean);
			String address = "/WEB-INF/pages/regular.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		else if(action.equals("home")) {
			String address = "/menu";
			response.sendRedirect(request.getContextPath() + address);
		}else if(action.equals("adviser") || action.equals("difficulty") || action.equals("attribute") || action.equals("category") 
				|| action.equals("exercise") || action.equals("intensity") || action.equals("logout") || action.equals("regular")) {
			String address = "/menu";
			String parameter ="?action=" + action;
			response.sendRedirect(request.getContextPath() + address + parameter);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		RegularUserBean regularUserBean = new RegularUserBean();
		
		if(action == null || action.equals(""))
			doGet(request, response);
		else if(action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("regularUserId"));
			regularUserBean.delete(id);
			response.sendRedirect(request.getRequestURI());
		}else if(action.equals("status")) {
			int id = Integer.parseInt(request.getParameter("regularUserId"));
			boolean status = Boolean.parseBoolean(request.getParameter("regularUserStatus"));
			regularUserBean.changeStatus(id,status);
			response.sendRedirect(request.getRequestURI());
		}
		else if(action.equals("edit") || action.equals("add")) {
			String name = request.getParameter("regularUserName");
			String surname = request.getParameter("regularUserSurname");
			String username = request.getParameter("regularUserUsername");
			String password = request.getParameter("regularUserPassword");
			String city = request.getParameter("regularUserCity");
			String email = request.getParameter("regularUserEmail");
			
			RegularUser regularUser = new RegularUser(name,surname,username,password,city,email);
			
			if(action.equals("add"))
				regularUserBean.insert(regularUser);
			else {
				int id = Integer.parseInt(request.getParameter("regularUserId"));
				regularUser.setId(id);
				regularUserBean.update(regularUser);
			}
			
			response.sendRedirect(request.getRequestURI());
		}
	}
}
