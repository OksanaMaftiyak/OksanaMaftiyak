package omaftiyak.javacourse.lab2.web.request.handlers;

import omaftiyak.javacourse.lab2.model.BookLibrary;
import omaftiyak.javacourse.lab2.service.BookLibraryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomePageRequestHandler implements RequestHandler {

    private static final String HOMEPAGE_PATH = "/";
    private static final String HOME_PAGE_HTML = "/files/html/index.html";
    private static final String LIBRARY_JSON = "/library.json";

    private final BookLibraryService bookLibraryService = new BookLibraryService();

    @Override
    public boolean handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String requestedPath = request.getPathInfo();
        if (HOMEPAGE_PATH.equals(requestedPath) && GET.equals(request.getMethod())) {
            Utils.sendFileContent(HOME_PAGE_HTML, request, response);
            return true;
        } else if (LIBRARY_JSON.equals(requestedPath)) {
            if (GET.equals(request.getMethod())) {
                returnAllLibraries(request, response);
                return true;
            } else if (POST.equals(request.getMethod())) {
                createNewLibrary(request, response);
                return true;
            }
        }
        return false;
    }

    private void returnAllLibraries(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Utils.writeJson(response, bookLibraryService.listAllLibraries());
    }

    private void createNewLibrary(HttpServletRequest request, HttpServletResponse response) throws IOException {
        bookLibraryService.create(Utils.readJson(request, BookLibrary.class));
    }

}
