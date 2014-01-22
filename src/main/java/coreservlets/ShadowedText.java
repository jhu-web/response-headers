package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that generates JPEG images representing a designated message with an oblique
 * shadowed version behind it.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the coreservlets.com
 * tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */
@WebServlet("/shadowed-text")
public class ShadowedText
  extends HttpServlet {

  private static final long serialVersionUID = 4826952298684377852L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    String wantsList = request.getParameter("showList");
    if (wantsList != null) {
      showFontList(response);
    }
    else {
      String message = request.getParameter("message");
      if ((message == null) || (message.length() == 0)) {
        message = "Missing 'message' parameter";
      }
      String fontName = request.getParameter("fontName");
      if ((fontName == null) || (fontName.length() == 0)) {
        fontName = "Serif";
      }
      String fontSizeString = request.getParameter("fontSize");
      int fontSize;
      try {
        fontSize = Integer.parseInt(fontSizeString);
      }
      catch (NumberFormatException nfe) {
        fontSize = 90;
      }
      response.setContentType("image/jpeg");
      MessageImage.writeJPEG(MessageImage.makeMessageImage(message, fontName, fontSize),
                             response.getOutputStream());
    }
  }

  private void showFontList(HttpServletResponse response)
    throws IOException {
    PrintWriter out = response.getWriter();
    String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
    String title = "Fonts Available on Server";
    out.println(docType + "<HTML>\n"
                + "<HEAD><TITLE>"
                + title
                + "</TITLE></HEAD>\n"
                + "<BODY BGCOLOR=\"#FDF5E6\">\n"
                + "<H1 ALIGN=CENTER>"
                + title
                + "</H1>\n"
                + "<UL>");
    String[] fontNames = MessageImage.getFontNames();
    for (int i = 0; i < fontNames.length; i++) {
      out.println("  <LI>" + fontNames[i]);
    }
    out.println("</UL>\n" + "</BODY></HTML>");
  }
}
