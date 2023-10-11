/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import db.DBBroker;
import domain.AbstraktanObjekat;
import domain.Zaposleni;
import java.util.ArrayList;
import so.AbstraktSO;

/**
 *
 * @author ivani
 */
public class SOVratiSveZaposlene extends AbstraktSO {

    private ArrayList<Zaposleni> lista;

    @Override
    protected void validate(AbstraktanObjekat ado) throws Exception {
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zaposleni!");
        }
    }

    @Override
    protected void execute(AbstraktanObjekat ado) throws Exception {
        ArrayList<AbstraktanObjekat> listaZaposlenih = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Zaposleni>) (ArrayList<?>) listaZaposlenih;
    }

    public ArrayList<Zaposleni> getLista() {
        return lista;
    }

}
