/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import db.DBBroker;
import domain.AbstraktanObjekat;
import domain.Zaposleni;
import java.util.ArrayList;
import so.AbstraktSO;

/**
 *
 * @author ivani
 */
public class SOLogin extends AbstraktSO {
    
    Zaposleni ulogovani;

    @Override
    protected void validate(AbstraktanObjekat ado) throws Exception {
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zaposleni!");
        }
    }

    @Override
    protected void execute(AbstraktanObjekat ado) throws Exception {

        Zaposleni z = (Zaposleni) ado;

        ArrayList<Zaposleni> listaZaposlenih
                = (ArrayList<Zaposleni>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Zaposleni zaposleni : listaZaposlenih) {
            if (zaposleni.getUsername().equals(z.getUsername())
                    && zaposleni.getPassword().equals(z.getPassword())) {
                ulogovani = zaposleni;
                return;
            }
        }

        throw new Exception("Ne postoji zaposleni sa tim kredencijalima.");
        
    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }
    
    

}
