package omaftiyak.javacourse.lab2.web.request.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final int BUFFER_SIZE = 4 * 1024;

    private Utils() {
        // nop
    }

    public static void copyStreamContent(InputStream src, OutputStream dst) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int read;
        while (-1 != (read = src.read(buffer))) {
            dst.write(buffer, 0, read);
        }
    }

    public static void sendFileContent(String file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (InputStream is = request.getServletContext().getResourceAsStream(file)) {
            if (is == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("File \"%s\" not found", file));
                return;
            }
            OutputStream os = response.getOutputStream();
            copyStreamContent(is, os);
            os.flush();
        }
    }

    public static void writeJson(HttpServletResponse response, Object data) throws IOException {
        response.setHeader("Content Type", "application/json");
        OBJECT_MAPPER.writeValue(response.getOutputStream(), data);
    }

    public static <T> T readJson(HttpServletRequest request, Class<T> aClass) throws IOException {
        return OBJECT_MAPPER.readValue(request.getInputStream(), aClass);
    }
}
