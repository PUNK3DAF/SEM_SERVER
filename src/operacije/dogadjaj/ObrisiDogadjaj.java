/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.dogadjaj;

import domen.Dogadjaj;
import operacije.ApstraktnaOperacija;

/**
 *
 * @author vldmrk
 */
public class ObrisiDogadjaj extends ApstraktnaOperacija {

    @Override
    public void preduslovi() throws Exception {
        if (objekat == null || !(objekat instanceof Dogadjaj)) {
            throw new Exception("Nije prosledjen validan dogadjaj!");
        }
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        Dogadjaj d = (Dogadjaj) objekat;
        repository.Broker.getInstanca().delete(d);
    }

}
