package omaftiyak.javacourse.lab2.validator;


public interface Validator<T> {

    void validate(String[] parts)throws ValidatorException;
}
