/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Administrator;
import domen.Ansambl;
import domen.ClanDrustva;
import java.util.List;
import operacije.ansambli.AzurirajSastavAnsambla;
import operacije.ansambli.DodajAnsamblSaSastavom;
import operacije.ansambli.ObrisiAnsambl;
import operacije.ansambli.UcitajAnsamblById;
import operacije.ansambli.UcitajAnsamble;
import operacije.ansambli.UcitajAnsamblaPoVrednosti;
import operacije.clanovi.AzurirajClan;
import operacije.clanovi.DodajClan;
import operacije.clanovi.ObrisiClan;
import operacije.clanovi.UcitajClanById;
import operacije.clanovi.UcitajClanove;
import operacije.clanovi.UcitajClanovePoVrednosti;
import operacije.login.LoginOperacija;

/**
 *
 * @author vldmrk
 */
public class Controller {

    private static Controller instanca;

    private Controller() {
    }

    public static Controller getInstanca() {
        if (instanca == null) {
            instanca = new Controller();
        }
        return instanca;
    }

    public Administrator login(Administrator a) {
        LoginOperacija operacija = new LoginOperacija();
        try {
            operacija.izvrsi(a, null);
        } catch (Exception ex) {
            // Silent fail - login will return null
        }
        return operacija.getA();
    }

    public List<Ansambl> ucitajAnsamble() {
        UcitajAnsamble operacija = new UcitajAnsamble();
        try {
            operacija.izvrsi(null, null);
        } catch (Exception ex) {
            // Silent fail
        }
        return operacija.getAnsambli();
    }

    public void obrisiAnsambl(Ansambl a) throws Exception {
        ObrisiAnsambl operacija = new ObrisiAnsambl();
        operacija.izvrsi(a, null);
    }

    public void kreirajAnsambl(Ansambl a) throws Exception {
        DodajAnsamblSaSastavom op = new DodajAnsamblSaSastavom();
        op.izvrsi(a, null);
    }

    public void izmenaAnsambla(Ansambl a) throws Exception {
        AzurirajSastavAnsambla op = new AzurirajSastavAnsambla();
        op.izvrsi(a, null);
    }

    public List<ClanDrustva> ucitajClanove() {
        UcitajClanove operacija = new UcitajClanove();
        try {
            operacija.izvrsi(null, null);
        } catch (Exception ex) {
            // Silent fail
        }
        return operacija.getClanovi();
    }

    public void obrisiClanaDrustva(ClanDrustva c) throws Exception {
        ObrisiClan operacija = new ObrisiClan();
        operacija.izvrsi(c, null);
    }

    public void kreirajClanaDrustva(ClanDrustva c) throws Exception {
        DodajClan operacija = new DodajClan();
        operacija.izvrsi(c, null);
    }

    public void izmeniClanaDrustva(ClanDrustva c) throws Exception {
        AzurirajClan operacija = new AzurirajClan();
        operacija.izvrsi(c, null);
    }

    public Ansambl getAnsamblById(int id) throws Exception {
        UcitajAnsamblById oper = new UcitajAnsamblById();
        Ansambl probe = new Ansambl();
        probe.setAnsamblID(id);
        oper.izvrsi(probe, null);
        return oper.getAnsambl();
    }

    public Ansambl ucitajAnsambl(int id) throws Exception {
        return getAnsamblById(id);
    }

    public ClanDrustva ucitajClanaDrustva(int id) throws Exception {
        UcitajClanById oper = new UcitajClanById();
        ClanDrustva probe = new ClanDrustva();
        probe.setClanID(id);
        oper.izvrsi(probe, null);
        return oper.getClan();
    }

    public ClanDrustva getClanById(int id) throws Exception {
        return ucitajClanaDrustva(id);
    }

    public List<ClanDrustva> nadjiClanaDrustva(String vrednost) throws Exception {
        UcitajClanovePoVrednosti op = new UcitajClanovePoVrednosti();
        op.izvrsi(vrednost, null);
        return op.getClanovi();
    }

    public List<Ansambl> nadjiAnsambla(String vrednost) throws Exception {
        UcitajAnsamblaPoVrednosti op = new UcitajAnsamblaPoVrednosti();
        op.izvrsi(vrednost, null);
        return op.getAnsambli();
    }
}
