/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.*;
import java.net.*;
import java.util.Random;

/**
 *
 * @author dangelo.gregorio
 */
public class GestoreClient extends Thread {
    private final Socket socket;
    private final int idClient;

    public GestoreClient(Socket socket, int idClient) {
        this.socket = socket;
        this.idClient = idClient;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Sceglie una personalita' casuale per questo client
            String[] tipi = {"Secca", "Normale", "Bini", "Meme"};
            String sceltaPersonalita = tipi[new Random().nextInt(tipi.length)];
            PallaMagica palla = new PallaMagica(sceltaPersonalita);

            // Messaggio iniziale con istruzioni
            out.writeUTF("Benvenuto nella palla 67!");
            out.writeUTF("Scegli una personalita tra: Normale, Secca, Bini, Meme");
            out.flush();

            // Legge la scelta inviata dal client
            String sceltaPersonalita = in.readUTF().trim();
            PallaMagica palla = new PallaMagica(sceltaPersonalita);

            out.writeUTF("Personalita impostata: " + palla.getPersonalita().toUpperCase());
            out.writeUTF("Scrivi 'aiuto' per le istruzioni, oppure fai subito una domanda");
            out.flush();

            while (true) {
                String messaggio = in.readUTF().trim();

                if (messaggio.equalsIgnoreCase("aiuto")) {
                    out.writeUTF("GUIDA AIUTO");
                    out.writeUTF("1. Fai domande a cui si risponde con SI o NO.");
                    out.writeUTF("2. La Palla Magica rispondera in base alla personalita (" + palla.getPersonalita().toUpperCase() + ").");
                    out.writeUTF("3. Scrivi 'esci' per chiudere la chat.\n");
                } else if (messaggio.equalsIgnoreCase("esci")) {
                    out.writeUTF("67");
                    out.flush();
                    break;
                } else if (messaggio.equalsIgnoreCase("67")) {
                    out.writeUTF("67 67 67 67 67 67 67");
                } else {
                    System.out.println("Client #" + idClient + " chiede: " + messaggio);
                    String risposta = palla.rispondi(messaggio);
                    out.writeUTF(risposta);
                }
                out.flush();
            }
        } catch (Exception e) {
            System.out.println("Client #" + idClient + " disconnesso.");
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }
}