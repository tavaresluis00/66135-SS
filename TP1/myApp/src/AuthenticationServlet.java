import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet{
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
        out.println("<H1>Log In</H1>");
        out.println("<br>");
        out.print("<form name=“AuthenticateForm” action=\"AuthenticationServlet\" method=\"POST\">");
        out.println("<input type=text name=username size=35 value=username>");
        out.println("<br>");
        out.println("<input type=password name=password size=35>");
        out.println("<br>");
        out.println("<input type=submit name=submit>");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        Authenticator auth = (Authenticator) getServletContext().getAttribute("authenticator");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<H1>The Counter App!</H1>");
        try {
            if(auth.log_in(username, password)){
                out.print("<form name=“AuthenticateForm” action=\"TestingServlet\" method=\"GET\">");
                out.print("<button type=\"submit\" name=\"myButton\">Start</button>");
                out.println("</form>");
            } else {
                out.println("<H1>The Counter App! Log In Failed. Please try again. </H1>");
                out.print("<form name=“AuthenticateForm” action=\"AuthenticationServlet\" method=\"GET\">");
                out.println("<input type=submit name=submit value=Login>");
                out.println("</form>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        out.println("<br>");
        out.println("</BODY>");
        out.println("</HTML>");





    }
}