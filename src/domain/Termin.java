/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ivani
 */
public class Termin extends AbstraktanObjekat {

    private Long terminID;
    private Date datumVreme;
    private double ukupnaCena;
    private Klijent klijent;
    private Zaposleni zaposleni;
    private ArrayList<StavkaTermina> stavkeTermina;

    public Termin(Long terminID, Date datumVreme, double ukupnaCena, Klijent klijent, Zaposleni zaposleni, ArrayList<StavkaTermina> stavkeTermina) {
        this.terminID = terminID;
        this.datumVreme = datumVreme;
        this.ukupnaCena = ukupnaCena;
        this.klijent = klijent;
        this.zaposleni = zaposleni;
        this.stavkeTermina = stavkeTermina;
    }

    public Termin() {
    }

    @Override
    public String nazivTabele() {
        return " Termin ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN KLIJENT K ON (K.KLIJENTID = T.KLIJENTID) "
                + "JOIN ZAPOSLENI Z ON (Z.ZAPOSLENIID = T.ZAPOSLENIID) ";
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

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVreme, ukupnaCena, KlijentID, ZaposleniID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " terminID = " + terminID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVreme.getTime()) + "', "
                + ukupnaCena + ", "
                + klijent.getKlijentID() + ", " + zaposleni.getZaposleniID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumVreme = '" + new Timestamp(datumVreme.getTime()) + "', "
                + "ukupnaCena = " + ukupnaCena + " ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getTerminID() {
        return terminID;
    }

    public void setTerminID(Long terminID) {
        this.terminID = terminID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public ArrayList<StavkaTermina> getStavkeTermina() {
        return stavkeTermina;
    }

    public void setStavkeTermina(ArrayList<StavkaTermina> stavkeTermina) {
        this.stavkeTermina = stavkeTermina;
    }

}
