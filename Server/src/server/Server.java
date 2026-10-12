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
    static List<DataOutputStream> clientList = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5555);
        Scanner tastiera = new Scanner(System.in);

        // THREAD 1: Invia da tastiera a tutti i client
        new Thread(() -> {
            while (true) {
                try {
                    String msg = tastiera.nextLine();
                    for (DataOutputStream out : clientList) {
                        out.writeUTF("Server: " + msg);
                        out.flush();
                    }
                } catch (Exception e) {}
            }
        }).start();

        // ACCETTA MULTI-CLIENT
        while (true) {
            Socket socket = server.accept();
            int id = ++count;
            System.out.println("Client #" + id + " connesso!");

            new Thread(() -> {
                try {
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    clientList.add(out);

                    while (true) {
                        System.out.println("Client #" + id + ": " + in.readUTF());
                    }
                } catch (Exception e) {
                    System.out.println("Client #" + id + " disconnesso.");
                }
            }).start();
        }
    }
}