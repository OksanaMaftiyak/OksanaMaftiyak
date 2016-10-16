package omaftiyak.javacourse.lab2.validator;

import omaftiyak.javacourse.lab2.model.Abonent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AbonentValidator {



        public void validate(String[] parts) throws ValidatorException {
            List<String> errors = new ArrayList<>();
            if (parts.length != 3) {
                throw new ValidatorException("Invalid line format");
            }
            int index = 0;
            int firstNameIndex = index++;
            int lastNameIndex = index++;
            int dateIndex = index++;
            validateString(parts[firstNameIndex], 32, "first name",errors);
            validateString(parts[lastNameIndex], 32, "last name",errors);
            try {
                int birthYear = Integer.parseInt(parts[dateIndex]);
                if (birthYear> Calendar.getInstance().get(Calendar.YEAR) -2 ||
                        birthYear < Calendar.getInstance().get(Calendar.YEAR)-100) {
                    errors.add("Year birth should be between 105 and 3 years");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                errors.add("Invalid characters in year of birth");
            }

            if (parts[firstNameIndex] == null || !parts[firstNameIndex].matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
                errors.add("Invalid first name");
            }
            if (parts[lastNameIndex] == null || !parts[lastNameIndex].matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
                errors.add("Invalid last name");
            }
            if (!errors.isEmpty()) {
                throw new ValidatorException(errors);
            }
        }

        private void validateString(String string, int maxLength, String fieldName,List<String> errors)  {
            if (string == null || string.length() >= maxLength) {
            errors.add(String.format("%s should be provided and length less than %s", fieldName, maxLength));
            }
        }


}
