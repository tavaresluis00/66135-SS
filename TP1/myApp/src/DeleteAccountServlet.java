import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteAccountServlet extends HttpServlet {
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
        out.println("<H1>Delete Account</H1>");
        out.println("<br>");
        out.print("<form name=“deleteform” action=\"DeleteAccountServlet\" method=\"POST\">");
        out.println("<input type=text name=username size=35 value=username>");
        out.println("<br>");
        out.println("<input type=password name=password size=35>");
        out.println("<br>");
        out.println("<input type=submit name=logout value=\"Delete Account\">");
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

        if(auth.delete_account(username, password)){
            out.println("<H1>Account Deleted</H1>");
            out.println("<br>");
            out.println("<a type=button href=\"HomeServlet\">Home</a>");
            out.println("<br>");
        } else {
            out.println("<H1>The Counter App! Delete Account Failed. Please try again. </H1>");
            out.print("<form name=“DeleteAccountForm” action=\"DeleteAccountServlet\" method=\"GET\">");
            out.println("<input type=submit name=submit value=\"Delete Account\">");
            out.println("</form>");
        }
        out.println("<br>");
        out.println("</BODY>");
        out.println("</HTML>");

    }
}
