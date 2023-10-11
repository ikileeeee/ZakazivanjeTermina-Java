/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.Kontroler;
import domain.Klijent;
import domain.Termin;
import domain.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.util.StatusOdgovora;
import transfer.util.Operacije;

/**
 *
 * @author ivani
 */
public class NitiKlijent extends Thread {

    private Socket socket;

    NitiKlijent(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtev kz = (KlijentskiZahtev) in.readObject();
                ServerskiOdgovor so = obradaKlijentskihZahteva(kz);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(so);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor obradaKlijentskihZahteva(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor(null, null, StatusOdgovora.Success);
        try {
            switch (kz.getOperation()) {
                case Operacije.ADD_KLIJENT:
                    Kontroler.getInstance().addKlijent((Klijent) kz.getData());
                    break;
                case Operacije.ADD_TERMIN:
                    Kontroler.getInstance().addTermin((Termin) kz.getData());
                    break;
                case Operacije.DELETE_KLIJENT:
                    Kontroler.getInstance().deleteKlijent((Klijent) kz.getData());
                    break;
                case Operacije.DELETE_TERMIN:
                    Kontroler.getInstance().deleteTermin((Termin) kz.getData());
                    break;
                case Operacije.UPDATE_KLIJENT:
                    Kontroler.getInstance().updateKlijent((Klijent) kz.getData());
                    break;
                case Operacije.UPDATE_TERMIN:
                    Kontroler.getInstance().updateTermin((Termin) kz.getData());
                    break;
                case Operacije.GET_ALL_ZAPOSLENI:
                    so.setData(Kontroler.getInstance().getAllZaposleni());
                    break;
                case Operacije.GET_ALL_STAVKA_TERMINA:
                    so.setData(Kontroler.getInstance().getAllStavkaTermina((Termin) kz.getData()));
                    break;
                case Operacije.GET_ALL_KLIJENT:
                    so.setData(Kontroler.getInstance().getAllKlijent());
                    break;
                case Operacije.GET_ALL_TERMIN:
                    so.setData(Kontroler.getInstance().getAllTermin());
                    break;
                case Operacije.GET_ALL_USLUGA:
                    so.setData(Kontroler.getInstance().getAllUsluga());
                    break;
                case Operacije.LOGIN:
                    Zaposleni zaposleni = (Zaposleni) kz.getData();
                    Zaposleni ulogovani = Kontroler.getInstance().login(zaposleni);
                    so.setData(ulogovani);
                    break;                                     
                default:
                    return null;
            }
        } catch (Exception e) {
            so.setResponseStatus(StatusOdgovora.Error);
            so.setException(e);
        }
        return so;
    }

}
