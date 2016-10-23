package omaftiyak.javacourse.lab2.reader;

import omaftiyak.javacourse.lab2.validator.Validator;
import omaftiyak.javacourse.lab2.validator.ValidatorException;

abstract class Parser<T> {

    public static final String SEPARATOR = "/";
    private Validator<T> validator;

    protected Parser(Validator<T> validator) {
        this.validator = validator;
    }

    public T parse(String input) throws ValidatorException {
        String[] parts = input.split(SEPARATOR);
        validator.validate(parts);
        return build(parts);
    }

    protected abstract T build(String[] parts);

    public abstract String[] getParts(T model);
}
