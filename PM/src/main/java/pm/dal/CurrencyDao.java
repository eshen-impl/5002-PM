package pm.dal;

import pm.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class CurrencyDao {
	protected ConnectionManager connectionManager;

	private static CurrencyDao instance = null;
	protected CurrencyDao() {
		connectionManager = new ConnectionManager();
	}
	public static CurrencyDao getInstance() {
		if(instance == null) {
			instance = new CurrencyDao();
		}
		return instance;
	}

	public Currency create(Currency currency) throws SQLException {
		String insert =
			"INSERT INTO Currency(currencyName, totalCap, weeklyCap, discontinued) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			
			insertStmt = connection.prepareStatement(insert);
			insertStmt.setString(1, currency.getCurrencyName());
			insertStmt.setInt(2, currency.getTotalCap());
			insertStmt.setInt(3, currency.getWeeklyCap());
			insertStmt.setBoolean(4, currency.getDiscontinued());
			insertStmt.executeUpdate();
			
		
			return currency;
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

		}
	}


	public Currency updateWeeklyCap(Currency currency, Integer newWeeklyCap) throws SQLException {
		String update = "UPDATE Currency SET weeklyCap=? WHERE currencyName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(update);
			updateStmt.setInt(1, newWeeklyCap);
			updateStmt.setString(2, currency.getCurrencyName());
			updateStmt.executeUpdate();

			currency.setWeeklyCap(newWeeklyCap);

			return currency;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	
	public Currency getCurrencyById(String currencyName) throws SQLException {
		String selectBlogPost =
			"SELECT currencyName, totalCap, weeklyCap, discontinued " +
			"FROM Currency WHERE currencyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogPost);
			selectStmt.setString(1, currencyName);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String resultCurrencyName = results.getString("currencyName");
				int totalCap = results.getInt("totalCap");
				int weeklyCap = results.getInt("weeklyCap");

				boolean discontinued = results.getBoolean("discontinued");
				
				
				Currency currency = new Currency(resultCurrencyName, totalCap, weeklyCap, discontinued);
				return currency;
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
