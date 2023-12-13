package pm.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pm.model.*;

public class GearJobDao {
	protected ConnectionManager connectionManager;
	private static GearJobDao instance = null;
	protected GearJobDao() {
		connectionManager = new ConnectionManager();
	}
	public static GearJobDao getInstance() {
		if(instance == null) {
			instance = new GearJobDao();
		}
		return instance;
	}
	public GearJob create(GearJob gearJob) throws SQLException {
	    String insertGearJob = "INSERT INTO GearJob(itemId, jobId) VALUES(?, ?);";
	    Connection connection = null;
	    PreparedStatement insertStmt = null;

	    try {
	        connection = connectionManager.getConnection();
	        insertStmt = connection.prepareStatement(insertGearJob);

	        // 从 gearJob 对象获取 itemId 和 jobId
	        insertStmt.setInt(1, gearJob.getGear().getItemId());
	        insertStmt.setInt(2, gearJob.getJob().getJobId());

	        insertStmt.executeUpdate();

	        // 不需要检索自动生成的键，因为它们应该已经存在
	        return gearJob;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (connection != null) {
	            connection.close();
	        }
	        if (insertStmt != null) {
	            insertStmt.close();
	        }
	    }
	}

	public GearJob getGearJobByItemIdJobId(int itemId, int jobId) throws SQLException {
	    // 根据itemId和jobId分别获取Gear和Job对象
	    GearDao gearDao = GearDao.getInstance();
	    JobDao jobDao = JobDao.getInstance();

	    String selectGearJob =
	        "SELECT itemId, jobId FROM GearJob WHERE itemId=? AND jobId=?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectGearJob);
	        selectStmt.setInt(1, itemId);
	        selectStmt.setInt(2, jobId);
	        results = selectStmt.executeQuery();

	        if(results.next()) {
	            // 如果找到匹配的GearJob，使用已检索的Gear和Job对象创建GearJob
	        	Integer resultItemId = results.getInt("itemId");
	        	Integer resultJobId = results.getInt("jobId");
	            GearJob gearJob = new GearJob(gearDao.getGearByItemID(resultItemId), jobDao.getJobByID(resultJobId)); // 假设构造函数接受Gear和Job对象
	            return gearJob;
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
	public GearJob deleteGearJob(GearJob gearJob) throws SQLException {
	    String deleteGearJob = "DELETE FROM GearJob WHERE itemId=? AND jobId=?;";
	    Connection connection = null;
	    PreparedStatement deleteStmt = null;
	    try {
	        connection = connectionManager.getConnection();
	        deleteStmt = connection.prepareStatement(deleteGearJob);
	        deleteStmt.setInt(1, gearJob.getGear().getItemId()); 
	        deleteStmt.setInt(2, gearJob.getJob().getJobId());  

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
