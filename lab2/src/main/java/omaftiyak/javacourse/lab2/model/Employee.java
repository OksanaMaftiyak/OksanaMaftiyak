package omaftiyak.javacourse.lab2.model;

import omaftiyak.javacourse.lab2.common.IdGenerator;

import java.util.Objects;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private int yearBirth;
    private int salary;

    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    public Employee(String firstName, String lastName, String position, int yearBirth, int salary) {
        this.id = ID_GENERATOR.nextId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.yearBirth = yearBirth;
        this.salary = salary;
    }

    public Employee(String str) {
        String[] parts = str.split("\\s");
        int fieldIndex = 0;
        this.id = Integer.parseInt(parts[fieldIndex++]);
        this.firstName = parts[fieldIndex++];
        this.lastName = parts[fieldIndex++];
        this.position = parts[fieldIndex++];
        this.yearBirth = Integer.parseInt(parts[fieldIndex++]);
        this.salary = Integer.parseInt(parts[fieldIndex++]);
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Employee employee = (Employee) obj;
        return id == employee.id;
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
        sb.append(", position: ").append(position);
        sb.append(", yearBirth: ").append(yearBirth);
        sb.append(", salary: ").append(salary);
        sb.append("]");
        return sb.toString();
    }
}
