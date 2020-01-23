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
 * Servlet implementation class AddStudents
 */
@WebServlet("/AddStudents")
public class AddStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();

		RequestDispatcher rd;
		RequestDispatcher rd1;

		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		
		String sname=req.getParameter("sname");
		String ct=req.getParameter("ct");
		String em=req.getParameter("em");
		String yp=req.getParameter("yop");
		String pc=req.getParameter("pc");
		
		String bname=req.getParameter("bname");
		
		int id=0;
		Criteria cr=ss.createCriteria(BatchDTO.class);
		cr.add(Restrictions.ilike("bname", bname));
		List<BatchDTO> bl=cr.list();
		for (BatchDTO batchDTO : bl) {
			id=batchDTO.getBid();
		}
		BatchDTO b=ss.load(BatchDTO.class, id);
		List<StudEntDTO> slist=b.getSlist();
		StudEntDTO s=new StudEntDTO();
		  s.setSname(sname);
		  
		 boolean b2=yp.isEmpty();
		 boolean b1=pc.isEmpty();
		 if(!b2 && !b1){
			 
		 int yop=Integer.parseInt(yp);
		 s.setYOP(yop);
		 double percentage=Double.parseDouble(pc);
		 s.setPercentage(percentage);
		  s.setSemail(em);
		  s.setContact(ct);
		  slist.add(s);
		  b.setSlist(slist);
		  ss.save(b);
		  
			ss.beginTransaction().commit();
			pw.print("<h4 style='color : green' align='center'>registration done<h4>");
			 rd1 = req.getRequestDispatcher("StudentServlet");
			rd1.include(req, resp);
		 }
		
		 else{
			 pw.print("<center> <h4 style=color:red>Star field can not be left empty</h4></center>");
			rd = req.getRequestDispatcher("StudentServlet");
			rd.include(req, resp);
		 }
		  ss.close();
		  sf.close();
	}

}
