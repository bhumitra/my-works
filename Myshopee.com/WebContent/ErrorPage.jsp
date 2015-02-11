<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="demoCSS.css" rel="stylesheet">

<script type="text/javascript">
document.oncontextmenu=new Function("return false");
</script>


</head>
<body onload="javascript:history.go(1)">

<f:view>
<div id="logo"><IMG src="logo1.JPG" height="80"></div>
<div id="header"><marquee><FONT size="3" color="blue">
Error Page </FONT></marquee></div>
<%@include file="head.html"%>
    

<center id="centerPane">
	<h:outputText styleClass="errormessage" value="!!!!!!!!!Currently You are not logged in!!!!!!!!!!!"></h:outputText>
<br><br>

<h3> <a href="Login.jsp"> Login to continue</a></h3>

</center>

<br><br><br><br><br><br>
<center id="footer">
	<h:outputText value="©Copyright 2011.All Rights Reserved by"></h:outputText>
	<a href="Login.jsp">Myshopee.com </a>
	</center>
</f:view>
</body>
</html>