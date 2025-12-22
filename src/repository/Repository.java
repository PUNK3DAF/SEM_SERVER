package repository;

import java.util.List;

public interface Repository<T> {

    List<T> getAll(T param, String uslov) throws Exception;

    void add(T param) throws Exception;

    void edit(T param) throws Exception;

    void delete(T param) throws Exception;

    List<T> getAll();

}
