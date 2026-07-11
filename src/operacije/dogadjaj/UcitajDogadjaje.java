package operacije.dogadjaj;

import domen.Ansambl;
import domen.Dogadjaj;
import domen.Mesto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajDogadjaje extends ApstraktnaGenerickaOperacija {

    private List<Dogadjaj> dogadjaji;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        dogadjaji = (List<Dogadjaj>) (List<?>) broker.getAll(new Dogadjaj(), null);
    }

    public List<Dogadjaj> getDogadjaji() {
        return dogadjaji;
    }
}

