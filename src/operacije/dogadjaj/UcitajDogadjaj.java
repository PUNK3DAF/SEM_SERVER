package operacije.dogadjaj;

import domen.Dogadjaj;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajDogadjaj extends ApstraktnaGenerickaOperacija {

    private Dogadjaj dogadjaj;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Dogadjaj)) {
            throw new Exception("Nije prosleđen validan događaj!");
        }

        Dogadjaj probe = (Dogadjaj) param;
        if (probe.getDogadjajID() <= 0) {
            throw new Exception("ID događaja nije ispravan!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Dogadjaj probe = (Dogadjaj) param;
        List<Dogadjaj> dogadjaji = (List<Dogadjaj>) (List<?>) broker.getAll(new Dogadjaj(), null);
        dogadjaj = null;

        if (dogadjaji != null) {
            for (Dogadjaj ucitan : dogadjaji) {
                if (ucitan != null && ucitan.getDogadjajID() == probe.getDogadjajID()) {
                    dogadjaj = ucitan;
                    return;
                }
            }
        }
    }

    public Dogadjaj getDogadjaj() {
        return dogadjaj;
    }
}