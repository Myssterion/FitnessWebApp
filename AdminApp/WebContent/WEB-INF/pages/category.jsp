<%@ page import="model.Category" %>
<%@ page import="model.Attribute" %>
<%@ page import="bean.CategoryBean" %>
<%@ page import="bean.AttributeBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="categoryBean" type="bean.CategoryBean" scope="session"/>
    <jsp:useBean id="attributeBean" type="bean.AttributeBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminApp</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
<link href="styles/style.css" type="text/css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.6/umd/popper.min.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/pages/menu.jsp"/>
<div class="btn-toolbar" style="margin-left: 10px; margin-top: 15px;">
    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addModal">Add category</button>
</div>
<div class="well">
    <table class="table">
        <thead>
            <tr>
                <th>#</th>
                <th>Difficulties</th>
                <th style="width: 36px;"></th>
            </tr>
        </thead>
        <tbody>
            <%-- Iterate over a collection of user objects using JSP scriptlets --%>
            <% int i = 0; %>
            <% for(Category category : categoryBean.getCategories()) { %>
                <% i++; %>
                <tr>
                    <td><%= i %></td>
                    <td><%= category.getName() %></td>
                    <td style="white-space: nowrap;">
                     	<button type="button" class="btn btn-primary" onclick="toggleAttributes('<%= category.getId() %>')">Show Attributes</button>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal" onclick="setEditItem('<%= category.getId() %>',
                        '<%= category.getName() %>')">
						<i class="fas fa-pencil-alt"></i>
						</button>
						<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="setDeleteItemId('<%= category.getId() %>')">
						<i class="fas fa-trash-alt"></i>
						</button>
            <!-- Add a button to toggle attributes -->
            <select id="attributesDropdown<%= category.getId() %>" style="display: none;">
                <!-- Pre-fill the dropdown with attributes -->
                <% for(Attribute attribute : category.getAttributes()) { %>
                    <option value="<%= attribute.getId() %>"><%= attribute.getName() %></option>
                <% } %>
            </select>
                    </td>
                </tr>
                 <tr id="attributesRow<%= category.getId() %>" style="display: none;">
        <td colspan="3" style="background-color: #dff0d8;">
            <table class="table table-success">
                <tr>
                    <th>#</th>
                    <th>Attribute</th>
                    <!-- Add more columns if needed -->
                </tr>
                <% int j = 0; %>
                <% for(Attribute attribute : category.getAttributes()) { %>
                    <% j++; %>
                    <tr>
                        <td><%= j %></td>
                        <td><%= attribute.getName() %></td>
                        <!-- Add more columns if needed -->
                    </tr>
                <% } %>
            </table>
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
    function setEditItem(categoryId, categoryName) {
        document.getElementById('editedItemId').value = categoryId;
        document.getElementById('editedItemName').value = categoryName;
    }
    
    function toggleAttributes(categoryId) {
        var attributesRow = document.getElementById('attributesRow' + categoryId);
        if (attributesRow.style.display === 'none') {
            attributesRow.style.display = 'table-row';
        } else {
            attributesRow.style.display = 'none';
        }
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
        	<input type="hidden" name="categoryId" id="deleteItemId">
        	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        	<button type="submit" class="btn btn-primary">Delete</button>
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
        <form action="?action=edit" method="post" >
        	<input type="hidden" name="categoryId" id="editedItemId">
        	<div style="margin-bottom: 10px;">
        	<label>Category</label>
        	<input type="text" name="categoryName" id="editedItemName">
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
        	<div style="margin-bottom: 10px;">
        	<label>Category</label>
        	<input type="text" name="categoryName" id="ItemName">
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