package repository.db;

import repository.Repository;

public interface DbRepository<T> extends Repository<T> {

    default public void connect() throws Exception {
        DbConnectionFactory.getInstanca().getConnection();
    }

    default public void disconnect() throws Exception {
        DbConnectionFactory.getInstanca().getConnection().close();
    }

    default public void commit() throws Exception {
        DbConnectionFactory.getInstanca().getConnection().commit();
    }

    default public void rollback() throws Exception {
        DbConnectionFactory.getInstanca().getConnection().rollback();
    }
}
