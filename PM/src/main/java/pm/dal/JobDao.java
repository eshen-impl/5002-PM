package pm.dal;

import pm.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JobDao {
	protected ConnectionManager connectionManager;

	private static JobDao instance = null;
	protected JobDao() {
		connectionManager = new ConnectionManager();
	}
	public static JobDao getInstance() {
		if(instance == null) {
			instance = new JobDao();
		}
		return instance;
	}

	public Job create(Job job) throws SQLException {
		String insert =
			"INSERT INTO Job(jobName, jobLevel, MinLevelExp, MaxLevelExp) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			
			insertStmt = connection.prepareStatement(insert,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, job.getJobName());
			insertStmt.setInt(2, job.getJobLevel());
			insertStmt.setInt(3, job.getMinLevelExp());
			insertStmt.setInt(4, job.getMaxLevelExp());

			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int jobId = -1;
			if(resultKey.next()) {
				jobId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			job.setJobId(jobId);
			return job;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	

	public Job getJobByID(int jobID) throws SQLException {
		String select =
			"SELECT jobId, jobName, jobLevel, MinLevelExp, MaxLevelExp " +
			"FROM Job WHERE jobId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, jobID);
			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultJobID = results.getInt("jobID");
				String jobName = results.getString("jobName");
				int jobLevel = results.getInt("jobLevel");
				int MinLevelExp = results.getInt("MinLevelExp");
				int MaxLevelExp = results.getInt("MaxLevelExp");
		
		
				
				Job job = new Job(resultJobID, jobName, jobLevel, MinLevelExp, MaxLevelExp);
				return job;
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


	
	
	
	
}
