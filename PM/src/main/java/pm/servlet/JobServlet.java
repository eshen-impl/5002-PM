package pm.servlet;

import pm.dal.*;
import pm.model.Item;
import pm.model.Job;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/job")
public class JobServlet extends HttpServlet {
    
    protected JobDao jobDao;
    
    @Override
    public void init() throws ServletException {
    	jobDao = JobDao.getInstance();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        // Retrieve and validate itemId.
        String jobIdStr = req.getParameter("jobId");
        if (jobIdStr == null || jobIdStr.trim().isEmpty()) {
            messages.put("result", "Invalid jobId.");
        } else {
            try {
                int jobId = Integer.parseInt(jobIdStr);
                Job job = jobDao.getJobByID(jobId);
                if (job != null) {
                    req.setAttribute("job",job);
                    messages.put("result", "Displaying job with ID: " + jobId);
                } else {
                    messages.put("result", "Job not found.");
                }
            } catch (NumberFormatException e) {
                messages.put("result", "Invalid jobId. Please enter a valid integer.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error accessing database.", e);
            }
        }

        req.getRequestDispatcher("/FindJob.jsp").forward(req, resp);
    }
}
