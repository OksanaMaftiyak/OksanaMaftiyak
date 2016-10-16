package omaftiyak.javacourse.lab2.reader;

import omaftiyak.javacourse.lab2.validator.Validator;
import omaftiyak.javacourse.lab2.validator.ValidatorException;

public abstract class Parser<T> {

    private Validator<T> validator;

    protected Parser(Validator<T> validator) {
        this.validator = validator;
    }

    public T parse(String input) throws ValidatorException {
        String[] parts = input.split("|");
        validator.validate(parts);
        return build(parts);
    }

    protected abstract T build(String[] parts);

}
