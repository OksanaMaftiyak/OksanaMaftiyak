package omaftiyak.javacourse.lab2.reader;

import omaftiyak.javacourse.lab2.model.Book;
import omaftiyak.javacourse.lab2.validator.BookValidator;
import omaftiyak.javacourse.lab2.validator.ValidatorException;


public class BookParser extends Parser<Book> {
    protected BookParser() {
        super(new BookValidator());
    }

    @Override
    protected Book build(String[] parts) {
        return new Book(parts);
    }

    @Override
    public Book parse(String input) throws ValidatorException {
        return super.parse(input);
    }
}
