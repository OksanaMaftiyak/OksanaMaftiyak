package omaftiyak.javacourse.lab2.reader;

import omaftiyak.javacourse.lab2.model.Employee;
import omaftiyak.javacourse.lab2.validator.EmployeeValidator;
import omaftiyak.javacourse.lab2.validator.Validator;
import omaftiyak.javacourse.lab2.validator.ValidatorException;


class EmployeeParser extends Parser<Employee> {

    protected EmployeeParser() {
        super(new EmployeeValidator());
    }

    @Override
    public Employee parse(String input) throws ValidatorException {
        return super.parse(input);
    }

    @Override
    protected Employee build(String[] parts) {
        return new Employee(parts);
    }

    @Override
    public String[] getParts(Employee model) {
        return model.getParts();
    }

}
