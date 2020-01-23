package com.faculty.ust;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
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
 * Servlet implementation class com
 */
@WebServlet("/com")
public class CompanyServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		 RequestDispatcher rd;
		 
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		
		 String cname=req.getParameter("cname");
		 String vacancies=req.getParameter("vacancy");
		 String yp=req.getParameter("yop");
		 String percentage=req.getParameter("percentage");
		 String jd=req.getParameter("jd");
		 
		 CompanyDTO cdto=new CompanyDTO();
		 
		 boolean b=vacancies.isEmpty();
		 boolean b1=yp.isEmpty();
		 boolean b2=percentage.isEmpty();
		 if(!b && !b1 && !b2){
		 int vc=Integer.parseInt(vacancies);
		 cdto.setVacancy(vc);
		 
		
		 double pc=Double.parseDouble(percentage);
		 cdto.setPercentage(pc);
		 int yop=Integer.parseInt(yp);
			cdto.setYop(yop);
			cdto.setCname(cname);
			cdto.setJobDescription(jd);
			pw.print("<h4 style='color : green' align='center'>requirment details filled <h4>");
			ss.save(cdto);
			ss.beginTransaction().commit();
		 }else{
			 pw.print("<center> <h4 style=color:red>Star field can not be left empty</h4></center>");
		 }
		
		ss.close();
		sf.close();
		rd = req.getRequestDispatcher("company.html");
		rd.include(req, resp);
	}

}
