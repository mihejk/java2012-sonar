<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1><s:text name="title.portfolio-edit" /></h1>

<s:form action="%{#request.contextPath}/portfolio/%{model.id}" method="post">
	<s:hidden name="_method" value="put"/>
	<s:hidden name="model.id"/>
	<label><s:text name="model.portfolio.name" /></label>
	<s:textfield name="model.name" />
	
	<s:submit />
</s:form>