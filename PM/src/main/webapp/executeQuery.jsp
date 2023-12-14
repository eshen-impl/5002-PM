<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 <%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" type="text/css" href="default.css">

</head>
<body>
	<h1>5200-PM-Group9</h1>
 	<h2>Database Connection Test</h2>
    <hr>

    <% 
	    String username = (String) session.getAttribute("dbUsername");
	    String password = (String) session.getAttribute("dbPassword");
	    String selectedTable = request.getParameter("table");
		String jdbcUrl = "jdbc:mysql://localhost:3306/CS5200Project?useSSL=false";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet tableResult = null;
       
        
        if (selectedTable != null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcUrl, username, password);
                
                if (connection != null) {
                	String query = "SELECT * FROM `" + selectedTable + "`";
                    preparedStatement = connection.prepareStatement(query);
                    tableResult = preparedStatement.executeQuery();
                    
                    out.println("<h2>Results for table: <i>" + selectedTable + "</i></h2>");
                    out.println("<table>");
                    ResultSetMetaData metaData = tableResult.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    
                    // Display table headers
                    out.println("<tr>");
                    for (int i = 1; i <= columnCount; i++) {
                        out.println("<th>" + metaData.getColumnName(i) + "</th>");
                    }
                    out.println("</tr>");
                    
                    // Display table data
                    while (tableResult.next()) {
                        out.println("<tr>");
                        for (int i = 1; i <= columnCount; i++) {
                            out.println("<td>" + tableResult.getString(i) + "</td>");
                        }
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    
                    tableResult.close();
                    preparedStatement.close();
                    connection.close();
                } else {
                    out.println("<p>Failed to connect to the database.</p>");
                }
            } catch (Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            }
        } else {
            out.println("<p>No table selected.</p>");
        }

    %>
    
    <br>
	<button onclick="goBack()">Go Back</button>
    <button onclick="refreshPage()">Refresh</button>
    

    <script>
        function goBack() {
            window.history.back();
        }
        function refreshPage() {
            location.reload(); 
        }
    </script>
 
 
 
</body>
</html>
