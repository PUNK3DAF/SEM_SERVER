package operacije.clanovi;

import domen.ClanDrustva;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class UcitajClanove extends ApstraktnaGenerickaOperacija {

    private List<ClanDrustva> clanovi;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<ClanDrustva> svi = broker.getAll(new ClanDrustva(), null);
        java.util.List<ClanDrustva> aktivni = new java.util.ArrayList<>();
        if (svi != null) {
            for (ClanDrustva c : svi) {
                if (c != null && c.getObrisan() == 0) {
                    aktivni.add(c);
                }
            }
        }
        clanovi = aktivni;
    }

    public List<ClanDrustva> getClanovi() {
        return clanovi;
    }

}
