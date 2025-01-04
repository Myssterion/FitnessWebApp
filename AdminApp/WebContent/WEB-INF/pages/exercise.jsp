<%@ page import="model.Exercise" %>
<%@ page import="bean.ExerciseBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="exerciseBean" type="bean.ExerciseBean" scope="session"/>
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
    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addModal">Add exercise</button>
</div>
<div class="well">
    <table class="table">
        <thead>
            <tr>
                <th>#</th>
                <th>Exercise</th>
                <th>Type</th>
                <th>Muscle</th>
                <th style="width: 36px;"></th>
            </tr>
        </thead>
        <tbody>
            <%-- Iterate over a collection of user objects using JSP scriptlets --%>
            <% int i = 0; %>
            <% for(Exercise exercise : exerciseBean.getExercises()) { %>
                <% i++; %>
                <tr>
                    <td><%= i %></td>
                    <td><%= exercise.getExerciseName() %></td>
                     <td><%= exercise.getType() %></td>
                      <td><%= exercise.getMuscle() %></td>
                    <td style="white-space: nowrap;">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal" onclick="setEditItem('<%= exercise.getId() %>',
                        '<%= exercise.getExerciseName() %>','<%= exercise.getType() %>','<%= exercise.getMuscle() %>')">
						<i class="fas fa-pencil-alt"></i>
						</button>
						<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="setDeleteItemId('<%= exercise.getId() %>')">
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
    function setEditItem(itemId,itemName,type,muscle) {
    	console.log(itemName);
        document.getElementById('editedItemId').value = itemId;
        document.getElementById('editedItemName').value = itemName;
        document.getElementById('editedItemType').value = type;
        document.getElementById('editedItemMuscle').value = muscle;
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
        	<input type="hidden" name="exerciseId" id="deleteItemId">
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
        <form action="?action=edit" method="post">
        	<input type="hidden" name="exerciseId" id="editedItemId">
        	<div style="margin-bottom: 10px;">
        	<label>Name</label>
        	<input type="text" name="exerciseName" id="editedItemName" required="required" autocomplete="off" class="input">
        	</div>
        	<div style="margin-bottom: 10px;">
        	<label>Type</label>
        	<input type="text" name="exerciseType" id="editedItemType" required="required" autocomplete="off" class="input">
        	</div>
        	<div style="margin-bottom: 10px;">
        	<label>Muscle</label>
        	<input type="text" name="exerciseMuscle" id="editedItemMuscle" required="required" autocomplete="off" class="input">
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
        	<div class="form-group"  style="margin-bottom: 10px;">
        	<label>Name</label>
        	<input type="text" name="exerciseName" id="ItemName" required="required" autocomplete="off" class="input">
        	</div>
        	<div class="form-group"  style="margin-bottom: 10px;">
        	<label>Type</label>
        	<input type="text" name="exerciseType" id="ItemType" required="required" autocomplete="off" class="input">
        	</div>
        	<div class="form-group"  style="margin-bottom: 10px;">
        	<label>Muscle</label>
        	<input type="text" name="exerciseMuscle" id="ItemMuscle" required="required" autocomplete="off" class="input">
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