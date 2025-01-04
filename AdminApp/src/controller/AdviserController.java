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
import bean.AdviserBean;
import model.Adviser;

@WebServlet("/adviser")
public class AdviserController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2263816585385945857L;

	public AdviserController() {
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
			AdviserBean adviserBean = new AdviserBean();
			session.setAttribute("adviserBean",adviserBean);
			String address = "/WEB-INF/pages/adviser.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		else if(action.equals("home")) {
			String address = "/menu";
			response.sendRedirect(request.getContextPath() + address);
		}else if(action.equals("regular") || action.equals("difficulty") || action.equals("attribute") || action.equals("category") 
				|| action.equals("exercise") || action.equals("intensity") || action.equals("logout") || action.equals("adviser")) {
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
		AdviserBean adviserBean = new AdviserBean();
		
		if(action == null || action.equals(""))
			doGet(request, response);
		else if(action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("adviserId"));
			adviserBean.delete(id);
			response.sendRedirect(request.getRequestURI());
		}
		else if(action.equals("edit") || action.equals("add")) {
			String name = request.getParameter("adviserName");
			String surname = request.getParameter("adviserSurname");
			String username = request.getParameter("adviserUsername");
			String password = request.getParameter("adviserPassword");
			
			Adviser adviser = new Adviser(name,surname,username,password);
			
			if(action.equals("add"))
				adviserBean.insert(adviser);
			else {
				int id = Integer.parseInt(request.getParameter("adviserId"));
				adviser.setId(id);
				adviserBean.update(adviser);
			}
			
			response.sendRedirect(request.getRequestURI());
		}
	}
}
