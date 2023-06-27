import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
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
            out.println("<H1>Create Account</H1>");
            out.println("<br>");
            out.print("<form name=“createAccountForm” action=\"CreateAccountServlet\" method=\"POST\">");
            out.println("<input type=text name=username size=35 value=username>");
            out.println("<br>");
            out.println("<input type=password name=password size=35>");
            out.println("<br>");
            out.println("<input type=password name=password2 size=35>");
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
            String password2 = request.getParameter("password2");

            PrintWriter out = response.getWriter();
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("</HEAD>");
            out.println("<BODY>");
            out.println("<H1>The Counter App!</H1>");

            try {
                if(auth.create_account(username, password, password2)){
                    out.println("<H1>Account Created with Success </H1>");
                    out.print("<form name=“form” action=\"AuthenticationServlet\" method=\"GET\">");
                    out.print("<button type=\"submit\" name=\"myButton\">Login</button>");
                    out.println("</form>");
                }else{
                    out.println("<H1>The Counter App! Create Account Failed. Please try again. User already Exists or the Passwords don't match</H1>");
                    out.print("<form name=“CreateAccountForm” action=\"CreateAccountServlet\" method=\"GET\">");
                    out.println("<input type=submit name=submit value=\"Create Account\">");
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
