package pm.dal;

import pm.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;


public class ItemDao {
    protected ConnectionManager connectionManager;

    private static ItemDao instance = null;
    protected ItemDao() {
        connectionManager = new ConnectionManager();
    }
    public static ItemDao getInstance() {
        if(instance == null) {
            instance = new ItemDao();
        }
        return instance;
    }


    public Item create(Item item) throws SQLException {
        String insertItem = "INSERT INTO Item(itemName,maxStackSize,vendorPrice,canBeSold) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertItem,
                    Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1, item.getItemName());
            insertStmt.setInt(2, item.getMaxStackSize());
            insertStmt.setInt(3, item.getVendorPrice());
            insertStmt.setBoolean(3, item.getCanBeSold());

            insertStmt.executeUpdate();
            resultKey = insertStmt.getGeneratedKeys();
            int itemId = -1;
            if(resultKey.next()) {
                itemId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            item.setItemId(itemId);

            return item;
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


    public Item updateItemName(Item item, String newItemName) throws SQLException {
        String updateItem = "UPDATE Item SET itemName=? WHERE itemId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateItem);
            updateStmt.setString(1, newItemName);
            updateStmt.setInt(2, item.getItemId());
            updateStmt.executeUpdate();

            item.setItemName(newItemName);
            return item;
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


    public Item delete(Item item) throws SQLException {
        String deleteItem = "DELETE FROM Item WHERE itemId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteItem);
            deleteStmt.setInt(1, item.getItemId());
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


    public Item getItemById(int itemId) throws SQLException {
        String selectPerson = "SELECT itemId,itemName,maxStackSize,vendorPrice,canBeSold FROM Item WHERE itemId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPerson);
            selectStmt.setInt(1, itemId);

            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultItemId = results.getInt("itemId");
                String itemName = results.getString("itemName");
                int maxStackSize = results.getInt("maxStackSize");
                int vendorPrice = results.getInt(("vendorPrice"));
                boolean canBeSold = results.getBoolean("canBeSold");
                return new Item(resultItemId,itemName,maxStackSize,vendorPrice,canBeSold);
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


    public List<Item> getItemsByMaxStackSize(int maxStackSize) throws SQLException {
        List<Item> items = new ArrayList<>();
        String selectItem =
                "SELECT itemId,itemName,maxStackSize,vendorPrice,canBeSold FROM Item WHERE maxStackSize=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectItem);
            selectStmt.setInt(1, maxStackSize);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int itemId = results.getInt("itemId");
                String itemName = results.getString("itemName");
                int vendorPrice = results.getInt(("vendorPrice"));
                boolean canBeSold = results.getBoolean("canBeSold");
                Item item = new Item(itemId,itemName,maxStackSize,vendorPrice,canBeSold);
                items.add(item);
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
        return items;
    }
}
