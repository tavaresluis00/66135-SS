import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AuthenticatorListener implements ServletContextListener {
    private Authenticator authenticator;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        authenticator = new Authenticator(); // create your authenticator instance
        context.setAttribute("authenticator", authenticator); // add it to the servlet context
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        context.removeAttribute("authenticator"); // remove it from the servlet context
        authenticator = null; // set it to null to allow garbage collection
    }
}