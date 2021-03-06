<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Home Page</title>
<LINK REL="StyleSheet" HREF="demoCSS.css" TYPE="text/css">
</head>
<body>
<f:view>
<div id="logo"><IMG src="logo1.JPG" height="80" ></div>
<div id="header" ><marquee><FONT size="3" color="blue"> Myshopee Customer Loyalty Program </FONT></marquee></div>
<%@include file="head.html" %>
<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
<h:form id="centerPane">
<h4 align="right">
<h:commandLink action="#{myshopee_LoginBean.logout}">
			<h:outputText value="Logout"></h:outputText>
		</h:commandLink>
</h4>
<h4 align="left">
Hi,<%=session.getAttribute("userName")%> 
</h4>
<center>
<h3>Welcome <%=session.getAttribute("userName")%></h3>
<h3><span class="TagLine">What do you wish to do?</span></h3>
<br>
<table> 
	<tr><td><a href="Purchase_Product.jsp"><IMG src="purchaseproduct.jpg" height="50" width="150"></a></td>
	<td><a href="ChangePassword.jsp"><IMG src="changepassword.jpg" height="50" width="150"></a></td></tr>
	<tr><td><a href="EditProfile.jsp"><IMG src="editprofile.jpg" height="50" width="150"></a></td>
	<td><a href="MemberBillDetails.jsp"><IMG src="mytransaction.jpg" height="50" width="150"></a></td></tr>
</table><br><br>
</center>
</h:form>
<%@include file="footer.html" %>
</f:view>
</body>
</html>