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


@WebServlet("/equippablebonus")
public class EquippableBonusView extends HttpServlet {
	
	protected EquippableBonusDao equippableBonusDao;
	protected ItemDao itemDao;
	
	@Override
	public void init() throws ServletException {
		equippableBonusDao = EquippableBonusDao.getInstance();
		itemDao = ItemDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 List<EquippableBonus> equippableBonusList = new ArrayList<EquippableBonus>();
		 Item item;
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate characterId.
        String itemId = req.getParameter("itemId");
        if (itemId == null || itemId.trim().isEmpty()) {
            messages.put("title", "Invalid itemId.");
        } else {
			try {
				item = itemDao.getItemById(Integer.parseInt(itemId));
				
				if (item!=null) {
					equippableBonusList = equippableBonusDao.getEquippableBonusByItemID(item);
					messages.put("title", "Equippable bonus for " + item.getItemName());
				} else {
					messages.put("title", "Non-existent equippable item.");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
        	
        }
        
        
        req.setAttribute("equippableBonus", equippableBonusList);
        req.getRequestDispatcher("/EquippableBonusView.jsp").forward(req, resp);
	}
}
