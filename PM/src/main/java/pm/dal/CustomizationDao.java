package pm.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pm.model.*;

import pm.model.Character;

public class CustomizationDao {
    // 单例模式
    protected ConnectionManager connectionManager;
    private static CustomizationDao instance = null;
    protected CustomizationDao() {
        connectionManager = new ConnectionManager();
    }
    public static CustomizationDao getInstance() {
        if(instance == null) {
            instance = new CustomizationDao();
        }
        return instance;
    }

    // 创建 Customization
    public Customization create(Customization customization) throws SQLException {
        String insertCustomization = "INSERT INTO Customization(itemId, dyeColor, isHighQuality, `condition`, madeBy) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCustomization, PreparedStatement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, customization.getItem().getItemId());
            insertStmt.setString(2, customization.getColor());
            insertStmt.setString(3, customization.getIsHighQuality());
            insertStmt.setFloat(4, customization.getCondition());
            insertStmt.setInt(5, customization.getCharacter().getCharacterId());
            insertStmt.executeUpdate();

            // 获取自动生成的主键
            resultKey = insertStmt.getGeneratedKeys();
            int customizationId = -1;
            if(resultKey.next()) {
                customizationId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            customization.setCustomizationId(customizationId);
            return customization;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(resultKey != null) {
                resultKey.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
    }

    // 通过 customizationId 获取 Customization
    public Customization getCustomizationById(int customizationId) throws SQLException {
        String selectCustomization = "SELECT customizationId, itemId, dyeColor,isHighQuality, `condition`, madeBy FROM Customization WHERE customizationId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCustomization);
            selectStmt.setInt(1, customizationId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int itemId = results.getInt("itemId");
                String dyeColor = results.getString("dyeColor");
                float condition = results.getFloat("condition");
                int madeBy = results.getInt("madeBy");
                String quality = results.getString("isHighQuality");
                

                Item item = ItemDao.getInstance().getItemById(itemId); 
                Character character = CharacterDao.getInstance().getCharacterById(madeBy);
                Customization customization = new Customization(customizationId, item, dyeColor, condition, quality, character);
                return customization;
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

    // 删除 Customization
    public Customization delete(Customization customization) throws SQLException {
        String deleteCustomization = "DELETE FROM Customization WHERE customizationId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCustomization);
            deleteStmt.setInt(1, customization.getCustomizationId());
            deleteStmt.executeUpdate();

          
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
}
