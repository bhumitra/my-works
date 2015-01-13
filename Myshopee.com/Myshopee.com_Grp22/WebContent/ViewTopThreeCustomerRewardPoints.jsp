<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Report</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Top three Customers having Highest Reward Points </FONT></marquee></div>
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
				<h3><span class="TagLine"><font color="maroon">Customers
				with Highest Reward Points</font></span></h3>
				<h:dataTable border="1"
					rowClasses="dataTableRowOne, dataTableRowTwo"
					headerClass="dataTableHeader"
					value="#{myshopee_RewardsPointsReportBean.memberList}" var="var">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="Member Id"></h:outputText>
						</f:facet>
						<h:outputText value="#{var.memberId}"></h:outputText>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="User Name"></h:outputText>
						</f:facet>
						<h:outputText value="#{var.userName}"></h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Points"></h:outputText>
						</f:facet>
						<h:outputText value="#{var.earnedPoints}"></h:outputText>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="Member Type"></h:outputText>
						</f:facet>
						<h:outputText value="#{var.memberType}"></h:outputText>
					</h:column>
					<h:column id="column5">
						<f:facet name="header">
							<h:outputText value="Expiry Date"></h:outputText>
						</f:facet>
						<h:outputText value="#{var.expiryDate}">
							<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
						</h:outputText>
					</h:column>
				</h:dataTable> <h:outputText value="#{myshopee_RewardsPointsReportBean.message}"
					style="errormessage"></h:outputText> <br>
				<h:messages style="errormessage"></h:messages>

				<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td height="60" colspan="3">&nbsp;</td>
					</tr>
					<tr align="center">
						<td colspan="3"><a href="AdminHome.jsp">Home</a></td>
					</tr>
				</table>
				</center>
			</h:form> <jsp:directive.include file="footer.html" /></td>
		</tr>
	</table>
</f:view>
</body>
</html>