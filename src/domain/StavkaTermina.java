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
public class StavkaTermina extends AbstraktanObjekat {

    private Termin termin;
    private int rbStavke;
    private double cenaStavke;
    private Usluga usluga;

    public StavkaTermina(Termin termin, int rbStavke, double cenaStavke, Usluga usluga) {
        this.termin = termin;
        this.rbStavke = rbStavke;
        this.cenaStavke = cenaStavke;
        this.usluga = usluga;
    }

    public StavkaTermina() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaTermina ";
    }

    @Override
    public String alijas() {
        return " st ";
    }

    @Override
    public String join() {
        return " JOIN USLUGA U ON (U.USLUGAID = ST.USLUGAID) "
                + "JOIN TERMIN T ON (T.TERMINID = ST.TERMINID) "
                + "JOIN KLIJENT K ON (K.KLIJENTID = T.KLIJENTID) "
                + "JOIN ZAPOSLENI Z ON (Z.ZAPOSLENIID = T.ZAPOSLENIID)";
    }

    @Override
    public ArrayList<AbstraktanObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstraktanObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Zaposleni z = new Zaposleni(rs.getLong("ZaposleniID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Klijent k = new Klijent(rs.getLong("KlijentID"),
                    rs.getString("ImeKlijenta"), rs.getString("PrezimeKlijenta"),
                    rs.getString("email"), rs.getString("telefon"));

            Termin t = new Termin(rs.getLong("terminID"), rs.getTimestamp("datumVreme"),
                    rs.getDouble("ukupnaCena"), k, z, null);

            Usluga u = new Usluga(rs.getLong("UslugaID"),
                    rs.getString("Naziv"), rs.getString("Opis"), rs.getDouble("Cena"));

            StavkaTermina st = new StavkaTermina(t, rs.getInt("rbStavke"),
                    rs.getDouble("cenaStavke"), u);

            lista.add(st);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (terminID, rbStavke, cenaStavke, UslugaID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " terminID = " + termin.getTerminID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + termin.getTerminID() + ", " + rbStavke + ", "
                + " " + cenaStavke + ", " + usluga.getUslugaID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " WHERE T.TERMINID = " + termin.getTerminID();
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

}
