package pm.dal;
import pm.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterCurrencyDao {
    protected ConnectionManager connectionManager;
    private static CharacterCurrencyDao instance = null;
    protected CharacterCurrencyDao() {
        connectionManager = new ConnectionManager();
    }
    public static CharacterCurrencyDao getInstance() {
        if(instance == null) {
            instance = new CharacterCurrencyDao();
        }
        return instance;
    }

    // Create method for inserting a new record
    public CharacterCurrency create(CharacterCurrency characterCurrency) throws SQLException {
        String insertQuery = "INSERT INTO CharacterCurrency (characterId, currencyName, amountOwned, weeklyAmountOwned) " +
                "VALUES (?, ?, ?, ?)";

        
        Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			pstmt = connection.prepareStatement(insertQuery);
			
            pstmt.setInt(1, characterCurrency.getCharacter().getCharacterId());
            pstmt.setString(2, characterCurrency.getCurrency().getCurrencyName());
            pstmt.setInt(3, characterCurrency.getAmountOwned());
            pstmt.setInt(4, characterCurrency.getWeeklyAmountOwned());


            pstmt.executeUpdate();
           
		} catch (SQLException e) {
			        e.printStackTrace();
			        throw e;
			
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}

		}
        
        
        return characterCurrency;
    }

    // Method for fetching records based on search keys
    public List<CharacterCurrency> getCharacterCurrenciesByCharacterId(int characterId) throws SQLException {
        List<CharacterCurrency> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM CharacterCurrency WHERE characterId = ?";
        Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectQuery);
			selectStmt.setInt(1, characterId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				CharacterCurrency characterCurrency = mapResultSetToCharacterCurrency(results);
                result.add(characterCurrency);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}		
        return result;
    }
    
public CharacterCurrency getCharacterCurrencyByCharacterIdAndCurrencyName(int characterId, String currencyName) throws SQLException {
        
        String selectQuery = "SELECT * FROM CharacterCurrency WHERE characterId = ? and currencyName = ?";
    	Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectQuery);
			selectStmt.setInt(1, characterId);
			selectStmt.setString(2, currencyName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				CharacterCurrency characterCurrency = mapResultSetToCharacterCurrency(results);
				return characterCurrency;
			}
			
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}		

		return null;
    }

    // Method for updating an attribute for a single record
    public CharacterCurrency updateAmountOwned(CharacterCurrency characterCurrency, int newAmount) throws SQLException {
        String updateQuery = "UPDATE CharacterCurrency SET amountOwned = ? WHERE characterId = ? AND currencyName = ?";        
        
        Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			 pstmt = connection.prepareStatement(updateQuery);
	           pstmt.setInt(1, newAmount);
	            pstmt.setInt(2, characterCurrency.getCharacter().getCharacterId());
	            pstmt.setString(3, characterCurrency.getCurrency().getCurrencyName());
            pstmt.executeUpdate();
            characterCurrency.setAmountOwned(newAmount);
			return characterCurrency;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		}
    }

    // Method for deleting a record
    public void delete(CharacterCurrency characterCurrency) throws SQLException {
        String deleteQuery = "DELETE FROM CharacterCurrency WHERE characterId = ? AND currencyName = ?";
 
        

        Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			pstmt = connection.prepareStatement(deleteQuery);
            pstmt.setInt(1, characterCurrency.getCharacter().getCharacterId());
            pstmt.setString(2, characterCurrency.getCurrency().getCurrencyName());
            pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		}
        
    }

    // Helper method to map ResultSet to CharacterCurrency object
    private CharacterCurrency mapResultSetToCharacterCurrency(ResultSet resultSet) throws SQLException {
        CharacterCurrency characterCurrency = new CharacterCurrency();
        CharacterDao characterDao = CharacterDao.getInstance();
        
        CurrencyDao currencyDao = CurrencyDao.getInstance();
        characterCurrency.setCharacter(characterDao.getCharacterById(resultSet.getInt("characterId")));
        characterCurrency.setCurrency(currencyDao.getCurrencyById(resultSet.getString("currencyName")));
        characterCurrency.setAmountOwned(resultSet.getInt("amountOwned"));
        characterCurrency.setWeeklyAmountOwned(resultSet.getInt("weeklyAmountOwned"));
        return characterCurrency;
    }
}