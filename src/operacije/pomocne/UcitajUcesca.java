package operacije.pomocne;

import operacije.ApstraktnaGenerickaOperacija;

/**
<<<<<<<< HEAD:src/operacije/pomocne/UcitajUcesca.java
 * Pomoćna operacija za učitavanje svih učešća
========
 * Deprecated - sada je u operacije.pomocne.UcitajUcesca
>>>>>>>> e0a9ac625bfe2ee037a08bc027ea6108418406d2:src/operacije/ucesce/UcitajUcesca.java
 */
public class UcitajUcesca extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
        throw new UnsupportedOperationException("Prebačeno u operacije.pomocne");
    }
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
<<<<<<<< HEAD:src/operacije/pomocne/UcitajUcesca.java
        listaUcesca = (List<Ucesce>) (List) broker.getAll(new Ucesce(), null);
    }

    public List<Ucesce> getListaUcesca() {
        return listaUcesca;
========
        throw new UnsupportedOperationException("Prebačeno u operacije.pomocne");
>>>>>>>> e0a9ac625bfe2ee037a08bc027ea6108418406d2:src/operacije/ucesce/UcitajUcesca.java
    }
}
