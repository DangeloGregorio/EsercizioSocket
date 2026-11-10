/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import com.google.gson.Gson;
import java.nio.file.*;
import java.util.*;

/**
 *
 * @author dangelo.gregorio
 */
public class PallaMagicaJson {
    
    private final String personalita;
    private final Map<String, List<String>> catalogo;
    private final Random random = new Random();
 
    public PallaMagicaJson(String personalita) throws Exception {
        this.personalita = personalita.toLowerCase();
        String testo = new String(Files.readAllBytes(Paths.get("risposte.json")), java.nio.charset.StandardCharsets.UTF_8);
        this.catalogo = new Gson().fromJson(testo.replace("\uFEFF", ""), Map.class);
    }
 
    public String getPersonalita() {
        return personalita;
    }
 
    public static boolean valida(String p) {
        return p != null && List.of("normale", "secca", "bini", "meme").contains(p.toLowerCase());
    }
 
    public String rispondi(String domanda) {
        List<String> risposte = catalogo.get(personalita);
        return "[" + personalita.toUpperCase() + "]: " + risposte.get(random.nextInt(risposte.size()));
    }
    
}