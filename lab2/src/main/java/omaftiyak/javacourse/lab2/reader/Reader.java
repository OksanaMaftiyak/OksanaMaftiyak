package omaftiyak.javacourse.lab2.reader;

import omaftiyak.javacourse.lab2.validator.ValidatorException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Reader<T> {

    private Parser<T> parser;

    public Reader(Parser<T> parser) {
        this.parser = parser;
    }

    public List<T> read(String filePath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            return doRead(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<T> doRead(BufferedReader bufferedReader) throws IOException {
        List<T> result = new ArrayList<>();
        do {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            try {
                result.add(parser.parse(line));
            } catch (ValidatorException e) {
                e.printStackTrace();
                continue;
            }
        } while (true);
        return result;
    }

}
