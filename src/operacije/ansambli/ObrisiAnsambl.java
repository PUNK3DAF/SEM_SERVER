/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.ansambli;

import domen.Ansambl;
import domen.Ucesce;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class ObrisiAnsambl extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Ansambl)) {
            throw new Exception("Sistem ne moze da obrise ansambl.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Ansambl a = (Ansambl) param;

        // Prvo obriši sve Ucesce zapise vezane za ovaj ansambl
        // da članovi ostanu, ali njihova učešća u ansamblu budu obrisana
        List<Ucesce> ucesca = a.getUcesca();
        if (ucesca != null && !ucesca.isEmpty()) {
            for (Ucesce u : ucesca) {
                broker.delete(u);
            }
        }

        // Zatim označi ansambl kao obrisan
        a.setObrisan(1);
        broker.edit(a);
    }

}
