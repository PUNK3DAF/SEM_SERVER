/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Administrator;
import java.io.IOException;
import java.net.Socket;
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
                // Klijent je zatvorio konekciju ili je došlo do greške pri čitanju; prekinuti nit
                prekini();
                break;
            }
            Odgovor odgovor = new Odgovor();
            switch (zahtev.getOperacija()) {
                case LOGIN:
                    Administrator a = (Administrator) zahtev.getParametar();
                    a = controller.Controller.getInstanca().login(a);
                    odgovor.setOdgovor(a);
                    break;
                default:
                    System.out.println("GRESKA");
                    break;
            }
            posiljalac.posalji(odgovor);
        }
    }

    public void prekini() {
        kraj = true;
        // zatvori streamove prvo
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
