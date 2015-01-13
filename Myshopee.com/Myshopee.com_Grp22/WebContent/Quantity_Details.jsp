<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quantity Details</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css" media="screen">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Purchase Product Quantity Details</FONT></marquee></div>
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
				<h3><font color="maroon">Please enter Quantity !</font></h3>
				<h:dataTable var="list"
					value="#{myshopee_PurchaseBean.transactionList}"
					headerClass="dataTableHeader"
					rowClasses="dataTableRowOne,dataTableRowTwo">
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
							<h:outputText value="Product Price">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.pricePerUnit}"></h:outputText>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="Enter Quantity"></h:outputText>
						</f:facet>
						<h:inputText id="qty" value="#{list.quantityPurchased}"
							required="true" requiredMessage="Quantity is Mandatory"
							converterMessage="Enter numeric value only"></h:inputText>
					</h:column>
				</h:dataTable> <br>
				<br>
				<h:commandButton value="Check Stock And Proceed"
					action="#{myshopee_PurchaseBean.checkStock}" ></h:commandButton>
				<br>
				<br>
				<h:message for="qty" styleClass="errormessage"></h:message><br>
				<h:outputText styleClass="errormessage"
					value="#{myshopee_PurchaseBean.message}"></h:outputText> <br>
				<br>
				<br>
				<br>
				<a href="MemberHome.jsp">Home</a></center>
			</h:form> <%@include file="footer.html"%></td>
		</tr>
	</table>
</f:view>
</body>
</html>