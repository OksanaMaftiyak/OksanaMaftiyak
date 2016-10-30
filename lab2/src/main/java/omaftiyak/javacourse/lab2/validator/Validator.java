package omaftiyak.javacourse.lab2.validator;


public interface Validator<T> {

    boolean validate(String[] parts)throws ValidatorException;
}
