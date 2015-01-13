<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Product</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Add Product Success </FONT></marquee></div>
	<%@include file="head.html"%>
	
	<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
	<h:form style="background-color: #F2F2F2">
		<h4 align="right"><h:commandLink
			action="#{myshopee_LoginBean.logout}">
			<h:outputText value="Logout"></h:outputText>
		</h:commandLink></h4>
		<h3>Hi <%=session.getAttribute("userName")%> !!</h3>
		<center>
		<h3><span class="TagLine">Add Product</span></h3>

		<table cellpadding="0" cellspacing="0" width="100%" id="centerPane">

			<tr>
				<td colspan="3" align="center" height="50"><h:outputText
					value="#{myshopee_AddProductBean.message}" styleClass="success"></h:outputText>
				</td>
			</tr>
			<tr align="center">
				<td height="100" colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td height="100" colspan="3">&nbsp;</td>
			</tr>
			<tr align="center">
				<td colspan="3"><a href="AdminHome.jsp">Home</a></td>
			</tr>
		</table>
		</center>
	</h:form>
	<%@include file="footer.html"%>
</f:view>
</body>
</html>