package pm.dal;

import pm.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;


/**
 * Data access object (DAO) class to interact with the underlying Item table in your MySQL
 * instance. This is used to store {@link Item} into your MySQL instance and retrieve
 * {@link Item} from MySQL instance.
 */
public class ItemDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
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

    /**
     * Save the Item instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
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
            // Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
            // statements, and it returns an int for the row counts affected (or 0 if the
            // statement returns nothing).
            insertStmt.executeUpdate();
            resultKey = insertStmt.getGeneratedKeys();
            int itemId = -1;
            if(resultKey.next()) {
                itemId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            item.setItemId(itemId);

            // Note 1: if this was an UPDATE statement, then the person fields should be
            // updated before returning to the caller.
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

    /**
     * Update the itemName of the Item instance.
     * This runs a UPDATE statement.
     */
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

            // Update the person param before returning to the caller.
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

    /**
     * Delete the Item instance.
     * This runs a DELETE statement.
     */
    public Item delete(Item item) throws SQLException {
        String deleteItem = "DELETE FROM Item WHERE itemId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteItem);
            deleteStmt.setInt(1, item.getItemId());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Item instance.
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

    /**
     * Get the Item record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Item instance.
     */
    public Item getItemById(int itemId) throws SQLException {
        String selectPerson = "SELECT itemId,itemName,maxStackSize,vendorPrice,canBeSold FROM Item WHERE itemId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPerson);
            selectStmt.setInt(1, itemId);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
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

    /**
     * Get the matching Item records by fetching from your MySQL instance.
     * This runs a SELECT statement and returns a list of matching Item.
     */
    public List<Item> getItemFromMaxStackSize(int maxStackSize) throws SQLException {
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
