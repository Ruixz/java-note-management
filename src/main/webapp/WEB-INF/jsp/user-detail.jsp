<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1>
	<c:out value="${user.name}" />
</h1>

<br />
<br />

<script type="text/javascript">
	$(document).ready(function() {
		var url;
		$('.nav-tabs a:first').tab('show'); // Select first tab
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
// 		$(".triggerRecord").click(function(e) {
// 			e.preventDefault();
// 			url = $(this).attr("href");
// 			$("#myModalRecord").modal();
// 		});
// 		$('#recordForm').submit(function() {
// 			this.action = url;
// 			return true;
// 		});
	});
</script>

<!-- <!-- <script type="text/javascript"> --> 
<!-- // 	function popup_share(url, width, height) { -->
<!-- // 		day = new Date(); -->
<!-- // 		id = day.getTime(); -->
<!-- // 		eval("page" -->
<!-- // 				+ id -->
<!-- // 				+ " = window.open(url, '" -->
<!-- // 				+ id -->
<!-- // 				+ "', 'toolbar=0,scrollbars=1,location=1,statusbar=0,menubar=0,resizable=0,width=" -->
<!-- // 				+ width + ", height=" + height + ", left = 363, top = 144');"); -->
<!-- // 	} -->
<!-- <!-- </script> --> 

<!-- Nav tabs -->
<ul class="nav nav-tabs">
	<c:forEach items="${user.notes}" var="note">
		<li><a href="#note_${note.id}" data-toggle="tab">${note.name}</a></li>
	</c:forEach>
</ul>

<!-- Tab panes -->
<div class="tab-content">
	<c:forEach items="${user.notes}" var="note">
		<div class="tab-pane" id="note_${note.id}">
			<h1>
				<c:out value="${note.name}" />
			</h1>
			<h4>
				@
				<c:out value="${note.location}" />
			</h4>
			<p>
				<a href='<spring:url value="/note/remove/${note.id}.html" />'
					class="btn btn-danger triggerRemove">Remove Note</a>&nbsp 
<%-- 				<a href='<spring:url value="/note/${note.id}.html" />' --%>
<!-- 					class="btn btn-info triggerRecord">New Record</a>&nbsp -->
<!-- 				<a -->
<!-- 				href="javascript:popup_share('http://twitter.com/home?status=https://github.com/SKipperRay/java-note-management')" -->
<!-- 				title="Share on Twitter"> -->
<!-- 				<button type="button" class="btn btn-default">share on -->
<!-- 					twitter</button> -->
<!-- 			</a> -->
			</p>
			
<!-- 			<table class="table table-bordered table-hover table-striped"> -->
<!-- 				<thead> -->
<!-- 					<tr> -->
<!-- 						<th>Title</th> -->
<!-- 						<th>Description</th> -->
<!-- 					</tr> -->
<!-- 				</thead> -->
<!-- 				<tbody> -->
<%-- 					<c:forEach items="${note.records}" var="record"> --%>
<!-- 						<tr> -->
<%-- 							<th><c:out value="${record.title}" /></th> --%>
<%-- 							<th><c:out value="${record.description}" /></th> --%>
<!-- 							<td> -->
<!-- 						</tr> -->
<%-- 					</c:forEach> --%>
<!-- 				</tbody> -->
<!-- 			</table> -->
		</div>
	</c:forEach>
</div>

<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Remove Note Book</h4>
			</div>
			<div class="modal-body">Really remove this Note?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger removeBtn">Remove</a>
			</div>
		</div>
	</div>
</div>