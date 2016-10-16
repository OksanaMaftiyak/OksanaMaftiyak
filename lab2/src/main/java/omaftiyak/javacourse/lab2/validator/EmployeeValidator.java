package omaftiyak.javacourse.lab2.validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmployeeValidator implements Validator {
    @Override
    public void validate(String[] parts) throws ValidatorException {
        List<String> errors = new ArrayList<>();
        if (parts.length != 4) {
            throw new ValidatorException("Invalid line format");
        }
        int index = 0;
        int firstNameIndex = index++;
        int lastNameIndex = index++;
        int positionIndex = index++;
        int dateIndex = index++;
        validateString(parts[firstNameIndex], 32, "first name", errors);
        validateString(parts[lastNameIndex], 32, "last name", errors);
        validateString(parts[positionIndex], 32, "position", errors);
        try {
            int birthYear = Integer.parseInt(parts[dateIndex]);
            if (birthYear < 1900 || birthYear > Calendar.getInstance().get(Calendar.YEAR) - 18) {
                errors.add("Year of publication should be between 1000 and this year");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errors.add("Invalid characters in year of birth");
        }

        if (parts[firstNameIndex] == null || !parts[firstNameIndex].matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
            errors.add("Employee first name should begin with upper case character");
        }
        if (parts[lastNameIndex] == null || !parts[lastNameIndex].matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
            errors.add("Employee last name should begin with upper case character");
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
