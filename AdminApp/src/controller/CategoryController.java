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

@WebServlet("/category")
public class CategoryController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4289862244121232034L;

	public CategoryController() {
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
			String address = "/WEB-INF/pages/category.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		else if(action.equals("home")) {
			String address = "/menu";
			response.sendRedirect(request.getContextPath() + address);
		}
		else if(action.equals("regular") || action.equals("adviser") || action.equals("attribute") || action.equals("difficulty") 
				|| action.equals("exercise") || action.equals("intensity") || action.equals("logout") || action.equals("category")) {
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
		CategoryBean categoryBean = new CategoryBean();
		
		if(action == null || action.equals(""))
			doGet(request, response);
		else if(action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("categoryId"));
			System.out.println(id);
			categoryBean.delete(id);
			response.sendRedirect(request.getRequestURI());
		}
		/*else if(action.equals("edit") || action.equals("add")) {
			ArrayList<Attribute> attributes = new ArrayList<>();
			String[] selectedIds = request.getParameterValues("selectedAttributes");
			if(selectedIds != null)
				  for (String id : selectedIds) 
					  attributes.add(new Attribute(Integer.parseInt(id)));
			
			String name = request.getParameter("categoryName");
			Category category = new Category(name,attributes);
			
			if(action.equals("edit")) {
				int id = Integer.parseInt(request.getParameter("categoryId"));
				category.setId(id);
				categoryBean.update(category);
			}
			else 
				categoryBean.insert(category);
			
			response.sendRedirect(request.getRequestURI());
		}*/
		else if(action.equals("edit")) {
			int id = Integer.parseInt(request.getParameter("categoryId"));
			String name = request.getParameter("categoryName");
			Category category = new Category(id, name);
			categoryBean.update(category);
			response.sendRedirect(request.getRequestURI());
		}
		else if(action.equals("add")) {
			String name = request.getParameter("categoryName");
			Category category = new Category(name);
			categoryBean.insert(category);
			response.sendRedirect(request.getRequestURI());
		}
	}
}
