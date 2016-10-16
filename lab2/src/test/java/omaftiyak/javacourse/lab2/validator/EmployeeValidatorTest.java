package omaftiyak.javacourse.lab2.validator;

import omaftiyak.javacourse.lab2.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;


public class EmployeeValidatorTest {

    private EmployeeValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new EmployeeValidator();
    }

    @Test(expected = ValidatorException.class)
    public void testValidate_exceptionIsThrown() throws ValidatorException {
        validator.validate(new String[]{"", "", "", "0"});
    }

    @Test
    public void testValidate_validEmployee() throws ValidatorException {
        validator.validate(new String[]{"Maftiyak-Melnitshuk", "Oksana", "student", "1997"});
    }

    @Test
    public void testValidate_allErrors() {
        ValidatorException exception = null;
        try {
            validator.validate(new String[]{"Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","2017"});
            Assert.fail("Employee is not valid. So validator should throw exception");
        } catch (ValidatorException e) {
            exception = e;
        }
        Assert.assertNotNull("Exception should be thrown", exception);
        Assert.assertEquals(Arrays.asList("first name should be provided and length less than 32",
                "last name should be provided and length less than 32",
                "position should be provided and length less than 32",
                "Year of publication should be between 1000 and this year"),
                exception.getErrors());
    }

}