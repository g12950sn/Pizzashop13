package test;

import java.io.IOException;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SuppressWarnings("serial")
public class MailSenderServlet extends HttpServlet {

	/*public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// 送られてきた値の取得
		String address = req.getParameter("address");
		String title = req.getParameter("title");
		String message = req.getParameter("message");
		// 宛先が入力されているか確認
		if (address == null || address.length() == 0) {
			resp.getWriter().println("宛先を入力してください。");
		} else {
			// 合言葉があっているか確認
			else {
			}
		}
	}
*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String address = req.getUserPrincipal().getName();
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		//String address = req.getParameter("address");
		PersistenceManagerFactory factory = PMF.get();
        PersistenceManager manager = factory.getPersistenceManager();
		
        String param1 = req.getParameter("id");
		List<LinkData> list = null;
        if (param1 == null || param1 ==""){
            String query = "select from " + LinkData.class.getName();
            try {
                list = (List<LinkData>)manager.newQuery(query).execute();
            } catch(JDOObjectNotFoundException e){}
        } else {
            try {
                LinkData data = (LinkData)manager.getObjectById(LinkData.class,Long.parseLong(param1));
                list = new ArrayList();
                list.add(data);
            } catch(JDOObjectNotFoundException e){}
        }
        String res = "";
        if (list != null){
            for(LinkData data:list){
                res += data.getTitle() + "さん、あなたの注文はクワトロ" + data.getCount1() +"枚　チーズメルト" + data.getCount2() +"枚　カマンベールミルフィーユ" +
            data.getCount3() +"枚　リストランテ" + data.getCount4() +"枚　トッピングは、チーズ" + data.getCount5() +"枚　スライスオニオン" + data.getCount6() +"枚　ソーセージ" + data.getCount7() +"枚です。";
            }
        }
        res += "ご注文ありがとうございました。";
		String title = "注文内容確認";
		//String message = req.getParameter(res);
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(address));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("g12950sn@gm.tsuda.ac.jp"));
			((MimeMessage) msg).setSubject(title, "UTF-8");
			msg.setText(res);
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		 resp.sendRedirect("/index.html");
	}
}
