/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Administrator;
import domen.Ansambl;
import domen.ClanDrustva;
import domen.Ucesce;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author vldmrk
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;
    private Administrator prijavljeniAdmin;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            Zahtev zahtev = (Zahtev) primalac.primi();
            if (zahtev == null) {
                prekini();
                break;
            }
            System.out.println("SERVER DEBUG: primljen zahtev - operacija = " + zahtev.getOperacija()
                    + " , parametar = " + (zahtev.getParametar() == null ? "null" : zahtev.getParametar().getClass().getName()));
            Odgovor odgovor = new Odgovor();
            try {
                switch (zahtev.getOperacija()) {
                    case ADMIN_LOGIN:
                        Administrator a = (Administrator) zahtev.getParametar();
                        a = controller.Controller.getInstanca().login(a);
                        odgovor.setOdgovor(a);
                        if (a != null) {
                            this.prijavljeniAdmin = a;
                        }
                        break;
                    case DODAJ_ANSAMBL:
                        Ansambl ans = (Ansambl) zahtev.getParametar();
                        if (prijavljeniAdmin == null) {
                            odgovor.setOdgovor(new Exception("Niste prijavljeni, nije dozvoljeno kreiranje ansambla."));
                        } else {
                            ans.setAdmin(prijavljeniAdmin);
                            controller.Controller.getInstanca().dodajAnsambl(ans);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case UCITAJ_ANSAMBLE:
                        List<Ansambl> ansambli = controller.Controller.getInstanca().ucitajAnsamble();
                        odgovor.setOdgovor(ansambli);
                        break;
                    case OBRISI_ANSAMBL:
                        Ansambl an = (Ansambl) zahtev.getParametar();
                        // permission check: samo admin koji je vlasnik ansambla sme obrisati
                        Ansambl dbAn = controller.Controller.getInstanca().getAnsamblById(an.getAnsamblID());
                        if (dbAn == null) {
                            odgovor.setOdgovor(new Exception("Ansambl ne postoji."));
                        } else if (prijavljeniAdmin == null || dbAn.getAdmin() == null
                                || dbAn.getAdmin().getAdminID() != prijavljeniAdmin.getAdminID()) {
                            odgovor.setOdgovor(new Exception("Nemate ovlascenje da obrisete ovaj ansambl."));
                        } else {
                            controller.Controller.getInstanca().obrisiAnsambl(an);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case AZURIRAJ_ANSAMBL:
                        Ansambl ansa = (Ansambl) zahtev.getParametar();
                        Ansambl dbAn2 = controller.Controller.getInstanca().getAnsamblById(ansa.getAnsamblID());
                        if (dbAn2 == null) {
                            odgovor.setOdgovor(new Exception("Ansambl ne postoji."));
                        } else if (prijavljeniAdmin == null || dbAn2.getAdmin() == null
                                || dbAn2.getAdmin().getAdminID() != prijavljeniAdmin.getAdminID()) {
                            odgovor.setOdgovor(new Exception("Nemate ovlascenje da azurirate ovaj ansambl."));
                        } else {
                            ansa.setAdmin(dbAn2.getAdmin());
                            controller.Controller.getInstanca().azurirajAnsambl(ansa);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case UCITAJ_CLANOVE:
                        List<ClanDrustva> clanovi = controller.Controller.getInstanca().ucitajClanove();
                        odgovor.setOdgovor(clanovi);
                        break;
                    case OBRISI_CLAN:
                        ClanDrustva c = (ClanDrustva) zahtev.getParametar();
                        ClanDrustva dbC = controller.Controller.getInstanca().getClanById(c.getClanID());
                        if (dbC == null) {
                            odgovor.setOdgovor(new Exception("Clan ne postoji."));
                        } else if (prijavljeniAdmin == null || dbC.getAdmin() == null
                                || dbC.getAdmin().getAdminID() != prijavljeniAdmin.getAdminID()) {
                            odgovor.setOdgovor(new Exception("Nemate ovlascenje da obrisete ovog clana."));
                        } else {
                            controller.Controller.getInstanca().obrisiClan(c);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case AZURIRAJ_CLAN:
                        ClanDrustva cln = (ClanDrustva) zahtev.getParametar();
                        ClanDrustva dbCln = controller.Controller.getInstanca().getClanById(cln.getClanID());
                        if (dbCln == null) {
                            odgovor.setOdgovor(new Exception("Clan ne postoji."));
                        } else if (prijavljeniAdmin == null || dbCln.getAdmin() == null
                                || dbCln.getAdmin().getAdminID() != prijavljeniAdmin.getAdminID()) {
                            odgovor.setOdgovor(new Exception("Nemate ovlascenje da azurirate ovog clana."));
                        } else {
                            controller.Controller.getInstanca().azurirajClan(cln);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case UCITAJ_UCESCA:
                        List<Ucesce> ucesca = controller.Controller.getInstanca().ucitajUcesca();
                        odgovor.setOdgovor(ucesca);
                        break;
                    case DODAJ_ANSAMBL_SA_SASTAVOM:
                        Ansambl ansSa = (Ansambl) zahtev.getParametar();
                        if (prijavljeniAdmin == null) {
                            odgovor.setOdgovor(new Exception("Niste prijavljeni, nije dozvoljeno kreiranje ansambla."));
                        } else {
                            ansSa.setAdmin(prijavljeniAdmin);
                            controller.Controller.getInstanca().dodajAnsamblSaSastavom(ansSa);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case AZURIRAJ_SASTAV_ANSAMBLA:
                        Ansambl ansUpd = (Ansambl) zahtev.getParametar();
                        Ansambl dbAn3 = controller.Controller.getInstanca().getAnsamblById(ansUpd.getAnsamblID());
                        if (dbAn3 == null) {
                            odgovor.setOdgovor(new Exception("Ansambl ne postoji."));
                        } else if (prijavljeniAdmin == null || dbAn3.getAdmin() == null
                                || dbAn3.getAdmin().getAdminID() != prijavljeniAdmin.getAdminID()) {
                            odgovor.setOdgovor(new Exception("Nemate ovlascenje da azurirate sastav ansambla."));
                        } else {
                            ansUpd.setAdmin(dbAn3.getAdmin());
                            controller.Controller.getInstanca().azurirajSastavAnsambla(ansUpd);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case DODAJ_CLAN:
                        ClanDrustva clan = (ClanDrustva) zahtev.getParametar();
                        System.out.println("SERVER DEBUG: primljen DODAJ_CLAN, ime='" + clan.getClanIme() + "', pol='" + clan.getClanPol() + "', god=" + clan.getClanGod() + ", tel='" + clan.getClanBrTel() + "'");
                        if (prijavljeniAdmin == null) {
                            odgovor.setOdgovor(new Exception("Niste prijavljeni, nije dozvoljeno kreiranje clana drustva."));
                        } else {
                            clan.setAdmin(prijavljeniAdmin);
                            controller.Controller.getInstanca().dodajClan(clan);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case NADJI_CLANOVE:
                        String trazena = (String) zahtev.getParametar();
                        List<ClanDrustva> nadjeni = controller.Controller.getInstanca().nadjiClanove(trazena);
                        odgovor.setOdgovor(nadjeni);
                        break;
                    case UCITAJ_CLANA:
                        ClanDrustva probe = (ClanDrustva) zahtev.getParametar();
                        ClanDrustva found = controller.Controller.getInstanca().getClanById(probe.getClanID());
                        if (found == null) {
                            odgovor.setOdgovor(new Exception("Clan ne postoji."));
                        } else {
                            odgovor.setOdgovor(found);
                        }
                        break;
                    default:
                        System.out.println("GRESKA");
                        odgovor.setOdgovor(new Exception("Nepoznata operacija na serveru"));
                        break;
                }
            } catch (Exception ex) {
                odgovor.setOdgovor(ex);
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                System.out.println("SERVER DEBUG: saljem odgovor klijentu za operaciju " + zahtev.getOperacija());
                posiljalac.posalji(odgovor);
                System.out.println("SERVER DEBUG: odgovor poslat.");
            } catch (Exception ioEx) {
                System.err.println("SERVER DEBUG: greska prilikom slanja odgovora klijentu:");
                ioEx.printStackTrace();
            }
        }
    }

    public void prekini() {
        kraj = true;
        try {
            if (posiljalac != null) {
                posiljalac.close();
            }
            if (primalac != null) {
                primalac.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.FINE, null, ex);
        }

        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }

}
