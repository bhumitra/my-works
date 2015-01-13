<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction Report</title>
<LINK REL="StyleSheet" HREF="demoCSS.css" TYPE="text/css">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><FONT size="3" color="blue"><marquee>
	Transaction in a given period</marquee></FONT></div>
	<%@include file="head.html"%>
	<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
	<h:form id="centerPane">
		<h4 align="right"><h:commandLink
			action="#{myshopee_LoginBean.logout}">
			<h:outputText value="Logout"></h:outputText>
		</h:commandLink></h4>
		<h3 align="left">Hi, <%=session.getAttribute("userName")%></h3>
		<center><span class="TagLine">Transaction Report</span><br>

		<h:panelGrid columns="2" bgcolor="#A9A9F5">
			<h:outputLabel value="From Date">
				<span style="color: red;">*</span>
				<br>
				<font class="mand">dd-MMM-yyyy</font>
			</h:outputLabel>
			<h:inputText id="fromdate"
				value="#{myshopee_ProductTransactionBean.fromDate}" required="true"
				requiredMessage="Please Enter a from date">
				<f:validator validatorId="fromDateValidator" />
				<f:convertDateTime pattern="dd-MMM-yyyy" />
			</h:inputText>

			<h:outputLabel value="To Date">
				<span style="color: red;">*</span>
				<br>
				<font class="mand">dd-MMM-yyyy</font>
			</h:outputLabel>
			<h:inputText id="todate"
				value="#{myshopee_ProductTransactionBean.toDate}" required="true"
				requiredMessage="Please Enter a to date"
				converter="javax.faces.DateTime">
				<f:validator validatorId="todateValidator" />
				<f:convertDateTime pattern="dd-MMM-yyyy" />
			</h:inputText>

			<h:outputText value="Mandatory" styleClass="mand">
				<span style="color: red;">*</span>
			</h:outputText>
			<h:panelGrid border="1" columns="2">
				<h:commandButton value="Show Bills"
					action="#{myshopee_ProductTransactionBean.displayTransaction}"></h:commandButton>
				<h:commandButton value="Reset" type="reset"></h:commandButton>

			</h:panelGrid>
		</h:panelGrid> <br>
		<br>
		<h:dataTable border="1" rowClasses="dataTableRowOne,dataTableRowTwo"
			headerClass="dataTableHeader"
			value="#{myshopee_ProductTransactionBean.billList}" var="billList">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="Bill Id" />
				</f:facet>
				<h:commandLink value="#{billList.billId}"
					actionListener="#{myshopee_ProductTransactionBean.getBillIdValue}"></h:commandLink>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Member Id" />
				</f:facet>
				<h:outputText value="#{billList.memberId}" />
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="User Name" />
				</f:facet>
				<h:outputText value="#{billList.userName}" />
			</h:column>
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Date of Transaction" />
				</f:facet>
				<h:outputText value="#{billList.dateOfTransaction}">
					<f:convertDateTime pattern="dd-MMM-yyyy" />
				</h:outputText>
			</h:column>
			<h:column id="column5">
				<f:facet name="header">
					<h:outputText value="Mode of Transaction" />
				</f:facet>
				<h:outputText value="#{billList.modeOfTransaction}" />
			</h:column>
			<h:column id="column6">
				<f:facet name="header">
					<h:outputText value="Total Amount" />
				</f:facet>
				<h:outputText value="#{billList.totalAmount}" />
			</h:column>
			<h:column id="column7">
				<f:facet name="header">
					<h:outputText value="Earned point" />
				</f:facet>
				<h:outputText value="#{billList.point_earned}" />
			</h:column>
			<h:column id="column8">
				<f:facet name="header">
					<h:outputText value="Redeemed points" />
				</f:facet>
				<h:outputText value="#{billList.point_redeemed}" />
			</h:column>
			<h:column id="column9">
				<f:facet name="header">
					<h:outputText value="Cash Partial" />
				</f:facet>
				<h:outputText value="#{billList.cash_Partial}" />
			</h:column>
		</h:dataTable> <br>
		<br>
		<h:message for="todate" styleClass="errormessage"></h:message>
		<h:message for="fromdate" styleClass="errormessage"></h:message>
		<h:outputText styleClass="errormessage"
			value="#{myshopee_ProductTransactionBean.message}"></h:outputText><br>
		<br>
		<a href="AdminHome.jsp">Home</a></center>
	</h:form>
	<%@include file="footer.html"%>
</f:view>
</body>
</html>
