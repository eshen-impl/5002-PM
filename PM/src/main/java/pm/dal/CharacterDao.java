package pm.dal;

import pm.model.*;
import pm.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;


public class CharacterDao {
	protected ConnectionManager connectionManager;

	private static CharacterDao instance = null;
	protected CharacterDao() {
		connectionManager = new ConnectionManager();
	}
	public static CharacterDao getInstance() {
		if(instance == null) {
			instance = new CharacterDao();
		}
		return instance;
	}

	public Character create(Character character) throws SQLException {
		String insert =
			"INSERT INTO `Character` (accountId, characterFirstName, characterLastName) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
		
			insertStmt = connection.prepareStatement(insert,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, character.getAccount().getAccountId());
			insertStmt.setString(2, character.getCharacterFirstName());
			insertStmt.setString(3, character.getCharacterLastName());
			insertStmt.executeUpdate();
			
	
			resultKey = insertStmt.getGeneratedKeys();
			int cid = -1;
			if(resultKey.next()) {
				cid = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			character.setCharacterId(cid);
			return character;
		} catch (SQLException e) {
			 if (e.getSQLState().equals("1062")) {
			        System.out.println("Duplicate combination of character's first name and last name exists.");
			      
			    } else {
			      
			        e.printStackTrace();
			    }
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	

	public Character getCharacterById(int characterId) throws SQLException {
		String select =
			"SELECT characterId, accountId, characterFirstName, characterLastName " +
			"FROM `Character` WHERE characterId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, characterId);
			results = selectStmt.executeQuery();
			PlayerDao playerDao = PlayerDao.getInstance();
			if(results.next()) {
				int resultCharacterId = results.getInt("characterId");
				String characterFirstName = results.getString("characterFirstName");
				String characterLastName = results.getString("characterLastName");
			
				Integer accountId = results.getInt("accountId");
				
				Player player = playerDao.getPlayerByID(accountId);
				Character character = new Character(resultCharacterId, player, characterFirstName, characterLastName);
				return character;
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
	
	
	public List<Character> getCharactersbyFirstName(String firstName) throws SQLException {
		List<Character> characters = new ArrayList<Character>();
		String select =
			"SELECT characterId, accountId, characterFirstName, characterLastName FROM `Character` WHERE characterFirstName LIKE ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, "%" + firstName + "%");
			results = selectStmt.executeQuery();
			PlayerDao playerDao = PlayerDao.getInstance();
			while(results.next()) {
				int characterId = results.getInt("characterId");
				String characterFirstName = results.getString("characterFirstName");
				String characterLastName = results.getString("characterLastName");
				Integer accountId = results.getInt("accountId");
				
				Player player = playerDao.getPlayerByID(accountId);
				Character character = new Character(characterId, player, characterFirstName, characterLastName);
				characters.add(character);
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
		return characters;
	}

	public List<Character> getCharactersForPlayer(Player player) throws SQLException {
		List<Character> characters = new ArrayList<Character>();
		String select =
			"SELECT characterId, accountId, characterFirstName, characterLastName FROM `Character` WHERE accountId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, player.getAccountId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int characterId = results.getInt("characterId");
				String characterFirstName = results.getString("characterFirstName");
				String characterLastName = results.getString("characterLastName");
				
				Character character = new Character(characterId, player, characterFirstName, characterLastName);
				characters.add(character);
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
		return characters;
	}
	
	
	public Character delete(Character character) throws SQLException {
		String delete = "DELETE FROM `Character` WHERE characterId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, character.getCharacterId());
			deleteStmt.executeUpdate();

			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
