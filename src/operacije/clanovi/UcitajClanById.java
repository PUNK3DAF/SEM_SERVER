/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.clanovi;

import domen.ClanDrustva;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class UcitajClanById extends ApstraktnaGenerickaOperacija {

    private ClanDrustva clan;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof ClanDrustva)) {
            throw new Exception("Neispravan parametar za ucitavanje clana po ID.");
        }
        ClanDrustva c = (ClanDrustva) param;
        if (c.getClanID() <= 0) {
            throw new Exception("Neispravan ID clana.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanDrustva c = (ClanDrustva) param;
        List<ClanDrustva> lista = broker.getAll(new ClanDrustva(), " WHERE clanID=" + c.getClanID());
        if (lista != null && !lista.isEmpty()) {
            clan = (ClanDrustva) lista.get(0);
        } else {
            clan = null;
        }
    }

    public ClanDrustva getClan() {
        return clan;
    }
}
