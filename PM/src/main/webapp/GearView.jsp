<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gear</title>
<link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Level</th>
                <th>Slot</th>
                <th>Required Job Level</th>
                <th>Defense Rating</th>
                <th>Magic Defense Rating</th>


            </tr>
            <tr>
                <td><c:out value="${equippable.getItemName()}" /></td>
                <td><c:out value="${equippable.getItemLevel()}" /></td>
				<td><c:out value="${equippable.getSlotType()}" /></td>
				<td><c:out value="${equippable.getRequiredJobLevel()}" /></td>
    			<td><c:out value="${equippable.getDefenseRating()}" /></td>
    			<td><c:out value="${equippable.getMagicDefenseRating()}" /></td>

    		
            </tr>


            
       </table>
</body>
</html>
