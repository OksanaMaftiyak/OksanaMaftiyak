package omaftiyak.javacourse.lab2.validator;

import omaftiyak.javacourse.lab2.model.Abonent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AbonentValidator {



        public void validate(Abonent abonent) throws ValidatorException {
            List<String> errors = new ArrayList<>();
            validateString(abonent.getFirstName(), 32, "first name",errors);
            validateString(abonent.getLastName(), 32, "last name",errors);
            if (abonent.getYearBirth() > Calendar.getInstance().get(Calendar.YEAR) -2 ||
                    abonent.getYearBirth() < Calendar.getInstance().get(Calendar.YEAR)-100) {
                errors.add("Year birth should be between 105 and 3 years");
            }
            if (abonent.getFirstName() == null || !abonent.getFirstName().matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
                errors.add("Invalid first name");
            }
            if (abonent.getLastName() == null || !abonent.getLastName().matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
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
