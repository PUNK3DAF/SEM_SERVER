/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.dogadjaj;

import domen.ApstraktniDomenskiObjekat;
import domen.Dogadjaj;
import domen.Mesto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import operacije.ApstraktnaOperacija;

/**
 *
 * @author vldmrk
 */
public class UcitajDogadjaje extends ApstraktnaOperacija {

    private List<Dogadjaj> dogadjaji;

    @Override
    public void preduslovi() throws Exception {
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        // Load all events
        List<ApstraktniDomenskiObjekat> dogadjaji_ado = repository.Broker.getInstanca().getAll(new Dogadjaj(), null);
        dogadjaji = new java.util.ArrayList<>();
        for (ApstraktniDomenskiObjekat obj : dogadjaji_ado) {
            dogadjaji.add((Dogadjaj) obj);
        }
        
        // Load all places for mapping
        List<ApstraktniDomenskiObjekat> mesta_ado = repository.Broker.getInstanca().getAll(new Mesto(), null);
        Map<Integer, Mesto> mestoMap = new HashMap<>();
        for (ApstraktniDomenskiObjekat obj : mesta_ado) {
            Mesto m = (Mesto) obj;
            mestoMap.put(m.getMestoID(), m);
        }
        
        // Set place objects in events
        for (Dogadjaj d : dogadjaji) {
            Mesto m = mestoMap.get(d.getMestoID());
            if (m != null) {
                d.setMesto(m);
            }
        }
    }

    public List<Dogadjaj> getDogadjaji() {
        return dogadjaji;
    }
}

