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


/**
 * Servlet implementation class Controller
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
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
		String address = "/WEB-INF/pages/login.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		session.setAttribute("notification", "");
		if (action == null || action.equals("")) {
			address = "WEB-INF/pages/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		} /*else if (action.equals("logout")) {
			session.invalidate();
			address = "/WEB-INF/pages/login.jsp";
		}*/ else if (action.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			AdminBean adminBean = new AdminBean();
			if (adminBean.login(username, password)) {
				session.setAttribute("adminBean", adminBean);
				//MessageBean messageBean = new MessageBean();
				//session.setAttribute("messageBean", messageBean);
				address = "/menu";
				System.out.println(address);
				response.sendRedirect(request.getContextPath() + address);
			} else {
				session.setAttribute("notification", "Pogresni parametri za pristup");
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
			}
		} /*else if (action.equals("registration")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			AdminBean userBean = new AdminBean();
			try {
				if (username != null) {
					if (userBean.isUserNameAllowed(request.getParameter("username"))) {
						User user = new User(0, username, password, request.getParameter("lastName"),
								request.getParameter("firstName"));
						if (userBean.add(user)) {
							MessageBean messageBean = new MessageBean();
							session.setAttribute("messageBean", messageBean);
							address = "/WEB-INF/pages/messages.jsp";
						}
					} else {
						session.setAttribute("notification", "Username je zauzet");
						address = "/WEB-INF/pages/registration.jsp";
					}
				} else {
					address = "/WEB-INF/pages/registration.jsp";
				}
			} catch (Exception e) {
				session.setAttribute("notification", "ERROR: " + e.getMessage());
			}
		} else {
			AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
			if (adminBean == null || !adminBean.isLoggedIn()) {
				address = "/WEB-INF/pages/login.jsp";
			} else {
				if (action.equals("messages")) {
					address = "/WEB-INF/pages/messages.jsp";
				} else if (action.equals("newMessage")) {
					address = "/WEB-INF/pages/new_message.jsp";
					MessageBean messageBean = (MessageBean) session.getAttribute("messageBean");
					if (request.getParameter("submit") != null && request.getParameter("text") != null) {
						try {
							String userInfo = userBean.getUser().getLastName() + " " + userBean.getUser().getFirstName()
									+ " (" + userBean.getUser().getUsername() + ")";
							String date = new SimpleDateFormat("dd.MM.yyyy. HH:mm").format(new Date());
							Message book = new Message(request.getParameter("text"), userInfo, date,
									request.getRemoteAddr(), 0);
							if (messageBean.add(book))
								address = "/WEB-INF/pages/messages.jsp";
						} catch (Exception e) {
							session.setAttribute("notification", "ERROR: " + e.getMessage());
						}
					}

				} else {
					address = "/WEB-INF/pages/404.jsp";
				}
			}

		}
*/		System.out.println(request.getContextPath() + address);
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
