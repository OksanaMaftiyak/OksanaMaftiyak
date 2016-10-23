package omaftiyak.javacourse.lab2.reader;

import omaftiyak.javacourse.lab2.model.Book;
import omaftiyak.javacourse.lab2.validator.BookValidator;
import omaftiyak.javacourse.lab2.validator.ValidatorException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class BookParserTest {
    private BookParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new BookParser();
    }

    @Test(expected = ValidatorException.class)
    public void testParse_exceptionIsThrown() throws ValidatorException {
        parser.parse("/////2027");
    }

    @Test
    public void testParse_validEmployee() throws ValidatorException {
        (new Book(1001, "Joan Rowling", "Harry Potter", "bla-bla-bla", "english", "fantasy")).equals(parser.parse("Joan Rowling" +
                "/Harry Potter/bla-bla-bla/english/fantasy/1001"));
    }

    @Test
    public void testParse_allErrors() {
        ValidatorException exception = null;
        try {
            parser.parse("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/" +
                    "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/" +
                    "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/50");
            Assert.fail("Employee is not valid. So validator should throw exception");
        } catch (ValidatorException e) {
            exception = e;

        }
        Assert.assertNotNull("Exception should be thrown", exception);
        Assert.assertEquals(Arrays.asList("author should be provided and length less than 32",
                "title should be provided and length less than 32",
                "description should be provided and length less than 32",
                "genre should be provided and length less than 32",
                "language should be provided and length less than 32",
                "Year of publication should be between 1000 and this year",
                "Author names should begin with upper case character"),
                exception.getErrors());

    }
}