package operacije.clanovi;

import domen.ClanDrustva;
import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Pretraži člana po vrednosti (ime člana)
 */
public class NadjiClanaDrustva extends ApstraktnaGenerickaOperacija {

    private List<ClanDrustva> clanovi;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof String)) {
            throw new Exception("Neispravan parametar za pretragu člana.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String vrednost = (String) param;
        List<ClanDrustva> svi = (List<ClanDrustva>) (List<?>) broker.getAll(new ClanDrustva(), null);
        clanovi = new ArrayList<>();
        
        if (svi != null) {
            for (ClanDrustva c : svi) {
                if (c.getClanIme() != null && c.getClanIme().toLowerCase().contains(vrednost.toLowerCase())) {
                    clanovi.add(c);
                }
            }
        }
    }

    public List<ClanDrustva> getClanovi() {
        return clanovi;
    }

}
