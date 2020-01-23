package com.faculty.ust;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * Servlet implementation class AddStDrive
 */
@WebServlet("/AddStDrive")
public class AddStDrive extends HttpServlet {
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
		Session ss = sf.openSession();

		String id=req.getParameter("id");
		int cid=Integer.parseInt(id);

		CompanyDTO cd=ss.load(CompanyDTO.class, cid);
		
		int yop=cd.getYop();
		
		double per=cd.getPercentage();

		Criteria cr=ss.createCriteria(StudEntDTO.class);
		
		cr.add(Restrictions.ge("percentage", per));
		cr.add(Restrictions.ge("YOP", yop));
		
		List<StudEntDTO> list=cr.list();

		pw.print("<h1 align='center'> Student Details</h1>");
		pw.print("<center><table ><table border='1'><tr><th> Student Name </th><th> Percentage </th> <th> YOP </th><th> Email </th><th> Contact</th> <th> Email </th></tr></center>");

		for (StudEntDTO sd : list) {
			pw.print("<tr><td>"+sd.getSname()+"</td> <td>"+sd.getPercentage()+"</td> <td>"+sd.getYOP()+"</td>   <td>"+sd.getSemail()+"</td> "
					+ "<td>"+sd.getContact()+"</td>   <td>"+"<a href='SendMail?email='"+sd.getSemail()+"'>"+sd.getSemail()+"</a>"+"</td></tr>");
		}
		pw.print("</table><a href='SendAll?id="+cd.getCid()+"'>send mail in batch</a>");

		ss.close();
		sf.close();

		
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("index.html");
		rd.include(req, resp);
	
	}

}
