package pm.servlet;

import pm.dal.*;
import pm.model.Item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/item")
public class ItemServlet extends HttpServlet {
    
    protected ItemDao itemDao;
    
    @Override
    public void init() throws ServletException {
        itemDao = ItemDao.getInstance();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        // Retrieve and validate itemId.
        String itemIdStr = req.getParameter("itemId");
        if (itemIdStr == null || itemIdStr.trim().isEmpty()) {
            messages.put("result", "Invalid itemId.");
        } else {
            try {
                int itemId = Integer.parseInt(itemIdStr);
                Item item = itemDao.getItemById(itemId);
                if (item != null) {
                    req.setAttribute("item", item);
                    messages.put("result", "Displaying item with ID: " + itemId);
                } else {
                    messages.put("result", "Item not found.");
                }
            } catch (NumberFormatException e) {
                messages.put("result", "Invalid itemId. Please enter a valid integer.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error accessing database.", e);
            }
        }

        req.getRequestDispatcher("/FindItem.jsp").forward(req, resp);
    }
}
