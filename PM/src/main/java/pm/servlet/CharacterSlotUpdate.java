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
import javax.servlet.http.HttpSession;


@WebServlet("/characterslotupdate")
public class CharacterSlotUpdate extends HttpServlet {
	
	protected CharacterSlotDao characterSlotDao;
	protected CharacterDao characterDao;
	protected CharacterAttributeDao characterAttributeDao;
	protected EquippableBonusDao equippableBonusDao;
	protected EquippableDao equippableDao;
	protected InventoryDao inventoryDao;
	// Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    HttpSession session;
	
	@Override
	public void init() throws ServletException {
		characterSlotDao = CharacterSlotDao.getInstance();
		characterDao = CharacterDao.getInstance();
		characterAttributeDao = CharacterAttributeDao.getInstance();
		equippableBonusDao = EquippableBonusDao.getInstance();
		equippableDao = EquippableDao.getInstance();
		inventoryDao = InventoryDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		session = req.getSession();
		 List<Inventory> inventory = new ArrayList<Inventory>();
		 Character character;
		 CharacterSlot characterSlot;

        session.setAttribute("messages", messages);
		
		// Retrieve and validate characterId.
        String characterId = req.getParameter("characterId");
        String slot = req.getParameter("slotType");
        if (characterId == null || characterId.trim().isEmpty() || slot == null || slot.trim().isEmpty()) {
            messages.put("title", "Invalid characterId or slot type");
        } else {
			try {
				int cId = Integer.parseInt(characterId);
				character = characterDao.getCharacterById(cId);
				characterSlot = characterSlotDao.getCharacterSlotByCharacterIdandSlotType(cId, slot);
				session.setAttribute("characterSlot", characterSlot);
				List<CharacterAttribute> characterAttributes =characterAttributeDao.getCharacterAttributeByIds(cId);
				session.setAttribute("characterAttributes", characterAttributes);
				if (character!=null && characterSlot!= null) {
					session.setAttribute("characterId", cId);
					messages.put("title", "Change " + slot.substring(0, 1).toUpperCase() + slot.substring(1) + " Equipment Slot for " + character.getCharacterFirstName() + " " + character.getCharacterLastName());
					;
					Item item = characterSlot.getEquippedItem();
					messages.put("current", item.getItemName());
					List<EquippableBonus> currentBonusList = equippableBonusDao.getEquippableBonusByItemID(item);
			        session.setAttribute("currentBonusList", currentBonusList);
					
					List<Equippable> availableEquipments = new ArrayList<Equippable>();
					availableEquipments.add(null);
					inventory = inventoryDao.getInventoryByCharacterId(cId);
					for (Inventory inv : inventory) {
						Equippable availEq = equippableDao.getEquippableByItemID(inv.getItem().getItemId());
						if (availEq!=null && availEq.getSlotType().equals(slot)) {
							availableEquipments.add(availEq);
						}
					}
					session.setAttribute("availableEquipments", availableEquipments);
					
					
				} else {
					messages.put("title", "Non-existent characterId or slot type.");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
        	
        }
        
        req.getRequestDispatcher("/CharacterSlotUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Integer characterId = (Integer) session.getAttribute("characterId");
		try {
			if (req.getParameter("try") != null) {

				handleEquipChange(req, resp);
				req.getRequestDispatcher("/CharacterSlotUpdate.jsp").forward(req, resp);


			} else if (req.getParameter("confirm") != null){
				handleEquipChange(req, resp);

				if (req.getAttribute("newItem")== null || req.getAttribute("effectMapping")== null) {
					req.getRequestDispatcher("/CharacterSlotUpdate.jsp").forward(req, resp);
				} else {
					
					CharacterSlot cslot = (CharacterSlot) session.getAttribute("characterSlot");
					Equippable newItem = (Equippable) req.getAttribute("newItem");
				    characterSlotDao.updateEquippedItem(cslot, newItem);
				    Map<String,Integer> effectMapping = (Map<String,Integer>) req.getAttribute("effectMapping");
				    	
				    for (Map.Entry<String, Integer> entry : effectMapping.entrySet()) {
				    	CharacterAttribute characterAttribute = characterAttributeDao.getCharacterAttributeByIdAndNames(characterId, entry.getKey());
					    characterAttributeDao.updateAttributeValue(characterAttribute, characterAttribute.getAttributeValue()+entry.getValue());
				    }
				    
				    clearSessionAttr();

					resp.sendRedirect(req.getContextPath() + "/characterslot?characterId=" + characterId);
				}
				
			} else {
				clearSessionAttr();

				resp.sendRedirect(req.getContextPath() + "/characterslot?characterId=" + characterId);
			}
				

				
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	private void handleEquipChange(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, SQLException, ServletException, IOException {
		String newItemId = req.getParameter("newEquipment");
		req.setAttribute("newItemId", newItemId);
		if (newItemId == null || newItemId.trim().isEmpty() ) {
			messages.put("success", "Please choose a new equipment from the drop down menu");
			
		} else {

			messages.put("success", "");
			Equippable newItem = equippableDao.getEquippableByItemID(Integer.parseInt(newItemId));
			req.setAttribute("newItem", newItem);
			messages.put("newEq", newItem.getItemName());
			List<EquippableBonus> newBonusList = equippableBonusDao.getEquippableBonusByItemID(newItem);
			req.setAttribute("newBonusList", newBonusList);
		
			
			List<EquippableBonus> currentBonusList = ((List<EquippableBonus>) session.getAttribute("currentBonusList"));
			Map<String,Integer> effectMapping = new HashMap<String,Integer>();
			for (EquippableBonus newBonus : newBonusList) {
				effectMapping.put(newBonus.getAttribute(), newBonus.getBonusValue());
			}
			for (EquippableBonus currentBonus : currentBonusList) {
				effectMapping.put(currentBonus.getAttribute(), effectMapping.getOrDefault(currentBonus.getAttribute(),0) - currentBonus.getBonusValue());
			}
			req.setAttribute("effectMapping", effectMapping);
			
		}

	}
	
	private void clearSessionAttr() {
		
		session.removeAttribute("messages");
		session.removeAttribute("characterId");
		session.removeAttribute("currentBonusList");
		session.removeAttribute("availableEquipments");
		session.removeAttribute("characterSlot");
		session.removeAttribute("characterAttributes");
		
	}
}
