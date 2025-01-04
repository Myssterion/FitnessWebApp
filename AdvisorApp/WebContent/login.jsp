<%@ page import="bean.AdvisorBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="advisorBean" class="bean.AdvisorBean" scope="session"/>
<jsp:useBean id="advisorService" class="service.AdvisorService" scope="application"/>
<jsp:useBean id="messageService" class="service.MessageService" scope="application"/>
<jsp:setProperty property="username" name="advisorBean" param="username"/>
<jsp:setProperty property="password" name="advisorBean" param="password"/>
<!DOCTYPE html>
<%
	if (request.getParameter("submit") != null) {
		AdvisorBean advisor = advisorService.loginAdvisor(advisorBean.getUsername(), advisorBean.getPassword());
		if (advisor != null) {
			advisorBean.setName(advisor.getName());
			advisorBean.setSurname(advisor.getSurname());
			advisorBean.setLoggedIn(true);
			session.setAttribute("notification", "");
			messageService.loadMessages();
			response.sendRedirect("inbox.jsp");
		} else {
			session.setAttribute("notification", "Unijeli ste neispravno korisni&#269;ko ime ili lozinku.");
			advisorBean.setLoggedIn(false);
		}
	} else {
		session.setAttribute("notification", "");
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Advisor App</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	</head>
<body>
    <div class="container-fluid d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="row justify-content-center">
            <div class="col-md-6 mx-auto text-center" style="max-width: 50%;">
                <form method="POST" action="?action=login" class="form">
                    <div class="mb-3 d-flex justify-content-center"> <!-- Center the input horizontally -->
                        <input type="text" name="username" id="username" placeholder="Username" autocomplete="off" class="form-control-lg">
                    </div>
                    <div class="mb-3 d-flex justify-content-center"> <!-- Center the input horizontally -->
                        <input type="password" name="password" id="password" placeholder="Password" class="form-control-lg">
                    </div>
                    <div class="error-message mb-3 text-center">
                        <%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%>
                    </div>
                    <div class="mb-3 text-center">
                        <button type="submit" name="submit" class="btn btn-dark">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>