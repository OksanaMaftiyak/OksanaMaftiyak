package service;

import model.Abonent;
import model.Library;

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
    public Abonent findAbonentById(int id) {
        for (Abonent abonent : Library.getLibrary().getAbonents()) {
            if (id == abonent.getId()) {
                return abonent;
            }
        }
        return null;
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
