import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User> {
    private final int id;
    private String firstName;
    private String lastName;
    private final int yearBirth;


    public User(String firstName, String lastName, int yearBirth, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearBirth = yearBirth;
        this.id = id;

    }

    @Override
    public int compareTo(User obj) {
        return firstName.compareToIgnoreCase(obj.firstName);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearBirth() {
        return yearBirth;
    }
}
