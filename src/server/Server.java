package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import niti.ObradaKlijentskihZahteva;

public class Server extends Thread {

    private boolean kraj = false;
    private ServerSocket serverSoket;
    private List<ObradaKlijentskihZahteva> klijenti;

    public Server() {
        klijenti = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            serverSoket = new ServerSocket(9000);
            while (!kraj) {
                Socket s = serverSoket.accept();
                System.out.println("KLIJENT JE POVEZAN.");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                klijenti.add(okz);
                okz.start();
            }
        } catch (IOException ex) {
            if (!kraj) {
                System.err.println("Greska pri pokretanju servera: " + ex.getMessage());
            }
        }
    }

    public void zaustaviServer() {
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva k : klijenti) {
                k.prekini();
            }
            if (serverSoket != null) {
                serverSoket.close();
            }
        } catch (IOException ex) {
            System.err.println("Greska pri zaustavljanju servera: " + ex.getMessage());
        }
    }
}
