<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Registration Success</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
<div id="logo"><IMG src="logo1.JPG" height="80" ></div>
	<div id="header" ><marquee><FONT size="3" color="blue"> Registration Stage One Completed </FONT></marquee></div>
	<%@include file="head.html" %><br>
	<h:form id="centerPane">
		<center>
			<h:outputLabel
			value="#{myshopee_MemberBean.gender}">
			<f:converter converterId="salutationconvertor"/>
			</h:outputLabel>
			
			<h:outputLabel
			value="#{myshopee_MemberBean.memberName}"></h:outputLabel>
		<br><br><br><br>
		<span class="success">Registration
		has been successfully completed</span><br>
		<span class="success"><h:outputLabel value="#{myshopee_MemberBean.message}"></h:outputLabel></span>
		<br><br><br>
		<span class="redirect">Please check your mail for further details</span>
		<br><br>
		<br>
		<a href="ConfirmRegistration.jsp">Confirm Registration</a>
		</center>
	</h:form>
<%@include file="footer.html" %><br>
</f:view>
</body>
</html>

