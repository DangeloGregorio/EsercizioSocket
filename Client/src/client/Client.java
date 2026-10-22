/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

/**
 *
 * @author dangelo.gregorio
 */


public class Client {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new ClientGUI().setVisible(true);
        });
    }
}