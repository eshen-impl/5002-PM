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



@WebServlet("/findcharacters")
public class CharacterView extends HttpServlet {
	
	protected CharacterDao characterDao;
	
	@Override
	public void init() throws ServletException {
		characterDao = CharacterDao.getInstance();
	}
	
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Character character;
        
       

        String characterIdParam = req.getParameter("characterId");
        if (characterIdParam == null || characterIdParam.trim().isEmpty()) {
            messages.put("success", "Invalid characterId.");
        } else {
        	
        	try {
        		int characterId = Integer.parseInt(characterIdParam);
            	character = characterDao.getCharacterById(characterId);
            	req.setAttribute("character", character);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	

        }
        
        
        
        req.getRequestDispatcher("/CharacterView.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		doGet(req, resp);

}
}

