<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table>
	<tr>
		<th><s:text name="label.ordinal" /></th>
		<th><s:text name="model.price.value" /></th>
	</tr>
	<s:iterator value="model" status="status">
		<tr>
			<td><s:property value="#status.count"/></td>
			<td>${value}</td>
		</tr>
	</s:iterator>
</table>