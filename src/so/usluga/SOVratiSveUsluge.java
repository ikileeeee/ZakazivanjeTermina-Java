/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.usluga;

import db.DBBroker;
import domain.AbstraktanObjekat;
import domain.Usluga;
import java.util.ArrayList;
import so.AbstraktSO;

/**
 *
 * @author ivani
 */
public class SOVratiSveUsluge extends AbstraktSO {

    private ArrayList<Usluga> lista;

    @Override
    protected void validate(AbstraktanObjekat ado) throws Exception {
        if (!(ado instanceof Usluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Usluga!");
        }
    }

    @Override
    protected void execute(AbstraktanObjekat ado) throws Exception {
        ArrayList<AbstraktanObjekat> usluge = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Usluga>) (ArrayList<?>) usluge;
    }

    public ArrayList<Usluga> getLista() {
        return lista;
    }

}
