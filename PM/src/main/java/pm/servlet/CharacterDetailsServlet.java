package pm.servlet;

import pm.dal.*;
import pm.model.*;
import pm.model.Character;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/characterdetails")
public class CharacterDetailsServlet extends HttpServlet {
	
	protected CharacterDao characterDao;
	
	@Override
	public void init() throws ServletException {
		characterDao = CharacterDao.getInstance();
	}
	
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Character> characters = new ArrayList<Character>();
        
       

        String firstName = req.getParameter("firstname");
        if (firstName == null || firstName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	
        	try {
            	characters = characterDao.getCharactersbyFirstName(firstName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + firstName);

        }
        Collections.sort(characters);
        req.setAttribute("characters", characters);
        
        req.getRequestDispatcher("/CharacterDetails.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		doGet(req, resp);

}
}

