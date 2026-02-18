package operacije.clanovi;

import domen.ApstraktniDomenskiObjekat;
import domen.ClanDrustva;
import domen.Ucesce;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class ObrisiClanaDrustva extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanDrustva)) {
            throw new Exception("Sistem ne moze da obrise clana drustva.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanDrustva c = (ClanDrustva) param;

        Ucesce probe = new Ucesce();
        probe.setClan(c);
        List<ApstraktniDomenskiObjekat> ucesca = broker.getAll(probe, null);

        if (ucesca != null && !ucesca.isEmpty()) {
            for (ApstraktniDomenskiObjekat u : ucesca) {
                if (u instanceof Ucesce) {
                    Ucesce ux = (Ucesce) u;
                    if (ux.getClan() != null && ux.getClan().getClanID() == c.getClanID()) {
                        broker.delete(u);
                    }
                }
            }
        }

        broker.delete(c);
    }

}
