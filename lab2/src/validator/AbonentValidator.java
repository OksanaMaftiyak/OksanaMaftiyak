package validator;

import model.Abonent;


import java.util.Calendar;

public class AbonentValidator {



        public void validate(Abonent abonent) throws ValidatorException {

            validateString(abonent.getFirstName(), 64, "first name");
            validateString(abonent.getLastName(), 64, "last name");
            if (abonent.getYearBirth() > Calendar.getInstance().get(Calendar.YEAR) -2 ||
                    abonent.getYearBirth() < Calendar.getInstance().get(Calendar.YEAR)-100) {
                throw new ValidatorException("Year birth should be between 105 and 3 years");
            }
            if (abonent.getFirstName() == null || !abonent.getFirstName().matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
                throw new ValidatorException("Invalid first name");
            }
            if (abonent.getLastName() == null || !abonent.getLastName().matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
                throw new ValidatorException("Invalid first name");
            }

        }

        private void validateString(String string, int maxLength, String fieldName) throws ValidatorException {
            if (string == null || string.length() >= maxLength) {
                throw new ValidatorException(String.format("%s should be provided and length less than %s", fieldName, maxLength));
            }
        }


}
