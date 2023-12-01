import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterSlotDAO {
    private Connection connection;

    // Constructor to initialize the connection
    public CharacterSlotDAO(Connection connection) {
        this.connection = connection;
    }

    // Create method for inserting a new record
    public CharacterSlot create(CharacterSlot characterSlot) throws SQLException {
        String insertQuery = "INSERT INTO CharacterSlot (characterId, slotType, equippedItem, customization) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, characterSlot.getCharacterId());
            pstmt.setString(2, characterSlot.getSlotType());
            pstmt.setInt(3, characterSlot.getEquippedItem());
            pstmt.setInt(4, characterSlot.getCustomization());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating CharacterSlot record failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    characterSlot.setSlotId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating CharacterSlot record failed, no ID obtained.");
                }
            }
        }
        return characterSlot;
    }

    // Method for fetching records based on search keys
    public List<CharacterSlot> getCharacterSlotsByCharacterId(int characterId) throws SQLException {
        List<CharacterSlot> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM CharacterSlot WHERE characterId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(selectQuery)) {
            pstmt.setInt(1, characterId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    CharacterSlot characterSlot = mapResultSetToCharacterSlot(resultSet);
                    result.add(characterSlot);
                }
            }
        }
        return result;
    }

    // Method for updating an attribute for a single record
    public void updateEquippedItem(CharacterSlot characterSlot, int newEquippedItem) throws SQLException {
        String updateQuery = "UPDATE CharacterSlot SET equippedItem = ? WHERE characterId = ? AND slotType = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {
            pstmt.setInt(1, newEquippedItem);
            pstmt.setInt(2, characterSlot.getCharacterId());
            pstmt.setString(3, characterSlot.getSlotType());
            pstmt.executeUpdate();
        }
    }

    // Method for deleting a record
    public void delete(CharacterSlot characterSlot) throws SQLException {
        String deleteQuery = "DELETE FROM CharacterSlot WHERE characterId = ? AND slotType = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, characterSlot.getCharacterId());
            pstmt.setString(2, characterSlot.getSlotType());
            pstmt.executeUpdate();
        }
    }

    // Helper method to map ResultSet to CharacterSlot object
    private CharacterSlot mapResultSetToCharacterSlot(ResultSet resultSet) throws SQLException {
        CharacterSlot characterSlot = new CharacterSlot();
        characterSlot.setCharacterId(resultSet.getInt("characterId"));
        characterSlot.setSlotType(resultSet.getString("slotType"));
        characterSlot.setEquippedItem(resultSet.getInt("equippedItem"));
        characterSlot.setCustomization(resultSet.getInt("customization"));
        return characterSlot;
    }
}
