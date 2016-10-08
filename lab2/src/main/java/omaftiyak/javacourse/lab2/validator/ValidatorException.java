package omaftiyak.javacourse.lab2.validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidatorException extends Exception {

    private List<String> errors = new ArrayList<>();

    public ValidatorException(String error) {
        errors.add(error);
    }

    public ValidatorException(List<String> errors) {
        this.errors.addAll(errors);
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    @Override
    public String getMessage() {
        return errors.toString();
    }

}
