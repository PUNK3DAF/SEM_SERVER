package operacije.pomocne;

import domen.ClanDrustva;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Pomoćna operacija za pretragu članova po vrednosti
 */
public class UcitajClanovePoVrednosti extends ApstraktnaGenerickaOperacija {

    private List<ClanDrustva> clanovi;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String vrednost = (String) param;
        String needle = (vrednost == null) ? "" : vrednost.toLowerCase();
        List<ClanDrustva> svi = broker.getAll(new ClanDrustva(), null);
        java.util.List<ClanDrustva> filtrirani = new java.util.ArrayList<>();
        if (svi != null) {
            for (ClanDrustva c : svi) {
                if (c == null) continue;
                String ime = c.getClanIme() == null ? "" : c.getClanIme().toLowerCase();
                String tel = c.getClanBrTel() == null ? "" : c.getClanBrTel().toLowerCase();
                if (needle.isEmpty() || ime.contains(needle) || tel.contains(needle)) {
                    filtrirani.add(c);
                }
            }
        }
        clanovi = filtrirani;
    }

    public List<ClanDrustva> getClanovi() {
        return clanovi;
    }
}
