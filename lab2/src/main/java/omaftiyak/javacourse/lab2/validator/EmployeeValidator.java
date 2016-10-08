package omaftiyak.javacourse.lab2.validator;

import omaftiyak.javacourse.lab2.model.Employee;

import java.util.Calendar;

public class EmployeeValidator {

    public void validate(Employee employee) throws ValidatorException {
        validateString(employee.getFirstName(), 64, "first name");
        validateString(employee.getLastName(), 256, "last name");
        validateString(employee.getPosition(), 1024, "position");

        if (employee.getYearBirth() < 1900 || employee.getYearBirth() > Calendar.getInstance().get(Calendar.YEAR) - 18) {
            throw new ValidatorException("Year of publication should be between 1000 and this year");
        }

        if (employee.getFirstName() == null || !employee.getFirstName().matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
            throw new ValidatorException("Employee names should begin with upper case character");
        }
        if (employee.getLastName() == null || !employee.getLastName().matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
            throw new ValidatorException("Employee names should begin with upper case character");
        }
    }

    private void validateString(String string, int maxLength, String fieldName) throws ValidatorException {
        if (string == null || string.length() >= maxLength) {
            throw new ValidatorException(String.format("%s should be provided and length less than %s", fieldName, maxLength));
        }
    }

}
