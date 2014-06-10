package test;

import java.io.IOException;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String title = req.getParameter("title");
		String message = req.getParameter("message");
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(address));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("g12950sn@gm.tsuda.ac.jp"));
			((MimeMessage) msg).setSubject(title, "UTF-8");
			msg.setText(message);
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		 resp.sendRedirect("/index.html");
	}
}
