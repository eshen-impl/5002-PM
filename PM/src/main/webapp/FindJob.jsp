<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job</title>
<link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
    <h1>${messages.result}</h1>
    <table border="1">
        <tr>
            <th>Job Name</th>
            <th>Job Level</th>
            <th>Min Exp</th>
            <th>Max Exp</th>
        </tr>
        <tr>
            <td><c:out value="${job.jobName}" /></td>
            <td><c:out value="${job.jobLevel}" /></td>
            <td><c:out value="${job.minLevelExp}" /></td>
            <td><c:out value="${job.maxLevelExp}" /></td>
        </tr>
    </table>
</body>
</html>

