package com.registraion.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

@WebServlet("/GmailServlet")
public class GmailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String sid= request.getParameter("id");
		int id=Integer.parseInt(sid);
		pw.print("from gmail");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
			PreparedStatement pst=con.prepareStatement("select * from gmail where id=? ");
			pst.setInt(1, id);
		
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
			
			 GmailServlet.send("choudharyshivam.146@gmail.com","ruchi@12345",rs.getString("email"),"hello javatpoint", "How r u?");
		} }
		
		
		catch (ClassNotFoundException | SQLException e) {
pw.print("<h3>gsdhsghdsjhs</h3>");
			e.printStackTrace();
		}
		
	}
	 public static void send(String from,String password,String to,String sub,String msg){  
         //Get properties object    
         Properties props = new Properties();    
         props.put("mail.smtp.host", "smtp.gmail.com");    
         props.put("mail.smtp.socketFactory.port", "465");    
         props.put("mail.smtp.socketFactory.class",    
                   "javax.net.ssl.SSLSocketFactory");    
         props.put("mail.smtp.auth", "true");    
         props.put("mail.smtp.port", "465");    
         //get Session   
         Session session = Session.getDefaultInstance(props,    
          new javax.mail.Authenticator() {    
          protected PasswordAuthentication getPasswordAuthentication() {    
          return new PasswordAuthentication(from,password);  
          }    
         });    
         //compose message    
         try {    
          MimeMessage message = new MimeMessage(session);    
          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
          message.setSubject(sub);    
          message.setText(msg);    
          //send message  
          Transport.send(message);    
          System.out.println("message sent successfully");    
         } catch (MessagingException e) {throw new RuntimeException(e);}    
            
   }  

}
