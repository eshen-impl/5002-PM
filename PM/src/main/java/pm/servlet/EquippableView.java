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


@WebServlet("/equippable")
public class EquippableView extends HttpServlet {
	
	protected EquippableDao equippableDao;
	protected WeaponDao weaponDao;
	protected GearDao gearDao;
	
	@Override
	public void init() throws ServletException {
		equippableDao = EquippableDao.getInstance();
		weaponDao = WeaponDao.getInstance();
		gearDao = GearDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 Equippable equippable;
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate characterId.
        String itemId = req.getParameter("itemId");
        if (itemId == null || itemId.trim().isEmpty()) {
            messages.put("title", "Invalid itemId.");
        } else {
			try {
				equippable = equippableDao.getEquippableByItemID(Integer.parseInt(itemId));
			
				if (equippable!=null) {
					
					if (equippable.getSlotType().equals("Main hand")) {
						equippable = weaponDao.getWeaponByItemID(Integer.parseInt(itemId));
						req.setAttribute("equippable", equippable);
						messages.put("title", "Weapon: " + equippable.getItemName());
						req.getRequestDispatcher("/WeaponView.jsp").forward(req, resp);
					} else {
						equippable = gearDao.getGearByItemID(Integer.parseInt(itemId));
						req.setAttribute("equippable", equippable);
						messages.put("title", "Gear: " + equippable.getItemName());
						req.getRequestDispatcher("/GearView.jsp").forward(req, resp);
					}
					req.setAttribute("equippable", equippable);
				} else {
					messages.put("title", "Non-existent equippable item.");
					req.getRequestDispatcher("/GearView.jsp").forward(req, resp);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
        	
        }
        

        
	}
}
