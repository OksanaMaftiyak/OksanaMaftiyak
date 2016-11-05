package omaftiyak.javacourse.lab2.reader;

import omaftiyak.javacourse.lab2.validator.ValidatorException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class TXTModelSerDe<T> implements ModelSerDe<T> {

    protected Parser<T> parser;

    public TXTModelSerDe(Parser<T> parser) {
        this.parser = parser;
    }

    @Override
    public List<T> read(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
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

    @Override
    public void write(String filePath, List<T> models) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(filePath)));
        for (T model : models) {
            String[] parts = parser.getParts(model);
            for (int i = 0; i < parts.length; i++) {
                if (i > 0) {
                    writer.print(Parser.SEPARATOR);
                }
                writer.print(parts[i]);
            }
            writer.println();
        }
        writer.close();
    }


}
