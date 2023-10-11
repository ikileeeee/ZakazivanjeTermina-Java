/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Klijent;
import domain.StavkaTermina;
import domain.Termin;
import domain.Usluga;
import domain.Zaposleni;
import java.util.ArrayList;
import so.klijent.SODodajKlijenta;
import so.klijent.SOObrisiKlijenta;
import so.klijent.SOVratiSveKlijente;
import so.klijent.SOUpdejtujKlijenta;
import so.zaposleni.SOVratiSveZaposlene;
import so.login.SOLogin;
import so.stavkaTermina.SOVratiSveStavkaTermina;
import so.termin.SODodajTermin;
import so.termin.SODeleteTermin;
import so.termin.SOVratiSveTermine;
import so.termin.SOUpdejtujTermin;
import so.usluga.SOVratiSveUsluge;

/**
 *
 * @author ivani
 */
public class Kontroler {

    private static Kontroler instance;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni zaposleni) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(zaposleni);
        return so.getUlogovani();
    }

    public void addKlijent(Klijent klijent) throws Exception {
        (new SODodajKlijenta()).templateExecute(klijent);
    }

    public void addTermin(Termin termin) throws Exception {
        (new SODodajTermin()).templateExecute(termin);
    }

    public void deleteKlijent(Klijent klijent) throws Exception {
        (new SOObrisiKlijenta()).templateExecute(klijent);
    }

    public void deleteTermin(Termin termin) throws Exception {
        (new SODeleteTermin()).templateExecute(termin);
    }

    public void updateKlijent(Klijent klijent) throws Exception {
        (new SOUpdejtujKlijenta()).templateExecute(klijent);
    }

    public void updateTermin(Termin termin) throws Exception {
        (new SOUpdejtujTermin()).templateExecute(termin);
    }

    public ArrayList<Zaposleni> getAllZaposleni() throws Exception {
        SOVratiSveZaposlene so = new SOVratiSveZaposlene();
        so.templateExecute(new Zaposleni());
        return so.getLista();
    }

    public ArrayList<Klijent> getAllKlijent() throws Exception {
        SOVratiSveKlijente so = new SOVratiSveKlijente();
        so.templateExecute(new Klijent());
        return so.getLista();
    }

    public ArrayList<Termin> getAllTermin() throws Exception {
        SOVratiSveTermine so = new SOVratiSveTermine();
        so.templateExecute(new Termin());
        return so.getLista();
    }

    public ArrayList<Usluga> getAllUsluga() throws Exception {
        SOVratiSveUsluge so = new SOVratiSveUsluge();
        so.templateExecute(new Usluga());
        return so.getLista();
    }

    public ArrayList<StavkaTermina> getAllStavkaTermina(Termin t) throws Exception {
        SOVratiSveStavkaTermina so = new SOVratiSveStavkaTermina();

        StavkaTermina st = new StavkaTermina();
        st.setTermin(t);

        so.templateExecute(st);
        return so.getLista();
    }


}
