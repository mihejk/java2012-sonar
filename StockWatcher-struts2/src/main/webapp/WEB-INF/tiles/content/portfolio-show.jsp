<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table>
	<tr>
		<th><s:text name="model.portfolio.name" /></th>
		<td>${model.name}</td>
	</tr>
</table>

<br/>

<table>
	<tr>
		<th><s:text name="model.stock.ticker" /></th>
		<th><s:text name="model.stock.name" /></th>
		<th><s:text name="model.position.quantity" /></th>
		<th><s:text name="label.actions" /></th>
	</tr>
	<s:iterator value="positions">
		<tr>
			<td>${stock.ticker}</td>
			<td>${stock.name}</td>
			<td>${quantity}</td>
			<td>
				<a href="<%=request.getContextPath() %>/position/${id}?_method=DELETE"><s:text name="label.delete" /></a>
			</td>
		</tr>
	</s:iterator>
</table>

<br/>

<s:form action="%{#request.contextPath}/position" method="post">
	<s:hidden name="model.portfolio" value="%{model.id}"/>
	<s:select list="stocks" listKey="id" listValue="ticker" name="model.stock" key="model.stock.ticker"/>
	<s:textfield name="model.quantity" key="model.position.quantity" />
	<s:submit />
</s:form>
