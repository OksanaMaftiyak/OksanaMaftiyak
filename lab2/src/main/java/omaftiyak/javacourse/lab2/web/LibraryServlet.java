package omaftiyak.javacourse.lab2.web;

import omaftiyak.javacourse.lab2.web.request.handlers.BookRequestHandler;
import omaftiyak.javacourse.lab2.web.request.handlers.FileRequestHandler;
import omaftiyak.javacourse.lab2.web.request.handlers.HomePageRequestHandler;
import omaftiyak.javacourse.lab2.web.request.handlers.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LibraryServlet extends HttpServlet {

    private List<RequestHandler> requestHandlers = new LinkedList<>();

    public LibraryServlet() {
        requestHandlers.addAll(Arrays.asList(
                new HomePageRequestHandler(),
                new BookRequestHandler(),
                new FileRequestHandler()
        ));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isHandled = false;
        try {
            for (RequestHandler requestHandler : requestHandlers) {
                if (requestHandler.handle(request, response)) {
                    isHandled = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            isHandled = true;
        }
        if (!isHandled) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("Resource \"%s\" is not served on this server", request.getPathInfo()));
        }
    }

}
