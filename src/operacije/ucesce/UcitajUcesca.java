package operacije.ucesce;

import domen.Ucesce;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class UcitajUcesca extends ApstraktnaGenerickaOperacija {

    private List<Ucesce> listaUcesca;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String join = " LEFT JOIN clandrustva ON ucesce.clan = clandrustva.clanID"
                + " LEFT JOIN ansambl ON ucesce.ansambl = ansambl.ansamblID";
        listaUcesca = (List<Ucesce>) (List) ((repository.db.impl.DbRepositoryGeneric) broker).getAll(new Ucesce(), join);
    }

    public List<Ucesce> getListaUcesca() {
        return listaUcesca;
    }
}
