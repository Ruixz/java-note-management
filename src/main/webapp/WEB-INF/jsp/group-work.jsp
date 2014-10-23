<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<h2>
	Hello:
	<c:out value="${user.name}" />
</h2>

<h3>
	Group Name:
	<c:out value="${group.name}" />
</h3>

<script type="text/javascript">
	$(document).ready(function() {
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
	});
</script>

<!-- Button trigger modal -->
<button class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myUpload">Upload File</button>

<form:form method="post" enctype="multipart/form-data"
	modelAttribute="uploadedFile" action="/group/${group.id}.html">
	<div class="modal fade" id="myUpload" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">File Upload</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">File:</label>
						<div class="col-sm-10">
							<input type="file" name="file" />
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Upload" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<br />
<h4>Project Name</h4>
<c:forEach items="${group.projects}" var="project">
	<p class="text-primary">
		<security:authorize access="hasRole('ROLE_SUPER')">
			<a
				href='<spring:url value="/group-work/remove/${project.id}.html" />'
				class="btn btn-danger btn-xs triggerRemove">Remove</a>
		</security:authorize>
		&nbsp&nbsp <a
			href='<spring:url value="/download/${project.name}.html" />'> <c:out
				value="${project.name}" /></a>

	</p>
</c:forEach>

<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Remove Project?</h4>
			</div>
			<div class="modal-body">Really remove this Project?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger removeBtn">Remove</a>
			</div>
		</div>
	</div>
</div>