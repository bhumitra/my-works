<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Product</title>
<LINK REL="StyleSheet" HREF="demoCSS.css" TYPE="text/css">
</head>
<body>
<f:view>
	<div id="logo"><IMG src="logo1.JPG" height="80" ></div>
	<div id="header" ><marquee><FONT size="3" color="blue"> Edit Product Success </FONT></marquee></div>
	<%@include file="head.html" %>
	<c:choose>
		<c:when test="${sessionScope.userName==null}">
		<jsp:forward page="ErrorPage.jsp"></jsp:forward>
			
		</c:when>
		</c:choose>
	<h:form id="centerPane" >
		<h4 align="right">
<h:commandLink action="#{myshopee_LoginBean.logout}">
			<h:outputText value="Logout"></h:outputText>
		</h:commandLink>
</h4>
<h3>Hi <%=session.getAttribute("userName")%> !!</h3>
		
		<center>
			<h4 class="success">	
				Details of the product
					<h:outputLabel value="#{myshopee_EditProductBean.productName}" style="color: #800000"></h:outputLabel>
				has been successfully modified.
			</h4>
			<br><br><br><h:outputLink value="AdminHome.jsp">Home</h:outputLink>
		</center>
	 </h:form>
	 <br><br>
	 <%@include file="footer.html" %>
</f:view>
</body>
</html>