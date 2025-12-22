package operacije.clanovi;

import domen.ClanDrustva;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class UcitajClanovePoVrednosti extends ApstraktnaGenerickaOperacija {

    private List<ClanDrustva> clanovi;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String vrednost = (String) param;
        String where = " WHERE obrisan=0 AND (ime LIKE '%" + vrednost + "%' OR prezime LIKE '%" + vrednost + "%' OR jmbg LIKE '%" + vrednost + "%')";
        clanovi = broker.getAll(new ClanDrustva(), where);
    }

    public List<ClanDrustva> getClanovi() {
        return clanovi;
    }

}
