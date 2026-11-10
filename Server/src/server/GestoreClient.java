/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.*;
import java.net.*;


/**
 *
 * @author dangelo.gregorio
 */

public class GestoreClient extends Thread {
    // variabili
    private final Socket socket;
    private final int idClient;
 
    // costruttore
    public GestoreClient(Socket socket, int idClient) {
        this.socket = socket;
        this.idClient = idClient;
    }
 
    @Override
     public void run() {
        try {
            // flusso dati
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
 
            // invio messaggio iniziale
            out.writeUTF("Benvenuto nella palla");
            out.writeUTF("Scegli una personalita tra: Normale, Secca, Bini, Meme");
            out.flush();
 
            // scelta personalita iniziale con controllo errore
            String sceltaPersonalita = in.readUTF().trim();
            
            while (!PallaMagicaJson.valida(sceltaPersonalita)) {
                out.writeUTF("Personalita non valida, Scegli tra: Normale, Secca, Bini, Meme");
                out.flush();
                sceltaPersonalita = in.readUTF().trim();
            }
 
            PallaMagicaJson palla = new PallaMagicaJson(sceltaPersonalita);
 
            out.writeUTF("Personalita impostata: " + palla.getPersonalita().toUpperCase());
            out.writeUTF("Premi 'aiuto' per le istruzioni, oppure fai subito una domanda");
            out.flush();
 
            // gestione messaggi
            while (true) {
                String messaggio = in.readUTF().trim();
 
                // controllo messaggio vuoto
                if (messaggio.isEmpty()) {
                    out.writeUTF("Non hai scritto niente, fai una domanda oppure digita 'aiuto', 'p' o 'esci'.");
                } else if (messaggio.equalsIgnoreCase("aiuto")) {
                    out.writeUTF("GUIDA AIUTO");
                    out.writeUTF("1. Fai domande a cui si risponde con SI o NO.");
                    out.writeUTF("2. La Palla Magica rispondera in base alla personalita (" + palla.getPersonalita().toUpperCase() + ").");
                    out.writeUTF("3. Clicca 'Cambia Personalità' per cambiare personalita.");
                    out.writeUTF("4. Clicca 'esci' per chiudere la chat.\n");
                } else if (messaggio.equalsIgnoreCase("esci")) {
                    out.flush();
                    break;
                } else if (messaggio.equalsIgnoreCase("p")) {
                    // cambio personalita con controllo errore
                    out.writeUTF("Scegli la nuova personalita (Normale, Secca, Bini, Meme):");
                    out.flush();
                    String nuovaPersonalita = in.readUTF().trim();
                    
                    while (!PallaMagicaJson.valida(nuovaPersonalita)) {
                        out.writeUTF("Personalita non valida, Scegli tra: Normale, Secca, Bini, Meme");
                        out.flush();
                        nuovaPersonalita = in.readUTF().trim();
                    }
                    
                    palla = new PallaMagicaJson(nuovaPersonalita);
                    out.writeUTF("Personalita impostata: " + palla.getPersonalita().toUpperCase());
                } else {
                    System.out.println("Client #" + idClient + " chiede: " + messaggio);
                    String risposta = palla.rispondi(messaggio);
                    out.writeUTF(risposta);
                    out.writeUTF("per cambiare personalita Premere 'Cambia Personalità'");
                }
                out.flush();
            }
        } catch (Throwable e) {
            System.out.println("Client #" + idClient + " disconnesso.");
            e.printStackTrace();
            try {
                new DataOutputStream(socket.getOutputStream()).writeUTF("ERRORE SERVER: " + e);
            } catch (IOException ignored) {}
        } finally {
            // chiusura socket
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }
}