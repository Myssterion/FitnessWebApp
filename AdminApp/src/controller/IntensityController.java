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
import bean.IntensityBean;
import model.Intensity;

@WebServlet("/intensity")
public class IntensityController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 256962748247102755L;

	public IntensityController() {
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
			IntensityBean intensityBean = new IntensityBean();
			session.setAttribute("intensityBean",intensityBean);
			String address = "/WEB-INF/pages/intensity.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		else if(action.equals("home")) {
			String address = "/menu";
			response.sendRedirect(request.getContextPath() + address);
		}else if(action.equals("adviser") || action.equals("difficulty") || action.equals("regular") || action.equals("category") 
				|| action.equals("exercise") || action.equals("logout") || action.equals("attribute") || action.equals("intensity")) {
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
		IntensityBean intensityBean = new IntensityBean();
		
		if(action == null || action.equals(""))
			doGet(request, response);
		else if(action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("intensityId"));
			intensityBean.delete(id);
			response.sendRedirect(request.getRequestURI());
		}
		else if(action.equals("edit")) {
			int id = Integer.parseInt(request.getParameter("intensityId"));
			String name = request.getParameter("intensityName");
			Intensity intensity = new Intensity(id,name);
			intensityBean.update(intensity);
			response.sendRedirect(request.getRequestURI());
		}
		else if(action.equals("add")) {
			String name = request.getParameter("intensityName");
			Intensity intensity = new Intensity(name);
			intensityBean.insert(intensity);
			response.sendRedirect(request.getRequestURI());
		}
	}

}
