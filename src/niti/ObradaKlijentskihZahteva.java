package niti;

import domen.Administrator;
import domen.Ansambl;
import domen.ClanDrustva;
import domen.Dogadjaj;
import domen.Mesto;
import domen.Ucesce;
import domen.Zanr;
import domen.Uloga;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

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
                    case KREIRAJ_ANSAMBL:
                        Ansambl ans = (Ansambl) zahtev.getParametar();
                        if (prijavljeniAdmin == null) {
                            odgovor.setOdgovor(new Exception("Niste prijavljeni, nije dozvoljeno kreiranje ansambla."));
                        } else {
                            ans.setAdmin(prijavljeniAdmin);
                            controller.Controller.getInstanca().kreirajAnsambl(ans);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case UCITAJ_ANSAMBLE:
                        List<Ansambl> ansambli = controller.Controller.getInstanca().ucitajAnsamble();
                        odgovor.setOdgovor(ansambli);
                        break;
                    case OBRISI_ANSAMBL:
                        Ansambl an = (Ansambl) zahtev.getParametar();
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
                    case IZMENA_ANSAMBLA:
                        Ansambl ansa = (Ansambl) zahtev.getParametar();
                        Ansambl dbAn2 = controller.Controller.getInstanca().getAnsamblById(ansa.getAnsamblID());
                        if (dbAn2 == null) {
                            odgovor.setOdgovor(new Exception("Ansambl ne postoji."));
                        } else if (prijavljeniAdmin == null || dbAn2.getAdmin() == null
                                || dbAn2.getAdmin().getAdminID() != prijavljeniAdmin.getAdminID()) {
                            odgovor.setOdgovor(new Exception("Nemate ovlascenje da azurirate ovaj ansambl."));
                        } else {
                            ansa.setAdmin(dbAn2.getAdmin());
                            controller.Controller.getInstanca().izmenaAnsambla(ansa);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case UCITAJ_CLANOVE:
                        List<ClanDrustva> clanovi = controller.Controller.getInstanca().ucitajClanove();
                        odgovor.setOdgovor(clanovi);
                        break;
                    case OBRISI_CLANA_DRUSTVA:
                        ClanDrustva c = (ClanDrustva) zahtev.getParametar();
                        ClanDrustva dbC = controller.Controller.getInstanca().ucitajClanaDrustva(c.getClanID());
                        if (dbC == null) {
                            odgovor.setOdgovor(new Exception("Clan ne postoji."));
                        } else if (prijavljeniAdmin == null || dbC.getAdmin() == null
                                || dbC.getAdmin().getAdminID() != prijavljeniAdmin.getAdminID()) {
                            odgovor.setOdgovor(new Exception("Nemate ovlascenje da obrisete ovog clana."));
                        } else {
                            controller.Controller.getInstanca().obrisiClanaDrustva(c);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case IZMENI_CLANA_DRUSTVA:
                        ClanDrustva cln = (ClanDrustva) zahtev.getParametar();
                        ClanDrustva dbCln = controller.Controller.getInstanca().ucitajClanaDrustva(cln.getClanID());
                        if (dbCln == null) {
                            odgovor.setOdgovor(new Exception("Clan ne postoji."));
                        } else if (prijavljeniAdmin == null || dbCln.getAdmin() == null
                                || dbCln.getAdmin().getAdminID() != prijavljeniAdmin.getAdminID()) {
                            odgovor.setOdgovor(new Exception("Nemate ovlascenje da azurirate ovog clana."));
                        } else {
                            controller.Controller.getInstanca().izmeniClanaDrustva(cln);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case KREIRAJ_CLANA_DRUSTVA:
                        ClanDrustva clan = (ClanDrustva) zahtev.getParametar();
                        if (prijavljeniAdmin == null) {
                            odgovor.setOdgovor(new Exception("Niste prijavljeni, nije dozvoljeno kreiranje clana drustva."));
                        } else {
                            clan.setAdmin(prijavljeniAdmin);
                            controller.Controller.getInstanca().kreirajClanaDrustva(clan);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case NADJI_CLANA_DRUSTVA:
                        String trazena = (String) zahtev.getParametar();
                        List<ClanDrustva> nadjeni = controller.Controller.getInstanca().nadjiClanaDrustva(trazena);
                        odgovor.setOdgovor(nadjeni);
                        break;
                    case UCITAJ_CLANA_DRUSTVA:
                        ClanDrustva probe = (ClanDrustva) zahtev.getParametar();
                        ClanDrustva found = controller.Controller.getInstanca().ucitajClanaDrustva(probe.getClanID());
                        if (found == null) {
                            odgovor.setOdgovor(new Exception("Clan ne postoji."));
                        } else {
                            odgovor.setOdgovor(found);
                        }
                        break;
                    case UCITAJ_ANSAMBL:
                        Ansambl probeAns = (Ansambl) zahtev.getParametar();
                        Ansambl foundAns = controller.Controller.getInstanca().getAnsamblById(probeAns.getAnsamblID());
                        if (foundAns == null) {
                            odgovor.setOdgovor(new Exception("Ansambl ne postoji."));
                        } else {
                            odgovor.setOdgovor(foundAns);
                        }
                        break;
                    case NADJI_ANSAMBL:
                        String trazenoAns = (String) zahtev.getParametar();
                        List<Ansambl> nadjenoAns = controller.Controller.getInstanca().nadjiAnsambl(trazenoAns);
                        odgovor.setOdgovor(nadjenoAns);
                        break;
                    case UCITAJ_UCESCA:
                        List<Ucesce> ucesca = controller.Controller.getInstanca().ucitajUcesca();
                        odgovor.setOdgovor(ucesca);
                        break;
                    case KREIRAJ_ZANR:
                        Zanr zanr = (Zanr) zahtev.getParametar();
                        if (prijavljeniAdmin == null) {
                            odgovor.setOdgovor(new Exception("Niste prijavljeni, nije dozvoljeno kreiranje zanra."));
                        } else {
                            controller.Controller.getInstanca().kreirajZanr(zanr);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case UCITAJ_ZANROVE:
                        List<Zanr> zanrovi = controller.Controller.getInstanca().ucitajZanrove();
                        odgovor.setOdgovor(zanrovi);
                        break;
                    case KREIRAJ_ULOGU:
                        Uloga uloga = (Uloga) zahtev.getParametar();
                        if (prijavljeniAdmin == null) {
                            odgovor.setOdgovor(new Exception("Niste prijavljeni, nije dozvoljeno kreiranje uloge."));
                        } else {
                            controller.Controller.getInstanca().kreirajUlogu(uloga);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case UCITAJ_ULOGE:
                        List<Uloga> uloge = controller.Controller.getInstanca().ucitajUloge();
                        odgovor.setOdgovor(uloge);
                        break;
                    case KREIRAJ_MESTO:
                        Mesto mesto = (Mesto) zahtev.getParametar();
                        if (prijavljeniAdmin == null) {
                            odgovor.setOdgovor(new Exception("Niste prijavljeni, nije dozvoljeno kreiranje mesta."));
                        } else {
                            controller.Controller.getInstanca().kreirajMesto(mesto);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case UCITAJ_MESTA:
                        List<Mesto> mesta = controller.Controller.getInstanca().ucitajMesta();
                        odgovor.setOdgovor(mesta);
                        break;
                    case KREIRAJ_DOGADJAJ:
                        Dogadjaj dogadjaj = (Dogadjaj) zahtev.getParametar();
                        if (prijavljeniAdmin == null) {
                            odgovor.setOdgovor(new Exception("Niste prijavljeni, nije dozvoljeno kreiranje događaja."));
                        } else {
                            controller.Controller.getInstanca().kreirajDogadjaj(dogadjaj);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case UCITAJ_DOGADJAJE:
                        List<Dogadjaj> dogadjaji = controller.Controller.getInstanca().ucitajDogadjaje();
                        odgovor.setOdgovor(dogadjaji);
                        break;
                    case IZMENI_DOGADJAJ:
                        Dogadjaj dogadjajIzmena = (Dogadjaj) zahtev.getParametar();
                        if (prijavljeniAdmin == null) {
                            odgovor.setOdgovor(new Exception("Niste prijavljeni, nije dozvoljeno menjanje događaja."));
                        } else {
                            controller.Controller.getInstanca().izmeniDogadjaj(dogadjajIzmena);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    case OBRISI_DOGADJAJ:
                        Dogadjaj dogadjajBrisanje = (Dogadjaj) zahtev.getParametar();
                        if (prijavljeniAdmin == null) {
                            odgovor.setOdgovor(new Exception("Niste prijavljeni, nije dozvoljeno brisanje događaja."));
                        } else {
                            controller.Controller.getInstanca().obrisiDogadjaj(dogadjajBrisanje);
                            odgovor.setOdgovor(null);
                        }
                        break;
                    default:
                        odgovor.setOdgovor(new Exception("Nepoznata operacija na serveru"));
                        break;
                }
            } catch (Exception ex) {
                odgovor.setOdgovor(ex);
            }
            try {
                posiljalac.posalji(odgovor);
            } catch (Exception ioEx) {
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
            // Ignore close errors
        }

        try {
            socket.close();
        } catch (IOException ex) {
            // Ignore socket close errors
        }
        interrupt();
    }

}
