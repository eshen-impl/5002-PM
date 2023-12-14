<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Character Slot Update</title>
<link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
	<h1>${messages.title}</h1>
	
	<div class="container">
		<div class="current">
			<h2>Current Equipment: <br>${messages.current}</h2>

		    <table border="1">
		         <tr>
		             <th>Bonus for Attribute</th>
		             <th>Bonus Value</th>
		         </tr>
		 		 <c:forEach items="${currentBonusList}" var="bonus" >
		             <tr>
		                 <td><c:out value="${bonus.getAttribute()}" /></td>
		                 <td><c:out value="${bonus.getBonusValue()}" /></td>
		             </tr>
		         </c:forEach>
		    </table>
		 </div>    
		 <div class="option">  
		    <h2>Choose a new equipment:</h2>
			<form action="characterslotupdate" method="post">
		   
				<select name="newEquipment">
				<c:forEach items="${availableEquipments}" var="availEquip" >
				  <option value="${availEquip!=null?availEquip.getItemId():''}" ${availEquip != null && availEquip.getItemId() eq newItemId ? 'selected' : ''}>${availEquip!=null?availEquip.getItemName():''}
				  </option>
				</c:forEach>
				</select>
		
				<br/><br/><br/>
				<input type="submit" name="cancel" value="Cancel" >
				<input type="submit" name="try" value="Try on" >
				<input type="submit" name="confirm" value="Confirm" >
				<br/><br/><br/>
				<span id="successMessage"><b>${messages.success}</b></span>
		
			</form>
		</div> 
		<div class="new">  	
			<c:if test="${newBonusList != null}">
			<h2>New Equipment: <br>${messages.newEq}</h2>
			      <table border="1">
			          <tr>
			              <th>Bonus for Attribute</th>
			              <th>Bonus Value</th>
			          </tr>
				 <c:forEach items="${newBonusList}" var="newBonus" >
			              <tr>
			                  <td><c:out value="${newBonus.getAttribute()}" /></td>
			                  <td><c:out value="${newBonus.getBonusValue()}" /></td>
			              </tr>
			          </c:forEach>
			      </table>
			</c:if>
		</div> 
		<div class="attr">  		
			<c:if test="${characterAttributes != null}">
			<h2>Character Attributes: </h2>
		       <table border="1">
		           <tr>
		               <th>Attribute</th>
		               <th>Value</th>
		               <th>Effect</th>
		           </tr>
				 <c:forEach items="${characterAttributes}" var="characterAttribute" >
		               <tr>
		                   <td><c:out value="${characterAttribute.getAttributes()}" /></td>
		                   <td><c:out value="${characterAttribute.getAttributeValue()}" /></td>
		                   <td class="number"><c:out value="${effectMapping.get(characterAttribute.getAttributes())}" /></td>
		               </tr>
		           </c:forEach>
		       </table>
		    </c:if>
		 </div> 
	</div>
	<script>	
		const numbers = document.querySelectorAll('.number');
		numbers.forEach(number => {
			const value = parseInt(number.textContent);
		    if (value < 0) {
		        number.classList.add('negative');
		    } else if (value > 0){
		    	number.textContent = "+" + number.textContent;
		    }
		});
	</script>
       
</body>
</html>
