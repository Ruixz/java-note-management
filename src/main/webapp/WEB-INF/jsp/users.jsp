<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<!-- AJAX script -->
<script>
	function loadXMLDoc() {
		var xmlhttp;
		var userName = document.getElementById("ajaxUserName").value;
		// alert(userName);
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("ajaxResponse").innerHTML = xmlhttp.responseText;
			}
		}
		xmlhttp.open("GET", "ajaxSearchUser.htm?userName=" + userName, true);
		xmlhttp.send();
	}

	function ajaxFunction2() {
		var xmlhttp;
		var userName = document.getElementById("requireUserName").value;
		alert(userName);
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("ajaxResponse").innerHTML = xmlhttp.responseText;
			}
		}
		xmlhttp.open("GET", "ajaxRequest.htm?userName=" + userName, true);
		xmlhttp.send();
	}
</script>
<!-- AJAX script above-->

<!-- AJAX part -->
<input type="text" name="userName" id="ajaxUserName" />
<input type="submit" class="btn btn-info" value="Search"
	onclick="loadXMLDoc()" />
<div id="ajaxResponse" style="color: red"></div>
<!-- AJAX part -->

<script type="text/javascript">
	$(document).ready(function() {
		// 		$('.nav-tabs a:first').tab('show'); // Select first tab
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
	});
</script>

<br />
<br />
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<td>User Name</td>
			<td>Belonged Group</td>
			<td>Operations</td>
			<td>Change Group</td>
			<td>Select Role</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><a href='<spring:url value="/users/${user.id}.html" />'>
						<c:out value="${user.name}" />
				</a></td>
				<td><c:out value="${user.workGroup}"></c:out></td>
				<td><a
					href='<spring:url value="/users/remove/${user.id}.html" />'
					class="btn btn-danger triggerRemove"> Remove User</a></td>
				<td><form:form commandName="user"
						action="/users.htm?user=${user.id}">

						<form:select path="workGroup.id">
							<option>Please select</option>
							<form:options items="${groups}" itemValue="id" itemLabel="name" />
						</form:select>

						<input type="submit" value="Select Group" class="btn btn-warning" />
					</form:form></td>
				<td><form:form commandName="role"
						action="/role.htm?user=${user.id}">

						<form:select path="name">
							<option>Please select</option>
							<form:options items="${roles}" itemValue="name" itemLabel="name" />
						</form:select>
<%-- 						<input type="hidden" name="userID" value="${user.id}" /> --%>
						<input type="submit" value="Select Role" class="btn btn-warning" />
					</form:form></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Remove User</h4>
			</div>
			<div class="modal-body">Really remove this User?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger removeBtn">Remove</a>
			</div>
		</div>
	</div>
</div>