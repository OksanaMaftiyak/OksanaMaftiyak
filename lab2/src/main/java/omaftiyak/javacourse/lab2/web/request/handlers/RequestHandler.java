package omaftiyak.javacourse.lab2.web.request.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RequestHandler {

    String GET = "GET";
    String POST = "POST";
    String PUT = "PUT";
    String DELETE = "DELETE";

    boolean handle(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
