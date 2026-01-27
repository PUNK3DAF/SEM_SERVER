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
public class IzmeniDogadjaj extends ApstraktnaOperacija {

    @Override
    public void preduslovi() throws Exception {
        if (objekat == null || !(objekat instanceof Dogadjaj)) {
            throw new Exception("Nije prosledjen validan dogadjaj!");
        }

        Dogadjaj d = (Dogadjaj) objekat;

        if (d.getNaziv() == null || d.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv događaja ne sme biti prazan!");
        }

        if (d.getDatum() == null) {
            throw new Exception("Datum događaja je obavezan!");
        }

        if (d.getMesto() == null || d.getMesto().getMestoID() == null) {
            throw new Exception("Mesto događaja nije izabrano!");
        }
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        Dogadjaj d = (Dogadjaj) objekat;
        d.setNaziv(d.getNaziv().trim());
        repository.Broker.getInstanca().edit(d);
    }

}
