/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.*;
import java.util.*;

/**
 *
 * @author dangelo.gregorio
 */


public class PallaMagica {
     private static HashMap<String, ArrayList<String>> catalogo = new HashMap<>();

    private String personalita;
    private Random random = new Random();

    // legge il file riga per riga
    public static void caricaCatalogo(String file) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"));
        ArrayList<String> lista = null;
        String riga;

        while ((riga = br.readLine()) != null) {
            riga = riga.trim();

            if (riga.endsWith("[")) {
                // riga tipo:  "normale": [   -> nuova personalita
                String nome = riga.split("\"")[1];
                lista = new ArrayList<>();
                catalogo.put(nome, lista);
            } else if (riga.startsWith("\"")) {
                // riga tipo:  "e certo",   -> una risposta
                lista.add(riga.split("\"")[1]);
            }
        }
        br.close();
    }

    public PallaMagica(String personalita) {
        this.personalita = personalita;
    }

    public String getPersonalita() {
        return personalita;
    }

    // valida se esiste nel catalogo
    public static boolean valida(String p) {
        return p != null && catalogo.containsKey(p.toLowerCase());
    }

    public String rispondi(String domanda) {
        ArrayList<String> scelte = catalogo.get(personalita.toLowerCase());
        return "[" + personalita.toUpperCase() + "]: " + scelte.get(random.nextInt(scelte.size()));
    }
}