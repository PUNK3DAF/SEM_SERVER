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
import java.util.logging.Level;
import java.util.logging.Logger;
import operacije.ansambli.AzurirajAnsambl;
import operacije.ansambli.AzurirajSastavAnsambla;
import operacije.ansambli.DodajAnsambl;
import operacije.ansambli.DodajAnsamblSaSastavom;
import operacije.ansambli.ObrisiAnsambl;
import operacije.ansambli.UcitajAnsamblById;
import operacije.login.LoginOperacija;
import operacije.ansambli.UcitajAnsamble;
import operacije.clanovi.AzurirajClan;
import operacije.clanovi.DodajClan;
import operacije.clanovi.ObrisiClan;
import operacije.clanovi.UcitajClanById;
import operacije.clanovi.UcitajClanove;
import operacije.clanovi.UcitajClanovePoVrednosti;
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
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("KLASA CONTROLLER ZAPOSLENI: " + operacija.getA());
        return operacija.getA();
    }

    public List<Ansambl> ucitajAnsamble() {
        UcitajAnsamble operacija = new UcitajAnsamble();
        try {
            operacija.izvrsi(null, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("KLASA CONTROLLER ANSAMBLI: " + operacija.getAnsambli());
        return operacija.getAnsambli();
    }

    public void obrisiAnsambl(Ansambl a) {
        ObrisiAnsambl operacija = new ObrisiAnsambl();
        try {
            operacija.izvrsi(a, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dodajAnsambl(Ansambl ans) {
        DodajAnsambl operacija = new DodajAnsambl();
        try {
            operacija.izvrsi(ans, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void azurirajAnsambl(Ansambl ansa) {
        AzurirajAnsambl operacija = new AzurirajAnsambl();
        try {
            operacija.izvrsi(ansa, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ClanDrustva> ucitajClanove() {
        UcitajClanove operacija = new UcitajClanove();
        try {
            operacija.izvrsi(null, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("KLASA CONTROLLER CLANOVI: " + operacija.getClanovi());
        return operacija.getClanovi();
    }

    public void obrisiClan(ClanDrustva c) {
        ObrisiClan operacija = new ObrisiClan();
        try {
            operacija.izvrsi(c, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dodajClan(ClanDrustva c) throws Exception {
        DodajClan operacija = new DodajClan();
        operacija.izvrsi(c, null);
    }

    public void azurirajClan(ClanDrustva c) {
        AzurirajClan operacija = new AzurirajClan();
        try {
            operacija.izvrsi(c, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Ansambl getAnsamblById(int id) {
        UcitajAnsamblById oper = new UcitajAnsamblById();
        Ansambl probe = new Ansambl();
        probe.setAnsamblID(id);
        try {
            oper.izvrsi(probe, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oper.getAnsambl();
    }

    public ClanDrustva getClanById(int id) {
        UcitajClanById oper = new UcitajClanById();
        ClanDrustva probe = new ClanDrustva();
        probe.setClanID(id);
        try {
            oper.izvrsi(probe, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oper.getClan();
    }

    public List<Ucesce> ucitajUcesca() {
        UcitajUcesca oper = new UcitajUcesca();
        try {
            oper.izvrsi(null, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oper.getListaUcesca();
    }

    public void dodajAnsamblSaSastavom(Ansambl a) {
        DodajAnsamblSaSastavom op = new DodajAnsamblSaSastavom();
        try {
            op.izvrsi(a, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void azurirajSastavAnsambla(Ansambl a) {
        AzurirajSastavAnsambla op = new AzurirajSastavAnsambla();
        try {
            op.izvrsi(a, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ClanDrustva> nadjiClanove(String vrednost) {
        UcitajClanovePoVrednosti op = new UcitajClanovePoVrednosti();
        try {
            op.izvrsi(vrednost, null);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return op.getClanovi();
    }
}
