package operacije.pomocne;

import domen.Ansambl;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Pomoćna operacija za pretragu ansambala po vrednosti
 */
public class UcitajAnsamblaPoVrednosti extends ApstraktnaGenerickaOperacija {

    private List<Ansambl> ansambli;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String vrednost = (String) param;
        String needle = (vrednost == null) ? "" : vrednost.toLowerCase();
        List<Ansambl> svi = broker.getAll(new Ansambl(), null);
        java.util.List<Ansambl> filtrirani = new java.util.ArrayList<>();
        if (svi != null) {
            for (Ansambl a : svi) {
                if (a == null) continue;
                String ime = a.getImeAnsambla() == null ? "" : a.getImeAnsambla().toLowerCase();
                String opis = a.getOpisAnsambla() == null ? "" : a.getOpisAnsambla().toLowerCase();
                if (needle.isEmpty() || ime.contains(needle) || opis.contains(needle)) {
                    filtrirani.add(a);
                }
            }
        }
        ansambli = filtrirani;
    }

    public List<Ansambl> getAnsambli() {
        return ansambli;
    }
}
