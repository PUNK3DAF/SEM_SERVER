package operacije.clanovi;

import domen.ClanDrustva;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class KreirajClanaDrustva extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanDrustva)) {
            throw new Exception("Neispravan parametar za dodavanje člana.");
        }
        ClanDrustva c = (ClanDrustva) param;

        if (c.getClanIme() == null || c.getClanIme().trim().isEmpty()) {
            throw new Exception("NEPRAVILAN UNOS: ime člana je obavezno.");
        }

        String[] deloviImena = c.getClanIme().trim().split("\\s+");
        if (deloviImena.length < 2) {
            throw new Exception("NEPRAVILAN UNOS: ime i prezime člana su obavezni.");
        }

        if (c.getClanPol() == null || c.getClanPol().trim().isEmpty()) {
            throw new Exception("NEPRAVILAN UNOS: pol člana je obavezan (M ili Z).");
        } else {
            String pol = c.getClanPol().trim().toUpperCase();
            if (!(pol.equals("M") || pol.equals("Z"))) {
                throw new Exception("NEPRAVILAN UNOS: pol člana mora biti 'M' ili 'Z'.");
            }
            c.setClanPol(pol);
        }
        int god = c.getClanGod();
        if (god < 0 || god > 120) {
            throw new Exception("NEPRAVILAN UNOS: godine člana moraju biti broj >= 0 i <= 120.");
        }

        if (c.getClanBrTel() == null || c.getClanBrTel().trim().isEmpty()) {
            throw new Exception("NEPRAVILAN UNOS: broj telefona člana je obavezan.");
        }

        String email = c.getClanEmail() == null ? "" : c.getClanEmail().trim().toLowerCase();
        if (email.isEmpty()) {
            throw new Exception("NEPRAVILAN UNOS: mejl člana je obavezan.");
        }
        if (!ClanDrustva.jeValidanEmail(email)) {
            throw new Exception("NEPRAVILAN UNOS: mejl člana nije u ispravnom formatu.");
        }
        c.setClanEmail(email);

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanDrustva c = (ClanDrustva) param;
        broker.add(c);
    }

}
