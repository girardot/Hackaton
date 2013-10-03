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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerHackaton extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(ServerHackaton.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String question = req.getParameter("q");

        logger.info("Question : \n" + question);

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
            case "quelle est la commande pour faire un save and quit dans vi ?":
                response = "wq";
                break;
            case "qui sera le prochain batman ?":
                response = "ben affleck";
                break;
            case "combien font 0 plus 0 ?":
                response = "chien";
                break;
            case "combien font 0 fois 1 ?":
                response = "chien";
                break;
            case "combien font 2 moins 1":
                response = "chien";
                break;
            case "combien font 0 plus 3 ?":
                response = "chien";
                break;
            case "de qui indiana jones tient-il son nom ?":
                response = "chien";
                break;
            case "de quelle serie vient la phrase : live long and prosper":
                response = "star trek";
                break;
            case "COMBIEN FONT (((1.1+2)+3.14+4+(5+6+7)+(8+9+10)*4267387833344334647677634)/2*553344300034334349999000)/31878018903828899277492024491376690701584023926880":
                response = "1.0";
                break;
            default:
                response = calculate(question.toLowerCase()).toString();
                logger.info("*******    NO Response : " + response);
        }

        logger.info("Response : \n" + response);
        resp.getWriter().print(response);
    }

    public static Integer calculate(String q) {

        Integer result = 0;
        if (q.toLowerCase().matches("combien font ([0-9]*) (.*?) ([0-9]*) \\?")) {

            Pattern pattern = Pattern.compile("combien font ([0-9]*) (.*?) ([0-9]*) \\?");
            final Matcher matcher = pattern.matcher(q.toLowerCase());

            matcher.find();

            final Integer left = Integer.parseInt(matcher.group(1));
            final Integer right = Integer.parseInt(matcher.group(3));
            final String op = matcher.group(2);

            switch (op) {
                case "plus":
                    result = left + right;
                    break;
                case "fois":
                    result = left * right;
                    break;
                case "moins":
                    result = left - right;
                    break;
                default:
                    result = 0;
            }
        } else if (q.toLowerCase().matches("quelle est la valeur decimal.*")) {
            Pattern pattern = Pattern.compile("quelle est la valeur decimal.* de 0x(.*)");
            final Matcher matcher = pattern.matcher(q);
            matcher.find();

            result = Integer.parseInt(matcher.group(1),16);
        }

        return result;
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
