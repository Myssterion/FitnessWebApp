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
import bean.DifficultyBean;
import model.Difficulty;

@WebServlet("/difficulty")
public class DifficultyController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5508150526651109707L;

	public DifficultyController() {
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
		HttpSession session = request.getSession();
		AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
		if (adminBean == null || !adminBean.isLoggedIn()) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		String action = request.getParameter("action");
		if(action == null) {
			DifficultyBean difficultyBean = new DifficultyBean();
			session.setAttribute("difficultyBean",difficultyBean);
			String address = "/WEB-INF/pages/difficulty.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		else if(action.equals("home")) {
			String address = "/menu";
			response.sendRedirect(request.getContextPath() + address);
		}else if(action.equals("regular") || action.equals("adviser") || action.equals("attribute") || action.equals("category") 
				|| action.equals("exercise") || action.equals("intensity") || action.equals("logout") || action.equals("difficulty")) {
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
		DifficultyBean difficultyBean = new DifficultyBean();
		
		if(action == null || action.equals(""))
			doGet(request, response);
		else if(action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("diffId"));
			difficultyBean.delete(id);
			response.sendRedirect(request.getRequestURI());
		}
		else if(action.equals("edit")) {
			int id = Integer.parseInt(request.getParameter("diffId"));
			String diff = request.getParameter("diffName");
			Difficulty difficulty = new Difficulty(id,diff);
			difficultyBean.update(difficulty);
			response.sendRedirect(request.getRequestURI());
		}
		else if(action.equals("add")) {
			String diff = request.getParameter("diffName");
			Difficulty difficulty = new Difficulty(diff);
			difficultyBean.insert(difficulty);
			response.sendRedirect(request.getRequestURI());
		}
	}
}
