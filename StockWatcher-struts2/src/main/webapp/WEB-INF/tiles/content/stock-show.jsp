<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table>
	<tr>
		<th><s:text name="model.stock.ticker" /></th>
		<td>${model.ticker}</td>
	</tr>
	<tr>
		<th><s:text name="model.stock.name" /></th>
		<td>${model.name}</td>
	</tr>
	<tr>
		<th><s:text name="model.stock.mean" /></th>
		<td>${model.mean}</td>
	</tr>
	<tr>
		<th><s:text name="model.stock.stdDev" /></th>
		<td>${model.stdDev}</td>
	</tr>
</table>

<br/>

<s:form action="%{#request.contextPath}/price!byStock" method="post">
	<s:hidden name="stock" value="%{model.id}" />
	<s:select name="n" list="{10, 50, 100, 500}"/>
	<s:submit />
</s:form>

<br/>

<s:form action="%{#request.contextPath}/price!generate" method="post">
	<s:hidden name="stock" value="%{model.id}" />
	<s:select name="n" list="{10, 50, 100, 500}"/>
	<s:submit />
</s:form>