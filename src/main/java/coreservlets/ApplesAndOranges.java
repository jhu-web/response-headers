package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that creates Excel spreadsheet comparing apples and oranges.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the coreservlets.com
 * tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */
@WebServlet("/apples-and-oranges")
public class ApplesAndOranges
  extends HttpServlet {

  private static final long serialVersionUID = -2698602370162778806L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("application/vnd.ms-excel");
    PrintWriter out = response.getWriter();
    out.println("\tQ1\tQ2\tQ3\tQ4\tTotal");
    out.println("Apples\t78\t87\t92\t29\t=SUM(B2:E2)");
    out.println("Oranges\t77\t86\t93\t30\t=SUM(B3:E3)");
  }
}
