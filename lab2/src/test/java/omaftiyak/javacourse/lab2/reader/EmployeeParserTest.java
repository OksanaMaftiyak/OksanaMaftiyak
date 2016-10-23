package omaftiyak.javacourse.lab2.reader;

import omaftiyak.javacourse.lab2.model.Employee;
import omaftiyak.javacourse.lab2.reader.EmployeeParser;
import omaftiyak.javacourse.lab2.validator.ValidatorException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Oksan on 23.10.2016.
 */
public class EmployeeParserTest {
    private EmployeeParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new EmployeeParser();
    }

    @Test(expected = ValidatorException.class)
    public void testParser_exceptionIsThrown() throws ValidatorException {
        parser.parse("///0/100");
    }

    @Test
    public void testParser_validEmployee() throws ValidatorException {
        (new Employee("Maftiyak-Melnitshuk","Oksana","student",1997,10000)).equals(parser.parse("Maftiyak-Melnitshuk/Oksana/student/1997/10000"));
    }

    @Test
    public void testParser_allErrors() {
        ValidatorException exception = null;
        try {
            parser.parse("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "/Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/2017/10000001");
            Assert.fail("Employee is not valid. So validator should throw exception");
        } catch (ValidatorException e) {
            exception = e;
        }
        Assert.assertNotNull("Exception should be thrown", exception);
        Assert.assertEquals(Arrays.asList("first name should be provided and length less than 32",
                "last name should be provided and length less than 32",
                "position should be provided and length less than 32",
                "Year of publication should be between 1000 and this year",
                "Salary is less then 100 and greater then 1000000"),
                exception.getErrors());
    }


}
