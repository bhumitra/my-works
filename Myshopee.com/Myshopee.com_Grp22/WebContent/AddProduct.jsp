<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Product</title>
<link href="demoCSS.css" rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Add Product </FONT></marquee></div>
	<%@include file="head.html"%>
	
	<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
	<table cellpadding="0" cellspacing="0" width="100%" bgcolor="#f2f2f2">
		<tr>
			<td width="100%"><h:form>
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
				<h3><span class="TagLine">Add Product</span></h3>
				<br>
				<table cellpadding="0" cellspacing="0" width="100%" id="centerPane">
					<tr>
						<td colspan="3" align="center"><h:panelGrid columns="2"
							bgcolor="#A9A9F5">
							<h:outputLabel value="Product Name :">
								<span style="color: red">*</span>
							</h:outputLabel>
							<h:inputText id="prodName"
								value="#{myshopee_AddProductBean.productName}"
								requiredMessage="Product name is Mandatory!! Please enter a product name"
								validatorMessage="Product name cannot exceed 30 chars!!"
								required="true">
								<f:validateLength maximum="30"></f:validateLength>
							</h:inputText>
							<h:outputLabel value="Product Price :">
								<span style="color: red">*</span>
							</h:outputLabel>
							<h:inputText id="price" value="#{myshopee_AddProductBean.price}"
								requiredMessage="Product Price is mandatory!! Please enter a product price."
								required="true">
							</h:inputText>
							<h:outputLabel value="Product Category :">
								<span style="color: red">*</span>
							</h:outputLabel>
							<h:selectOneMenu id="catg"
								value="#{myshopee_AddProductBean.category}"
								requiredMessage="Product Category is mandatory!! Please select a product category."
								required="true">
								<f:selectItem itemLabel="-Select-" itemValue="0" />
								<f:selectItems value="#{myshopee_AddProductBean.categorylist}" />
							</h:selectOneMenu>
							<h:outputLabel value="Product Stock :">
								<span style="color: red">*</span>
							</h:outputLabel>
							<h:inputText id="stock"
								value="#{myshopee_AddProductBean.quantityInStock}"
								required="true"
								requiredMessage="Product Stock is mandatory!! Please enter a product stock.">
							</h:inputText>
							<h:outputLabel>
								<span style="color: red">*</span>
								<span class="mand">Mandatory</span>
							</h:outputLabel>
							<h:panelGrid columns="2">
								<h:commandButton value="Add Product"
									action="#{myshopee_AddProductBean.addProduct}"></h:commandButton>
								<h:commandButton value="Reset" type="reset"></h:commandButton>
							</h:panelGrid>
						</h:panelGrid></td>
					</tr>
					<tr align="center">
						<td height="50" colspan="3"><h:outputText
							value="#{myshopee_AddProductBean.message}"
							styleClass="errormessage"></h:outputText> <br>
						<h:messages styleClass="errormessage"></h:messages></td>
					</tr>
					<tr>
						<td height="50" colspan="3">&nbsp;</td>
					</tr>
					<tr align="center">
						<td colspan="3"><a href="AdminHome.jsp">Home</a></td>
					</tr>
				</table>
				</center>
			</h:form></td>
		</tr>
	</table>
	<%@include file="footer.html"%>
</f:view>
</body>
</html>