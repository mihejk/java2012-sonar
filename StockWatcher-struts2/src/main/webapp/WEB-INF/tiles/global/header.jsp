<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<p>
	<a href="<%=request.getContextPath()%>/portfolio"><s:text name="title.portfolio-index" /></a> |
	<a href="<%=request.getContextPath()%>/stock"><s:text name="title.stock-index" /></a> |
	<a href="<%=request.getContextPath()%>/statistics"><s:text name="title.statistics-index" /></a>
</p>
