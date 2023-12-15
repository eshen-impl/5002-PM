package pm.dal;
import pm.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CharacterJobDao {


    protected ConnectionManager connectionManager;
    private static CharacterJobDao instance = null;
    protected CharacterJobDao() {
        connectionManager = new ConnectionManager();
    }
    public static CharacterJobDao getInstance() {
        if(instance == null) {
            instance = new CharacterJobDao();
        }
        return instance;
    }


    // Create method for inserting a new record
    public CharacterJob create(CharacterJob characterJob) throws SQLException {
        String insertQuery = "INSERT INTO CharacterJob (characterId, jobId, currentExp, isUnlocked, isCurrentJob) " +
                "VALUES (?, ?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			pstmt = connection.prepareStatement(insertQuery);
			
			
            pstmt.setInt(1, characterJob.getCharacter().getCharacterId());
            pstmt.setInt(2, characterJob.getJob().getJobId());
            pstmt.setLong(3, characterJob.getCurrentExp());
            pstmt.setBoolean(4, characterJob.isUnlocked());
            pstmt.setBoolean(5, characterJob.isCurrentJob());


            pstmt.executeUpdate();
           
		} catch (SQLException e) {
			        e.printStackTrace();
			        throw e;
			
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}

		}


        return characterJob;
    }

    // Method for fetching records based on search keys
    public List<CharacterJob> getCharacterJobsByCharacterId(int characterId) throws SQLException {
        List<CharacterJob> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM CharacterJob WHERE characterId = ?";
        Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectQuery);
			selectStmt.setInt(1, characterId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				CharacterJob characterJob = mapResultSetToCharacterJob(results);
                result.add(characterJob);
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

        return result;
    }

    // Method for updating an attribute for a single record
    public CharacterJob updateCurrentExp(CharacterJob characterJob, long newExp) throws SQLException {
        String updateQuery = "UPDATE CharacterJob SET currentExp = ? WHERE characterId = ? AND jobId = ?";

        
        Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			 pstmt = connection.prepareStatement(updateQuery);
	            pstmt.setLong(1, newExp);
	            pstmt.setInt(2, characterJob.getCharacter().getCharacterId());
	            pstmt.setInt(3, characterJob.getJob().getJobId());
            pstmt.executeUpdate();
            characterJob.setCurrentExp(newExp);
			return characterJob;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		}
        
    }
    
    public void updateJob(CharacterJob characterJob, Integer newjobId) throws SQLException {
        String updateQuery = "UPDATE CharacterJob SET jobId = ? WHERE characterId = ? AND jobId = ?";

        
        Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			 pstmt = connection.prepareStatement(updateQuery);
	            pstmt.setInt(1, newjobId);
	            pstmt.setInt(2, characterJob.getCharacter().getCharacterId());
	            pstmt.setInt(3, characterJob.getJob().getJobId());
            pstmt.executeUpdate();

			return;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		}
        
    }

    // Method for deleting a record
    public void delete(CharacterJob characterJob) throws SQLException {
        String deleteQuery = "DELETE FROM CharacterJob WHERE characterId = ? AND jobId = ?";
        

        Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			pstmt = connection.prepareStatement(deleteQuery);
            pstmt.setInt(1, characterJob.getCharacter().getCharacterId());
            pstmt.setInt(2, characterJob.getJob().getJobId());
            pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		}
        
    }
 // Method for fetching a single CharacterJob record based on characterId and jobId
    public CharacterJob getCharacterJobByCharacterIdAndJobId(int characterId, int jobId) throws SQLException {
        String selectQuery = "SELECT * FROM CharacterJob WHERE characterId = ? AND jobId = ?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setInt(1, characterId);
            selectStmt.setInt(2, jobId);
            results = selectStmt.executeQuery();

            if (results.next()) {
                return mapResultSetToCharacterJob(results);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (results != null) {
                results.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return null; // 当找不到对应的CharacterJob时返回null
    }


    // Helper method to map ResultSet to CharacterJob object
    private CharacterJob mapResultSetToCharacterJob(ResultSet resultSet) throws SQLException {
        CharacterJob characterJob = new CharacterJob();
        
        
        CharacterDao characterDao = CharacterDao.getInstance();
      
        JobDao jobDao = JobDao.getInstance();
        

        characterJob.setCharacter(characterDao.getCharacterById(resultSet.getInt("characterId")));
        characterJob.setJob(jobDao.getJobByID(resultSet.getInt("jobId")));
        characterJob.setCurrentExp(resultSet.getLong("currentExp"));
        characterJob.setUnlocked(resultSet.getBoolean("isUnlocked"));
        characterJob.setCurrentJob(resultSet.getBoolean("isCurrentJob"));
        return characterJob;
    }
}