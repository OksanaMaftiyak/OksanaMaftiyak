package omaftiyak.javacourse.lab2.reader;


import java.io.IOException;
import java.util.List;

public interface ModelSerDe<T> {

    List<T> read(String filePath) throws IOException;

    void write(String filePath, List<T> models) throws IOException;

}
