package pm.servlet;

import pm.dal.*;
import javax.servlet.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/charactercurrency")
public class CharacterCurrency extends HttpServlet {
    protected CharacterCurrencyDao characterCurrencyDao;

    @Override
    public void init() throws ServletException {
        characterCurrencyDao = CharacterCurrencyDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate characterId.
        int characterId = req.getParameter("characterid");
        if ((characterId == null) || characterId.trim().isEmpty()) {
            messages.put("title", "Invalid character ID.");
        } else {
            messages.put("title", "Currencies for " + characterId);
        }

        // Retrieve Character, and store in the request.
        List<CharacterCurrency> characterCurrencies = new ArrayList<>();
        try {
            characterCurrencies = characterCurrencyDao.getCharacterCurrenciesByCharacterId(characterId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute(
                "characterCurrencies", characterCurrencies);
        req.getRequestDispatcher("/CharacterCurrency.jsp").forward(req, resp);
    }
}
