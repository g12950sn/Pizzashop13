package test;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        UserService userService = UserServiceFactory.getUserService();

        String thisURL = req.getRequestURI();

        resp.setContentType("text/html");
        if (req.getUserPrincipal() != null) {
            resp.getWriter().println("<p>こんにちは！あなたは " +
                                     req.getUserPrincipal().getName() +
                                     "という名前でログインしています。   <a href=\"" +
                                     userService.createLogoutURL(thisURL) +
                                     "\">サインアウト</a>.</p>");
        } else {
            resp.getWriter().println("<p>こんにちは！こちらから <a href=\"" +
                                     userService.createLoginURL(thisURL) +
                                     "\">サインイン</a>してください！</p>");
        }
    }
}