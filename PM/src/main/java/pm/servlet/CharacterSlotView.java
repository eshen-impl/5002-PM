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


@WebServlet("/characterslot")
public class CharacterSlotView extends HttpServlet {
	
	protected CharacterSlotDao characterSlotDao;
	protected CharacterDao characterDao;
	String characterId;
	
	@Override
	public void init() throws ServletException {
		characterSlotDao = CharacterSlotDao.getInstance();
		characterDao = CharacterDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		renderView(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		renderView(req, resp);
		
	}
	
	 private void renderView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 List<CharacterSlot> characterSlots = new ArrayList<CharacterSlot>();
		 Character character;
		 
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        characterId = req.getParameter("characterId");
        if (characterId == null || characterId.trim().isEmpty()) {
            messages.put("title", "Invalid characterId.");
        } else {
			try {
				character = characterDao.getCharacterById(Integer.parseInt(characterId));
				characterSlots = characterSlotDao.getCharacterSlotsByCharacterId(Integer.parseInt(characterId));
				if (character!=null) {
					messages.put("title", "Equipment Slots for " + character.getCharacterFirstName() + " " + character.getCharacterLastName());
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
        
        
        req.setAttribute("characterSlots", characterSlots);
        req.getRequestDispatcher("/CharacterSlotView.jsp").forward(req, resp); 
		 
		 
		 
		 
	 }
}
