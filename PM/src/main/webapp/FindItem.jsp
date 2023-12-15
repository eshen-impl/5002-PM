<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item</title>
<link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
    <h1>${messages.result}</h1>
    <table border="1">
        <tr>
            <th>Item Name</th>
            <th>Max Stack Size</th>
            <th>Vendor Price</th>
            <th>Can Be Sold?</th>
        </tr>
        <tr>
            <td><c:out value="${item.itemName}" /></td>
            <td><c:out value="${item.maxStackSize}" /></td>
            <td><c:out value="${item.vendorPrice}" /></td>
            <td><c:out value="${item.canBeSold}" /></td>
        </tr>
    </table>
</body>
</html>

