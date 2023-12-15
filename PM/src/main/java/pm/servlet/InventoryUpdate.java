package pm.servlet;

import pm.dal.*;
import pm.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/inventoryupdate")
public class InventoryUpdate extends HttpServlet {
    
    protected InventoryDao inventoryDao;
    protected ItemDao itemDao;
    
    @Override
    public void init() throws ServletException {
        inventoryDao = InventoryDao.getInstance();
        itemDao = ItemDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        // Retrieve inventory details.
        String characterId = req.getParameter("characterId");
        String slotId = req.getParameter("slotId");
        if ((characterId == null || characterId.trim().isEmpty()) || (slotId == null || slotId.trim().isEmpty())) {
            messages.put("result", "Please enter a valid characterId and SlotId.");
        } else {
            try {
                List<Inventory> inventories = inventoryDao.getInventoryByCharacterId(Integer.parseInt(characterId));
                if (inventories == null) {
                    messages.put("result", "Inventory item does not exist for the given characterId and SlotId.");
                }
                req.setAttribute("inventory", inventories);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        
        req.getRequestDispatcher("/InventoryUpdate.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        String characterIdStr = req.getParameter("characterId");
        String slotIdStr = req.getParameter("slotId");
        String newQuantityStr = req.getParameter("newQuantity");

        try {
            if (characterIdStr == null || characterIdStr.trim().isEmpty() || 
                slotIdStr == null || slotIdStr.trim().isEmpty() || 
                newQuantityStr == null || newQuantityStr.trim().isEmpty()) {
                messages.put("result", "Please enter valid Character ID, Slot ID, and Quantity.");
            } else {
                int characterId = Integer.parseInt(characterIdStr);
                int slotId = Integer.parseInt(slotIdStr);
                int newQuantity = Integer.parseInt(newQuantityStr);

                if (newQuantity < 0) {
                    throw new IllegalArgumentException("Quantity cannot be negative.");
                }

                // Retrieve the inventory item for the given characterId and slotId
                Inventory inventory = inventoryDao.getInventoryByCharacterAndSlot(characterId, slotId);
                if (inventory != null) {
                	int maxSize = itemDao.getItemById(inventory.getItem().getItemId()).getMaxStackSize();
                	if (newQuantity > maxSize) {
                		messages.put("result", "Cannot exceed maximum stack size: " + maxSize);
                	} else {
                		inventoryDao.updateQuantity(inventory, newQuantity);
                		messages.put("result", "Successfully updated quantity for Character ID: " + characterId + ", Slot ID: " + slotId);
                	}
                } else {
                    messages.put("result", "Inventory item not found for the given Character ID and Slot ID.");
                }
            }
        } catch (IllegalArgumentException e) {
            messages.put("result", "Invalid input: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            messages.put("result", "Database error while updating inventory.");
        }

        req.getRequestDispatcher("/InventoryUpdate.jsp").forward(req, resp);
    }

}
