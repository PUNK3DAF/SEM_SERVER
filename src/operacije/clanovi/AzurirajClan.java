package operacije.clanovi;

import domen.ClanDrustva;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class AzurirajClan extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanDrustva)) {
            throw new Exception("Neispravan parametar za azuriranje clana.");
        }
        ClanDrustva c = (ClanDrustva) param;

        if (c.getClanIme() == null || c.getClanIme().trim().isEmpty()) {
            throw new Exception("NEPRAVILAN UNOS: ime clana je obavezno.");
        }

        if (c.getClanPol() == null || c.getClanPol().trim().isEmpty()) {
            throw new Exception("NEPRAVILAN UNOS: pol clana je obavezan (M ili Z).");
        } else {
            String pol = c.getClanPol().trim().toUpperCase();
            if (!(pol.equals("M") || pol.equals("Z"))) {
                throw new Exception("NEPRAVILAN UNOS: pol clana mora biti 'M' ili 'Z'.");
            }
            c.setClanPol(pol);
        }
        int god = c.getClanGod();
        if (god < 0 || god > 120) {
            throw new Exception("NEPRAVILAN UNOS: godine clana moraju biti broj >= 0 i <= 120.");
        }

        if (c.getClanBrTel() == null || c.getClanBrTel().trim().isEmpty()) {
            throw new Exception("NEPRAVILAN UNOS: broj telefona clana je obavezan.");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanDrustva c = (ClanDrustva) param;
        broker.edit(c);
    }

}
