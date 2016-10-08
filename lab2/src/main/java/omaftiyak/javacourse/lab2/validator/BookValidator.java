package omaftiyak.javacourse.lab2.validator;

import omaftiyak.javacourse.lab2.model.Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Validator for books
 */
public class BookValidator {

    /**
     * Validates provided book
     *
     * @param book book to be validated
     * @throws ValidatorException in the case book is null or provided book is not valid
     */
    public void validate(Book book) throws ValidatorException {
        List<String> errors = new ArrayList<>();
        validateString(book.getAuthor(), 64, "author", errors);
        validateString(book.getBookTitle(), 256, "title", errors);
        validateString(book.getDescription(), 1024, "description", errors);
        validateString(book.getGenre(), 64, "genre", errors);
        validateString(book.getLanguage(), 32, "language", errors);
        if (book.getYearPublication() < 1000 || book.getYearPublication() > Calendar.getInstance().get(Calendar.YEAR)) {
            errors.add("Year of publication should be between 1000 and this year");
        }
        if (book.getAuthor() == null || !book.getAuthor().matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)\\s?[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
            errors.add("Author names should begin with upper case character");
        }
        if (!errors.isEmpty()) {
            throw new ValidatorException(errors);
        }
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
