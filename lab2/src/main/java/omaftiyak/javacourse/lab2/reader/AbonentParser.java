package omaftiyak.javacourse.lab2.reader;


import omaftiyak.javacourse.lab2.model.Abonent;
import omaftiyak.javacourse.lab2.validator.AbonentValidator;
import omaftiyak.javacourse.lab2.validator.ValidatorException;

class AbonentParser extends Parser<Abonent> {
    protected AbonentParser() {
        super(new AbonentValidator());
    }

    @Override
    public Abonent parse(String input) throws ValidatorException {
        return super.parse(input);
    }

    @Override
    protected Abonent build(String[] parts) {
        return new Abonent(parts);
    }

    @Override
    public String[] getParts(Abonent model) {
        return model.getParts();
    }

}
