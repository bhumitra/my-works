<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logged Out!!</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
<div id="logo"><IMG src="logo1.JPG" height="80" ></div>
<div id="header" ><marquee><FONT size="3" color="blue"> Thanks for Visiting the Myshopee.com Portal!! </FONT></marquee></div>
<%@include file="head.html" %>
<h:form id="centerPane">
<center>
<table width="100%">
<tr>
<td height="370">Thanks for visiting the Myshopee.com Web Portal<br><br><br>
<a href="Login.jsp"><font size="+2">Login</font></a> again to access the portal. 
</td>
</tr>
</table>
</center>
</h:form>
<%@include file="footer.html" %>
</f:view>
</body>
</html>