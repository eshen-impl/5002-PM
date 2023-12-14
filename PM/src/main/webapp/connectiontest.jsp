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



    <form method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br>

        <input type="submit" value="Test Connection">
    </form>

    <hr>

    <% 
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String jdbcUrl = "jdbc:mysql://localhost:3306/CS5200Project?useSSL=false";
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcUrl, username, password);

                if (connection != null) {
                    out.println("<h2>Connected to the database successfully!</h2>");
                    session.setAttribute("dbUsername", username);
                    session.setAttribute("dbPassword", password);
                    
                    
                     statement = connection.createStatement();
                     resultSet = statement.executeQuery("SHOW TABLES");
                    
                     out.println("<table>");
                     ResultSetMetaData metaData = resultSet.getMetaData();
                     int columnCount = metaData.getColumnCount();
                     
                     // Display table headers
                     out.println("<tr>");
                     for (int i = 1; i <= columnCount; i++) {
                         out.println("<th>" + metaData.getColumnName(i) + "</th>");
                     }
                     out.println("</tr>");
                     
                     // Display table data
                     while (resultSet.next()) {
                         out.println("<tr>");
                         for (int i = 1; i <= columnCount; i++) {
                             out.println("<td><a href='executeQuery.jsp?table=" + resultSet.getString(i) + "'>" +resultSet.getString(i)+ "</a></td>");
                         }
                         out.println("</tr>");
                     }
                     out.println("</table>");
                     
                    
                } else {
                 	
                    out.println("<h2>Failed to connect to the database.</h2>");
                }
            } catch (Exception e) {

                out.println("<h2>Error: " + e.getMessage() + "</h2>");
            } finally {
    			if(connection != null) {
    				connection.close();
    			}
    			if(statement != null) {
    				statement.close();
    			}
    			if(resultSet != null) {
    				resultSet.close();
    			}
            }
        }
    %>
 
 
 
</body>
</html>
