<%@ page import="model.RegularUser" %>
<%@ page import="bean.RegularUserBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="regularUserBean" type="bean.RegularUserBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminApp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
<link href="styles/style.css" type="text/css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
</head>
<body>


<jsp:include page="/WEB-INF/pages/menu.jsp"/>
<div class="btn-toolbar" style="margin-left: 10px; margin-top: 15px;">
    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addModal">Add regular user</button>
</div>
<div class="well">
    <table class="table">
        <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Username</th>
                <th>Password</th>
               	<th>City</th>
                <th>Email</th>
                <th>Status</th>
                <th style="width: 36px;"></th>
            </tr>
        </thead>
        <tbody>
            <%-- Iterate over a collection of user objects using JSP scriptlets --%>
            <% int i = 0; %>
            <% for(RegularUser regularUser : regularUserBean.getRegularUsers()) { %>
                <% i++; %>
                <tr>
                    <td><%= i %></td>
                    <td><%= regularUser.getName() %></td>
                    <td><%= regularUser.getSurname() %></td>
                    <td><%= regularUser.getUsername() %></td>
                    <td><%= regularUser.getPassword() %></td>
                    <td><%= regularUser.getCity() %></td>
                    <td><%= regularUser.getEmail() %></td>
                     <td><%= regularUser.getStatus() ? "ACTIVE" : "INACTIVE" %></td>
                    <td style="white-space: nowrap;">
                    	<button type="button" class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#statusModal" onclick="changeStatus('<%= regularUser.getId() %>'
                    	,'<%= regularUser.getStatus() %>')">
						<i class="fas fa-unlock"></i>
                    	</button>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal" onclick="setEditItem('<%= regularUser.getId() %>',
                        '<%= regularUser.getName() %>','<%= regularUser.getSurname() %>','<%= regularUser.getUsername() %>','<%= regularUser.getPassword() %>',
                        '<%= regularUser.getCity() %>','<%= regularUser.getEmail() %>')">
						<i class="fas fa-pencil-alt"></i>
						</button>
						<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="setDeleteItemId('<%= regularUser.getId() %>')">
						<i class="fas fa-trash-alt"></i>
						</button>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
</div>
<script>
    function setDeleteItemId(itemId) {
        document.getElementById('deleteItemId').value = itemId;
    }
    function setEditItem(itemId,itemName,itemSurname,itemUsername,itemPassword,itemCity,itemEmail) {
    	 document.getElementById('editedItemId').value = itemId;
        document.getElementById('ItemName').value = itemName;
        document.getElementById('ItemSurname').value = itemSurname;
        document.getElementById('ItemUsername').value = itemUsername;
        document.getElementById('ItemPassword').value = itemPassword;
        document.getElementById('ItemCity').value = itemCity;
        document.getElementById('ItemEmail').value = itemEmail;
    }
    
    function changeStatus(itemId,itemStatus){
    	 document.getElementById('statusItemId').value = itemId;
    	 document.getElementById('statusId').value = itemStatus;
    }
    
</script>
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="deleteModalLabel">Are you sure you want to delete?</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="?action=delete" method="post">
        	<input type="hidden" name="regularUserId" id="deleteItemId">
        	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        	<button type="submit" class="btn btn-primary">Delete</button>
    	</form>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="statusModal" tabindex="-1" aria-labelledby="statusModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="statusModalLabel">Are you sure you want to change status?</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="?action=status" method="post">
        	<input type="hidden" name="regularUserId" id="statusItemId">
        	<input type="hidden" name="regularUserStatus" id="statusId">
        	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
        	<button type="submit" class="btn btn-primary">Yes</button>
    	</form>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLable" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="editModalLabel">Edit</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="?action=edit" method="post">
        	<input type="hidden" name="regularUserId" id="editedItemId">
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>Name</label>
        	<input type="text" name="regularUserName" id="ItemName" required="required" autocomplete="off" class="input">
        	</div>
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>Surname</label>
        	<input type="text" name="regularUserSurname" id="ItemSurname" required="required" autocomplete="off" class="input">
        	</div>
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>Username</label>
        	<input type="text" name="regularUserUsername" id="ItemUsername" required="required" autocomplete="off" class="input">
        	</div>
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>Password</label>
        	<input type="text" name="regularUserPassword" id="ItemPassword" required="required" autocomplete="off" class="input">
        	</div>
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>City</label>
        	<input type="text" name="regularUserCity" id="ItemCity" required="required" autocomplete="off" class="input"> 
        	</div>
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>Email</label>
        	<input type="text" name="regularUserEmail" id="ItemEmail" required="required" autocomplete="off" class="input">
        	</div>
        	 <div class="modal-footer">
       		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        	<button type="submit" class="btn btn-primary">Save changes</button>
   	 </div>
    	</form>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLable" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="addModalLabel">Add</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="?action=add" method="post">
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>Name</label>
        	<input type="text" name="regularUserName" id="ItemName" required="required" autocomplete="off" class="input">
        	</div>
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>Surname</label>
        	<input type="text" name="regularUserSurname" id="ItemSurname" required="required" autocomplete="off" class="input">
        	</div>
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>Username</label>
        	<input type="text" name="regularUserUsername" id="ItemUsername" required="required" autocomplete="off" class="input">
        	</div>
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>Password</label>
        	<input type="text" name="regularUserPassword" id="ItemPassword" required="required" autocomplete="off" class="input">
        	</div>
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>City</label>
        	<input type="text" name="regularUserCity" id="ItemCity" required="required" autocomplete="off" class="input">
        	</div>
        	<div class="form-group" style="margin-bottom: 10px;">
        	<label>Email</label>
        	<input type="text" name="regularUserEmail" id="ItemEmail" required="required" autocomplete="off" class="input">
        	</div>
        	 <div class="modal-footer">
       		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        	<button type="submit" class="btn btn-primary">Add</button>
   	 </div>
    	</form>
      </div>
    </div>
  </div>
</div>


</body>
</html>