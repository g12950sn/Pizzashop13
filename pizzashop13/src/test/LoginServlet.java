package test;

import java.io.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html; charset=UTF-8");
        UserService userService = UserServiceFactory.getUserService();//UserServiceインスタンスを取得します。
        PrintWriter out = resp.getWriter();
        String thisURL = req.getRequestURI();//このサーブレットが実行されているウェブページのURIを取得
        String Url = resp.encodeURL("/add2.html");
        resp.setContentType("text/html");
        if (req.getUserPrincipal() != null) {//このサーブレットにリクエストを出したプリンシパルを取得(認証していない場合null)
            resp.getWriter().println("<p>こんにちは！あなたは " +
                                     req.getUserPrincipal().getName() +
                                     "という名前でログインしています。 "+"<br>違うアカウントでログインしたい場合は"+ "<a href=\"" +
                                     userService.createLogoutURL(thisURL) +//ログアウト用のGoogle AccountsのURLを取得
                                     "\">こちら</a>"+"<br>注文画面に行きたい場合は<a href=\"" +Url+"\">こちら</a></p>");
        } else {
            resp.getWriter().println("<p>サインインは<a href=\"" +
                                     userService.createLoginURL(thisURL) +//ログイン用のGoogle AccountsのURLを取得
                                     "\">こちら</a>.</p>");
        }
        out.close();
    }
}