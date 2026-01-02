package operacije.pomocne;

import domen.Ucesce;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Pomoćna operacija za učitavanje svih učešća
 */
public class UcitajUcesca extends ApstraktnaGenerickaOperacija {

    private List<Ucesce> listaUcesca;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaUcesca = (List<Ucesce>) (List) broker.getAll(new Ucesce(), null);
    }

    public List<Ucesce> getListaUcesca() {
        return listaUcesca;
    }
}
