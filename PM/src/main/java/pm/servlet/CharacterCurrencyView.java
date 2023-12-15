package pm.servlet;

import pm.dal.*;
import pm.model.*;

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

@WebServlet("/charactercurrency")
public class CharacterCurrencyView extends HttpServlet {
    protected CharacterCurrencyDao characterCurrencyDao;
    protected CharacterDao characterDao;

    @Override
    public void init() throws ServletException {
        characterCurrencyDao = CharacterCurrencyDao.getInstance();
        characterDao = CharacterDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<CharacterCurrency> characterCurrencies = new ArrayList<>();
        // Retrieve and validate characterId.
        String characterIdParam = req.getParameter("characterId");
        if ((characterIdParam == null) || characterIdParam.trim().isEmpty()) {
            messages.put("title", "Invalid character ID.");
        } else {

            try {
            	int characterId = Integer.parseInt(characterIdParam);
                messages.put("title", "Currencies for " + characterDao.getCharacterById(characterId).getCharacterFirstName() + " " + characterDao.getCharacterById(characterId).getCharacterLastName());
                // Retrieve Character, and store in the request.

                characterCurrencies = characterCurrencyDao.getCharacterCurrenciesByCharacterId(characterId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }


        req.setAttribute(
                "characterCurrencies", characterCurrencies);
        req.getRequestDispatcher("/CharacterCurrency.jsp").forward(req, resp);
    }
}
