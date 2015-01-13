<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction Success</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css" media="screen">
</head>
<body>
<f:view>
<div id="logo"><IMG src="logo1.JPG" height="80" ></div>
<div id="header" ><marquee><FONT size="3" color="blue"> Billing Details </FONT></marquee></div>
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
<h3>Hi <%=session.getAttribute("userName")%> !!</h3>
<center>
<font color="maroon">Your transaction is successful and the details are:</font>
<br><br>
		<h:panelGrid border="2" columns="2" >
			<h:outputText value="Billing Id"></h:outputText>
			<h:outputText value="#{myshopee_BillingBean.billId}"></h:outputText>
			<h:outputText value="Bill Amount"></h:outputText>
			<h:outputText value="#{myshopee_BillingBean.totalAmount}"></h:outputText>
			<h:outputText value="Points Earned"></h:outputText>
			<h:outputText value="#{myshopee_BillingBean.earnedPoints}"></h:outputText>
			<h:outputText value="Points Redeemed"></h:outputText>
			<h:outputText value="#{myshopee_BillingBean.redeemedPoints}"></h:outputText>
		</h:panelGrid>
		<br><br>
<h:commandLink action="#{myshopee_PurchaseBean.shopAgain}">Continue Shopping</h:commandLink>
<br><br>
<h:outputText styleClass="errormessage" value="#{myshopee_PurchaseBean.message}"></h:outputText>
<br><br><br><br>
	<h:outputLink value="MemberHome.jsp" styleClass="link">Home</h:outputLink>

	</center>
	</h:form>
<%@include file="footer.html" %>
</f:view>
</body>
</html>