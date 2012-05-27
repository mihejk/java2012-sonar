<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table>
	<tr>
		<th><s:text name="model.portfolio.name" /></th>
		<th><s:text name="label.actions" /></th>
	</tr>
	<s:iterator value="portfolioList">
		<tr>
			<td>${name}</td>
			<td>
				<a href="<%=request.getContextPath() %>/statistics!exactVar95?portfolio=${id}"><s:text name="statistics.exactVar95" /></a> |
				<a href="<%=request.getContextPath() %>/statistics!exactVar99?portfolio=${id}"><s:text name="statistics.exactVar99" /></a> |
				<a href="<%=request.getContextPath() %>/statistics!historicVar95?portfolio=${id}"><s:text name="statistics.historicVar95" /></a> |
				<a href="<%=request.getContextPath() %>/statistics!historicVar99?portfolio=${id}"><s:text name="statistics.historicVar99" /></a> |
				<a href="<%=request.getContextPath() %>/statistics!hybridVar95?portfolio=${id}"><s:text name="statistics.hybridVar95" /></a> |
				<a href="<%=request.getContextPath() %>/statistics!hybridVar99?portfolio=${id}"><s:text name="statistics.hybridVar99" /></a>
			</td>
		</tr>
	</s:iterator>
</table>
