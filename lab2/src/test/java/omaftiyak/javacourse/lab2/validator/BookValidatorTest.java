package omaftiyak.javacourse.lab2.validator;

import omaftiyak.javacourse.lab2.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class BookValidatorTest {
    private BookValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new BookValidator();
    }

    @Test(expected = ValidatorException.class)
    public void testValidate_exceptionIsThrown() throws ValidatorException {
        validator.validate(new String[]{"","","","","","2027"});
    }

    @Test
    public void testValidate_validEmployee() throws ValidatorException {
        validator.validate(new String[]{"Joan Rowling", "Harry Potter", "bla-bla-bla", "english","fantasy","1001"});
    }

    @Test
    public void testValidate_allErrors() {
        ValidatorException exception = null;
        try {
            validator.validate(new String[]{"Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","50"});
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

