/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import kontroler.KlijentKontroler;
import domain.StavkaTermina;
import domain.Termin;
import domain.Usluga;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ivani
 */
public class ModelTabeleStavke extends AbstractTableModel {

    private ArrayList<StavkaTermina> lista;
    private String[] kolone = {"Rb", "Usluga", "Cena"};
    private int rb = 0;

    public ModelTabeleStavke() {
        lista = new ArrayList<>();
    }

    public ModelTabeleStavke(Termin t) {
        try {
            lista = KlijentKontroler.getInstance().getAllStavkaTermina(t);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleStavke.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaTermina st = lista.get(row);

        switch (column) {
            case 0:
                return st.getRbStavke();
            case 1:
                return st.getUsluga().getNaziv();
            case 2:
                return st.getCenaStavke() + "din";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaTermina st) {
        rb = lista.size();
        st.setRbStavke(++rb);
        lista.add(st);
        fireTableDataChanged();
    }

    public boolean postojiUsluga(Usluga u) {
        for (StavkaTermina stavkaTermina : lista) {
            if (stavkaTermina.getUsluga().getUslugaID().equals(u.getUslugaID())) {
                return true;
            }
        }
        return false;
    }

    public void obrisiUslugu(int row) {
        lista.remove(row);
        
        rb = 0;
        for (StavkaTermina stavkaTermina : lista) {
            stavkaTermina.setRbStavke(++rb);
        }
        
        fireTableDataChanged();
    }

    public double vratiUkupnuCenu() {
        double ukupnaCena = 0;
        
        for (StavkaTermina stavkaTermina : lista) {
            ukupnaCena += stavkaTermina.getCenaStavke();
        }
        
        return ukupnaCena;
    }

    public ArrayList<StavkaTermina> getLista() {
        return lista;
    }

}
