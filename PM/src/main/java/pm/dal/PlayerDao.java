package pm.dal;

import pm.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class PlayerDao {
	protected ConnectionManager connectionManager;

	private static PlayerDao instance = null;
	protected PlayerDao() {
		connectionManager = new ConnectionManager();
	}
	public static PlayerDao getInstance() {
		if(instance == null) {
			instance = new PlayerDao();
		}
		return instance;
	}

	public Player create(Player player) throws SQLException {
		String insert =
			"INSERT INTO Player(`name`, emailAddress, isActive) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			
			insertStmt = connection.prepareStatement(insert,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, player.getName());
			insertStmt.setString(2, player.getEmailAddress());
			insertStmt.setBoolean(3, player.getIsActive());
		
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int postId = -1;
			if(resultKey.next()) {
				postId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			player.setAccountId(postId);
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
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

	

	public Player getPlayerByID(int accountID) throws SQLException {
		String select =
			"SELECT accountId, `name`, emailAddress, isActive " +
			"FROM Player WHERE accountId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, accountID);
			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultAccountID = results.getInt("accountId");
				String name = results.getString("name");
				String emailAddress = results.getString("emailAddress");
				boolean isActive = results.getBoolean("isActive");
		
				
				Player player = new Player(resultAccountID, name, emailAddress, isActive);
				return player;
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


	
}
