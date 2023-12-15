package pm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pm.dal.*;
import pm.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





@WebServlet("/expupdate")
public class ExpUpdate extends HttpServlet {
	protected CharacterJobDao characterJobDao;
	protected JobDao jobDao;
	protected int id;
	@Override
	public void init() throws ServletException {
		characterJobDao = CharacterJobDao.getInstance();
		jobDao = JobDao.getInstance();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Map<String, String> messages = new HashMap<>();
		    request.setAttribute("messages", messages);
		    

		    try {
		       
		        HttpSession session = request.getSession();
		        String characterIdStr = (String) session.getAttribute("characterId");
		        int characterId = Integer.parseInt(characterIdStr);
		        String jobIdStr = request.getParameter("jobId");
		        
		        int jobId = Integer.parseInt(jobIdStr);
		        id = jobId;
		        CharacterJob characterJob = characterJobDao.getCharacterJobByCharacterIdAndJobId(characterId, jobId);
		       
		        messages.put("success", "Successfully get Character ID: " + characterId + " and Job ID: " + jobId);
		    } catch (NumberFormatException | SQLException e) {
		        e.printStackTrace();
		        messages.put("error", "Error updating Exp: " + e.getMessage());
		    }

		    request.getRequestDispatcher("/ExpUpdate.jsp").forward(request, response);
		}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<>();
	    request.setAttribute("messages", messages);
	    
	    String newExpStr = request.getParameter("exp1");



	    try {
		    if (newExpStr == null || newExpStr.trim().isEmpty()) {
		        messages.put("success", "Please enter a valid Exp value.");

		    } else {
		        long newExp = Long.parseLong(newExpStr);
		        
	            if (newExp < 0) {
	            	
	                throw new NumberFormatException("New Exp cannot be negative.");
	            }
		        HttpSession session = request.getSession();
		        String characterIdStr = (String) session.getAttribute("characterId");
		        int characterId = Integer.parseInt(characterIdStr);
		       
		      
	
		        CharacterJob characterJob = characterJobDao.getCharacterJobByCharacterIdAndJobId(characterId, id);

		        characterJobDao.updateCurrentExp(characterJob, newExp); 
		        request.setAttribute("latestExp", newExp);
		        List<Job> jobs = jobDao.getJobByName(characterJob.getJob().getJobName());
		        
		        for (Job job : jobs) {
		        	long minExp = job.getMinLevelExp();
		        	long maxExp = job.getMaxLevelExp();
		        	if (newExp>=minExp && newExp<maxExp ) {
		        		int newJobId = job.getJobId();
		        		if (newJobId!=characterJob.getJob().getJobId()) {
		        			characterJobDao.updateJob(characterJob, newJobId);
		        			id = newJobId;
		        			messages.put("level", "LevelUp: " + job.getJobLevel());
		        		}
		        	}
		        }
		        
		        
		        messages.put("success", "Successfully updated Exp for Character ID: " + characterId + " and Job ID: " + id);
			}
	    } catch (IllegalArgumentException e) {
            messages.put("success", "Invalid input: " + e.getMessage());
	    } catch ( SQLException e) {
	        e.printStackTrace();
	        messages.put("success", "Error updating Exp: " + e.getMessage());
	    }

	    request.getRequestDispatcher("/ExpUpdate.jsp").forward(request, response);
	}

}
