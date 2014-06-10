package test;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AddLinkDataServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
    @Override
    protected void doGet(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("no url...");
    }
 
    @Override
    protected void doPost(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("title");
        String url = req.getParameter("url");
        String pass = req.getParameter("comment");
        String count1 = req.getParameter("count1");
        int num1 = Integer.parseInt(count1);
         int num2=num1*2400;
        String count2 = req.getParameter("count2");
        int num3 = Integer.parseInt(count2);
        int num4 = num3*3000;
        String count3 = req.getParameter("count3");
        int num5 = Integer.parseInt(count3);
        int num6 = num5*2700;
        String count4 = req.getParameter("count4");
        int num7 = Integer.parseInt(count4);
        int num8 = num7*3400;
        String count5 = req.getParameter("count5");
        int num9 = Integer.parseInt(count5);
        int num10 = num9*200;
        String count6 = req.getParameter("count6");
        int num11 = Integer.parseInt(count6);
        int num12 = num11*150;
        String count7 = req.getParameter("count7");
        int num13 = Integer.parseInt(count7);
        int num14 = num13*300;
        int num15 = num2+num4+num6+num8+num10+num12+num14;
        String total = String.valueOf(num15);

        LinkData data = new LinkData(name,url,pass,count1,count2,count3,count4,count5,count6,count7,total);
        PersistenceManagerFactory factory = PMF.get();
        PersistenceManager manager = factory.getPersistenceManager();
        try {
            manager.makePersistent(data);
        } finally {
            manager.close();
        }
        resp.sendRedirect("/exit.html");
    }

}
