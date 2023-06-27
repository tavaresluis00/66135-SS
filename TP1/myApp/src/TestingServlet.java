import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/TestingServlet")
public class TestingServlet extends HttpServlet {

	static int counter = 0;

     public void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException
      {
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<H1>The Counter App!</H1>");
        out.println("<H1>Value="+counter+"</H1>");
        out.print("<form action=\"");
        out.print("TestingServlet\" ");
        out.println("method=GET>");
        out.println("<br>");
        out.println("<input type=submit name=increment>");
        out.println("</form>");
        out.println("<br>");
        out.print("<form name=“logout-form” action=\"LogOutServlet\" method=\"GET\">");
        out.println("<input type=submit name=submit value=\"Logout\">");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
        counter ++;
      }


  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
          throws ServletException, IOException
  {
      PrintWriter out = response.getWriter();
      out.println("<HTML>");
      out.println("<HEAD>");
      out.println("</HEAD>");
      out.println("<BODY>");
      out.println("<H1>The Counter App!</H1>");
      out.print("<form name=“testingForm” action=\"LogOutServlet\" method=\"GET\">");
      out.println("<input type=button name=logout value=Logout>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");
    }
}

