<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title><s:text name="title.portfolio-show" /></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<h1>
		<s:text name="title.portfolio-show" />
	</h1>
	<table>
		<tr>
			<th><s:text name="model.portfolio.name" /></th>
			<td><s:property value="model.name" /></td>
		</tr>
	</table>
</body>
</html>