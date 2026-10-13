/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

/**
 *
 * @author dangelo.gregorio
 */

import java.net.*;

public class Server {
    private static int contatoreClient = 0;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("Server Palla Magica attivo");

        while (true) {
            Socket socket = serverSocket.accept();
            contatoreClient++;
            System.out.println("Client #" + contatoreClient + " connesso!");

            new GestoreClient(socket, contatoreClient).start();
        }
    }
}