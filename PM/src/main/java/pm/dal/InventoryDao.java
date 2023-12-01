package pm.dal;
import pm.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDao {
    private Connection connection;

    // Constructor to initialize the connection
    public InventoryDao(Connection connection) {
        this.connection = connection;
    }

    // Create method for inserting a new record
    public Inventory create(Inventory inventory) throws SQLException {
        String insertQuery = "INSERT INTO Inventory (characterId, slotId, customizationId, itemId, quantity, isEquipped) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, inventory.getCharacterId());
            pstmt.setInt(2, inventory.getSlotId());
            pstmt.setInt(3, inventory.getCustomizationId());
            pstmt.setInt(4, inventory.getItemId());
            pstmt.setInt(5, inventory.getQuantity());
            pstmt.setBoolean(6, inventory.isEquipped());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Inventory record failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    inventory.setInventoryId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating Inventory record failed, no ID obtained.");
                }
            }
        }
        return inventory;
    }

    // Method for fetching records based on search keys
    public List<Inventory> getInventoryByCharacterId(int characterId) throws SQLException {
        List<Inventory> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM Inventory WHERE characterId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(selectQuery)) {
            pstmt.setInt(1, characterId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Inventory inventory = mapResultSetToInventory(resultSet);
                    result.add(inventory);
                }
            }
        }
        return result;
    }

    // Method for updating an attribute for a single record
    public void updateQuantity(Inventory inventory, int newQuantity) throws SQLException {
        String updateQuery = "UPDATE Inventory SET quantity = ? WHERE characterId = ? AND slotId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, inventory.getCharacterId());
            pstmt.setInt(3, inventory.getSlotId());
            pstmt.executeUpdate();
        }
    }

    // Method for deleting a record
    public void delete(Inventory inventory) throws SQLException {
        String deleteQuery = "DELETE FROM Inventory WHERE characterId = ? AND slotId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, inventory.getCharacterId());
            pstmt.setInt(2, inventory.getSlotId());
            pstmt.executeUpdate();
        }
    }

    // Helper method to map ResultSet to Inventory object
    private Inventory mapResultSetToInventory(ResultSet resultSet) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setCharacterId(resultSet.getInt("characterId"));
        inventory.setSlotId(resultSet.getInt("slotId"));
        inventory.setCustomizationId(resultSet.getInt("customizationId"));
        inventory.setItemId(resultSet.getInt("itemId"));
        inventory.setQuantity(resultSet.getInt("quantity"));
        inventory.setEquipped(resultSet.getBoolean("isEquipped"));
        return inventory;
    }
}