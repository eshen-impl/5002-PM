<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Displaying inventory items</title>
<link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
	<form action="inventory" method="post">
		<h1>Display the inventory for the character</h1>
		<p>
			<label for="characterId">characterId</label>
			<input id="characterId" name="characterId" value="${fn:escapeXml(param.characterId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.result}</b></span>
		</p>
	</form>
	<br/>
	<h1>Matching Inventory</h1>
        <table border="1">
            <tr>
                <th>character First Name</th>
                <th>character Last Name</th>
                <th>Item Name</th>
                <th>Customization Item Color</th>
                <th>Quantity</th>
                <th>Update Inventory</th>
            </tr>
            <c:forEach items="${inventoryDisplays}" var="item">
               <tr>
                   <td><c:out value="${item.getCharacter().getCharacterFirstName()}" /></td>
                   <td><c:out value="${item.getCharacter().getCharacterLastName()}" /></td>
                   <td><a href="item?itemId=<c:out value='${item.getItem().getItemId()}'/>"><c:out value="${item.getItem().getItemName()}"/></a></td>
                   <td><c:out value="${item.getCustomization().getColor()}" /></td>
                   <td><c:out value="${item.getQuantity()}" /></td>
                  <td><a href="inventoryupdate?characterId=${item.getCharacter().getCharacterId()}&slotId=${item.getSlotId()}">Update</a></td>
              </tr>
           </c:forEach>
       </table>
</body>
</html>
