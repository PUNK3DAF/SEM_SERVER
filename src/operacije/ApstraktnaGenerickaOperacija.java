/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import repository.db.DbRepository;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author vldmrk
 */
public abstract class ApstraktnaGenerickaOperacija {

    protected final repository.Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }

    public final void izvrsi(Object objekat, String kljuc) throws Exception {
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakciju();
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        } finally {
        }
    }

    protected abstract void preduslovi(Object param) throws Exception;

    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DbRepository) broker).connect();
    }

    private void potvrdiTransakciju() throws Exception {
        try {
            System.out.println("SERVER DEBUG: POKUSAJ COMMIT-a transakcije...");
            ((DbRepository) broker).commit();
            System.out.println("SERVER DEBUG: COMMIT uspesno izveden.");
        } catch (Exception ex) {
            System.err.println("SERVER DEBUG: GREŠKA prilikom COMMIT-a, baca se izuzetak i pokreće rollback.");
            ex.printStackTrace();
            throw ex;
        }
    }

    private void ponistiTransakciju() throws Exception {
        try {
            System.err.println("SERVER DEBUG: POČINJEM ROLLBACK transakcije...");
            ((DbRepository) broker).rollback();
            System.err.println("SERVER DEBUG: ROLLBACK završen.");
        } catch (Exception ex) {
            System.err.println("SERVER DEBUG: GREŠKA prilikom ROLLBACK-a.");
            ex.printStackTrace();
            throw ex;
        }
    }

}
