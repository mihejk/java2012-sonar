<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="%{#request.contextPath}/portfolio" method="post">
	<s:textfield name="model.name" key="model.portfolio.name" />
	<s:submit />
</s:form>