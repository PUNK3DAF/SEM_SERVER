/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Administrator;
import domen.Ansambl;
import domen.ClanDrustva;
import domen.Mesto;
import domen.Ucesce;
import domen.Uloga;
import domen.Zanr;
import java.util.List;
import operacije.ansambli.IzmenaAnsambla;
import operacije.ansambli.KreirajAnsambl;
import operacije.ansambli.ObrisiAnsambl;
import operacije.ansambli.UcitajAnsamble;
import operacije.ansambli.NadjiAnsambl;
import operacije.ansambli.UcitajAnsambl;
import operacije.clanovi.IzmeniClanaDrustva;
import operacije.clanovi.KreirajClanaDrustva;
import operacije.clanovi.ObrisiClanaDrustva;
import operacije.clanovi.UcitajClanove;
import operacije.clanovi.NadjiClanaDrustva;
import operacije.clanovi.UcitajClanaDrustva;
import operacije.login.AdminLogin;
import operacije.pomocne.UcitajUcesca;
import operacije.zanr.IzmeniZanr;
import operacije.zanr.KreirajZanr;
import operacije.zanr.ObrisiZanr;
import operacije.zanr.UcitajZanrove;
import operacije.uloga.IzmeniUlogu;
import operacije.uloga.KreirajUlogu;
import operacije.uloga.ObrisiUlogu;
import operacije.uloga.UcitajUloge;
import operacije.mesto.IzmeniMesto;
import operacije.mesto.KreirajMesto;
import operacije.mesto.ObrisiMesto;
import operacije.mesto.UcitajMesta;

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
        AdminLogin operacija = new AdminLogin();
        try {
            operacija.izvrsi(a, null);
        } catch (Exception ex) {
        }
        return operacija.getA();
    }

    public List<Ansambl> ucitajAnsamble() {
        UcitajAnsamble operacija = new UcitajAnsamble();
        try {
            operacija.izvrsi(null, null);
        } catch (Exception ex) {
        }
        return operacija.getAnsambli();
    }

    public void obrisiAnsambl(Ansambl a) throws Exception {
        ObrisiAnsambl operacija = new ObrisiAnsambl();
        operacija.izvrsi(a, null);
    }

    public void kreirajAnsambl(Ansambl a) throws Exception {
        KreirajAnsambl op = new KreirajAnsambl();
        op.izvrsi(a, null);
    }

    public void izmenaAnsambla(Ansambl a) throws Exception {
        IzmenaAnsambla op = new IzmenaAnsambla();
        op.izvrsi(a, null);
    }

    public List<ClanDrustva> ucitajClanove() {
        UcitajClanove operacija = new UcitajClanove();
        try {
            operacija.izvrsi(null, null);
        } catch (Exception ex) {
        }
        return operacija.getClanovi();
    }

    public void obrisiClanaDrustva(ClanDrustva c) throws Exception {
        ObrisiClanaDrustva operacija = new ObrisiClanaDrustva();
        operacija.izvrsi(c, null);
    }

    public void kreirajClanaDrustva(ClanDrustva c) throws Exception {
        KreirajClanaDrustva operacija = new KreirajClanaDrustva();
        operacija.izvrsi(c, null);
    }

    public void izmeniClanaDrustva(ClanDrustva c) throws Exception {
        IzmeniClanaDrustva operacija = new IzmeniClanaDrustva();
        operacija.izvrsi(c, null);
    }

    public Ansambl getAnsamblById(int id) throws Exception {
        UcitajAnsambl oper = new UcitajAnsambl();
        Ansambl probe = new Ansambl();
        probe.setAnsamblID(id);
        oper.izvrsi(probe, null);
        return oper.getAnsambl();
    }

    public Ansambl ucitajAnsambl(int id) throws Exception {
        return getAnsamblById(id);
    }

    public ClanDrustva ucitajClanaDrustva(int id) throws Exception {
        UcitajClanaDrustva oper = new UcitajClanaDrustva();
        ClanDrustva probe = new ClanDrustva();
        probe.setClanID(id);
        oper.izvrsi(probe, null);
        return oper.getClan();
    }

    public ClanDrustva getClanById(int id) throws Exception {
        return ucitajClanaDrustva(id);
    }

    public List<ClanDrustva> nadjiClanaDrustva(String vrednost) throws Exception {
        NadjiClanaDrustva op = new NadjiClanaDrustva();
        op.izvrsi(vrednost, null);
        return op.getClanovi();
    }

    public List<Ansambl> nadjiAnsambl(String vrednost) throws Exception {
        NadjiAnsambl op = new NadjiAnsambl();
        op.izvrsi(vrednost, null);
        return op.getAnsambli();
    }

    public List<Ucesce> ucitajUcesca() {
        UcitajUcesca oper = new UcitajUcesca();
        try {
            oper.izvrsi(null, null);
        } catch (Exception ex) {
        }
        return oper.getListaUcesca();
    }

    public void kreirajZanr(Zanr z) throws Exception {
        KreirajZanr op = new KreirajZanr();
        op.izvrsi(z, null);
    }

    public List<Zanr> ucitajZanrove() {
        UcitajZanrove op = new UcitajZanrove();
        try {
            op.izvrsi(null, null);
        } catch (Exception ex) {
        }
        return op.getZanrovi();
    }

    public void izmeniZanr(Zanr z) throws Exception {
        IzmeniZanr op = new IzmeniZanr();
        op.izvrsi(z, null);
    }

    public void obrisiZanr(Zanr z) throws Exception {
        ObrisiZanr op = new ObrisiZanr();
        op.izvrsi(z, null);
    }

    public void kreirajUlogu(Uloga u) throws Exception {
        KreirajUlogu op = new KreirajUlogu();
        op.izvrsi(u, null);
    }

    public List<Uloga> ucitajUloge() {
        UcitajUloge op = new UcitajUloge();
        try {
            op.izvrsi(null, null);
        } catch (Exception ex) {
        }
        return op.getUloge();
    }

    public void izmeniUlogu(Uloga u) throws Exception {
        IzmeniUlogu op = new IzmeniUlogu();
        op.izvrsi(u, null);
    }

    public void obrisiUlogu(Uloga u) throws Exception {
        ObrisiUlogu op = new ObrisiUlogu();
        op.izvrsi(u, null);
    }

    public void kreirajMesto(Mesto m) throws Exception {
        KreirajMesto op = new KreirajMesto();
        op.izvrsi(m, null);
    }

    public List<Mesto> ucitajMesta() {
        UcitajMesta op = new UcitajMesta();
        try {
            op.izvrsi(null, null);
        } catch (Exception ex) {
        }
        return op.getMesta();
    }

    public void izmeniMesto(Mesto m) throws Exception {
        IzmeniMesto op = new IzmeniMesto();
        op.izvrsi(m, null);
    }

    public void obrisiMesto(Mesto m) throws Exception {
        ObrisiMesto op = new ObrisiMesto();
        op.izvrsi(m, null);
    }
}
