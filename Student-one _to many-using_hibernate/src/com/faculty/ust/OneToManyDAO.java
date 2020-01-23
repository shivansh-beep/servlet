package com.faculty.ust;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet("/reg")
public class OneToManyDAO extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		 RequestDispatcher rd;
		 
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		
		 String fname=req.getParameter("fname");
		 String bname=req.getParameter("bname");
		 
		 List<StudEntDTO> list = new LinkedList<StudEntDTO>();
		 
		BatchDTO b = new BatchDTO();
		b.setBname(bname);
		b.setFname(fname);
		b.setSlist(list);
		
		ss.save(b);
		ss.beginTransaction().commit();
		pw.print("<h4 style='color : green' align='center'>registration done<h4>");

		ss.close();
		sf.close();
		rd = req.getRequestDispatcher("index.html");
		rd.include(req, resp);
		
	}
		
	}
