<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Membership Expiry Details</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Edit Product </FONT></marquee></div>
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
				<h4 align="left">Hi,<%=session.getAttribute("userName")%></h4>
				<center>
				<h3><span class="TagLine">Membership Expiry Report</span></h3>

				<h:panelGrid columns="2" styleClass="dataTableHeader">
					<h:outputText>Report for month<font color="red">
						* </font>
						<br>
						<font class="mand">MMM-yyyy</font>
					</h:outputText>
					<h:inputText id="date" value="#{myshopee_MemberExpiryBean.date1}"
						required="true" requiredMessage="Please enter month and year"
						converterMessage="Invalid date format. Please re-enter">
						<f:convertDateTime pattern="MMM-yyyy" />
						<f:validator validatorId="datevalidator" />
					</h:inputText>

					<h:outputText styleClass="mand" value="Mandatory">
						<font color="red">*</font>
					</h:outputText>
					<h:panelGroup>
						<h:commandButton value="Display"
							action="#{myshopee_MemberExpiryBean.displayMembers}"
							type="submit"></h:commandButton>
						<h:commandButton value="Reset" type="reset"></h:commandButton>
					</h:panelGroup>
				</h:panelGrid> <br>
				<div id="centerPane"><span class="errormessage"><h:messages></h:messages></span></div>
				<br>
				<br>
				<c:if test="${not empty myshopee_MemberExpiryBean.memberList}">
					<h:dataTable border="1"
						rowClasses="dataTableRowOne, dataTableRowTwo"
						value="#{myshopee_MemberExpiryBean.memberList}" var="list"
						headerClass="dataTableHeader">
						<h:column id="CardNo">
							<f:facet name="header">
								<h:outputText value="Card No"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.cardno}"></h:outputText>
						</h:column>
						<h:column id="UserName">
							<f:facet name="header">
								<h:outputText value="User Name"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.userName}"></h:outputText>
						</h:column>
						<h:column id="MemberId">
							<f:facet name="header">
								<h:outputText value="Member Id"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.memberId}"></h:outputText>
						</h:column>
						<h:column id="ExpiryDate">
							<f:facet name="header">
								<h:outputText value="Expiry Date"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.expiryDate}">
								<f:convertDateTime pattern="dd-MMM-yyyy" />
							</h:outputText>
						</h:column>
						<h:column id="EarnedPoints">
							<f:facet name="header">
								<h:outputText value="Earned points"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.earnedPoints}"></h:outputText>
						</h:column>
						<h:column id="MemberType">
							<f:facet name="header">
								<h:outputText value="Member Type"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.memberType}"></h:outputText>
						</h:column>
						<h:column id="Status">
							<f:facet name="header">
								<h:outputText value="Status"></h:outputText>
							</f:facet>
							<h:outputText value="To Be Expired"></h:outputText>
						</h:column>
					</h:dataTable>
				</c:if> <br>
				<h4><font color="red"> <h:outputText
					value="#{myshopee_MemberExpiryBean.message}"></h:outputText></font></h4>
				<a href="AdminHome.jsp">Home</a></center>
			</h:form> <%@include file="footer.html"%></td>
		</tr>
	</table>
</f:view>
</body>
</html>
