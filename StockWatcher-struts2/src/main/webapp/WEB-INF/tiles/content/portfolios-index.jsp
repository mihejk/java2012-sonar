<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table>
	<tr>
		<th><s:text name="model.portfolio.name" /></th>
		<th><s:text name="label.actions" /></th>
	</tr>
	<s:iterator value="model">
		<tr>
			<td><s:property value="name" /></td>
			<td><a href="<%=request.getContextPath() %>/portfolio/${id}"><s:text name="label.view" /></a></td>
		</tr>
	</s:iterator>
</table>