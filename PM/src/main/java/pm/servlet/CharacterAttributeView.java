package pm.servlet;

import pm.dal.*;
import pm.model.*;
import pm.model.Character;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/characterattribute")
public class CharacterAttributeView extends HttpServlet {
	
	protected CharacterAttributeDao characterAttributeDao;
	protected CharacterDao characterDao;
	
	@Override
	public void init() throws ServletException {
		characterAttributeDao = CharacterAttributeDao.getInstance();
		characterDao = CharacterDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 List<CharacterAttribute> characterAttributes = new ArrayList<CharacterAttribute>();
		 Character character;
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate characterId.
        String characterId = req.getParameter("characterId");
        if (characterId == null || characterId.trim().isEmpty()) {
            messages.put("title", "Invalid characterId.");
        } else {
			try {
				character = characterDao.getCharacterById(Integer.parseInt(characterId));
				characterAttributes = characterAttributeDao.getCharacterAttributeByIds(Integer.parseInt(characterId));
				if (character!=null) {
					messages.put("title", "Attributes for " + character.getCharacterFirstName() + " " + character.getCharacterLastName());
				} else {
					messages.put("title", "Non-existent characterId.");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
        	
        }
        
        
        req.setAttribute("characterAttributes", characterAttributes);
        req.getRequestDispatcher("/CharacterAttributeView.jsp").forward(req, resp);
	}
}
