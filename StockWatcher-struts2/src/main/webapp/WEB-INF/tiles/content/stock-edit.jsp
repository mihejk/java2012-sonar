<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="%{#request.contextPath}/stock/%{model.id}" method="post">
	<s:hidden name="_method" value="put"/>
	<s:hidden name="model.id"/>
	<s:textfield name="model.ticker" key="model.stock.ticker" />
	<s:submit />
</s:form>