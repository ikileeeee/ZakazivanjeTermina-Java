/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavkaTermina;

import db.DBBroker;
import domain.AbstraktanObjekat;
import domain.StavkaTermina;
import java.util.ArrayList;
import so.AbstraktSO;

/**
 *
 * @author ivani
 */
public class SOVratiSveStavkaTermina extends AbstraktSO {

    private ArrayList<StavkaTermina> lista;

    @Override
    protected void validate(AbstraktanObjekat ado) throws Exception {
        if (!(ado instanceof StavkaTermina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaTermina!");
        }
    }

    @Override
    protected void execute(AbstraktanObjekat ado) throws Exception {
        ArrayList<AbstraktanObjekat> stavkeTermina = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaTermina>) (ArrayList<?>) stavkeTermina;
    }

    public ArrayList<StavkaTermina> getLista() {
        return lista;
    }

}
