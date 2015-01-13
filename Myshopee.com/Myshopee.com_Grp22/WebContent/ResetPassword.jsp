<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
<LINK REL="StyleSheet" HREF="demoCSS.css" TYPE="text/css">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Reset Password </FONT></marquee></div>
	<%@include file="head.html"%>
<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
	<table width="100%" bgcolor="#f2f2f2">
		<tr>
			<td><h:form>
				<h4 align="right"><a href="Logout.jsp">Logout</a></h4>
			</h:form></td>
		</tr>
		<tr>
			<td><h:form id="centerPane">
				<h3>Hi <%=session.getAttribute("userName")%> !!</h3>
				<center>
				<h3><span class="TagLine">Reset Password</span></h3>

				<h:panelGrid columns="3" bgcolor="#A9A9F5">
					<h:outputText value="User Name"></h:outputText>
					<h:inputText id="userName"
						value="#{myshopee_ResetPasswordBean.userName}" required="true"
						requiredMessage="Please Enter the UserName"></h:inputText>

				</h:panelGrid><br>
				<br>
				<h:commandButton id="resetPassword"
					action="#{myshopee_ResetPasswordBean.resetPassword}" type="submit"
					value="Reset Password"></h:commandButton> <br>
				<h:messages styleClass="errormessage"></h:messages><br>
				<br>
				<h:outputLabel value="#{myshopee_ResetPasswordBean.message}"
					styleClass="errormessage"></h:outputLabel> <br>
				<br>
				<h:outputLink value="AdminHome.jsp">Home</h:outputLink></center>
			</h:form> <%@include file="footer.html"%></td>
		</tr>
	</table>
</f:view>
</body>
</html>