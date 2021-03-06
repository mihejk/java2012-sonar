<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table>
	<tr>
		<th><s:text name="model.portfolio.name" /></th>
		<td colspan="3">${model.name}</td>
	</tr>
	<tr>
		<th><s:text name="label.value" /></th>
		<td colspan="3"><s:property value="statistics['value']" /></td>
	</tr>
	<tr>
		<th><s:text name="statistics.exactVar95" /></th>
		<td><s:property value="statistics['exactVar95']"/></td>
		<th><s:text name="statistics.exactVar99" /></th>
		<td><s:property value="statistics['exactVar99']"/></td>
	</tr>
	<tr>
		<th><s:text name="statistics.historicVar95" /></th>
		<td><s:property value="statistics['historicVar95']"/></td>
		<th><s:text name="statistics.historicVar99" /></th>
		<td><s:property value="statistics['historicVar99']"/></td>
	</tr>
	<tr>
		<th><s:text name="statistics.hybridVar95" /></th>
		<td><s:property value="statistics['hybridVar95']"/></td>
		<th><s:text name="statistics.hybridVar99" /></th>
		<td><s:property value="statistics['hybridVar99']"/></td>
	</tr>
</table>

<br/>

<table>
	<tr>
		<th><s:text name="model.stock.ticker" /></th>
		<th><s:text name="model.stock.name" /></th>
		<th><s:text name="model.position.quantity" /></th>
		<th><s:text name="label.last_price" /></th>
		<th><s:text name="label.actions" /></th>
	</tr>
	<s:iterator value="positions"  status="status">
		<tr>
			<td>${stock.ticker}</td>
			<td>${stock.name}</td>
			<td>${quantity}</td>
			<td><s:property value="lastPrice[#status.index].value"/></td>
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
