package omaftiyak.javacourse.lab2.validator;

import omaftiyak.javacourse.lab2.model.Book;

import java.util.Calendar;

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

        validateString(book.getAuthor(), 64, "author");
        validateString(book.getBookTitle(), 256, "title");
        validateString(book.getDescription(), 1024, "description");
        validateString(book.getGenre(), 64, "genre");
        validateString(book.getLanguage(), 32, "language");
        if (book.getYearPublication() < 1000 || book.getYearPublication() > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new ValidatorException("Year of publication should be between 1000 and this year");
        }
        if (book.getAuthor() == null || !book.getAuthor().matches("[A-Z][a-z]+-?([A-Z]?[a-z]*)\\s?[A-Z][a-z]+-?([A-Z]?[a-z]*)")) {
            throw new ValidatorException("Author names should begin with upper case character");
        }

    }

    /**
     * Validates provided string to be not null and to not exceed provided length
     *
     * @param string    string to be validated
     * @param maxLength max length
     * @param fieldName field name
     * @throws ValidatorException in the case if provided string is null or length exceeds provided maxLength
     */
    private void validateString(String string, int maxLength, String fieldName) throws ValidatorException {
        if (string == null || string.length() >= maxLength) {
            throw new ValidatorException(String.format("%s should be provided and length less than %s", fieldName, maxLength));
        }
    }

}
