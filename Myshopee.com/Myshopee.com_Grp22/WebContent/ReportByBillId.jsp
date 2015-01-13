<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	Transaction for a particular Bill </FONT></marquee></div>
	<%@include file="head.html"%><br>
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
				<h3>Hi <%=session.getAttribute("userName")%> !!</h3>
				<center><h:dataTable
					rowClasses="dataTableRowOne,dataTableRowTwo"
					headerClass="dataTableHeader"
					value="#{myshopee_ProductTransactionBean.transactionList}"
					var="transaction">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="Transaction Id"></h:outputText>
						</f:facet>
						<h:outputText value="#{transaction.transactionId}"></h:outputText>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="Product Name"></h:outputText>
						</f:facet>
						<h:outputText value="#{transaction.productName}"></h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Amount"></h:outputText>
						</f:facet>
						<h:outputText value="#{transaction.amount}"></h:outputText>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="Quantity Purchased"></h:outputText>
						</f:facet>
						<h:outputText value="#{transaction.quantityPurchased}"></h:outputText>
					</h:column>
				</h:dataTable> <br>
				<br>
				<a href="AdminTransactionReport.jsp">Back</a></center>
			</h:form> <%@include file="footer.html"%></td>
		</tr>
	</table>
</f:view>
</body>
</html>