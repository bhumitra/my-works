<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password Success</title>
<LINK REL="StyleSheet" HREF="demoCSS.css" TYPE="text/css">
</head>
<body>
<f:view>
<div id="logo"><IMG src="logo1.JPG" height="80" ></div>
<div id="header" ><marquee><FONT size="3" color="blue"> Reset Password Success </FONT></marquee></div>
<%@include file="head.html" %>

	<h:form id="centerPane" >
		<h3 align="right">
			<h:commandLink styleClass="logout" action="#{myshopee_LoginBean.logout}">
				<h:outputText value="Logout"></h:outputText>		
			</h:commandLink>
		</h3>
			
		<h5 align="left">
			Hi <%=session.getAttribute("userName")%>
		</h5>
		
		<center>
			<h4 class="success">
				The password for the username 
					<h:outputLabel value="#{myshopee_ResetPasswordBean.userName}" style="color: #800000"></h:outputLabel>
				has been reset successfully.
			</h4>
			<br><br><br><h:outputLink value="AdminHome.jsp">Home</h:outputLink>
		</center>
	</h:form>
	<%@include file="footer.html"%>
</f:view>
</body>
</html>