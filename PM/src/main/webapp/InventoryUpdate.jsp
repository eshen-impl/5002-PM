<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update quantity in inventory</title>
<link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
	<h1>Update Inventory Quantity</h1>
	<form action="inventoryupdate" method="post">
		<p>
			<label for="characterId">Character ID:</label>
			<input id="characterId" name="characterId" type="text" value="${param.characterId}" readonly>
		</p>
		<p>
			<label for="slotId">Slot ID:</label>
			<input id="slotId" name="slotId" type="text" value="${param.slotId}" readonly>
		</p>
		<p>
		
		   <label for="newQuantity">New Quantity:</label>
           <input id="newQuantity" name="newQuantity" type="text">
        <p>
        <p>
			<input type="submit" value = "Update Quantity">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.result}</b></span>
	</p>
</body>
</html>