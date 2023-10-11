/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ivani
 */
public class Usluga extends AbstraktanObjekat {

    private Long uslugaID;
    private String naziv;
    private String opis;
    private double cena;

    @Override
    public String toString() {
        return naziv + " (Osnovna cena: " + cena + "din)";
    }

    public Usluga(Long uslugaID, String naziv, String opis, double cena) {
        this.uslugaID = uslugaID;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public Usluga() {
    }

    @Override
    public String nazivTabele() {
        return " Usluga ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstraktanObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstraktanObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Usluga u = new Usluga(rs.getLong("UslugaID"),
                    rs.getString("Naziv"), rs.getString("Opis"), rs.getDouble("Cena"));

            lista.add(u);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " UslugaID = " + uslugaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(Long uslugaID) {
        this.uslugaID = uslugaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

}
