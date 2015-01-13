<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Report</title>
<LINK REL="StyleSheet" HREF="demoCSS.css" TYPE="text/css">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Products that were purchased less frequently </FONT></marquee></div>
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
				<h3>Hi <%=session.getAttribute("userName")%> !!</h3>
				<center>
				<h3><span class="TagLine">Product Report</span></h3>
				<br>
				<br>
				<h:panelGrid columns="2" bgcolor="#A9A9F5">
					<h:outputLabel value="From Date">
						<span style="color: red;">*</span>
						<br>
						<span class="mand">dd-MMM-yyyy</span>
					</h:outputLabel>
					<h:inputText id="fromDate"
						value="#{myshopee_ProductReportBean.fromDate}"
						requiredMessage="Mandatory Field" required="true"
						converterMessage="Date format should be dd-MMM-yyyy">
						<f:convertDateTime pattern="dd-MMM-yyyy" />
						<f:validator validatorId="fromDateValidator" />
					</h:inputText>

					<h:outputLabel value="To Date">
						<span style="color: red;">*</span>
						<br>
						<span class="mand">dd-MMM-yyyy</span>
					</h:outputLabel>
					<h:inputText id="toDate"
						value="#{myshopee_ProductReportBean.toDate}"
						requiredMessage="Mandatory Field" required="true"
						converterMessage="Date format should be dd-MMM-yyyy">
						<f:validator validatorId="todateValidator" />
						<f:convertDateTime pattern="dd-MMM-yyyy" />
					</h:inputText>

					<h:outputLabel value="No.of Transaction">
						<span style="color: red;">*</span>
					</h:outputLabel>
					<h:inputText id="transaction"
						value="#{myshopee_ProductReportBean.noOfTransaction}">
						<f:validator validatorId="transactionvalidator" />
					</h:inputText>

					<h:outputText value="Mandatory" styleClass="mand">
						<span style="color: red;">*</span>
					</h:outputText>
					<h:panelGrid columns="2">
						<h:commandButton value="Display Product"
							action="#{myshopee_ProductReportBean.displayProducts}"
							type="submit"></h:commandButton>
						<h:commandButton value="Reset" type="reset"></h:commandButton>
					</h:panelGrid>
				</h:panelGrid> <br>
				<br>

				<h:dataTable value="#{myshopee_ProductReportBean.productList}"
					var="product" rowClasses="dataTableRowOne, dataTableRowTwo"
					headerClass="dataTableHeader">
					<h:column id="productId">
						<f:facet name="header">
							<h:outputText value="Product Id"></h:outputText>
						</f:facet>
						<h:outputText value="#{product.productId}"></h:outputText>
					</h:column>
					<h:column id="productName">
						<f:facet name="header">
							<h:outputText value="Product Name"></h:outputText>
						</f:facet>
						<h:outputText value="#{product.productName}"></h:outputText>
					</h:column>
					<h:column id="price">
						<f:facet name="header">
							<h:outputText value="Price"></h:outputText>
						</f:facet>
						<h:outputText value="#{product.pricePerUnit}"></h:outputText>
					</h:column>
					<h:column id="quantity">
						<f:facet name="header">
							<h:outputText value="Quantity"></h:outputText>
						</f:facet>
						<h:outputText value="#{product.qtyInStock}"></h:outputText>
					</h:column>
				</h:dataTable><br>
				<h:message for="fromDate" styleClass="errormessage"></h:message><br>
				<h:message for="toDate" styleClass="errormessage"></h:message><br>
				<h:outputText value="#{myshopee_ProductReportBean.message}"
					styleClass="errormessage"></h:outputText> <br>
				<a href="AdminHome.jsp">Home</a> <br>
				<br>
				</center>
			</h:form> <%@include file="footer.html"%></td>
		</tr>
	</table>
</f:view>
</body>
</html>
