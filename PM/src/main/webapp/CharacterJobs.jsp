<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CharacterJobs</title>
<link rel="stylesheet" type="text/css" href="default.css">
</head>
<body>
	<h1>Jobs</h1>
        <table border="1">
            <tr>
                <th>JobName</th>
                <th>CurrentExp</th>
                <th>isUnlocked</th>
                <th>isCurrentJob</th>
                 <th>updateExp</th>
            </tr>
            <c:forEach items="${characterJobs}" var="characterJob" >
                <tr>
                 	<td><a href="job?jobId=<c:out value='${characterJob.getJob().getJobId()}'/>"><c:out value="${characterJob.getJob().getJobName()}"/></a></td>
                    
                    <td><c:out value="${characterJob.currentExp}" /></td>
                    <td><c:out value="${characterJob.unlocked}" /></td>
                    <td><c:out value="${characterJob.currentJob}" /></td>
            <td><a href="expupdate?jobName=${characterJob.job.jobName}&currentExp=${characterJob.currentExp}&jobId=${characterJob.job.jobId}">Update</a></td>

      

                </tr>
            </c:forEach>
       </table>
</body>
</html>
