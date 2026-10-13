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
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner tastiera = new Scanner(System.in);

        // Legge i messaggi del server
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("\n" + in.readUTF());
                } catch (Exception e) {
                    System.out.println("Server disconnesso.");
                    System.exit(0);
                }
            }
        }).start();

        //Invia i messaggi al server
        while (true) {
            out.writeUTF(tastiera.nextLine());
            out.flush();
        }
    }
}