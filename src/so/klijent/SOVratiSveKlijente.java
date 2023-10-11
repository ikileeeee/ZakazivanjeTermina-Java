/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import db.DBBroker;
import domain.AbstraktanObjekat;
import domain.Klijent;
import java.util.ArrayList;
import so.AbstraktSO;

/**
 *
 * @author ivani
 */
public class SOVratiSveKlijente extends AbstraktSO {

    private ArrayList<Klijent> lista;

    @Override
    protected void validate(AbstraktanObjekat ado) throws Exception {
        if (!(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }
    }

    @Override
    protected void execute(AbstraktanObjekat ado) throws Exception {
        ArrayList<AbstraktanObjekat> klijenti = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Klijent>) (ArrayList<?>) klijenti;
    }

    public ArrayList<Klijent> getLista() {
        return lista;
    }

}
