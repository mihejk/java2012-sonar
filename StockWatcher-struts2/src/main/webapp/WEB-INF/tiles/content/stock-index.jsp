<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table>
	<tr>
		<th><s:text name="model.stock.ticker" /></th>
		<th><s:text name="model.stock.name" /></th>
		<th><s:text name="model.stock.mean" /></th>
		<th><s:text name="model.stock.stdDev" /></th>
		<th><s:text name="label.actions" /></th>
	</tr>
	<s:iterator value="model">
		<tr>
			<td>${ticker}</td>
			<td>${name}</td>
			<td>${mean}</td>
			<td>${stdDev}</td>
			<td>
				<a href="stock/${id}"><s:text name="label.view" /></a> |
				<a href="stock/${id}/edit"><s:text name="label.edit" /></a> |
				<a href="stock/${id}?_method=DELETE"><s:text name="label.delete" /></a>
			</td>
		</tr>
	</s:iterator>
</table>

<p>
	<a href="stock/new"><s:text name="title.stock-new" /></a>
</p>