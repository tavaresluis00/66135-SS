import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
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
        out.println("<H1>Logout</H1>");
        out.println("<br>");
        out.print("<form name=“logoutform” action=\"LogOutServlet\" method=\"POST\">");
        out.println("<input type=text name=username size=35 value=username>");
        out.println("<br>");
        out.println("<input type=submit name=logout value=Logout>");
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

        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<H1>The Counter App!</H1>");

        if(auth.logout(username)){
            out.println("<H1>Logout Successful </H1>");
            out.println("<br>");
            out.println("<a type=button href=\"AuthenticationServlet\">Login</a>");
            out.println("<br>");
            out.println("<a type=button href=\"CreateAccountServlet\">Create Account</a>");
            out.println("<br>");
            out.println("<a type=button href=\"DeleteAccountServlet\">Delete Account</a>");
            out.println("<br>");
        } else {
            out.println("<H1>Logout Failed. Try again</H1>");
        }

        out.println("</BODY>");
        out.println("</HTML>");

        //auth.create_account(username, password, password2);


    }
}
