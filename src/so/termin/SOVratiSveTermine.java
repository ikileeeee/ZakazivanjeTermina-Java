/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.termin;

import db.DBBroker;
import domain.AbstraktanObjekat;
import domain.Termin;
import java.util.ArrayList;
import so.AbstraktSO;

/**
 *
 * @author ivani
 */
public class SOVratiSveTermine extends AbstraktSO {

    private ArrayList<Termin> lista;

    @Override
    protected void validate(AbstraktanObjekat ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }
    }

    @Override
    protected void execute(AbstraktanObjekat ado) throws Exception {
        ArrayList<AbstraktanObjekat> termini = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Termin>) (ArrayList<?>) termini;
    }

    public ArrayList<Termin> getLista() {
        return lista;
    }

}
