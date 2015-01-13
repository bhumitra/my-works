<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Partial Transaction</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Billing Details </FONT></marquee></div>
	<%@include file="head.html"%>
	<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
	<table width="100%" bgcolor="#f2f2f2">
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
				<h3>Hi <%=session.getAttribute("userName")%> !! <br>
				You have <%=session.getAttribute("points")%> points.</h3>
				<center><br>
				Amount payable is Rs. : <h:outputText
					value="#{myshopee_BillingBean.totalAmount}"></h:outputText> <br>
				Minimum Amount payable is Rs. : <h:outputText
					value="#{(myshopee_BillingBean.totalAmount)/2}"></h:outputText> <br>
				<br>
				<h:panelGrid columns="2" border="1">
					<h:outputText value="Enter the Amount"></h:outputText>
					<h:inputText value="#{myshopee_BillingBean.cash_partial}"
						onchange="submit()"
						valueChangeListener="#{myshopee_BillingBean.calculatePointsNeeded}"></h:inputText>
					<h:outputText value="Points Needed"></h:outputText>
					<h:outputText value="#{myshopee_BillingBean.redeemedPoints}"></h:outputText>
					<h:commandButton value="Proceed"
						action="#{myshopee_BillingBean.paymentByPartial_Temp}"></h:commandButton>
				</h:panelGrid> <br>
				<br>
				<c:if test="${myshopee_BillingBean.billId > 0}">
					<h:panelGrid border="1" columns="2"
						style="width: 330px; background-color: #8899ee;">
						<h:outputText value="Billing Id"></h:outputText>
						<h:outputText value="#{myshopee_BillingBean.billId}"></h:outputText>
						<h:outputText value="Partial Cash Paid"></h:outputText>
						<h:outputText value="#{myshopee_BillingBean.cash_partial}"></h:outputText>
						<h:outputText value="Points Earned"></h:outputText>
						<h:outputText value="#{myshopee_BillingBean.earnedPoints}"></h:outputText>
						<h:outputText value="Points Redeemed"></h:outputText>
						<h:outputText value="#{myshopee_BillingBean.redeemedPoints}"></h:outputText>
					</h:panelGrid>
					<h2><b>Congratulations your transaction is successful!</b></h2>
					<br>
					<br>
					<h:commandLink action="#{myshopee_PurchaseBean.shopAgain}">Continue Shopping</h:commandLink>
				</c:if> <br>
				<br>
				<h:outputText styleClass="errormessage"
					value="#{myshopee_BillingBean.message}"></h:outputText> <br>
				<h:outputLink value="MemberHome.jsp" styleClass="link">Home</h:outputLink>
				<br>
				</center>
			</h:form> <%@include file="footer.html"%></td>
		</tr>
	</table>
</f:view>
</body>
</html>