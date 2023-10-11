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
public class SOUpdejtujKlijenta extends AbstraktSO {

    @Override
    protected void validate(AbstraktanObjekat ado) throws Exception {
        if (!(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }

        Klijent k = (Klijent) ado;

        ArrayList<Klijent> klijenti = (ArrayList<Klijent>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Klijent klijent : klijenti) {
            if (!klijent.getKlijentID().equals(k.getKlijentID())) {
                if (klijent.getEmail().equals(k.getEmail())) {
                    throw new Exception("Vec postoji klijent s tim emailom!");
                }
                if (klijent.getTelefon().equals(k.getTelefon())) {
                    throw new Exception("Vec postoji klijent s tim telefonom!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstraktanObjekat ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
