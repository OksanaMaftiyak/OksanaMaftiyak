package omaftiyak.javacourse.lab2;

import omaftiyak.javacourse.lab2.model.Employee;
import omaftiyak.javacourse.lab2.reader.SerDeFactory;

import java.io.IOException;
import java.util.ArrayList;

public class App {

//    public static void main(String... args) {
//        Controller controller = new Controller();
//        controller.run();
//    }

    public static void main(String[] args) throws IOException {
        ArrayList<Employee> content = new ArrayList<>();
        content.add(new Employee("John", "Smith", "Sparrow", 1990, 1650));
        content.add(new Employee("Harry", "Potter", "CEO", 1997, 2000));
        SerDeFactory.instance().newSerDe("D:\\employee.xml", Employee.class).write("D:\\employee.xml", content);
        SerDeFactory.instance().newSerDe("D:\\employee.json", Employee.class).write("D:\\employee.json", content);
        SerDeFactory.instance().newSerDe("D:\\employee.txt", Employee.class).write("D:\\employee.txt", content);
        System.out.println(SerDeFactory.instance().newSerDe("D:\\employee.xml", Employee.class).read("D:\\employee.xml"));
        System.out.println(SerDeFactory.instance().newSerDe("D:\\employee.json", Employee.class).read("D:\\employee.json"));
        System.out.println(SerDeFactory.instance().newSerDe("D:\\employee.txt", Employee.class).read("D:\\employee.txt"));
    }


}
