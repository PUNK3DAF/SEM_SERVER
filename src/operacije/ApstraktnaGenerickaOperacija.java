package operacije;

import repository.db.DbRepository;
import repository.db.impl.DbRepositoryGeneric;

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
        }
    }

    protected abstract void preduslovi(Object param) throws Exception;

    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DbRepository) broker).connect();
    }

    private void potvrdiTransakciju() throws Exception {
        try {
            ((DbRepository) broker).commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void ponistiTransakciju() throws Exception {
        try {
            ((DbRepository) broker).rollback();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
