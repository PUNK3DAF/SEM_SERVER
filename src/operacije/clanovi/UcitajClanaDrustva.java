package operacije.clanovi;

import domen.ClanDrustva;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Učitaj člana po ID
 */
public class UcitajClanaDrustva extends ApstraktnaGenerickaOperacija {

    private ClanDrustva clan;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanDrustva)) {
            throw new Exception("Neispravan parametar za učitavanje člana.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanDrustva probe = (ClanDrustva) param;
        List<ClanDrustva> svi = (List<ClanDrustva>) (List<?>) broker.getAll(new ClanDrustva(), null);
        
        if (svi != null) {
            for (ClanDrustva c : svi) {
                if (c.getClanID() == probe.getClanID()) {
                    this.clan = c;
                    return;
                }
            }
        }
        this.clan = null;
    }

    public ClanDrustva getClan() {
        return clan;
    }

}
