package service;

import model.Employee;
import model.Library;

/**
 * Service to manage employees
 */
public class EmployeeService {

    /**
     * Adds employee to library
     *
     * @param employee employee to be added
     */
    public void addEmployee(Employee employee) {
        Library.getLibrary().getEmployees().add(employee);
    }

}
