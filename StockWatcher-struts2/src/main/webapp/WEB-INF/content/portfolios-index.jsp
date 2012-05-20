<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title><s:text name="title.portfolios-index" /></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<h1>
		<s:text name="title.portfolios-index" />
	</h1>
	<table>
		<tr>
			<th><s:text name="model.portfolio.name" /></th>
			<th><s:text name="label.actions" /></th>
		</tr>
		<s:iterator value="model">
			<tr>
				<td><s:property value="name" /></td>
				<td>
					<a href="<%=request.getContextPath() %>/portfolio/${id}"><s:text name="label.view"/></a>
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>