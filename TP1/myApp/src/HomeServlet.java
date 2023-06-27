import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet{
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
        out.println("<H1>Home</H1>");
        out.println("<br>");
        out.println("<a type=button href=\"AuthenticationServlet\">Login</a>");
        out.println("<br>");
        out.println("<a type=button href=\"CreateAccountServlet\">Create Account</a>");
        out.println("<br>");
        out.println("<a type=button href=\"DeleteAccountServlet\">Delete Account</a>");
        out.println("</BODY>");
        out.println("</HTML>");
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
        out.println("<H1>Home</H1>");
        out.println("<br>");
        out.print("<form name=“authenticatorForm” action=\"AuthenticationServlet\" method=\"GET\">");
        out.println("<input type=button name=username size=35 value=Login>");
        out.println("<br>");
        out.println("</form>");
        out.print("<form name=“createAccountForm” action=\"CreateAccountServlet\" method=\"GET\">");
        out.println("<input type=button name=username size=35 value=CreateAccount>");
        out.println("<br>");
        out.println("</form>");
        out.print("<form name=“deleteAccountForm” action=\"DeleteAccountServlet\" method=\"GET\">");
        out.println("<input type=button name=username size=35 value=DeleteAccount>");
        out.println("<br>");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
}
