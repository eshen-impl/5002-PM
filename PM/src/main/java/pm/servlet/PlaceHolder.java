package pm.servlet;



import javax.servlet.annotation.*;

import javax.servlet.http.HttpServlet;




@WebServlet("/placeholder")
public class PlaceHolder extends HttpServlet {
//	
//	protected BlogUsersDao blogUsersDao;
//	
//	@Override
//	public void init() throws ServletException {
//		blogUsersDao = BlogUsersDao.getInstance();
//	}
//	
//	@Override
//	public void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		// Map for storing messages.
//        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//
//        List<BlogUsers> blogUsers = new ArrayList<BlogUsers>();
//        
//        // Retrieve and validate name.
//        // firstname is retrieved from the URL query string.
//        String firstName = req.getParameter("firstname");
//        if (firstName == null || firstName.trim().isEmpty()) {
//            messages.put("success", "Please enter a valid name.");
//        } else {
//        	// Retrieve BlogUsers, and store as a message.
//        	try {
//            	blogUsers = blogUsersDao.getBlogUsersFromFirstName(firstName);
//            } catch (SQLException e) {
//    			e.printStackTrace();
//    			throw new IOException(e);
//            }
//        	messages.put("success", "Displaying results for " + firstName);
//        	// Save the previous search term, so it can be used as the default
//        	// in the input box when rendering FindUsers.jsp.
//        	messages.put("previousFirstName", firstName);
//        }
//        req.setAttribute("blogUsers", blogUsers);
//        
//        req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
//	}
//	
//	@Override
//    public void doPost(HttpServletRequest req, HttpServletResponse resp)
//    		throws ServletException, IOException {
//        // Map for storing messages.
//        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//
//        List<BlogUsers> blogUsers = new ArrayList<BlogUsers>();
//        
//        // Retrieve and validate name.
//        // firstname is retrieved from the form POST submission. By default, it
//        // is populated by the URL query string (in FindUsers.jsp).
//        String firstName = req.getParameter("firstname");
//        if (firstName == null || firstName.trim().isEmpty()) {
//            messages.put("success", "Please enter a valid name.");
//        } else {
//        	// Retrieve BlogUsers, and store as a message.
//        	try {
//            	blogUsers = blogUsersDao.getBlogUsersFromFirstName(firstName);
//            } catch (SQLException e) {
//    			e.printStackTrace();
//    			throw new IOException(e);
//            }
//        	messages.put("success", "Displaying results for " + firstName);
//        }
//        req.setAttribute("blogUsers", blogUsers);
//        
//        req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
//    }
}
