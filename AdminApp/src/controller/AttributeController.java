package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AdminBean;
import bean.AttributeBean;
import bean.CategoryBean;
import model.Attribute;
import model.Category;

@WebServlet("/attribute")
public class AttributeController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4586132660532932935L;

	public AttributeController() {
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
			AttributeBean attributeBean = new AttributeBean();
			session.setAttribute("attributeBean",attributeBean);
			CategoryBean categoryBean = new CategoryBean();
			session.setAttribute("categoryBean",categoryBean);
			String address = "/WEB-INF/pages/attribute.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		else if(action.equals("home")) {
			String address = "/menu";
			response.sendRedirect(request.getContextPath() + address);
		}else if(action.equals("adviser") || action.equals("difficulty") || action.equals("regular") || action.equals("category") 
				|| action.equals("exercise") || action.equals("intensity") || action.equals("logout") || action.equals("attribute")) {
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
		AttributeBean attributeBean = new AttributeBean();
		
		if(action == null || action.equals(""))
			doGet(request, response);
		else if(action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("attributeId"));
			attributeBean.delete(id);
			response.sendRedirect(request.getRequestURI());
		}
		/*else if(action.equals("edit")) {
			int id = Integer.parseInt(request.getParameter("attributeId"));
			String diff = request.getParameter("attributeName");
			Attribute attribute = new Attribute(id,diff);
			attributeBean.update(attribute);
			response.sendRedirect(request.getRequestURI());
		}
		else if(action.equals("add")) {
			String diff = request.getParameter("attributeName");
			Attribute attribute = new Attribute(diff);
			attributeBean.insert(attribute);
			response.sendRedirect(request.getRequestURI());
		}*/
		else if(action.equals("edit") || action.equals("add")) {
			Category category = null;
			String selectedId = request.getParameter("selectedCategory");
			if(selectedId != null)
				  category = new Category(Integer.parseInt(selectedId));
			
			String attributeName = request.getParameter("attributeName");
			Attribute attribute = new Attribute(attributeName, category);
			
			if(action.equals("edit")) {
				int id = Integer.parseInt(request.getParameter("attributeId"));
				attribute.setId(id);
				attributeBean.update(attribute);
			}
			else 
				attributeBean.insert(attribute);
			
			response.sendRedirect(request.getRequestURI());
		}
	}

}
