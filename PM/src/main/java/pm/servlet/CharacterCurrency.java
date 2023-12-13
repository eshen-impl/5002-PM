package pm.servlet;

import pm.dal.*;
import javax.servlet.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/charactercurrency")
public class CharacterCurrency extends HttpServlet {
    protected CharacterCurrencyDao characterCurrencyDao;

    @Override
    public void init() throws ServletException {
        characterCurrencyDao = CharacterCurrencyDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate UserName.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
            messages.put("title", "BlogPosts for " + userName);
        }

        // Retrieve BlogUsers, and store in the request.
        List<BlogPosts> blogPosts = new ArrayList<BlogPosts>();
        try {
            BlogUsers blogUser = new BlogUsers(userName);
            blogPosts= blogPostsDao.getBlogPostsForUser(blogUser);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("" +
                "blogPosts", blogPosts);
        req.getRequestDispatcher("/CharacterCurrency.jsp").forward(req, resp);
    }
}
