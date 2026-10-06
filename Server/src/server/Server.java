/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

/**
 *
 * @author dangelo.gregorio
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5555);
        Socket clientSocket = serverSocket.accept();

        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        Scanner tastiera = new Scanner(System.in);

        while (true) {
        // Legge il messaggio del client
        System.out.println("Client: " + in.readUTF());

        // Scrive e invia la risposta
        System.out.print("Server: ");
        out.writeUTF(tastiera.nextLine());

        }
    }
}