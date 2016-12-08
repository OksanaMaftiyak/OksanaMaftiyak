package omaftiyak.javacourse.lab2.service;

import omaftiyak.javacourse.lab2.model.Abonent;
import omaftiyak.javacourse.lab2.model.Library;

/**
 * Service to manage abonent
 */
public class AbonentService {

    /**
     * Finds abonent by its id
     *
     * @param id abonent's id
     * @return abonent or null if not found
     */
    public Abonent findAbonentById(long id) {
        return Library.getLibrary().getAbonents().stream()
                .filter(abonent -> abonent.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Register abonent within library
     *
     * @param abonent abonent to be registered
     */
    public void addAbonent(Abonent abonent) {
        Library.getLibrary().getAbonents().add(abonent);
    }

}
