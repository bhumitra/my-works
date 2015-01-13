<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
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
	<div id="header"><marquee><FONT size="3" color="blue">
	Member Confirmation </FONT></marquee><br>
	</div>
	<%@include file="head.html"%><br>
	<h:form id="centerPane">
		<center><span class="TagLine">Member Confirmation</span> <h:panelGrid
			columns="2" bgcolor="#A9A9F5">
			<h:outputLabel value="Request Id">
				<span style="color: red;">*</span>
			</h:outputLabel>
			<h:inputText id="requestid" value="#{myshopee_LoginBean.requestId}"
				required="true" requiredMessage="Please Enter a Request Id"></h:inputText>

			<h:outputLabel value="Confirmation String">
				<span style="color: red;">*</span>
			</h:outputLabel>
			<h:inputText id="ConfirmationString"
				value="#{myshopee_LoginBean.tempString}" required="true"
				requiredMessage="Please Enter a Confirmation String"></h:inputText>
			<h:outputText value="Mandatory" styleClass="mand">
				<span style="color: red;">*</span>
			</h:outputText>
			<h:panelGrid columns="2">

				<h:commandButton action="#{myshopee_LoginBean.confirmRegistration}"
					value="Confirm Registration"></h:commandButton>
				<h:commandButton value="Reset" type="reset"></h:commandButton>
			</h:panelGrid>
		</h:panelGrid> <h:message for="requestid" styleClass="errormessage"></h:message><br>
		<h:message for="ConfirmationString" styleClass="errormessage"></h:message><br>
		<h:outputText styleClass="errormessage"
			value="#{myshopee_LoginBean.message}"></h:outputText> <br>
		<br>
		</center>
	</h:form>
	<%@include file="footer.html"%>
</f:view>
</body>
</html>
