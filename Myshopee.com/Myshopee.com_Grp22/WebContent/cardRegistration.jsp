<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Card Registration</title>
<style type="text/css">
@IMPORT url("demoCSS.css");
</style>
</head>
<body>
<f:view>
	<div id="logo"><IMG align="left" src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Card Details Form </FONT></marquee></div>
	<%@include file="head.html"%>
		<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
	<table width="100%" bgcolor="#F2f2f2" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%"><h:form>
				<h4 align="right"><a href="Logout.jsp">Logout</a></h4>
			</h:form></td>
		</tr>
		<tr>
			<td><h:form id="centerPane">
				<h3>Hi <%=session.getAttribute("userName")%> !!</h3>
				<center>

				<h3><span class="TagLine">ABC Bank Card Details</span></h3>

				<h:panelGrid columns="2" style="background-color: #A083ED">
					<h:outputLabel value="Card Type">
						<span style="color: red;">*</span>
					</h:outputLabel>
					<h:selectOneRadio id="cardType"
						value="#{myshopee_CardBean.cardType}" required="true"
						requiredMessage="Card Type is mandatory.Please enter Card Type!!!!">
						<f:selectItem itemValue="CC" itemLabel="Credit Card" />
						<f:selectItem itemValue="DC" itemLabel="Debit Card" />
					</h:selectOneRadio>
					<h:outputLabel value="Card Number">
						<span style="color: red;">*</span>
						<br>
						<span class="mand">12 digit</span>
					</h:outputLabel>
					<h:panelGrid border="1" columns="3">
						<h:inputText style="width: 68px" id="cardNo1"
							value="#{myshopee_CardBean.cardNo1}" required="true"
							requiredMessage="Please enter the Card Number in all the fields"
							maxlength="4"
							validatorMessage="Each block of Card No must have 4 digits">
							<f:validateLength minimum="4" maximum="4"></f:validateLength>
						</h:inputText>
						<h:inputText style="width: 68px" id="cardNo2"
							value="#{myshopee_CardBean.cardNo2}" required="true"
							requiredMessage="Please enter the Card Number in all the fields"
							maxlength="4"
							validatorMessage="Each block of Card No must have 4 digits">
							<f:validateLength minimum="4" maximum="4"></f:validateLength>
						</h:inputText>
						<h:inputText style="width: 68px" id="cardNo3"
							value="#{myshopee_CardBean.cardNo3}" required="true"
							requiredMessage="Please enter the Card Number in all the fields"
							maxlength="4"
							validatorMessage="Each block of Card No must have 4 digits">
							<f:validateLength minimum="4" maximum="4"></f:validateLength>
						</h:inputText>
					</h:panelGrid>

					<h:outputLabel value="Card Category">
						<span style="color: red;">*</span>
					</h:outputLabel>
					<h:selectOneMenu id="cardCategory"
						value="#{myshopee_CardBean.cardCategory}" required="true"
						requiredMessage="Card Category is Mandatory.Please enter Card Category!!!! ">
						<f:selectItem itemLabel="-select-" />
						<f:selectItem itemValue="P" itemLabel="Platinum" />
						<f:selectItem itemValue="G" itemLabel="Gold" />
						<f:selectItem itemValue="S" itemLabel="Silver" />
					</h:selectOneMenu>
					<h:outputText value="Mandatory" styleClass="mand">
						<span style="color: red;">*</span>
					</h:outputText>
					<h:panelGrid columns="2">
						<h:commandButton value="Submit Details"
							action="#{myshopee_CardBean.cardDetailsEntry}"></h:commandButton>
						<h:commandButton type="reset" value="Reset"></h:commandButton>
					</h:panelGrid>
				</h:panelGrid></center>
				<center><span style="color: red;"><h:messages></h:messages><br>
				<h:outputText value="#{myshopee_CardBean.message}"></h:outputText><br>
				</span><br>
				<a href="MemberHome.jsp">Home</a></center>
			</h:form></td>
		</tr>
	</table>
	<%@include file="footer.html"%>
</f:view>
</body>
</html>