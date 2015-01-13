<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Purchase Confirmation</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Selected Product details </FONT></marquee></div>
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
				<center>
				<h3><font color="maroon">The following items are there
				in your wish list:</font></h3>
				<br>
				<h:dataTable var="list" value="#{myshopee_PurchaseBean.wishList}"
					headerClass="dataTableHeader"
					rowClasses="dataTableRowOne,dataTableRowTwo">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="Product Name">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.productName}"></h:outputText>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="Product Price"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.pricePerUnit}"></h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Points Associated"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.associatedPoints}"></h:outputText>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="Availability"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.qtyInStock}">
							<f:converter converterId="QtyConverter" />
						</h:outputText>

					</h:column>
					<h:column id="column5">
						<f:facet name="header">
							<h:outputText value="Confirm Product"></h:outputText>
						</f:facet>
						<h:selectBooleanCheckbox id="check1" value="#{list.checked}"
							required="true" requiredMessage="Select atleast one item"></h:selectBooleanCheckbox>
					</h:column>
				</h:dataTable> <br>
				<br>
				<h:commandButton value="Continue Shopping"
					action="#{myshopee_PurchaseBean.continueShopping}"></h:commandButton>
				<br>
				<br>
				<h:message for="check1"></h:message><br>
				<h:outputText value="#{myshopee_PurchaseBean.message}"
					styleClass="errormessage"></h:outputText> <h:messages
					styleClass="errormessage"></h:messages> <br>
				<br>
				<a href="MemberHome.jsp">Home</a><br>
				</center>
			</h:form> <%@include file="footer.html"%></td>
		</tr>
	</table>
</f:view>
</body>
</html>