import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServerHackaton extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(ServerHackaton.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String question = req.getParameter("q");

        logger.info("Question : " + question);

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
            case "que boit la vache ?":
                response = "eau";
                break;
            case "de quelle couleur est le cheval blanc d'henry 4 ":
                response = "blanc";
                break;
            case "qui est le fondateur de facebook ?":
                response = "zuckerberg";
                break;
            case "quelle est la commande pour faire un save and quit dans VI ?":
                response = "wq";
                break;
            case "qui sera le prochain batman ?":
                response = "ben affleck";
                break;
            case "de qui indiana jones tire-t-il son nom ?":
                response = "indien";
                break;
            default:
                response = "no valid question";
        }

        logger.info("Response : " + response);
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
