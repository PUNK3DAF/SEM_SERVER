/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.login;

import domen.Administrator;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Administrator a;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Administrator parametar je null.");
        }
        if (!(param instanceof Administrator)) {
            throw new Exception("Ocekivan parametar tipa Administrator.");
        }
        Administrator adm = (Administrator) param;
        if (adm.getAdminUsername() == null || adm.getAdminUsername().trim().isEmpty()) {
            throw new Exception("Korisnicko ime ne sme biti prazno.");
        }
        if (adm.getAdminPassword() == null || adm.getAdminPassword().trim().isEmpty()) {
            throw new Exception("Lozinka ne sme biti prazna.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Administrator trazeni = (Administrator) param;
        String trazeniUser = (trazeni.getAdminUsername() == null) ? "" : trazeni.getAdminUsername().trim();
        String trazeniPass = (trazeni.getAdminPassword() == null) ? "" : trazeni.getAdminPassword().trim();

        List<Administrator> sviAdmini = broker.getAll(param, null);
        System.out.println("KLASA LoginOperacija lista admina: " + sviAdmini);
        System.out.println("LOGIN - primljeni username: '" + trazeniUser + "' password: '" + trazeniPass + "'");

        for (Administrator ad : sviAdmini) {
            String dbUser = (ad.getAdminUsername() == null) ? "" : ad.getAdminUsername().trim();
            String dbPass = (ad.getAdminPassword() == null) ? "" : ad.getAdminPassword().trim();
            System.out.println("  DB admin -> username: '" + dbUser + "' password: '" + dbPass + "'");

            // strogo poređenje username i password (trim, null-safe)
            if (dbUser.equals(trazeniUser) && dbPass.equals(trazeniPass)) {
                a = ad;
                System.out.println("  -> Uspesno pronadjen admin: " + a);
                return;
            }
        }

        a = null;
        System.out.println("  -> Nije pronadjen odgovarajuci admin (login neuspesan).");
    }

    public Administrator getA() {
        return a;
    }

    public void setA(Administrator a) {
        this.a = a;
    }

}
