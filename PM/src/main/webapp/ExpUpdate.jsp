<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update CharacterJob CurrentExp</title>
<link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
	<h1>Update CurrentExp</h1>
	<form action="expupdate" method="post">
		<p>
			<label for="jobname">JobName</label>
			<input id="jobname" name="jobName" value="${fn:escapeXml(param.jobName)}"readonly>

		</p>
		<p>
			<label for="jobid">JobID</label>
			<input id="jobid" name="jobId" value="${fn:escapeXml(param.jobId)}"readonly>

		</p>
		<p>
			<label for="currentexp">CurrentExp</label>
			<input id="currentexp" name="currentExp" value="${latestExp!= null ? latestExp: (fn:escapeXml(param.currentExp))}"readonly>

		</p>
		<p>
			<label for="exp">New Exp</label>
			<input id="exp" name="exp1" value="">
		</p>
		<p>
			<input type="submit">
		</p>
		<p>
		<span id="successMessage"><b>${messages.success}</b></span>
		<br><br>
		<span id="level"><b>${messages.level}</b></span>
	</p>
	</form>
	<br/><br/>

</body>
</html>