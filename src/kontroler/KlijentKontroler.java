/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domain.Klijent;
import domain.StavkaTermina;
import domain.Termin;
import domain.Usluga;
import domain.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import sesija.Sesija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.util.StatusOdgovora;
import transfer.util.Operacije;

/**
 *
 * @author ivani
 */
public class KlijentKontroler {

    private static KlijentKontroler instance;

    private KlijentKontroler() {
    }

    public static KlijentKontroler getInstance() {
        if (instance == null) {
            instance = new KlijentKontroler();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni zaposleni) throws Exception {
        return (Zaposleni) posaljiZahtev(Operacije.LOGIN, zaposleni);
    }

    public void addKlijent(Klijent klijent) throws Exception {
        posaljiZahtev(Operacije.ADD_KLIJENT, klijent);
    }

    public void addTermin(Termin termin) throws Exception {
        posaljiZahtev(Operacije.ADD_TERMIN, termin);
    }

    public void deleteKlijent(Klijent klijent) throws Exception {
        posaljiZahtev(Operacije.DELETE_KLIJENT, klijent);
    }

    public void deleteTermin(Termin termin) throws Exception {
        posaljiZahtev(Operacije.DELETE_TERMIN, termin);
    }

    public void updateKlijent(Klijent klijent) throws Exception {
        posaljiZahtev(Operacije.UPDATE_KLIJENT, klijent);
    }

    public void updateTermin(Termin termin) throws Exception {
        posaljiZahtev(Operacije.UPDATE_TERMIN, termin);
    }

    public ArrayList<Zaposleni> getAllZaposleni() throws Exception {
        return (ArrayList<Zaposleni>) posaljiZahtev(Operacije.GET_ALL_ZAPOSLENI, null);
    }

    public ArrayList<Klijent> getAllKlijent() throws Exception {
        return (ArrayList<Klijent>) posaljiZahtev(Operacije.GET_ALL_KLIJENT, null);
    }

    public ArrayList<Termin> getAllTermin() throws Exception {
        return (ArrayList<Termin>) posaljiZahtev(Operacije.GET_ALL_TERMIN, null);
    }

    public ArrayList<Usluga> getAllUsluga() throws Exception {
        return (ArrayList<Usluga>) posaljiZahtev(Operacije.GET_ALL_USLUGA, null);
    }

    public ArrayList<StavkaTermina> getAllStavkaTermina(Termin t) throws Exception {
        return (ArrayList<StavkaTermina>) posaljiZahtev(Operacije.GET_ALL_STAVKA_TERMINA, t);
    }

    private Object posaljiZahtev(int operation, Object data) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev(operation, data);

        ObjectOutputStream oos = new ObjectOutputStream(Sesija.getInstance().getSocket().getOutputStream());
        oos.writeObject(kz);

        ObjectInputStream ois = new ObjectInputStream(Sesija.getInstance().getSocket().getInputStream());
        ServerskiOdgovor so = (ServerskiOdgovor) ois.readObject();

        if (so.getResponseStatus().equals(StatusOdgovora.Error)) {
            throw so.getException();
        } else {
            return so.getData();
        }

    }
}
