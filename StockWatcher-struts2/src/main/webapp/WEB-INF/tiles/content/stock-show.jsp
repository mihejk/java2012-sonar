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