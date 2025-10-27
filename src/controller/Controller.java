/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Administrator;
import java.util.logging.Level;
import java.util.logging.Logger;
import operacije.login.LoginOperacija;

/**
 *
 * @author vldmrk
 */
public class Controller {

    private static Controller instanca;

    private Controller() {
    }

    public static Controller getInstanca() {
        if (instanca == null) {
            instanca = new Controller();
        }
        return instanca;
    }

    public Administrator login(Administrator a) {
        LoginOperacija operacija = new LoginOperacija();
        try {
            operacija.izvrsi(a, null);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("KLASA CONTROLLER ZAPOSLENI: " + operacija.getA());
        return operacija.getA();
    }

}
