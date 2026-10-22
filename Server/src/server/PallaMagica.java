/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.util.Random;

/**
 *
 * @author dangelo.gregorio
 */


public class PallaMagica {
    // variabili
    private String personalita;
    private Random random = new Random();
    
    // liste risposte
    private final String[] risposteNormali = {
        "e certo",
        "e decisamente così",
        "senza dubbio",
        "si, sicuramente",
        "puoi contarci",
        "come la vedo io, si",
        "molto probabilmente",
        "buone prospettive",
        "i segni dicono di si",
        "risposta confusa, riprova",
        "chiedi piu tardi",
        "meglio non dirtelo ora",
        "non posso prevedere ora",
        "concentrati e chiedi di nuovo",
        "non contarci",
        "la mia risposta e no",
        "le mie fonti dicono di no",
        "prospettive non buone",
        "molto dubbio"
    };
    
    private final String[] risposteSecche = {
        "si.",
        "no",
        "forse",
        "snasni"
    };
    
    private final String[] risposteMeme = {
        "ma chi sei marlon oh",
        "si, bobby restera a Roma",
        "e guardamose",
        "casalekkio",
        "ah io cappo ",
        "ma cosa ho appena letto",
        "real",
    };
    
    private final String[] risposteBini = {
        "perche?",
        "boh, secondo te?",
        "eh?",
        "ma che ne so io",
        "dangelo e un terrone",
        "casalekkio",
        "uuoh oh",
        "non ho capito",
        "boh",
        "e stato dangelo"
    };
    
    // costruttore
    public PallaMagica(String personalita) {
        this.personalita = personalita;
    }

    // getter
    public String getPersonalita() {
        return personalita;
    }

    // controllo personalita valida
    public static boolean valida(String p) {
        if (p == null) return false;
        String s = p.toLowerCase();
        return s.equals("normale") || s.equals("secca") || s.equals("bini") || s.equals("meme");
    }

    // generazione risposta
    public String rispondi(String domanda) {
        String[] scelte;
        switch (personalita.toLowerCase()) {
            case "bini":
                scelte = risposteBini;
                break;
            case "meme":
                scelte = risposteMeme;
                break;
            case "secca":
                scelte = risposteSecche;
                break;
            default:
                scelte = risposteNormali;
                break;
        }
        return "[" + personalita.toUpperCase() + "]: " + scelte[random.nextInt(scelte.length)];
    }
}