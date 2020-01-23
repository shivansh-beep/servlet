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
import org.hibernate.engine.jdbc.batch.spi.Batch;

/**
 * Servlet implementation class Detail
 */
@WebServlet("/Detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();

		RequestDispatcher rd;

		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		String bname = req.getParameter("bname");

		int id = 0;
		Criteria cr = ss.createCriteria(BatchDTO.class);
		cr.add(Restrictions.ilike("bname", bname));
		List<BatchDTO> bl = cr.list();
		for (BatchDTO batchDTO : bl) {
			id = batchDTO.getBid();
		}

		BatchDTO b = ss.load(BatchDTO.class, id);
		List<StudEntDTO> slist = b.getSlist();

		pw.print("bname : " + b.getBname() + " " + "<br><br>fname : " + b.getFname() + " ");
		int i = 1;
		for (StudEntDTO studEntDTO : slist) {
			pw.print("<br><br>student name " + i + ": " + studEntDTO.getSname());
			pw.print("<br><br>student contact " + i + " : " + studEntDTO.getContact());
			pw.print("<br><br>student precentage" + i + " : " + studEntDTO.getPercentage());
			pw.print("<br><br>student yop" + i + " : " + studEntDTO.getYOP());
			pw.print("<br><br>student email " + i + " : " + studEntDTO.getSemail()+"<br><br>");
			i++;
		}

		ss.close();
		sf.close();

		rd = req.getRequestDispatcher("index.html");
		rd.include(req, resp);
	}

}
