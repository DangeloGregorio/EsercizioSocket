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
import java.util.*;

public class Server {
    // Lista di tutti i client connessi
    private static List<DataOutputStream> listaClient = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("In attesa del client...");
        Scanner tastiera = new Scanner(System.in);

        // Legge dalla tastiera del Server e invia a tutti i client
        new Thread(() -> {
            try {
                while (true) {
                    String msg = tastiera.nextLine();
                    synchronized (listaClient) {
                        for (DataOutputStream out : listaClient) {
                            out.writeUTF(msg);
                            out.flush();
                        }
                    }
                }
            } catch (Exception e) {
                System.exit(0);
            }
        }).start();

        // Accetta nuovi client continuamente
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connesso!");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            synchronized (listaClient) {
                listaClient.add(out);
            }

            //Legge i messaggi inviati da questo specifico Client
            new Thread(() -> {
                try {
                    while (true) {
                        String msg = in.readUTF();
                        System.out.println("\nClient: " + msg);
                    }
                } catch (Exception e) {
                    System.out.println("\nUn client si e' disconnesso.");
                    synchronized (listaClient) {
                        listaClient.remove(out);
                    }
                }
            }).start();
        }
    }
}