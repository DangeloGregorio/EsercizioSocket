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
    private  String personalita;
    private  Random random = new Random();
    
    
    private final String[] risposteNormali = {
        "e certo",
        "e decisamente così",
        "senza dubbio",
        "sì, sicuramente",
        "puoi contarci",
        "come la vedo io, sì",
        "molto probabilmente",
        "buone prospettive",
        "i segni dicono di sì",
        "risposta confusa, riprova",
        "chiedi più tardi",
        "meglio non dirtelo ora",
        "non posso prevedere ora",
        "concentrati e chiedi di nuovo",
        "non contarci",
        "la mia risposta è no",
        "le mie fonti dicono di no",
        "prospettive non buone",
        "molto dubbio"
        
        
    };
    private final String[] risposteSecche = {
        "sì.",
        "no",
        "forse",
        "snasni"
        
    };
    
    private final String[] risposteMeme = {
        "ma chi sei marlon oh",
        "si, bobby restera a Roma",
        "e guardamose",
        "casalekkio",
        "ah io cappo "
    };
    
    private final String[] risposteBini = {
        "perche?",
        "boh, secondo te?",
        "eh?",
        "ma che ne so io",
        "dangelo e un terrone",
        "casalekkio",
        "uuoh oh"
    };
    
    public PallaMagica(String personalita) {
        this.personalita = personalita;
    }

    public String getPersonalita() {
        return personalita;
    }

    public String rispondi(String domanda) {
        String[] scelte;
        switch (personalita.toLowerCase()) {
            case "Bini":
                scelte = risposteBini;
                break;
            case "Meme":
                scelte = risposteMeme;
                break;
            case "Secche":
                scelte = risposteSecche;
                break;
            default:
                scelte = risposteNormali;
                break;
        }
        return "[" + personalita.toUpperCase() + "]: " + scelte[random.nextInt(scelte.length)];
    }
}

