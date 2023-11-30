package pm.dal;

import pm.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsumableDao extends ItemDao{
    private static ConsumableDao instance = null;
    protected ConsumableDao() {
        super();
    }
    public static ConsumableDao getInstance() {
        if(instance == null) {
            instance = new ConsumableDao();
        }
        return instance;
    }

    public Consumable create(Consumable consumable) throws SQLException {
        create(new Item(consumable.getItemId(), consumable.getItemName(),
                consumable.getMaxStackSize(), consumable.getVendorPrice(), consumable.getCanBeSold()));

        String insertConsumable = "INSERT INTO Consumable(itemId, itemLevel, description) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertConsumable);
            insertStmt.setInt(1, consumable.getItemId());
            insertStmt.setInt(2, consumable.getItemLevel());
            insertStmt.setString(3, consumable.getDescription());
            insertStmt.executeUpdate();
            return consumable;
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


    public Consumable updateItemLevel(Consumable consumable, int newItemLevel) throws SQLException {
        String updateConsumable = "UPDATE Consumable SET itemLevel=? WHERE itemId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateConsumable);
            updateStmt.setInt(1, newItemLevel);
            updateStmt.setInt(2,consumable.getItemId());
            updateStmt.executeUpdate();
            consumable.setItemLevel(newItemLevel);
            return consumable;
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


    public Consumable delete(Consumable consumable) throws SQLException {
        String deleteConsumable = "DELETE FROM Consumable WHERE itemId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteConsumable);
            deleteStmt.setInt(1, consumable.getItemId());
            deleteStmt.executeUpdate();

            super.delete(consumable);

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

    public Consumable getConsumablesById(int itemId) throws SQLException {
        String selectConsumable =
                "SELECT Consumable.itemId AS itemId,itemName,maxStackSize,vendorPrice,canBeSold,itemLevel,description " +
                        "FROM Consumable INNER JOIN Item " +
                        "  ON Consumable.itemId = Item.itemId " +
                        "WHERE Consumable.itemId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectConsumable);
            selectStmt.setInt(1, itemId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultItemId = results.getInt("itemId");
                String itemName = results.getString("itemName");
                int maxStackSize = results.getInt("maxStackSize");
                int vendorPrice = results.getInt(("vendorPrice"));
                boolean canBeSold = results.getBoolean("canBeSold");
                int itemLevel = results.getInt("itemLevel");
                String description = results.getString("description");
                return new Consumable(resultItemId,itemName,maxStackSize,vendorPrice,canBeSold,itemLevel,description);
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

    public List<Consumable> getConsumableByItemLevel(int itemLevel)
            throws SQLException {
        List<Consumable> consumables = new ArrayList<>();
        String selectConsumable =
                "SELECT Consumable.itemId AS itemId,itemName,maxStackSize,vendorPrice,canBeSold,itemLevel,description " +
                        "FROM Consumable INNER JOIN Item " +
                        "  ON Consumable.itemId = Item.itemId " +
                        "WHERE Consumbale.itemLevel=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectConsumable);
            selectStmt.setInt(1, itemLevel);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int itemId = results.getInt("itemId");
                String itemName = results.getString("itemName");
                int maxStackSize = results.getInt("maxStackSize");
                int vendorPrice = results.getInt(("vendorPrice"));
                boolean canBeSold = results.getBoolean("canBeSold");
                String description = results.getString("description");
                Consumable consumable = new Consumable(itemId,itemName,maxStackSize,vendorPrice,canBeSold,itemLevel,description);
                consumables.add(consumable);
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
        return consumables;
    }
}
