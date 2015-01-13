<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Bill Details</title>
<style type="text/css">
@IMPORT url("demoCSS.css");
</style>
</head>
<body>
<f:view>
	<div id="logo"><IMG align="left" src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Transaction Report </FONT></marquee></div>
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
				<h4>Hi, <%=session.getAttribute("userName")%></h4>
				<h4>You have earned <%=session.getAttribute("points")%> points</h4>
				<center><h:panelGrid columns="2" border="1">
					<h:outputText value="From Date"></h:outputText>
					<h:inputText id="fromdate"
						value="#{myshopee_TransactionReportBean.fromDate}"
						converterMessage="Invalid From date format.Please re-enter date in (dd-MMM-yyyy) format">
						<f:convertDateTime pattern="dd-MMM-yyyy" />
					</h:inputText>

					<h:outputText value="To Date"></h:outputText>
					<h:inputText id="todate"
						value="#{myshopee_TransactionReportBean.toDate}"
						converterMessage="Invalid To date format.Please re-enter date in (dd-MMM-yyyy) format"
						validator="#{myshopee_TransactionReportBean.validator}">
						<f:convertDateTime pattern="dd-MMM-yyyy" />

					</h:inputText>
					<h:outputText></h:outputText>
					<h:commandButton type="submit" value="Show Bills"
						action="#{myshopee_TransactionReportBean.displayCustomerReport}"></h:commandButton>
				</h:panelGrid><br>
				</center>
				<c:if test="${not empty myshopee_TransactionReportBean.billList}">
					<center><h:dataTable
						value="#{myshopee_TransactionReportBean.billList}" var="trans"
						headerClass="dataTableHeader"
						rowClasses="dataTableRowOne,dataTableRowTwo">
						<h:column id="column1">
							<f:facet name="header">
								<h:outputText value="Bill Id"></h:outputText>
							</f:facet>
							<h:outputText value="#{trans.billId}"></h:outputText>
						</h:column>
						<h:column id="column2">
							<f:facet name="header">
								<h:outputText value="Date of Transaction"></h:outputText>
							</f:facet>
							<h:outputText value="#{trans.dateOfTransaction}">
								<f:convertDateTime pattern="dd-MMM-yyyy" />
							</h:outputText>
						</h:column>
						<h:column id="column3">
							<f:facet name="header">
								<h:outputText value="Mode of Transaction"></h:outputText>
							</f:facet>
							<h:outputText value="#{trans.modeOfTransaction}"></h:outputText>
						</h:column>
						<h:column id="column4">
							<f:facet name="header">
								<h:outputText value="Total Amount"></h:outputText>
							</f:facet>
							<h:outputText value="#{trans.totalAmount}"></h:outputText>
						</h:column>
						<h:column id="column5">
							<f:facet name="header">
								<h:outputText value="Earned Points"></h:outputText>
							</f:facet>
							<h:outputText value="#{trans.point_earned}"></h:outputText>
						</h:column>
						<h:column id="column6">
							<f:facet name="header">
								<h:outputText value="Redeemed Points"></h:outputText>
							</f:facet>
							<h:outputText value="#{trans.point_redeemed}"></h:outputText>
						</h:column>
						<h:column id="column7">
							<f:facet name="header">
								<h:outputText value="Cash Partial"></h:outputText>
							</f:facet>
							<h:outputText value="#{trans.cash_Partial}"></h:outputText>
						</h:column>

					</h:dataTable><br>
					<br>
					</center>
					<center><h:outputText value="Bill Id"></h:outputText> <h:inputText
						id="billid" value="#{myshopee_TransactionReportBean.billId}">
					</h:inputText><br>
					<br>
					<h:commandButton type="submit" value="Show Transactions"
						action="#{myshopee_TransactionReportBean.displayCustomerTransaction}"></h:commandButton><br>
					<br>

					<h:dataTable border="1"
						value="#{myshopee_TransactionReportBean.transactionList}"
						var="transaction" headerClass="dataTableHeader"
						rowClasses="dataTableRowOne,dataTableRowTwo">
						<h:column id="column1">
							<f:facet name="header">
								<h:outputText value="Transaction Id"></h:outputText>
							</f:facet>
							<h:outputText value="#{transaction.transactionId}"></h:outputText>
						</h:column>
						<h:column id="column2">
							<f:facet name="header">
								<h:outputText value="Product name"></h:outputText>
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
					</h:dataTable></center>
				</c:if>
				<center><font color="red"><h:message for="todate"></h:message></font><br>
				<font color="red"><h:message for="fromdate"></h:message></font><br>
				<font color="red"><h:outputText
					value="#{myshopee_TransactionReportBean.message}"></h:outputText></font><br>
				<br>
				<a href="MemberHome.jsp">Home</a></center>
			</h:form> <%@include file="footer.html"%></td>
		</tr>
	</table>
</f:view>
</body>
</html>