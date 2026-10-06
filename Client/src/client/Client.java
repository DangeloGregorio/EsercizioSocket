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
        Socket socket = new Socket("localhost", 5555);

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        Scanner tastiera = new Scanner(System.in);

        while (true) {
        // Scrive e invia il messaggio
        System.out.print("Client: ");
        out.writeUTF(tastiera.nextLine());

        // Legge la risposta del server
        System.out.println("Server: " + in.readUTF());

        }
    }
}