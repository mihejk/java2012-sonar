<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1><s:text name="title.portfolio-show" /></h1>

<table>
	<tr>
		<th><s:text name="model.portfolio.name" /></th>
		<td>${model.name}</td>
	</tr>
</table>