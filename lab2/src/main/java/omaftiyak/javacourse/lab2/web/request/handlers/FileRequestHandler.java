package omaftiyak.javacourse.lab2.web.request.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileRequestHandler implements RequestHandler {

    private static final String FILES_PREFIX = "/files";

    @Override
    public boolean handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestedPath = request.getPathInfo();
        if (!requestedPath.startsWith(FILES_PREFIX)) {
            return false;
        }
        Utils.sendFileContent(requestedPath, request, response);
        return true;
    }

}
