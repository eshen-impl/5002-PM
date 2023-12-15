package pm.servlet;

import pm.dal.*;
import pm.model.Inventory;

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



@WebServlet("/inventory")

public class InventoryServlet extends HttpServlet {
    
    protected InventoryDao inventoryDao;
    
    @Override
    public void init() throws ServletException {
        inventoryDao = InventoryDao.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<Inventory> inventoryDisplays = new ArrayList<>();
        
        // Retrieve inventory information depending on valid CharacterID.
        String characterId = req.getParameter("characterId");
        try {
            if (characterId != null && !characterId.trim().isEmpty()) {
                int characterid = Integer.parseInt(characterId);
                inventoryDisplays = inventoryDao.getInventoryByCharacterId(characterid);
                messages.put("result", "Inventory for Character " + characterId);
            } else {
                messages.put("result", "Invalid Character ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        req.setAttribute("inventoryDisplays", inventoryDisplays);
        req.getRequestDispatcher("/FindInventory.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Inventory> inventoryDisplays = new ArrayList<>();
        
        // Retrieve and validate characterId.
        String characterIdStr = req.getParameter("characterId");
        if (characterIdStr == null || characterIdStr.trim().isEmpty()) {
            messages.put("result", "Please enter a valid characterId.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		int characterId = Integer.parseInt(characterIdStr);
            	inventoryDisplays = inventoryDao.getInventoryByCharacterId(characterId);
            	messages.put("result", "Displaying results for " + characterId);
            } catch (NumberFormatException e) {
                // Handle the case where characterIdStr is not a valid integer
                messages.put("result", "Invalid characterId. Please enter a valid integer.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.setAttribute("inventoryDisplays", inventoryDisplays);
        
        req.getRequestDispatcher("/FindInventory.jsp").forward(req, resp);
    }
}

