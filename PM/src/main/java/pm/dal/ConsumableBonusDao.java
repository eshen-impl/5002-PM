package pm.dal;

import pm.model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsumableBonusDao {
    protected ConnectionManager connectionManager;

    private static ConsumableBonusDao instance = null;
    protected ConsumableBonusDao() {
        connectionManager = new ConnectionManager();
    }
    public static ConsumableBonusDao getInstance() {
        if(instance == null) {
            instance = new ConsumableBonusDao();
        }
        return instance;
    }

    public ConsumableBonus create(ConsumableBonus consumableBonus) throws SQLException {
        String insertConsumableBonus =
                "INSERT INTO ConsumableBonus(itemId,attribute,bonusPercentage,bonusCap) " +
                        "VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertConsumableBonus);
            insertStmt.setInt(1, consumableBonus.getItem().getItemId());
            insertStmt.setString(2, consumableBonus.getAttribute());
            insertStmt.setBigDecimal(3, consumableBonus.getBonusPercentage());
            insertStmt.setLong(4, consumableBonus.getBonusCap());
            insertStmt.executeUpdate();
            return consumableBonus;
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


    public ConsumableBonus updateBonusPercentage(ConsumableBonus consumableBonus, BigDecimal newBonusPercentage) throws SQLException {
        String updateBonusPercentage = "UPDATE ConsumableBonus SET bonusPercentage=? WHERE itemId=? AND attribute=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateBonusPercentage);
            updateStmt.setBigDecimal(1, newBonusPercentage);
            updateStmt.setInt(2, consumableBonus.getItem().getItemId());
            updateStmt.setString(3, consumableBonus.getAttribute());
            updateStmt.executeUpdate();

            consumableBonus.setBonusPercentage(newBonusPercentage);
            return consumableBonus;
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


    public ConsumableBonus delete(ConsumableBonus consumableBonus) throws SQLException {
        String deleteConsumableBonus = "DELETE FROM ConsumableBonus WHERE itemId=? AND attribute=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteConsumableBonus);
            deleteStmt.setInt(1, consumableBonus.getItem().getItemId());
            deleteStmt.setString(2,consumableBonus.getAttribute());
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


    public ConsumableBonus getConsumableBonusByItemAttribute(Item item, String attribute) throws SQLException {
        String selectConsumableBonus =
                "SELECT itemId,attribute,bonusPercentage,bonusCap " +
                        "FROM ConsumableBonus " +
                        "WHERE itemId=? AND attribute=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectConsumableBonus);
            selectStmt.setInt(1, item.getItemId());
            selectStmt.setString(2,attribute);
            results = selectStmt.executeQuery();
            ItemDao itemDao = ItemDao.getInstance();
            if(results.next()) {
                int itemId = results.getInt("itemId");
                Item resultItem = itemDao.getItemById(itemId);
                String resultAttritbute = results.getString("attribute");
                BigDecimal bonusPercentage = results.getBigDecimal("bonusPercentage");
                long bonusCap = results.getLong("bonusCap");

                return new ConsumableBonus(resultItem,resultAttritbute,bonusPercentage,bonusCap);
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


    public List<ConsumableBonus> getConsumableBonusesByBonusCap(long bonusCap) throws SQLException {
        List<ConsumableBonus> consumableBonuses = new ArrayList<>();
        String selectConsumableBonus =
                "SELECT itemId,attribute,bonusPercentage,bonusCap " +
                        "FROM ConsumableBonus " +
                        "WHERE bonusCap=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectConsumableBonus);
            selectStmt.setLong(1, bonusCap);
            results = selectStmt.executeQuery();
            ItemDao itemDao = ItemDao.getInstance();
            while(results.next()) {
                int itemId = results.getInt("itemId");
                Item item = itemDao.getItemById(itemId);
                String attritbute = results.getString("attribute");
                BigDecimal bonusPercentage = results.getBigDecimal("bonusPercentage");
                ConsumableBonus consumableBonus  = new ConsumableBonus(item,attritbute,bonusPercentage,bonusCap);
                consumableBonuses.add(consumableBonus);
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
        return consumableBonuses;
    }
}
