package operacije.dogadjaj;

import domen.Dogadjaj;
import operacije.ApstraktnaGenerickaOperacija;

public class IzmeniDogadjaj extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Dogadjaj)) {
            throw new Exception("Nije prosledjen validan dogadjaj!");
        }

        Dogadjaj d = (Dogadjaj) param;

        if (d.getNaziv() == null || d.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv događaja ne sme biti prazan!");
        }

        if (d.getDatum() == null) {
            throw new Exception("Datum događaja je obavezan!");
        }

        if (d.getMesto() == null || d.getMesto().getMestoID() <= 0) {
            throw new Exception("Mesto događaja nije izabrano!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Dogadjaj d = (Dogadjaj) param;
        broker.edit(d);
    }
}
