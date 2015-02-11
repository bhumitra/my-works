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
<div id="logo"><IMG src="logo1.JPG" height="80" ></div>
<br>
<div id="header" ><marquee><FONT size="3" color="blue"> Myshopee Customer Loyalty Program </FONT></marquee></div>
<%@include file="head.html" %>
<h:form id="centerPane">
<center>
<h3>
<span class="TagLine">Login</span>
</h3>
<h:panelGrid columns="2" bgcolor="#A9A9F5">
				 <h:outputLabel value="UserName"><span style="color:red;">*</span></h:outputLabel>
                  <h:inputText id="username" value="#{myshopee_LoginBean.userName}" required="true" requiredMessage="Please Enter username">
                  <f:validator validatorId="usernamevalidator"/>
                  </h:inputText>
                  
                  <h:outputLabel value="Password"><span style="color:red;">*</span></h:outputLabel>
                  <h:inputSecret id="password" value="#{myshopee_LoginBean.password}" required="true" requiredMessage="Please Enter password" validatorMessage="Invalid Password">
                  <f:validator validatorId="passwordvalidator"/>
                  </h:inputSecret>
                  
                 <h:outputText value="Mandatory" style="color:blue;" styleClass="mand"><span style="color:red;">*</span></h:outputText> 
                  <h:panelGrid columns="2" >
       			  <h:commandButton value="Login" action="#{myshopee_LoginBean.checkLoginDetails}"></h:commandButton>
        		  <h:commandButton value="Reset"></h:commandButton>
                  </h:panelGrid>
</h:panelGrid>
       <div id="centerPane"> <a href="MemberRegistration.jsp">New User ?</a> | <a href="ConfirmRegistration.jsp">Confirm Registration</a></div>
        <br>
        <h:messages styleClass="errormessage"></h:messages>
        <br>
        <h:outputText value="#{myshopee_LoginBean.message}" styleClass="errormessage"></h:outputText>
<br>
<br>
<br>
</center>
</h:form>
<%@include file="footer.html" %>
</f:view>
</body>
</html>