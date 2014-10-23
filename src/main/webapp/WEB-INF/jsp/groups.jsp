<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<!-- Button trigger modal -->
<button class="btn btn-default btn-lg" data-toggle="modal"
	data-target="#myModal">New Group</button>


<form:form commandName="group" cssClass="form-horizontal groupForm">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">New Group</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Name:</label>
						<div class="col-sm-10">
							<form:input path="name" cssClass="form-control" />
							<form:errors path="name" />
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-success" value="Save" />
				</div>
			</div>
		</div>
	</div>
</form:form>


<script type="text/javascript">
	$(document).ready(function() {
		// 		$('.nav-tabs a:first').tab('show'); // Select first tab
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
		$(".groupForm").validate(
				{
					rules : {
						name : {
							required : true,
							minlength : 1,
						}
					},
					highlight : function(element) {
						$(element).closest('.form-group').removeClass(
								'has-success').addClass('has-error');
					},
					unhighlight : function(element) {
						$(element).closest('.form-group').removeClass(
								'has-error').addClass('has-success');
					},
				});
	});
</script>

<br />
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<td>Group Name</td>
			<td>Operations</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${groups}" var="workGroup">
			<tr>
				<td><a
					href='<spring:url value="/groups/${workGroup.id}.html" />'> <c:out
							value="${workGroup.name}" />
				</a></td>
				<td><a
					href='<spring:url value="/groups/remove/${workGroup.id}.html" />'
					class="btn btn-danger triggerRemove"> remove</a></td>
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
				<h4 class="modal-title" id="myModalLabel">Remove Group</h4>
			</div>
			<div class="modal-body">Really remove this Group?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger removeBtn">Remove</a>
			</div>
		</div>
	</div>
</div>