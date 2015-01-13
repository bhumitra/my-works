<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<LINK REL="StyleSheet" HREF="demoCSS.css" TYPE="text/css">
</head>
<body>
<f:view>
<div id="logo"><IMG src="logo1.JPG" height="80"></div>
<div id="header" ><marquee><FONT size="3" color="blue"> Please register your card information </FONT></marquee></div>
 <%@include file="head.html" %>

<h:form id="centerPane">
    <center>
    <span class="TagLine">Please find your login credentials</span><br><br>
   <h:panelGrid columns="2" >
<h:outputText value="Your UserName:"></h:outputText>
<h:outputText value="#{myshopee_LoginBean.userName}"></h:outputText>
<h:outputText value="Your Password:"></h:outputText>
<h:outputText value="#{myshopee_LoginBean.password}"></h:outputText>

</h:panelGrid>

<h5><span style="color:red">Are you an ABC Bank customer?</span><br><br>
<a href="cardRegistration.jsp">Yes </a> | <h:commandLink value="No" action="#{myshopee_CardBean.noCardDetailsEntry}"></h:commandLink></h5>

<br>
<br>
<a href="Login.jsp">Login</a><font size="-1"> with new credentials.</font>
</center>
</h:form>
<%@include file="footer.html" %>
</f:view>
</body>
</html>
