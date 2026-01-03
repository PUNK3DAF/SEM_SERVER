package operacije.pomocne;

import domen.Ansambl;
import domen.ClanDrustva;
import domen.Ucesce;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Pomoćna operacija za učitavanje svih učešća
 */
public class UcitajUcesca extends ApstraktnaGenerickaOperacija {

    private List<Ucesce> listaUcesca;

    @Override
    protected void preduslovi(Object param) throws Exception {
        // No specific preconditions
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaUcesca = (List<Ucesce>) (List<?>) broker.getAll(new Ucesce(), null);

        // Enrich with names (in-memory, no SQL joins)
        List<Ansambl> sviAnsambli = (List<Ansambl>) (List<?>) broker.getAll(new Ansambl(), null);
        List<ClanDrustva> sviClanovi = (List<ClanDrustva>) (List<?>) broker.getAll(new ClanDrustva(), null);

        Map<Integer, Ansambl> ansPoId = new HashMap<>();
        if (sviAnsambli != null) {
            for (Ansambl a : sviAnsambli) {
                ansPoId.put(a.getAnsamblID(), a);
            }
        }

        Map<Integer, ClanDrustva> clanPoId = new HashMap<>();
        if (sviClanovi != null) {
            for (ClanDrustva c : sviClanovi) {
                clanPoId.put(c.getClanID(), c);
            }
        }

        if (listaUcesca != null) {
            for (Ucesce u : listaUcesca) {
                if (u == null) continue;

                // Ansambl enrichment
                Integer ansId = (u.getAnsambl() == null) ? null : u.getAnsambl().getAnsamblID();
                if (ansId != null) {
                    Ansambl fromMap = ansPoId.get(ansId);
                    if (fromMap != null) {
                        u.setAnsambl(fromMap);
                    } else {
                        Ansambl stub = new Ansambl();
                        stub.setAnsamblID(ansId);
                        u.setAnsambl(stub);
                    }
                }

                // Clan enrichment
                Integer clanId = (u.getClan() == null) ? null : u.getClan().getClanID();
                if (clanId != null) {
                    ClanDrustva fromMap = clanPoId.get(clanId);
                    if (fromMap != null) {
                        u.setClan(fromMap);
                    } else {
                        ClanDrustva stub = new ClanDrustva();
                        stub.setClanID(clanId);
                        u.setClan(stub);
                    }
                }
            }
        }
    }

    public List<Ucesce> getListaUcesca() {
        return listaUcesca;
    }
}
