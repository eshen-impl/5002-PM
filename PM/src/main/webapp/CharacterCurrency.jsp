<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Character Currencies</title>
</head>
<body>
<h1>${messages.title}</h1>
<table border="1">
  <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Currency Name</th>\
    <th>Amount Owned</th>
    <th>Weekly Owned</th>
    <th>Update Amount Owned</th>
  </tr>
  <c:forEach items="${characterCurrencies}" var="characterCurrency" >
    <tr>
      <td><c:out value="${characterCurrency.getCharacter().getCharacterFirstName()}" /></td>
      <td><c:out value="${characterCurrency.getCharacter().getCharacterLastName()}" /></td>
      <td><c:out value="${characterCurrency.getCurrency.getCurrencyName()}" /></td>
      <td><c:out value="${characterCurrency.getAmountOwned()}" /></td>
      <td><c:out value="${characterCurrency.getWeeklyAmountOwned}" /></td>
      <td><a href="updateamountowned?=characterid=<c:out value="${characterCurrency.getCharacter().getCharacterId}"/>&currencyname=<c:out value="${characterCurrency.getCurrency.getCurrencyName()}"/>">Update Amount Owned</a></td>

    </tr>
  </c:forEach>
</table>
</body>
</html>

