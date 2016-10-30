package omaftiyak.javacourse.lab2.validator;

import omaftiyak.javacourse.lab2.model.Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Validator for books
 */
public class BookValidator implements Validator<Book> {

    /**
     * Validates provided book
     *
     * @param   parts of book to be validated
     * @throws ValidatorException in the case book is null or provided book is not valid
     */
    @Override
    public boolean validate(String []parts) throws ValidatorException {
        List<String> errors = new ArrayList<>();
        if (parts.length != 6) {
            throw new ValidatorException("Invalid line format");
        }
        int index=0;
        int authorIndex=index++;
        int titleIndex=index++;
        int descriptionIndex=index++;
        int genreIndex=index++;
        int languageIndex=index++;
        int yearOfPublicationIndex=index++;

        validateString(parts[authorIndex], 32, "author", errors);
        validateString(parts[titleIndex], 32, "title", errors);
        validateString(parts[descriptionIndex], 32, "description", errors);
        validateString(parts[genreIndex], 32, "genre", errors);
        validateString(parts[languageIndex], 32, "language", errors);

        try {
            int yearOfPublication = Integer.parseInt(parts[yearOfPublicationIndex]);
            if (yearOfPublication < 1000 || yearOfPublication > Calendar.getInstance().get(Calendar.YEAR) ) {
                errors.add("Year of publication should be between 1000 and this year");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errors.add("Invalid characters in year of publication");
        }
        if (parts[authorIndex] == null || !parts[authorIndex].matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)\\s?[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
            errors.add("Author names should begin with upper case character");
        }
        if (!errors.isEmpty()) {
            throw new ValidatorException(errors);
        }else return true;
    }

    /**
     * Validates provided string to be not null and to not exceed provided length
     *
     * @param string    string to be validated
     * @param maxLength max length
     * @param fieldName field name
     * @param errors    list to collect errors during validation
     */
    private void validateString(String string, int maxLength, String fieldName, List<String> errors) {
        if (string == null || string.length() >= maxLength) {
            errors.add(String.format("%s should be provided and length less than %s", fieldName, maxLength));
        }
    }

}
