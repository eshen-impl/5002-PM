package pm.servlet;

import pm.dal.*;
import pm.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/charactercurrencyupdate")
public class CharacterCurrencyUpdate extends HttpServlet{
    protected CharacterCurrencyDao characterCurrencyDao;

    @Override
    public void init() throws ServletException {
        characterCurrencyDao = CharacterCurrencyDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);


        String characterIdParam = req.getParameter("characterid");
        String currencyName = req.getParameter("currencyname");

        if (characterIdParam == null || characterIdParam.trim().isEmpty() || currencyName == null || currencyName.trim().isEmpty()) {
            messages.put("success", "Please enter valid character ID and currency name.");
        } else {
            try {
                int characterId = Integer.parseInt(characterIdParam);
                CharacterCurrency characterCurrency = characterCurrencyDao.getCharacterCurrencyByCharacterIdAndCurrencyName(characterId, currencyName);

                if (characterCurrency == null) {
                    messages.put("success", "Character ID or currency name does not exist.");
                }

                req.setAttribute("characterCurrency", characterCurrency);
            } catch (NumberFormatException e) {
                messages.put("success", "Invalid character ID format.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/CharacterCurrencyUpdate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);


        String characterIdParam = req.getParameter("characterid");
        String currencyName = req.getParameter("currencyname");

        if (characterIdParam == null || characterIdParam.trim().isEmpty() || currencyName == null || currencyName.trim().isEmpty()) {
            messages.put("success", "Please enter valid character ID and currency name.");
        } else {
            try {
                int characterId = Integer.parseInt(characterIdParam);
                CharacterCurrency characterCurrency = characterCurrencyDao.getCharacterCurrencyByCharacterIdAndCurrencyName(characterId, currencyName);
                if (characterCurrency == null) {
                    messages.put("success", "Character ID or currency name does not exist. No update to perform.");
                } else {
                    String newAmountOwned = req.getParameter("amountowned");
                    if (newAmountOwned == null || newAmountOwned.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid LastName.");
                    } else {
                        characterCurrency = characterCurrencyDao.updateAmountOwned(characterCurrency, newAmountOwned);
                        messages.put("success", "Successfully updated " + currencyName);
                    }
                }
                req.setAttribute("characterCurrency", characterCurrency);
            } catch (NumberFormatException e) {
                messages.put("success", "Invalid character ID format.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/CharacterCurrencyUpdate.jsp").forward(req, resp);
    }
}
