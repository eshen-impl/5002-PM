import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterJobDAO {
    private Connection connection;

    // Constructor to initialize the connection
    public CharacterJobDAO(Connection connection) {
        this.connection = connection;
    }

    // Create method for inserting a new record
    public CharacterJob create(CharacterJob characterJob) throws SQLException {
        String insertQuery = "INSERT INTO CharacterJob (characterId, jobId, currentExp, currentLevel, isUnlocked, isCurrentJob) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, characterJob.getCharacterId());
            pstmt.setInt(2, characterJob.getJobId());
            pstmt.setString(3, characterJob.getCurrentExp());
            pstmt.setInt(4, characterJob.getCurrentLevel());
            pstmt.setBoolean(5, characterJob.isUnlocked());
            pstmt.setBoolean(6, characterJob.isCurrentJob());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating CharacterJob record failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    characterJob.setCharacterJobId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating CharacterJob record failed, no ID obtained.");
                }
            }
        }
        return characterJob;
    }

    // Method for fetching records based on search keys
    public List<CharacterJob> getCharacterJobsByCharacterId(int characterId) throws SQLException {
        List<CharacterJob> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM CharacterJob WHERE characterId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(selectQuery)) {
            pstmt.setInt(1, characterId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    CharacterJob characterJob = mapResultSetToCharacterJob(resultSet);
                    result.add(characterJob);
                }
            }
        }
        return result;
    }

    // Method for updating an attribute for a single record
    public void updateCurrentLevel(CharacterJob characterJob, int newLevel) throws SQLException {
        String updateQuery = "UPDATE CharacterJob SET currentLevel = ? WHERE characterId = ? AND jobId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {
            pstmt.setInt(1, newLevel);
            pstmt.setInt(2, characterJob.getCharacterId());
            pstmt.setInt(3, characterJob.getJobId());
            pstmt.executeUpdate();
        }
    }

    // Method for deleting a record
    public void delete(CharacterJob characterJob) throws SQLException {
        String deleteQuery = "DELETE FROM CharacterJob WHERE characterId = ? AND jobId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, characterJob.getCharacterId());
            pstmt.setInt(2, characterJob.getJobId());
            pstmt.executeUpdate();
        }
    }

    // Helper method to map ResultSet to CharacterJob object
    private CharacterJob mapResultSetToCharacterJob(ResultSet resultSet) throws SQLException {
        CharacterJob characterJob = new CharacterJob();
        characterJob.setCharacterId(resultSet.getInt("characterId"));
        characterJob.setJobId(resultSet.getInt("jobId"));
        characterJob.setCurrentExp(resultSet.getString("currentExp"));
        characterJob.setCurrentLevel(resultSet.getInt("currentLevel"));
        characterJob.setUnlocked(resultSet.getBoolean("isUnlocked"));
        characterJob.setCurrentJob(resultSet.getBoolean("isCurrentJob"));
        return characterJob;
    }
}
