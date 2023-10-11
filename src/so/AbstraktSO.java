/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domain.AbstraktanObjekat;
import java.sql.SQLException;

/**
 *
 * @author ivani
 */
public abstract class AbstraktSO {
    
    protected abstract void validate(AbstraktanObjekat ado) throws Exception;
    protected abstract void execute(AbstraktanObjekat ado) throws Exception;

    public void templateExecute(AbstraktanObjekat ado) throws Exception {
        try {
            validate(ado);
            execute(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
