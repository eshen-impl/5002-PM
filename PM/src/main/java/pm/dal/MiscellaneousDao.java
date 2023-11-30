package pm.dal;

import pm.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MiscellaneousDao extends ItemDao{
    private static MiscellaneousDao instance = null;
    protected MiscellaneousDao() {
        super();
    }
    public static MiscellaneousDao getInstance() {
        if(instance == null) {
            instance = new MiscellaneousDao();
        }
        return instance;
    }

    public Miscellaneous create(Miscellaneous miscellaneous) throws SQLException {
        create(new Item(miscellaneous.getItemId(), miscellaneous.getItemName(),
                miscellaneous.getMaxStackSize(), miscellaneous.getVendorPrice(), miscellaneous.getCanBeSold()));

        String insertMiscellaneous = "INSERT INTO Miscellaneous(itemId, description) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertMiscellaneous);
            insertStmt.setInt(1, miscellaneous.getItemId());
            insertStmt.setString(2, miscellaneous.getDescription());
            insertStmt.executeUpdate();
            return miscellaneous;
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


    public Miscellaneous updateDescription(Miscellaneous miscellaneous, String newDescription) throws SQLException {
        String updateMiscellaneous = "UPDATE Miscellaneous SET description=? WHERE itemId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateMiscellaneous);
            updateStmt.setString(1, newDescription);
            updateStmt.setInt(2,miscellaneous.getItemId());
            updateStmt.executeUpdate();

            miscellaneous.setDescription(newDescription);
            return miscellaneous;
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


    public Miscellaneous delete(Miscellaneous miscellaneous) throws SQLException {
        String deleteMiscellaneous = "DELETE FROM Miscellaneous WHERE itemId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteMiscellaneous);
            deleteStmt.setInt(1, miscellaneous.getItemId());
            deleteStmt.executeUpdate();

            super.delete(miscellaneous);

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

    public Miscellaneous getMiscellaneoussById(int itemId) throws SQLException {
        String selectMiscellaneous =
                "SELECT Miscellaneous.itemId AS itemId,itemName,maxStackSize,vendorPrice,canBeSold,description " +
                        "FROM Miscellaneous INNER JOIN Item " +
                        "  ON Miscellaneous.itemId = Item.itemId " +
                        "WHERE Miscellaneous.itemId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectMiscellaneous);
            selectStmt.setInt(1, itemId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultItemId = results.getInt("itemId");
                String itemName = results.getString("itemName");
                int maxStackSize = results.getInt("maxStackSize");
                int vendorPrice = results.getInt(("vendorPrice"));
                boolean canBeSold = results.getBoolean("canBeSold");
                String description = results.getString("description");
                return new Miscellaneous(resultItemId,itemName,maxStackSize,vendorPrice,canBeSold,description);
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

    public List<Miscellaneous> getMiscellaneousByDescription(String description)
            throws SQLException {
        List<Miscellaneous> miscellaneous_ = new ArrayList<>();
        String selectMiscellaneous =
                "SELECT Miscellaneous.itemId AS itemId,itemName,maxStackSize,vendorPrice,canBeSold,itemLevel,description " +
                        "FROM Miscellaneous INNER JOIN Item " +
                        "  ON Miscellaneous.itemId = Item.itemId " +
                        "WHERE Miscellaneous.description =?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectMiscellaneous);
            selectStmt.setString(1, description);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int itemId = results.getInt("itemId");
                String itemName = results.getString("itemName");
                int maxStackSize = results.getInt("maxStackSize");
                int vendorPrice = results.getInt(("vendorPrice"));
                boolean canBeSold = results.getBoolean("canBeSold");
                Miscellaneous miscellaneous = new Miscellaneous(itemId,itemName,maxStackSize,vendorPrice,canBeSold,description);
                miscellaneous_.add(miscellaneous);
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
        return miscellaneous_;
    }
}
