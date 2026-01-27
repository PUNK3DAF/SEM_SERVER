/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.dogadjaj;

import domen.Dogadjaj;
import java.util.List;
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
        dogadjaji = repository.Broker.getInstanca().getAll(new Dogadjaj(), null);
    }

    public List<Dogadjaj> getDogadjaji() {
        return dogadjaji;
    }

}
