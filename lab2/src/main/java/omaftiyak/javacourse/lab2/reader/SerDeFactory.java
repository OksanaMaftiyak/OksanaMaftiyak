package omaftiyak.javacourse.lab2.reader;

import omaftiyak.javacourse.lab2.model.Abonent;
import omaftiyak.javacourse.lab2.model.Book;
import omaftiyak.javacourse.lab2.model.Employee;

import java.util.HashMap;
import java.util.Map;

public class SerDeFactory {

    private static SerDeFactory instnce;

    private static final String FORMAT_JSON = "json";
    private static final String FORMAT_XML = "xml";
    private static final String FORMAT_TXT = "txt";

    private final Map<String, Parser> parserMap = new HashMap<>();

    public static SerDeFactory instance(){
        if(instnce == null){
            instnce = new SerDeFactory();
        }
        return instnce;
    }

    private SerDeFactory() {
        parserMap.put(Employee.class.getSimpleName(),new EmployeeParser());
        parserMap.put(Book.class.getSimpleName(),new BookParser());
        parserMap.put(Abonent.class.getSimpleName(),new AbonentParser());
    }

    public <T> ModelSerDe<T> newSerDe(String filePath, Class<T> modelClass) {
        String[] parts = filePath.split("\\.");
        String format = parts[parts.length - 1];
        if (FORMAT_JSON.equalsIgnoreCase(format)) {
            return new JSONModelSerDe<>(modelClass);
        } else if (FORMAT_XML.equalsIgnoreCase(format)) {
            return new XMLModelSerDe<>(modelClass);
        } else {
            return new TXTModelSerDe<>(parserMap.get(modelClass.getSimpleName()));
        }
    }

}
