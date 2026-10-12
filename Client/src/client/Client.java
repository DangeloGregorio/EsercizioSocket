/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

/**
 *
 * @author dangelo.gregorio
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
       
        Socket socket = new Socket("127.0.0.1", 5555);
        System.out.println("Connesso al server");

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner tastiera = new Scanner(System.in);

        //Legge i messaggi inviati dal Server e li mostra a schermo
        new Thread(() -> {
            try {
                while (true) {
                    String msg = in.readUTF();
                    System.out.println("\nServer: " + msg);
                }
            } catch (Exception e) {
                System.out.println("\nIl server si e' disconnesso.");
                System.exit(0);
            }
        }).start();

        // Legge dalla tastiera del Client e invia al Server
        try {
            while (true) {
                String msg = tastiera.nextLine();
                out.writeUTF(msg);
                out.flush();
            }
        } catch (Exception e) {
            System.exit(0);
        }
    }
}