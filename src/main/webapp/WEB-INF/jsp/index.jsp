<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>
<style>
/*
 * Base structure
 */
html,body {
	height: 100%;
	background-color: #fff;
}

body {
	color: #000;
	text-align: center;
}

/* Extra markup and styles for table-esque vertical and horizontal centering */
.site-wrapper {
	display: table;
	width: 100%;
	height: 100%; /* For at least Firefox */
	min-height: 100%;
}

.site-wrapper-inner {
	display: table-cell;
	vertical-align: top;
}

.cover-container {
	margin-right: auto;
	margin-left: auto;
}

/* Padding for spacing */
.inner {
	padding: 60px;
}

/* Start the vertical centering */
.site-wrapper-inner {
	vertical-align: middle;
}

/* Handle the widths */
.masthead,.mastfoot,.cover-container {
	width: 100%;
	/* Must be percentage or pixels for horizontal alignment */
}
</style>

<div class="inner cover">
	<h1 class="cover-heading">Record Everyday</h1>
	<br />
	<p class="lead">
		The Web Note can help you record every valuable moment in your life.<br />
		Let you easily collect and find everything that matters.
	</p>
	<br /> <br />
	<p class="lead">
		<a href='<spring:url value="/" />' class="btn btn-info btn-lg">Learn
			more</a>
	</p>

</div>

