/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import repository.Repository;

/**
 *
 * @author vldmrk
 * @param <T>
 */
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
