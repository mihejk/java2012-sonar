<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table>
	<tr>
		<th><s:text name="model.portfolio.name" /></th>
		<td>${portfolio.name}</td>
	</tr>
	<tr>
		<th><s:text name="statistics.method" /></th>
		<td><s:text name="statistics.%{analysisMethod}" /></td>
	</tr>
	<tr>
		<th><s:text name="statistics.result" /></th>
		<td>${model}</td>
	</tr>
</table>