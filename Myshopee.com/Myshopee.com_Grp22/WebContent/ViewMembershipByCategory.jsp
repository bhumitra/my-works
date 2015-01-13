<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK REL="StyleSheet" HREF="demoCSS.css" TYPE="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Report</title>
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Customer details based on membership Category </FONT></marquee></div>
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
				<h3><span class="TagLine">Report based on Membership
				Category</span></h3>

				<h:selectOneRadio
					value="#{myshopee_MemberReportBean.memberCategory}"
					valueChangeListener="#{myshopee_MemberReportBean.displayMembers}"
					onchange="submit()">
					<f:selectItem itemLabel="Gold" itemValue="Gold" />
					<f:selectItem itemLabel="Silver" itemValue="Silver" />
					<f:selectItem itemLabel="Platinum" itemValue="Platinum" />
				</h:selectOneRadio><br>
				<br>
				<br>

				<h:dataTable id="memberReport"
					value="#{myshopee_MemberReportBean.memberList}" var="rep"
					rowClasses="dataTableRowOne, dataTableRowTwo"
					headerClass="dataTableHeader">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="Member Id"></h:outputText>
						</f:facet>
						<h:outputLabel value="#{rep.memberId}"></h:outputLabel>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="User Name"></h:outputText>
						</f:facet>
						<h:outputLabel value="#{rep.userName}"></h:outputLabel>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Email"></h:outputText>
						</f:facet>
						<h:outputLabel value="#{rep.email}"></h:outputLabel>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="DOR"></h:outputText>
						</f:facet>
						<h:outputLabel value="#{rep.DOR}">
							<f:convertDateTime pattern="dd-MMM-yyyy" />
						</h:outputLabel>
					</h:column>
					<h:column id="column5">
						<f:facet name="header">
							<h:outputText value="Mobile"></h:outputText>
						</f:facet>
						<h:outputLabel value="#{rep.mobile}"></h:outputLabel>
					</h:column>
				</h:dataTable> <h:outputLabel value="#{myshopee_MemberReportBean.message}"
					styleClass="errormessage"></h:outputLabel> <br>
				<br>
				<br>
				<h:outputLink value="AdminHome.jsp">Home</h:outputLink></center>
			</h:form> <%@include file="footer.html"%></td>
		</tr>
	</table>
</f:view>
</body>
</html>