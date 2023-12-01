package pm.dal;
import pm.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterCurrencyDao {
    private Connection connection;

    // Constructor to initialize the connection
    public CharacterCurrencyDao(Connection connection) {
        this.connection = connection;
    }

    // Create method for inserting a new record
    public CharacterCurrency create(CharacterCurrency characterCurrency) throws SQLException {
        String insertQuery = "INSERT INTO CharacterCurrency (characterId, currencyName, amountOwned, weeklyAmountOwned) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setInt(1, characterCurrency.getCharacterId());
            pstmt.setString(2, characterCurrency.getCurrencyName());
            pstmt.setString(3, characterCurrency.getAmountOwned());
            pstmt.setString(4, characterCurrency.getWeeklyAmountOwned());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating CharacterCurrency record failed, no rows affected.");
            }
        }
        return characterCurrency;
    }

    // Method for fetching records based on search keys
    public List<CharacterCurrency> getCharacterCurrenciesByCharacterId(int characterId) throws SQLException {
        List<CharacterCurrency> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM CharacterCurrency WHERE characterId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(selectQuery)) {
            pstmt.setInt(1, characterId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    CharacterCurrency characterCurrency = mapResultSetToCharacterCurrency(resultSet);
                    result.add(characterCurrency);
                }
            }
        }
        return result;
    }

    // Method for updating an attribute for a single record
    public void updateAmountOwned(CharacterCurrency characterCurrency, String newAmount) throws SQLException {
        String updateQuery = "UPDATE CharacterCurrency SET amountOwned = ? WHERE characterId = ? AND currencyName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {
            pstmt.setString(1, newAmount);
            pstmt.setInt(2, characterCurrency.getCharacterId());
            pstmt.setString(3, characterCurrency.getCurrencyName());
            pstmt.executeUpdate();
        }
    }

    // Method for deleting a record
    public void delete(CharacterCurrency characterCurrency) throws SQLException {
        String deleteQuery = "DELETE FROM CharacterCurrency WHERE characterId = ? AND currencyName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, characterCurrency.getCharacterId());
            pstmt.setString(2, characterCurrency.getCurrencyName());
            pstmt.executeUpdate();
        }
    }

    // Helper method to map ResultSet to CharacterCurrency object
    private CharacterCurrency mapResultSetToCharacterCurrency(ResultSet resultSet) throws SQLException {
        CharacterCurrency characterCurrency = new CharacterCurrency();
        characterCurrency.setCharacterId(resultSet.getInt("characterId"));
        characterCurrency.setCurrencyName(resultSet.getString("currencyName"));
        characterCurrency.setAmountOwned(resultSet.getString("amountOwned"));
        characterCurrency.setWeeklyAmountOwned(resultSet.getString("weeklyAmountOwned"));
        return characterCurrency;
    }
}