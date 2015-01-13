<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Card Registration Success</title>
<style type="text/css">
@IMPORT url("demoCSS.css");
</style>
</head>
<body>
<f:view>
	<center>
	<div id="logo"><IMG align="left" src="logo1.JPG" height="80"></div>
	</center>
	<div id="header"><marquee><FONT size="3" color="blue">
	Welcome to Myshopee!! </FONT></marquee></div>
	<%@include file="head.html"%>
		<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
	<h:form id="centerPane">
		<br>
		<br>
		<br>
		<br>
		<center>Hi <h:outputText
			value="#{myshopee_CardBean.userName}"></h:outputText>
		<h2><span style="color: purple;">You are now Myshopee
		Member!</span></h2>
		<h2><span style="color: green;">Card details successfully
		added Member Id is generated <h:outputText
			value="#{myshopee_CardBean.message}" /></span></h2>
		<a href="MemberHome.jsp">Home</a></center>
	</h:form>
</f:view>
</body>
<%@include file="footer.html"%>
</html>