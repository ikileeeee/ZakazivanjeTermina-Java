/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.termin;

import db.DBBroker;
import domain.AbstraktanObjekat;
import domain.StavkaTermina;
import domain.Termin;
import java.util.Date;
import so.AbstraktSO;

/**
 *
 * @author ivani
 */
public class SOUpdejtujTermin extends AbstraktSO {

    @Override
    protected void validate(AbstraktanObjekat ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }
        
        Termin t = (Termin) ado;

        if (!t.getDatumVreme().after(new Date())) {
            throw new Exception("Datum i vreme termina mora biti u buducnosti!");
        }

        if (t.getStavkeTermina().isEmpty()) {
            throw new Exception("Termin mora imati barem jednu stavku!");
        }
        
    }

    @Override
    protected void execute(AbstraktanObjekat ado) throws Exception {
        
        DBBroker.getInstance().update(ado);
        
        Termin t = (Termin) ado;
        
        DBBroker.getInstance().delete(t.getStavkeTermina().get(0));
        
        for (StavkaTermina stavkaTermina : t.getStavkeTermina()) {
            DBBroker.getInstance().insert(stavkaTermina);
        }
        
    }

}
