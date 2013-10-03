import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServerHackaton extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String question = req.getParameter("q");

        String response = "No question";

        switch (question.toLowerCase()) {
            case "quelle est ton identity ? (cf dashboard)":
                response = "Antoine Michaud";
                break;
            case "quelle couleur est le contraire du noir":
                response = "blanc";
                break;
            case "quel est le résultat d'addition de toutes les couleurs":
                response = "blanc";
                break;
            case "de quelle couleur est une robe de mariee":
                response = "blanc";
                break;
            default:
                response = "no valid question";
        }

        resp.getWriter().print(response);
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new ServerHackaton()), "/hackatonsoat/*");
        server.start();
        server.join();
    }
}
