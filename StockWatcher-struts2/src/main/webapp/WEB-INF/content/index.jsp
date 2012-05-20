<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title><s:text name="title.portfolio-index" /></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<ul>
		<li><a href="<%=request.getContextPath()%>/portfolios"><s:text name="title.portfolios-index" /></a></li>
	</ul>
</body>
</html>