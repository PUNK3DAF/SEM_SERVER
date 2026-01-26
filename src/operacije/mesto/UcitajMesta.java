package operacije.mesto;

import domen.Mesto;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajMesta extends ApstraktnaGenerickaOperacija {

    private List<Mesto> mesta;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        mesta = (List<Mesto>) (List<?>) broker.getAll(new Mesto(), null);
    }

    public List<Mesto> getMesta() {
        return mesta;
    }
}
