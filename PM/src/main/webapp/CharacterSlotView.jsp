<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Character Equipment Slots</title>
<link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>Slot</th>
                <th>Equipment Name</th>
                <th>Color</th>
                <th>Quality</th>
                <th>Condition</th>
                <th>Made By</th>
                <th>Bonus</th>
                <th>Action</th>

            </tr>
			 <c:forEach items="${characterSlots}" var="characterSlot" >
                <tr>
                    <td><c:out value="${characterSlot.getSlotType()}" /></td>
                    <td>
	                    <a href="equippable?itemId=<c:out value="${characterSlot.getEquippedItem().getItemId()}"/>">
	                    	<c:out value="${characterSlot.getEquippedItem().getItemName()}" />
	                    </a>
                    </td>

        			<td>
            			${characterSlot.getCustomization()!= null ? characterSlot.getCustomization().getColor() : '/'}
        			</td>
        			<td>
            			${characterSlot.getCustomization()!= null ? characterSlot.getCustomization().getIsHighQuality() : '/'}
        			</td>
        			<td>
            			${characterSlot.getCustomization()!= null ? characterSlot.getCustomization().getCondition(): '/'}
        			</td>
					<c:choose>
					    <c:when test="${characterSlot.getCustomization() ne null}">
					        <td>
					            <a href="findcharacters?characterId=<c:out value="${characterSlot.getCustomization().getCharacter().getCharacterId()}"/>">
					                ${characterSlot.getCustomization().getCharacter().getCharacterFirstName()}
					            </a>
					        </td>
					    </c:when>
					    <c:otherwise>
					        <td>/</td>
					    </c:otherwise>
					</c:choose>
                    <td>
	                    <a href="equippablebonus?itemId=<c:out value="${characterSlot.getEquippedItem().getItemId()}"/>">
	                    	Bonus
	                    </a>
                    </td>
                    <td>
	                    <a href="characterslotupdate?characterId=<c:out value="${characterSlot.getCharacter().getCharacterId()}"/>&slotType=<c:out value="${characterSlot.getSlotType()}"/>">
	                    	Change
	                    </a>
                    </td>
        		
                </tr>
            </c:forEach>

            
       </table>
</body>
</html>
