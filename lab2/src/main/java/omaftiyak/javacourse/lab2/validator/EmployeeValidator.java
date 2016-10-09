package omaftiyak.javacourse.lab2.validator;

import omaftiyak.javacourse.lab2.model.Employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmployeeValidator {

    public void validate(Employee employee) throws ValidatorException {
        List<String> errors = new ArrayList<>();
        validateString(employee.getFirstName(), 32, "first name",errors);
        validateString(employee.getLastName(), 32, "last name",errors);
        validateString(employee.getPosition(), 32, "position",errors);
        if (employee.getYearBirth() < 1900 || employee.getYearBirth() > Calendar.getInstance().get(Calendar.YEAR) - 18) {
            errors.add("Year of publication should be between 1000 and this year");
        }
        if (employee.getFirstName() == null || !employee.getFirstName().matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
            errors.add("Employee names should begin with upper case character");
        }
        if (employee.getLastName() == null || !employee.getLastName().matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
            errors.add("Employee names should begin with upper case character");
        }
        if (!errors.isEmpty()) {
            throw new ValidatorException(errors);
        }
    }

    private void validateString(String string, int maxLength, String fieldName,List<String> errors) {
        if (string == null || string.length() >= maxLength) {
            errors.add(String.format("%s should be provided and length less than %s", fieldName, maxLength));
        }
    }

}
