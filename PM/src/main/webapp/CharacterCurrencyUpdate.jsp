<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update Amount Owned
    </title>
    <link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
<h1>${messages.title}</h1>
<form action="charactercurrencyupdate" method="post">
    <p>

        <input type="hidden" id="characterid" name="characterId" value="${fn:escapeXml(param.characterId)}" readonly>
    </p>
    <p>
        <label for="currencyname">Currency Name</label>
        <input id="currencyname" name="currencyName" value="${fn:escapeXml(param.currencyName)}" readonly>
    </p>
    <p>
        <label for="amountowned">New Amount Owned</label>
        <input id="amountowned" name="amountowned" value="">
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/><br/>
<p>
    <span id="successMessage"><b>${messages.success}</b></span>
</p>
</body>
</html>
