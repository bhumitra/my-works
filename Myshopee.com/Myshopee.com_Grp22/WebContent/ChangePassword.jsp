<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="demoCSS.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<div id="logo"><IMG src="logo1.JPG" height="80"></div>
<div id="header"><marquee><FONT size="3" color="blue">
Change Password </FONT></marquee></div>
<%@include file="head.html"%>
<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
<f:view>
	<table bgcolor="#f2f2f2" width="100%">
		<tr>
			<td><h:form>
				<h4 align="right"><h:commandLink
					action="#{myshopee_LoginBean.logout}">
					<h:outputText value="Logout"></h:outputText>
				</h:commandLink></h4>
			</h:form></td>
		</tr>
		<tr>
			<td><h:form id="centerPane">
				<h3>Hi <%=session.getAttribute("userName")%> !!</h3>
				<center>
				<h3>Change Password Form</h3>
				<h:panelGrid columns="2" bgcolor="#A9A9F5">
					<h:outputLabel value="Current Password">
						<font color="red">*</font>
					</h:outputLabel>
					<h:inputSecret value="#{myshopee_LoginBean.password}"
						required="true" requiredMessage="Please enter Current password"></h:inputSecret>
					<h:outputLabel value="New Password">
						<font color="red">*</font>
					</h:outputLabel>
					<h:inputSecret value="#{myshopee_LoginBean.newPassword1}"
						required="true" requiredMessage="Please enter new password"></h:inputSecret>
					<h:outputLabel value="Confirm new password">
						<font color="red">*</font>
					</h:outputLabel>
					<h:inputSecret value="#{myshopee_LoginBean.newPassword2}"
						required="true"
						requiredMessage="Please enter confirm new password"></h:inputSecret>
					<h:outputText value="Mandatory" styleClass="mand">
						<span style="color: red;">*</span>
					</h:outputText>
					<h:panelGroup>
						<h:commandButton value="Change password"
							action="#{myshopee_LoginBean.changePassword}"></h:commandButton>
						<h:commandButton value="Reset" type="reset"></h:commandButton>
					</h:panelGroup>

				</h:panelGrid> <h:messages styleClass="errormessage"></h:messages><br>
				<h:outputText styleClass="errormessage"
					value="#{myshopee_LoginBean.message}"></h:outputText><br>
					<c:choose>
					<c:when test="${myshopee_LoginBean.role=='A'}">
					
					<a href="AdminHome.jsp">Home</a>
					
					</c:when>
					<c:otherwise>
						<a href="MemberHome.jsp">Home</a>
					</c:otherwise>
					</c:choose>
					</center>
			</h:form></td>
		</tr>
	</table>
</f:view>
<%@include file="footer.html"%>
</body>
</html>