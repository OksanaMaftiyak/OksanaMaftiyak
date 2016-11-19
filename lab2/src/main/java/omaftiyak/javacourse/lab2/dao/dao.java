package omaftiyak.javacourse.lab2.dao;


public interface Dao<T>{
     void persist(T model);
    void update (T model);
}
