<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Member Profile Edit Form </FONT></marquee></div>
	<%@include file="head.html"%>
<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
	<table bgcolor="#F2F2F2" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><h:form>
				<h4 align="right"><h:commandLink
					action="#{myshopee_LoginBean.logout}">
					<h:outputText value="Logout"></h:outputText>
				</h:commandLink></h4>
			</h:form> <h:form style="background-color: #F2F2F2">
				<h3>Welcome <%=session.getAttribute("userName")%>!!</h3>
			</h:form>

			<center>
			<h3><span class="TagLine">Edit Profile</span></h3>
			</center>

			<h:form style="background-color: #F2F2F2">
				<table cellpadding="0" cellspacing="0" width="100%" id="centerPane">
					<tr>
						<td colspan="3" align="center" height="40"><h:panelGrid
							columns="3" style="background-color: #A9A9F5">
							<h:outputLabel value="Username :">
								<span style="color: red;"><sup>*</sup></span>
							</h:outputLabel>
							<h:inputText id="username"
								value="#{myshopee_MemberBean.userName}" required="true"
								requiredMessage="Username is Mandatory!! Please enter valid username"
								valueChangeListener="#{myshopee_MemberBean.getMemberDetails}"
								onchange="submit()"></h:inputText>
							<h:message for="username"></h:message>
						</h:panelGrid></td>
					</tr>
				</table>
			</h:form>

			<center><h:form id="centerPane">
				<br>
				<br>
				<br>
				<c:if test="${myshopee_MemberBean.memberName!=null}">
					<h:inputHidden value="#{myshopee_MemberBean.status}"></h:inputHidden>
					<h:inputHidden value="#{myshopee_MemberBean.tempString}"></h:inputHidden>
					<h:inputHidden value="#{myshopee_MemberBean.tempId}"></h:inputHidden>
					<h:panelGrid columns="2" style="background-color: #ACACAC">
						<h:outputText value="Member Name :"></h:outputText>
						<h:outputText value="#{myshopee_MemberBean.memberName}"></h:outputText>
						<h:outputText value="Gender :"></h:outputText>
						<h:outputText value="#{myshopee_MemberBean.gender}"></h:outputText>
						<h:outputText value="Date Of Birth :"></h:outputText>
						<h:outputText value="#{myshopee_MemberBean.DOB}">
							<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
						</h:outputText>
						<h:outputText value="Date Of Registration :"></h:outputText>
						<h:outputText value="#{myshopee_MemberBean.DOR}">
							<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
						</h:outputText>
						<h:outputText value="Mobile :"></h:outputText>
						<h:inputText id="mobile" value="#{myshopee_MemberBean.mobile}"
							required="true" requiredMessage="Please Enter the mobile no"
							validatorMessage="Mobile number should be 10 digits long!!"
							converterMessage="Mobile number can have only number. Any other character and spaces not allowed!!">
							<f:convertNumber pattern="#" />
							<f:validateLength minimum="10" maximum="10"></f:validateLength>
						</h:inputText>
						<h:outputText value="Email :"></h:outputText>
						<h:inputText id="mailid" value="#{myshopee_MemberBean.email}"
							required="true"
							requiredMessage="Email id is mandatory!! Please enter valid email id.">
							<f:validator validatorId="mailValid" />
						</h:inputText>
						<h:outputText value="City :"></h:outputText>
						<h:inputText id="city" value="#{myshopee_MemberBean.city}"
							required="true"
							requiredMessage="City is mandatory!! Please enter valid City name."
							validatorMessage="City name cannot exceed 30 characters!! Please enter valid City name.">
							<f:validateLength maximum="30"></f:validateLength>
						</h:inputText>
						<h:outputText></h:outputText>
						<h:panelGrid columns="2">
							<h:commandButton value="Update"
								action="#{myshopee_MemberBean.editMember}"></h:commandButton>
							<h:commandButton value="Reset" type="reset"></h:commandButton>
						</h:panelGrid>
					</h:panelGrid>
				</c:if>
				<br>
				<br>
				<br>
				<h:outputText value="#{myshopee_MemberBean.message}"
					styleClass="errormessage"></h:outputText>
				<br>
				<h:messages styleClass="errormessage"></h:messages>
				<br>
				<a href="MemberHome.jsp">Home</a>
			</h:form></center>
			</td>
		</tr>
	</table>
	<%@include file="footer.html"%>
</f:view>
</body>
</html>