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
			<td>${name}</td>
			<td>
				<a href="portfolio/${id}"><s:text name="label.view" /></a> |
				<a href="portfolio/${id}/edit"><s:text name="label.edit" /></a> |
				<a href="portfolio/${id}?_method=DELETE"><s:text name="label.delete" /></a>
			</td>
		</tr>
	</s:iterator>
</table>

<p>
	<a href="portfolio/new"><s:text name="title.portfolio-new" /></a>
</p>