<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table>
	<tr>
		<th>portfolio</th>
		<td>${portfolio.name}</td>
	</tr>
	<tr>
		<th>method</th>
		<td>${statisticsMethod}</td>
	</tr>
	<tr>
		<th>result</th>
		<td>${model}</td>
	</tr>
</table>