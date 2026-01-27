package operacije.dogadjaj;

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
        // Load all events
        dogadjaji = (List<Dogadjaj>) (List<?>) broker.getAll(new Dogadjaj(), null);
        
        // Load all places for mapping
        List<Mesto> mesta = (List<Mesto>) (List<?>) broker.getAll(new Mesto(), null);
        Map<Integer, Mesto> mestoMap = new HashMap<>();
        for (Mesto m : mesta) {
            mestoMap.put(m.getMestoID(), m);
        }
        
        // Set place objects in events
        for (Dogadjaj d : dogadjaji) {
            Mesto m = mestoMap.get(d.getMestoID());
            if (m != null) {
                d.setMesto(m);
            }
        }
    }

    public List<Dogadjaj> getDogadjaji() {
        return dogadjaji;
    }
}

