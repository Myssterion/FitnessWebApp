<%@ page import="bean.MessageBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<jsp:useBean id="messageService" class="service.MessageService" scope="application"/>
    <jsp:useBean id="advisorBean" class="bean.AdvisorBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<%
	String action = request.getParameter("action");
	String content = "";
	String sentAt = "";
	int userId = -1;
	
	if(!(advisorBean.isLoggedIn())) 
		response.sendRedirect("login.jsp");
	
	else if ("status".equalsIgnoreCase(action) && "POST".equalsIgnoreCase(request.getMethod())) {
        String messageId = request.getParameter("messageId");

        if (messageId != null && !messageId.isEmpty()) {
        	 content = request.getParameter("hiddenContent");
             sentAt = request.getParameter("hiddenSentAt");
             userId = Integer.parseInt(request.getParameter("userId"));
            int id = Integer.parseInt(messageId);
            messageService.updateStatus(id);System.out.println("RADI");
        }
    }
%>
<html>
<head>
<meta charset="UTF-8">
<title>Advisor App</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="styles/style.css">
</head>
<body>

<div>
    <div class="row container">
        <div class="col inbox">
        <form method="post" action="?action=status" id="conversationForm">
        	<input type="hidden" name="messageId" id="messageId">
        	<input type="hidden" name="hiddenContent" id="hiddenContent">
        	<input type="hidden" name="hiddenSentAt" id="hiddenSentAt">
        	<input type="hidden" name="userId" id="userId">
        </form>
        <div class="search-container">
         <input type="text" class="form-control search-input" placeholder="Search" name="search" id="search"
                autocomplete="off" oninput="onSearch()">
        </div>
        <div id="message-cards-container">
        <% for(MessageBean message : messageService.getMessages()) { %>
            <div class="card mb-3 message-card" onclick="showConversation('<%= message.getContent() %>','<%= message.getSentAt() %>','<%= message.getId() %>','<%= message.getUser1().getId() %>')">
                <div class="card-header">
                  <div class="user"><%= message.getUser1().getUsername() %></div>
                  <div><%= message.getSentAt() %></div>
                </div>
                <div class="card-body">
                  <p class="card-text"><%= message.getContent() %></p>
                </div>
              </div>
              <% } %>
        </div>
        </div>
        <div class="col conversation w-full">
          <div class="prev-conversation">
            <div class="message-box">
              <div class="message">
                <div class="message-content">
                  <div class="flex flex-row justify-between" id="content">
                   <%= content %>
                  </div>
                  <div class="flex flex-row justify-between" style="font-size: 10px;" id="sentAt">
                	<%= sentAt %>
                  </div>
                </div>
            </div>
          </div>
          </div>
          <div class="message-area">
          <form class="form-horizontal" method="post" action="FileUploadServlet" enctype="multipart/form-data">
          	<input type="hidden" name="id" id="id" value="<%= userId %>">
            <div class="form-group">
            <div class="input-group message-size">
                <textarea  class="form-control textarea  message-size" name="message" id="message" placeholder="Enter your message" autocomplete="off" maxlength="600" required></textarea>
            </div>
          </div>
          
           <div class="form-group">
            <div class="input-group">
                <input type="file" name="attachment" id="attachment">
            </div>
        </div>

          <div class="form-group ">
            <button type="submit" id="submitButton" class="btn btn-primary btn-lg pull-right button-body" disabled>Posalji poruku</button>
        </div>
        </form>
      </div>
        </div>
    </div>
</div>

<script>
	

	function showConversation(content, sentAt, messageId, userId) {
		document.getElementById('hiddenContent').value = content;
		document.getElementById('hiddenSentAt').value = sentAt;
		document.getElementById('messageId').value = messageId;
		document.getElementById('userId').value = userId;
		
	    document.getElementById('conversationForm').submit();
	    
	}
	
	const messageTextarea = document.getElementById('message');
    const submitButton = document.getElementById('submitButton');

    messageTextarea.addEventListener('input', function() {
        // Enable the button if there's text in the textarea, otherwise disable it
        if (messageTextarea.value.trim().length > 0 && userId != -1) {
            submitButton.disabled = false;
        } else {
            submitButton.disabled = true;
        }
    });
    
    
    function onSearch() {
        // Get the search input value and convert it to lowercase
        var searchValue = document.getElementById('search').value.toLowerCase();

        // Get all the message card elements
        var messageCards = document.querySelectorAll('#message-cards-container .message-card');

        // Loop through each message card and check if it matches the search term
        messageCards.forEach(function (card) {
            var username = card.querySelector('.user').textContent.toLowerCase();
            var content = card.querySelector('.card-text').textContent.toLowerCase();

            // If the username or message content includes the search term, show the card; otherwise, hide it
            if (username.includes(searchValue) || content.includes(searchValue)) {
                card.style.display = '';
            } else {
                card.style.display = 'none';
            }
        });
    }
</script>

</body>
</html>