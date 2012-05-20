<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<tiles:importAttribute name="title" />

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title><s:text name="%{'title.' + #attr['title']}"/></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<tiles:insertAttribute name="header" />
	<h1><s:text name="%{'title.' + #attr['title']}"/></h1>
	<tiles:insertAttribute name="content" />
</body>
</html>