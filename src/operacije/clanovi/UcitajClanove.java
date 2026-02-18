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
        clanovi = (svi == null) ? new java.util.ArrayList<>() : svi;
    }

    public List<ClanDrustva> getClanovi() {
        return clanovi;
    }

}
