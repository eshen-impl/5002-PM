package pm.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pm.model.*;
import pm.model.Character;


public class CharacterAttributeDao {
  
    protected ConnectionManager connectionManager;
    private static CharacterAttributeDao instance = null;
    protected CharacterAttributeDao() {
        connectionManager = new ConnectionManager();
    }
    public static CharacterAttributeDao getInstance() {
        if(instance == null) {
            instance = new CharacterAttributeDao();
        }
        return instance;
    }

    // 创建 CharacterAttribute
    public CharacterAttribute create(CharacterAttribute characterAttribute) throws SQLException {
        String insertCharacterAttribute = "INSERT INTO CharacterAttribute(characterId, attributeName, attributeValue) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacterAttribute);
            insertStmt.setInt(1, characterAttribute.getCharacter().getCharacterId());
            insertStmt.setString(2, characterAttribute.getAttributes());
            insertStmt.setInt(3, characterAttribute.getAttributeValue());
            insertStmt.executeUpdate();
            return characterAttribute;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(insertStmt != null) {
                insertStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
    }

    // 通过 characterId 和 attributeName 获取 CharacterAttribute
    public CharacterAttribute getCharacterAttributeByIdAndNames(int characterId, String attributeName) throws SQLException {
        String selectCharacterAttribute = "SELECT characterId, attributeName, attributeValue FROM CharacterAttribute WHERE characterId=? AND attributeName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacterAttribute);
            selectStmt.setInt(1, characterId);
            selectStmt.setString(2, attributeName);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultCharacterId = results.getInt("characterId");
                String resultAttributeName = results.getString("attributeName");
                int attributeValue = results.getInt("attributeValue");

                CharacterDao characterDao = CharacterDao.getInstance();
                
                Character character = characterDao.getCharacterById(resultCharacterId); 
                CharacterAttribute characterAttribute = new CharacterAttribute(character, resultAttributeName, attributeValue);
                return characterAttribute;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(results != null) {
                results.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
        return null;
    }
    
    public List<CharacterAttribute> getCharacterAttributeByIds(int characterId) throws SQLException {
		List<CharacterAttribute> characterAttributes = new ArrayList<CharacterAttribute>();
		String select =
			"SELECT characterId, attributeName, attributeValue FROM CharacterAttribute WHERE characterId=? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, characterId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultCharacterId = results.getInt("characterId");
                String resultAttributeName = results.getString("attributeName");
                int attributeValue = results.getInt("attributeValue");
                CharacterDao characterDao = CharacterDao.getInstance();
                
                Character character = characterDao.getCharacterById(resultCharacterId); 
                CharacterAttribute characterAttribute = new CharacterAttribute(character, resultAttributeName, attributeValue);
			
                characterAttributes.add(characterAttribute);
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
		return characterAttributes;
	}
    
    

    // 删除 CharacterAttribute
    public CharacterAttribute delete(CharacterAttribute characterAttribute) throws SQLException {
        String deleteCharacterAttribute = "DELETE FROM CharacterAttribute WHERE characterId=? AND attributeName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCharacterAttribute);
            deleteStmt.setInt(1, characterAttribute.getCharacter().getCharacterId());
            deleteStmt.setString(2, characterAttribute.getAttributes());
            deleteStmt.executeUpdate();

            // 返回 null 表示对象已被删除
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(deleteStmt != null) {
                deleteStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
    }
    
    
    public CharacterAttribute updateAttributeValue(CharacterAttribute characterAttribute, int attributeValue) throws SQLException {
        String updateQuery = "UPDATE CharacterAttribute SET attributeValue = ? WHERE characterId = ? AND attributeName = ?";

        
        Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connectionManager.getConnection();
			 pstmt = connection.prepareStatement(updateQuery);
	            pstmt.setInt(1, attributeValue);
	            pstmt.setInt(2, characterAttribute.getCharacter().getCharacterId());
	            pstmt.setString(3, characterAttribute.getAttributes());
            pstmt.executeUpdate();
            characterAttribute.setAttributeValue(attributeValue);;;
			return characterAttribute;
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
}

