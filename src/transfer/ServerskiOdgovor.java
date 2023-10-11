/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import transfer.util.StatusOdgovora;

/**
 *
 * @author ivani
 */
public class ServerskiOdgovor implements Serializable {

    private Object data;
    private Exception exc;
    private StatusOdgovora responseStatus;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object data, Exception exc, StatusOdgovora responseStatus) {
        this.data = data;
        this.exc = exc;
        this.responseStatus = responseStatus;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Exception getException() {
        return exc;
    }

    public void setException(Exception exc) {
        this.exc = exc;
    }

    public StatusOdgovora getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(StatusOdgovora responseStatus) {
        this.responseStatus = responseStatus;
    }

}
