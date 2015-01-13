<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Purchase Product</title>
<style type="text/css">
@IMPORT url("demoCSS.css");
</style>
</head>
<body>

<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80"></div>
	<div id="header"><marquee><FONT size="3" color="blue">
	Purchase Product </FONT></marquee></div>
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
				<h3>Hi <%=session.getAttribute("userName")%> !! <br>
				You have <%=session.getAttribute("points")%> points.</h3>
				<center>
				<h3><span class="TagLine">Purchase Product</span></h3>
				<h:panelGrid columns="2" style=" background-color: #A9A9F5;">
					<h:panelGrid columns="2" style=" background-color: #A9A9F5;">
						<h:outputText value="Category"></h:outputText>
						<h:outputText value="*" style="color:red"></h:outputText>
					</h:panelGrid>
					<h:selectOneMenu id="category"
						value="#{myshopee_PurchaseBean.productCategory}"
						valueChangeListener="#{myshopee_PurchaseBean.getProductsByCategory}"
						onchange="submit()" required="true"
						requiredMessage="Category is Mandatory">
						<f:selectItem itemLabel="-Select-" />
						<f:selectItem itemLabel="Household" itemValue="H" />
						<f:selectItem itemLabel="Electronics" itemValue="E" />
						<f:selectItem itemLabel="Books" itemValue="B" />
						<f:selectItem itemLabel="Accessories" itemValue="A" />
						<f:selectItem itemLabel="FoodItems" itemValue="F" />
					</h:selectOneMenu>
				</h:panelGrid> <c:if test="${not empty myshopee_PurchaseBean.productList}">
					<h:dataTable border="1"
						value="#{myshopee_PurchaseBean.productList}" var="list"
						headerClass="dataTableHeader"
						rowClasses="dataTableRowOne,dataTableRowTwo">
						<h:column id="column1">
							<f:facet name="header">
								<h:outputText value="Product Name">
								</h:outputText>
							</f:facet>
							<h:outputText value="#{list.productName}"></h:outputText>
						</h:column>

						<h:column id="column2">
							<f:facet name="header">
								<h:outputText value="Product Price"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.pricePerUnit}"></h:outputText>
						</h:column>
						<h:column id="column3">
							<f:facet name="header">
								<h:outputText value="Points Associated"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.associatedPoints}"></h:outputText>
						</h:column>
						<h:column id="column4">
							<f:facet name="header">
								<h:outputText value="Availability"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.qtyInStock}">
								<f:converter converterId="QtyConverter" />
							</h:outputText>
						</h:column>
						<h:column id="column5">
							<f:facet name="header">
								<h:outputText value="Select your product"></h:outputText>
							</f:facet>
							<h:selectBooleanCheckbox id="check" value="#{list.checked}"
								required="true" requiredMessage="Select at least one item"></h:selectBooleanCheckbox>
						</h:column>
					</h:dataTable>
					<h:commandButton value="Add to Wish List"
						action="#{myshopee_PurchaseBean.addProductToWishList}"
						></h:commandButton>
					<h:commandButton value="Purchase Product"
						action="#{myshopee_PurchaseBean.purchaseProduct}"></h:commandButton>
				</c:if> <c:if test="${not empty myshopee_PurchaseBean.wishList}">
					<br>
					<font color="maroon">Your products have been added to
					wishList</font>
					<br />
					<br />
					<font color="maroon">Your wishList is:</font>
					<br />
					<br />
					<h:dataTable var="list1" border="1"
						value="#{myshopee_PurchaseBean.wishList}"
						headerClass="dataTableHeader"
						style="width: 330px; background-color: #A9A9F5;"
						rowClasses="dataTableRowOne,dataTableRowTwo">
						<h:column>
							<f:facet name="header">
								<h:outputText value="Product Name" />
							</f:facet>
							<h:outputText value="#{list1.productName}"></h:outputText>
						</h:column>
					</h:dataTable>
				</c:if><br>
				<br>
				<h:outputText value="#{myshopee_PurchaseBean.message}"
					styleClass="errormessage"></h:outputText><br>
				<h:messages styleClass="errormessage"></h:messages> <br>
				<br>
				<br>
				<a href="MemberHome.jsp">Home</a></center>
			</h:form> <%@include file="footer.html"%></td>
		</tr>
	</table>
</f:view>

</body>
</html>