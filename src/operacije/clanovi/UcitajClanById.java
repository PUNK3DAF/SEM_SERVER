package operacije.clanovi;

import operacije.ApstraktnaGenerickaOperacija;

/**
 * Deprecated - sada je u operacije.pomocne.UcitajClanById
 */
public class UcitajClanById extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
        throw new UnsupportedOperationException("Prebačeno u operacije.pomocne");
    }
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        throw new UnsupportedOperationException("Prebačeno u operacije.pomocne");
    }
}
