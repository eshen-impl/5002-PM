<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Character</title>
<link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
	
	<h1>Character</h1>
        <table border="1">
            <tr>
                <th>Character ID</th>
                <th>Account Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Attribute</th>
                <th>Slot</th>
                <th>Job</th>
                <th>Inventory</th>
                <th>Currency</th>
            </tr>
            
                <tr>
                    <td><c:out value="${character.getCharacterId()}" /></td>
                    <td><c:out value="${character.getAccount().getEmailAddress()}" /></td>
                    <td><c:out value="${character.getCharacterFirstName()}" /></td>
                    <td><c:out value="${character.getCharacterLastName()}" /></td>
                   
                    <td><a href="characterattribute?characterId=<c:out value="${character.getCharacterId()}"/>">Attribute</a></td>
                    <td><a href="characterslot?characterId=<c:out value="${character.getCharacterId()}"/>">Slot</a></td>
                    <td><a href="characterjob?characterId=<c:out value="${character.getCharacterId()}"/>">Job</a></td>
                    <td><a href="inventory?characterId=<c:out value="${character.getCharacterId()}"/>">Inventory</a></td>
                    <td><a href="charactercurrency?characterId=<c:out value="${character.getCharacterId()}"/>">Currency</a></td>
                </tr>
           
       </table>
</body>
</html>
