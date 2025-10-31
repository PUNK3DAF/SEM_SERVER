/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.ansambli;

import domen.Ansambl;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class UcitajAnsamble extends ApstraktnaGenerickaOperacija {

    private List<Ansambl> ansambli;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ansambli = broker.getAll(new Ansambl(), " WHERE obrisan=0");
    }

    public List<Ansambl> getAnsambli() {
        return ansambli;
    }

}
