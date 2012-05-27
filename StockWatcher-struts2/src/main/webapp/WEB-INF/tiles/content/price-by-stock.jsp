<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table>
	<tr>
		<th><s:text name="model.value" /></th>
	</tr>
	<s:iterator value="model">
		<tr>
			<td>${value}</td>
		</tr>
	</s:iterator>
</table>