/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Administrator;
import domen.Ansambl;
import domen.ClanDrustva;
import domen.Ucesce;
import java.util.List;
import operacije.ansambli.AzurirajAnsambl;
import operacije.ansambli.AzurirajSastavAnsambla;
import operacije.ansambli.DodajAnsambl;
import operacije.ansambli.DodajAnsamblSaSastavom;
import operacije.ansambli.ObrisiAnsambl;
import operacije.ansambli.UcitajAnsamblById;
import operacije.ansambli.UcitajAnsamble;
import operacije.clanovi.AzurirajClan;
import operacije.clanovi.DodajClan;
import operacije.clanovi.ObrisiClan;
import operacije.clanovi.UcitajClanById;
import operacije.clanovi.UcitajClanove;
import operacije.clanovi.UcitajClanovePoVrednosti;
import operacije.login.LoginOperacija;
import operacije.ucesce.UcitajUcesca;

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

    public void dodajAnsambl(Ansambl ans) throws Exception {
        DodajAnsambl operacija = new DodajAnsambl();
        operacija.izvrsi(ans, null);
    }

    public void azurirajAnsambl(Ansambl ansa) throws Exception {
        AzurirajAnsambl operacija = new AzurirajAnsambl();
        operacija.izvrsi(ansa, null);
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

    public void obrisiClan(ClanDrustva c) throws Exception {
        ObrisiClan operacija = new ObrisiClan();
        operacija.izvrsi(c, null);
    }

    public void dodajClan(ClanDrustva c) throws Exception {
        DodajClan operacija = new DodajClan();
        operacija.izvrsi(c, null);
    }

    public void azurirajClan(ClanDrustva c) throws Exception {
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

    public ClanDrustva getClanById(int id) throws Exception {
        UcitajClanById oper = new UcitajClanById();
        ClanDrustva probe = new ClanDrustva();
        probe.setClanID(id);
        oper.izvrsi(probe, null);
        return oper.getClan();
    }

    public List<Ucesce> ucitajUcesca() {
        UcitajUcesca oper = new UcitajUcesca();
        try {
            oper.izvrsi(null, null);
        } catch (Exception ex) {
            // Silent fail
        }
        return oper.getListaUcesca();
    }

    public void dodajAnsamblSaSastavom(Ansambl a) throws Exception {
        DodajAnsamblSaSastavom op = new DodajAnsamblSaSastavom();
        op.izvrsi(a, null);
    }

    public void azurirajSastavAnsambla(Ansambl a) throws Exception {
        AzurirajSastavAnsambla op = new AzurirajSastavAnsambla();
        op.izvrsi(a, null);
    }

    public List<ClanDrustva> nadjiClanove(String vrednost) throws Exception {
        UcitajClanovePoVrednosti op = new UcitajClanovePoVrednosti();
        op.izvrsi(vrednost, null);
        return op.getClanovi();
    }
}
