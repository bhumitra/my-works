<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose Payment Mode</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css" media="screen">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Payment Mode Details </FONT></marquee></div>
	<%@include file="head.html"%>
	<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
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
				<h3>You have <%=session.getAttribute("points")%> points</h3>
				<center><font color="maroon">Details of your
				Payment!</font> <h:dataTable var="list"
					value="#{myshopee_PurchaseBean.transactionList}"
					headerClass="dataTableHeader"
					rowClasses="dataTableRowOne,dataTableRowTwo" style="width: 330px;">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="Product Id">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.productId}"></h:outputText>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="Product Name">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.productName}"></h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Price Per Unit">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.pricePerUnit}"></h:outputText>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="Quantity Purchased"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.quantityPurchased}"></h:outputText>
					</h:column>
					<h:column id="column5">
						<f:facet name="header">
							<h:outputText value="Amount"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.amount}"></h:outputText>
					</h:column>
				</h:dataTable> <br>
				<br>
				<br>
				Amount Payable in Rs.: <br>
				<br>
				<span style="color: green;"> <h:outputText
					value="#{myshopee_PurchaseBean.totalAmount}"></h:outputText></span> <br>
				<br>
				<b>How would you like to pay?</b> <br>
				<br>
				<h:selectOneRadio id="mode"
					value="#{myshopee_BillingBean.modeOfTransaction}" required="true"
					requiredMessage="Select atleast One mode of payment">
					<f:selectItem itemLabel="Cash On Demand" itemValue="CT" />
					<f:selectItem itemLabel="Debit Card" itemValue="DC" />
					<f:selectItem itemLabel="Redeem Points" itemValue="RP" />
					<f:selectItem itemLabel="Partial Transaction" itemValue="PT" />
					<f:selectItem itemLabel="Credit Card" itemValue="CC" />
				</h:selectOneRadio> <br>
				<br>
				<h:commandButton value="Make Payment"
					action="#{myshopee_BillingBean.makePayment}"></h:commandButton> <br>
				<br>
				<h:message for="mode" styleClass="errormessage"></h:message><br>
				<h:outputText styleClass="errormessage"
					value="#{myshopee_BillingBean.message}"></h:outputText> <br>
				<br>
				<a href="MemberHome.jsp">Home</a> <br>
				<br>
				<br>

				</center>
			</h:form></td>
		</tr>
	</table>
	<%@include file="footer.html"%>
</f:view>
</body>
</html>