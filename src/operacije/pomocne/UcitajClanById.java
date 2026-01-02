package operacije.pomocne;

import domen.ClanDrustva;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Pomoćna operacija za učitavanje člana po ID-u
 */
public class UcitajClanById extends ApstraktnaGenerickaOperacija {

    private ClanDrustva clan;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanDrustva)) {
            throw new Exception("Neispravan parametar za ucitavanje clana po ID.");
        }
        ClanDrustva c = (ClanDrustva) param;
        if (c.getClanID() <= 0) {
            throw new Exception("Neispravan ID clana.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanDrustva c = (ClanDrustva) param;
        List<ClanDrustva> lista = broker.getAll(new ClanDrustva(), null);
        clan = null;
        if (lista != null) {
            for (ClanDrustva x : lista) {
                if (x != null && x.getClanID() == c.getClanID()) {
                    clan = x;
                    break;
                }
            }
        }
    }

    public ClanDrustva getClan() {
        return clan;
    }
}
