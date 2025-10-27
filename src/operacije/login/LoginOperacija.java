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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Administrator> sviAdmini = broker.getAll(param, null);
        System.out.println("KLASA LoginOperacija lista admina: " + sviAdmini);

        for (Administrator ad : sviAdmini) {
            if (ad.equals(param)) {
                a = ad;
                return;
            }
        }

        a = null;
    }

    public Administrator getA() {
        return a;
    }

    public void setA(Administrator a) {
        this.a = a;
    }

}
