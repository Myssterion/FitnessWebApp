<%@ page import="model.Attribute" %>
<%@ page import="model.Category" %>
<%@ page import="bean.AttributeBean" %>
<%@ page import="bean.CategoryBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="attributeBean" type="bean.AttributeBean" scope="session"/>
<jsp:useBean id="categoryBean" type="bean.CategoryBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin App</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="/WEB-INF/pages/menu.jsp"/>
<div class="btn-toolbar" style="margin-left: 10px; margin-top: 15px;">
    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addModal" onclick="resetRadios()">Add attribute</button>
</div>
<div class="well">
    <table class="table">
        <thead>
            <tr>
                <th>#</th>
                <th>Attributes</th>
                 <th>Category</th>
                <th style="width: 36px;"></th>
            </tr>
        </thead>
        <tbody>
            <%-- Iterate over a collection of user objects using JSP scriptlets --%>
            <% int i = 0; %>
            <% for(Attribute attribute : attributeBean.getAttributes()) { %>
                <% i++; %>
                <tr>
                    <td><%= i %></td>
                    <td><%= attribute.getName() %></td>
                     <td><%= attribute.getCategory().getName() %></td>
                    <td style="white-space: nowrap;">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal" onclick="setEditItem('<%= attribute.getId() %>',
                        '<%= attribute.getName() %>',' <%= attribute.getCategory().getId() %>')">
						<i class="fas fa-pencil-alt"></i>
						</button>
						<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="setDeleteItemId('<%= attribute.getId() %>')">
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
    function setEditItem(itemId,itemName, categoryId) {
    	console.log(categoryId);
        document.getElementById('editedItemId').value = itemId;
        document.getElementById('editedItemName').value = itemName;
        
        var allRadios = document.querySelectorAll('#dropdownMenuEdit input[type="radio"]');
        allRadios.forEach(function(radio) {
            radio.checked = false;
        });
        
        var trimmedCategoryId = categoryId.trim();
        var radioId = 'radio_edit_' + trimmedCategoryId;
        document.getElementById(radioId).checked = true;
      
        
    }
    
    function validateForm(type) {
        // Get all checkboxes inside the dropdown menu
        var radios;
        console.log(type);
        if(type === 'Add')
        	radios = document.querySelectorAll('#dropdownMenuAdd input[type="radio"]');
        else if(type === 'Edit')
        	radios = document.querySelectorAll('#dropdownMenuEdit input[type="radio"]');
        
        var checked = false;
        
        // Check if at least one checkbox is checked
        radios.forEach(function(radio) { console.log(radio.id);
            if (radio.checked) {console.log(radio.id);
                checked = true;
            }
        });

        // If no checkbox is checked, display an error message and prevent form submission
        if (!checked) {
            alert('Please select one category.');
            return false; // Prevent form submission
        }

        return true; // Allow form submission
    }
    
    $(document).ready(function() {
        $(".dropdown-toggle").dropdown();
    });
    
    function resetRadios() {
    	  resetForm('addForm');
    	  var allRadios = document.querySelectorAll('#dropdownMenuAdd input[type="radio"]');
          allRadios.forEach(function(radio) {
              radio.checked = false;
          });
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
        	<input type="hidden" name="attributeId" id="deleteItemId">
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
        <form action="?action=edit" method="post"  onsubmit="return validateForm('Edit')">
        	<input type="hidden" name="attributeId" id="editedItemId">
        	<div style="margin-bottom: 10px;">
        	<label>Attribute</label>
        	<input type="text" name="attributeName" id="editedItemName">
        	</div>
        	<div class="dropdown">
        	<label>Select category</label> 
            <button class="btn btn-success dropdown-toggle"
                    type="button" 
                    id="multiSelectDropdown"
                    data-bs-toggle="dropdown" 
                    data-bs-target="#dropdownMenuEdit"
                    aria-expanded="false"> 
              Categories
            </button> 
            <ul id="dropdownMenuEdit" class="dropdown-menu" 
                aria-labelledby="multiSelectDropdown"> 
                <% for(Category category : categoryBean.getCategories()) { %>
                   <li>
      				<input type="radio" name="selectedCategory" value="<%= category.getId() %>" id="radio_edit_<%= category.getId() %>">
				    <label for="radio_edit_<%= category.getId() %>"><%= category.getName() %></label>
                </li> 
                <% } %>
            </ul> 
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
        <form action="?action=add" method="post"  onsubmit="return validateForm('Add')" id="addForm">
        	<div style="margin-bottom: 10px;">
        	<label>Attribute</label>
        	<input type="text" name="attributeName" id="ItemName">
        	</div>
        	<div class="dropdown">
        	<label>Select category</label> 
            <button class="btn btn-success dropdown-toggle"
                    type="button" 
                    id="multiSelectDropdown"
                    data-bs-toggle="dropdown" 
                    data-bs-target="#dropdownMenuAdd"
                    aria-expanded="false"> 
              Categories
            </button> 
            <ul id="dropdownMenuAdd" class="dropdown-menu" 
                aria-labelledby="multiSelectDropdown"> 
                <% for(Category category : categoryBean.getCategories()) { %>
                   <li>
      				<input type="radio" name="selectedCategory" value="<%= category.getId() %>" id="radio_add_<%= category.getId() %>">
				    <label for="radio_add_<%= category.getId() %>"><%= category.getName() %></label>
                </li> 
                <% } %>
            </ul> 
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