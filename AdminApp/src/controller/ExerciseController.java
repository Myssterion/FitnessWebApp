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
import bean.ExerciseBean;
import model.Exercise;

@WebServlet("/exercise")
public class ExerciseController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6776990331150588135L;

	public ExerciseController() {
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
			ExerciseBean exerciseBean = new ExerciseBean();
			session.setAttribute("exerciseBean",exerciseBean);
			String address = "/WEB-INF/pages/exercise.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}else if(action.equals("home")) {
			String address = "/menu";
			response.sendRedirect(request.getContextPath() + address);
		}else if(action.equals("adviser") || action.equals("difficulty") || action.equals("regular") || action.equals("category") 
				|| action.equals("intensity") || action.equals("logout") || action.equals("attribute") || action.equals("exercise")) {
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
		ExerciseBean exerciseBean = new ExerciseBean();
		
		if(action == null || action.equals(""))
			doGet(request, response);
		else if(action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("exerciseId"));
			exerciseBean.delete(id);
			response.sendRedirect(request.getRequestURI());
		}
		else if(action.equals("edit") || action.equals("add")) {
			String name = request.getParameter("exerciseName");
			String type = request.getParameter("exerciseType");
			String muscle = request.getParameter("exerciseMuscle");
			Exercise exercise = new Exercise(name,type,muscle);
			
			if(action.equals("edit")) {
				int id = Integer.parseInt(request.getParameter("exerciseId"));
				exercise.setId(id);
				exerciseBean.update(exercise);
			}
			else 
				exerciseBean.insert(exercise);
			
			response.sendRedirect(request.getRequestURI());
		}
	}


}
