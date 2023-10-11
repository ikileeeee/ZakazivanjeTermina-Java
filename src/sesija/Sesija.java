/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesija;

import domain.Zaposleni;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author ivani
 */
public class Sesija {

    private static Sesija instance;
    private Socket socket;
    private Zaposleni ulogovani;

    private Sesija() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Sesija getInstance() {
        if (instance == null) {
            instance = new Sesija();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

}
