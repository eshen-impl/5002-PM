package pm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pm.model.*;
import pm.model.Character;
import pm.dal.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/characterjob")
public class CharacterJobView extends HttpServlet {
	protected CharacterJobDao characterJobDao;
	@Override
	public void init() throws ServletException {
		characterJobDao = CharacterJobDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<String, String>();
	    request.setAttribute("messages", messages);
	    String characterIdStr = request.getParameter("characterId");
	    int cid = Integer.parseInt(characterIdStr);
	    List<CharacterJob> characterJobs = new ArrayList<CharacterJob>();
	    try {
	    	
	    	characterJobs = characterJobDao.getCharacterJobsByCharacterId(cid);
	    	//System.out.println(characterJobs.toString());
	    }catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
	   
	    HttpSession session = request.getSession();
	    session.setAttribute("characterId", characterIdStr);

	    request.setAttribute("characterJobs", characterJobs);
	    request.getRequestDispatcher("/CharacterJobs.jsp").forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
