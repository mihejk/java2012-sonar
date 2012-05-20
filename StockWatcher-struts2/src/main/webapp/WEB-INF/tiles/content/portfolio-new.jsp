<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1><s:text name="title.portfolio-new" /></h1>

<s:form action="%{#request.contextPath}/portfolio" method="post">
	<label><s:text name="model.portfolio.name" /></label>
	<s:textfield name="model.name" />
	
	<s:submit />
</s:form>