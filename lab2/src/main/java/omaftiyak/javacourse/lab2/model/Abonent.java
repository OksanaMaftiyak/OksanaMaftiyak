package omaftiyak.javacourse.lab2.model;

import omaftiyak.javacourse.lab2.common.IdGenerator;

import java.util.Objects;

public class Abonent implements Comparable<Abonent> {

    private int id;
    private String firstName;
    private String lastName;
    private int yearBirth;

    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    public Abonent(String firstName, String lastName, int yearBirth) {
        this.id = ID_GENERATOR.nextId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearBirth = yearBirth;
    }

    public Abonent(String str) {
        String[] parts = str.split("\\s");
        int fieldIndex = 0;
        this.id = Integer.parseInt(parts[fieldIndex++]);
        this.firstName = parts[fieldIndex++];
        this.lastName = parts[fieldIndex++];
        this.yearBirth = Integer.parseInt(parts[fieldIndex++]);
    }

    @Override
    public int compareTo(Abonent obj) {
        return firstName.compareToIgnoreCase(obj.firstName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Abonent)) {
            return false;
        }
        Abonent abonent = (Abonent) obj;
        return id == abonent.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("id: ").append(id);
        sb.append(", firstName: ").append(firstName);
        sb.append(", lastName: ").append(lastName);
        sb.append(", yearBirth: ").append(yearBirth);
        sb.append("]");
        return sb.toString();
    }
}
