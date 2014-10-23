<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/taglib.jsp"%>
    
<c:if test="${not empty ajaxUser}">${ajaxUser.name} exists</c:if>
<c:if test="${ empty ajaxUser}">Not found 
<input type="hidden" value="${name}" id="requireUserName"/>
</c:if>