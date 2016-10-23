package omaftiyak.javacourse.lab2.reader;

import omaftiyak.javacourse.lab2.model.Abonent;
import omaftiyak.javacourse.lab2.validator.ValidatorException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class AbonentParserTest {


    private AbonentParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new AbonentParser();
    }

    @Test(expected = ValidatorException.class)
    public void testParse_exceptionIsThrown() throws ValidatorException {

        parser.parse("||0");
    }

    @Test
    public void testParse_validAbonent() throws ValidatorException {
        (new Abonent("Maftiyak-Melnitshuk","Oksana",1997)).equals(parser.parse("Maftiyak-Melnitshuk/Oksana/1997"));
    }

    @Test
    public void testValidate_allErrors() {
        ValidatorException exception = null;
        try {
            parser.parse("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/2017");
            Assert.fail("Employee is not valid. So validator should throw exception");
        } catch (ValidatorException e) {
            exception = e;
        }
        Assert.assertNotNull("Exception should be thrown", exception);
        Assert.assertEquals(Arrays.asList("first name should be provided and length less than 32",
                "last name should be provided and length less than 32", "Year birth should be between 105 and 3 years"
                ),
                exception.getErrors());
    }


}

