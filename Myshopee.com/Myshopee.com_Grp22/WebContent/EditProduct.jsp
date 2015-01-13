<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Product</title>
<LINK REL="StyleSheet" HREF="demoCSS.css" TYPE="text/css">
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
				<center>
				<h4 align="left">Hi <%=session.getAttribute("userName")%></h4>

				<h3><span class="TagLine">Edit Product</span></h3>

				<h:panelGrid columns="2" bgcolor="#A9A9F5">
					<h:outputLabel value="Product Category">
						<span style="color: red;">*</span>
					</h:outputLabel>
					<h:selectOneMenu id="category"
						value="#{myshopee_EditProductBean.category}"
						valueChangeListener="#{myshopee_EditProductBean.getProductByCategory}"
						onchange="submit();" required="true"
						requiredMessage="Please Select the Product Category">
						<f:selectItem itemLabel="-Select-" itemValue="0" />
						<f:selectItem itemLabel="Electronics" itemValue="E" />
						<f:selectItem itemLabel="Books" itemValue="B" />
						<f:selectItem itemLabel="Accessories" itemValue="A" />
						<f:selectItem itemLabel="HouseHold" itemValue="H" />
						<f:selectItem itemLabel="Food" itemValue="F" />
					</h:selectOneMenu>


					<h:outputLabel value="Product">
						<span style="color: red;">*</span>
					</h:outputLabel>
					<h:selectOneMenu value="#{myshopee_EditProductBean.productId}"
						id="product" required="true"
						requiredMessage="Please Select the Product">
						<f:selectItems value="#{myshopee_EditProductBean.productList}" />
					</h:selectOneMenu>


					<h:outputText value="Mandatory" styleClass="mand">
						<span style="color: red;">*</span>
					</h:outputText>
					<h:commandButton type="submit" value="Get Details"
						action="#{myshopee_EditProductBean.getProductById}"></h:commandButton>
				</h:panelGrid> <br>
				<br>
				</center>
			</h:form> <h:form style="background-color: #F2F2F2">
				<center><br>
				<br>

				<h:panelGrid columns="2"
					style="background-color: #AAAAAA; font-size: 18px">

					<h:outputLabel value="Product Name">
						<span style="color: red;">*</span>
					</h:outputLabel>
					<h:inputText value="#{myshopee_EditProductBean.productName}"
						id="name" required="true"
						requiredMessage="Please enter the Product Name">
						<f:validateLength maximum="30"></f:validateLength>
					</h:inputText>


					<h:outputLabel value="Price Per Unit">
						<span style="color: red;">*</span>
					</h:outputLabel>
					<h:inputText value="#{myshopee_EditProductBean.price}" id="price"
						required="true" requiredMessage="Please enter the Product Price">
						<f:validator validatorId="productPriceValidator" />
					</h:inputText>


					<h:outputLabel value="Product Category">
						<span style="color: red;">*</span>
					</h:outputLabel>
					<h:selectOneMenu value="#{myshopee_EditProductBean.category}"
						id="category1" required="true"
						requiredMessage="Please enter the category">
						<f:selectItem itemLabel="Electronics" itemValue="E" />
						<f:selectItem itemLabel="Books" itemValue="B" />
						<f:selectItem itemLabel="Accessories" itemValue="A" />
						<f:selectItem itemLabel="HouseHold" itemValue="H" />
						<f:selectItem itemValue="F" itemLabel="Food" />
					</h:selectOneMenu>


					<h:outputLabel value="Quantity in Stock">
						<span style="color: red;">*</span>
					</h:outputLabel>
					<h:inputText value="#{myshopee_EditProductBean.quantityInStock}"
						id="qty" required="true"
						requiredMessage="Please enter the product Quantity"></h:inputText>


					<h:outputText value=" Mandatory"
						style="font-size: 12px; color: #0000FF">
						<span style="color: red;">*</span>
					</h:outputText>
					<h:panelGrid columns="2" cellpadding="10">
						<h:commandButton type="submit" value="Update Details"
							action="#{myshopee_EditProductBean.editProduct}"></h:commandButton>
						<h:commandButton value="Reset" type="reset"></h:commandButton>
					</h:panelGrid>
				</h:panelGrid> <br>
				<h:messages style="color: red;"></h:messages> <br>
				<br>
				<h:outputLabel value="#{myshopee_EditProductBean.message}"
					style="color: red;"></h:outputLabel> <br>
				<br>
				<h:outputLink value="AdminHome.jsp">Home</h:outputLink></center>
			</h:form></td>
		</tr>
	</table>
	<br>
	<jsp:directive.include file="footer.html" />
</f:view>
</body>
</html>