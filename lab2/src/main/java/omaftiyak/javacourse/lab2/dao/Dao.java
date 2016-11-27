package omaftiyak.javacourse.lab2.dao;


import java.util.List;

public interface Dao<T> {
    void persist(T model);
    void update(T model);
    T findById(long id);
    List<T> selectAll();
    void deleteById(long id);
}
