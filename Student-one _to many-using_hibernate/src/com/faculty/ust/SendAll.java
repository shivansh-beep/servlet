package com.faculty.ust;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import javax.mail.Session;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * Servlet implementation class SendAll
 */
@WebServlet("/SendAll")
public class SendAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		org.hibernate.Session ss = sf.openSession();
	

		String id=req.getParameter("id");
		int cid=Integer.parseInt(id);

		CompanyDTO cd=ss.load(CompanyDTO.class, cid);
		
		int yop=cd.getYop();
		
		double per=cd.getPercentage();

		Criteria cr=ss.createCriteria(StudEntDTO.class);
		
		cr.add(Restrictions.ge("percentage", per));
		cr.add(Restrictions.ge("YOP", yop));
		
		List<StudEntDTO> list=cr.list();
	

		for (StudEntDTO sd : list) {
			SendAll.send("choudharyshivam.146@gmail.com", "ruchi@12345", sd.getSemail(), "hello javatpoint", "How r u?");
		
		}
	}
		public static void send(String from, String password, String to, String sub, String msg) {
			// Get properties object
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			// get Session
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});
			try {
				MimeMessage message = new MimeMessage(session);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(sub);
				message.setText(msg);
				// send message
				Transport.send(message);
				System.out.println("message sent successfully");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		
		}
}


