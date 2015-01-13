<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Registration Form</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css">
</head>

<body>
<f:view>
<div id="logo"><IMG src="logo1.JPG" height="80"></div>
<div id="header"><marquee><FONT size="3" color="blue">
Member Registration Form </FONT></marquee></div>
<%@include file="head.html"%>
	<h:form id="centerPane">
		<center>
		<h3><span class="Tagline">Member Registration Form</span></h3>
		<h:panelGrid columns="2" styleClass="dataTableHeader">

			<h:outputText></h:outputText>
			<h:selectOneRadio id="Salutation"
				value="#{myshopee_MemberBean.gender}" required="true"
				requiredMessage="Please select Salutation">
				<f:selectItem itemLabel="Mr." itemValue="M" />
				<f:selectItem itemLabel="Mrs." itemValue="F" />
				<f:selectItem itemLabel="Ms." itemValue="f" />
			</h:selectOneRadio>
			<h:outputText>Name<font color="red">*</font>
			</h:outputText>
			<h:inputText id="Name" value="#{myshopee_MemberBean.memberName}"
				required="true" requiredMessage="Please enter Name">
				<f:validator validatorId="namevalidator" />
			</h:inputText>
			<h:outputText>Date of Birth<font color="red">*</font>
				<br>
				<font color="blue" size="-1">dd-MMM-yyyy</font>
			</h:outputText>
			<h:inputText id="dob" value="#{myshopee_MemberBean.DOB}"
				required="true" requiredMessage="Please Enter the date of birth"
				converterMessage="Invalid date format. Please reenter">
				<f:convertDateTime pattern="dd-MMM-yyyy" />
				<f:validator validatorId="dobvalidator" />
			</h:inputText>
			<h:outputText>Mobile<font color="red">*</font>
			</h:outputText>
			<h:inputText id="mobile" value="#{myshopee_MemberBean.mobile}"
				required="true" requiredMessage="Please Enter the mobile no"
				validatorMessage="Invalid mobile number. Please reenter"
				converterMessage="Only digits are allowed. Please re-enter.">
				<f:convertNumber pattern="#" />
				<f:validateLength minimum="10" maximum="10"></f:validateLength>

			</h:inputText>
			<h:outputText>Email<font color="red">*</font>
			</h:outputText>
			<h:inputText id="email" value="#{myshopee_MemberBean.email}"
				required="true" requiredMessage="Please enter email">
				<f:validator validatorId="mailValid" />
			</h:inputText>
			<h:outputText>City<font color="red">*</font>
			</h:outputText>
			<h:inputText id="city" value="#{myshopee_MemberBean.city}"
				required="true" requiredMessage="Please Enter the city name"
				validatorMessage="Invalid City name. Please re-enter.">
				<f:validateLength minimum="1" maximum="30"></f:validateLength>

			</h:inputText>
			<h:outputText value="Mandatory" styleClass="mand">
				<font color="red">*</font>
			</h:outputText>
			<h:panelGroup>
				<h:commandButton value="Register"
					action="#{myshopee_MemberBean.registerMember}"></h:commandButton>
				<h:commandButton value="Reset" type="reset"></h:commandButton>
			</h:panelGroup>
		</h:panelGrid> <br>
		<br>
		<table>
			<tr>
				<td align="left">
				<div id="centerPane"><span class="errormessage"><h:messages></h:messages></span></div>
				</td>
			</tr>
			<tr>
				<td>
				<div id="centerPane"><span class="errormessage"><h:outputText
					value="#{myshopee_MemberBean.message}"></h:outputText></span></div>
				</td>
			</tr>
		</table>
		</center>
	</h:form>
	<%@include file="footer.html"%>
</f:view>

</body>
</html>
