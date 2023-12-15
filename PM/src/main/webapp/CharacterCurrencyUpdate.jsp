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
</head>
<body>
<h1>Update Amount Owned</h1>
<form action="amountownedupdate" method="post">
    <p>
        <label for="characterid">Character ID</label>
        <input id="characterid" name="characterid" value="${fn:escapeXml(param.characterid)}">
    </p>
    <p>
        <label for="currencyname">Currency Name</label>
        <input id="currencyname" name="currencyname" value="${fn:escapeXml(param.currencyname)}">
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
