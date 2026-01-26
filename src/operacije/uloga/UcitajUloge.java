package operacije.uloga;

import domen.Uloga;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajUloge extends ApstraktnaGenerickaOperacija {

    private List<Uloga> uloge;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        uloge = (List<Uloga>) (List<?>) broker.getAll(new Uloga(), null);
    }

    public List<Uloga> getUloge() {
        return uloge;
    }
}
