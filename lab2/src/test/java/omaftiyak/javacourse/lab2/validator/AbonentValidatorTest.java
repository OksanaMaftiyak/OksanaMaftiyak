package omaftiyak.javacourse.lab2.validator;

import omaftiyak.javacourse.lab2.model.Abonent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class AbonentValidatorTest {
    private AbonentValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new AbonentValidator();
    }

    @Test(expected = ValidatorException.class)
    public void testValidate_exceptionIsThrown() throws ValidatorException {

        validator.validate(new String[]{"", "", "0"});
    }

    @Test
    public void testValidate_validAbonent() throws ValidatorException {
        validator.validate(new String[]{"Maftiyak-Melnitshuk", "Oksana", "1997"});
    }

    @Test
    public void testValidate_allErrors() {
        ValidatorException exception = null;
        try {
            validator.validate(new String[]{"Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "2017"});
            Assert.fail("Employee is not valid. So validator should throw exception");
        } catch (ValidatorException e) {
            exception = e;
        }
        Assert.assertNotNull("Exception should be thrown", exception);
        Assert.assertEquals(Arrays.asList("first name should be provided and length less than 32",
                "last name should be provided and length less than 32","Year birth should be between 105 and 3 years"
       ),
                exception.getErrors());
    }



}