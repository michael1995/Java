/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author michaelborodin
 */
public class Chat_Server_Return implements Runnable {

    /**
     * global variables
     */
    Socket SOCK;
    private Scanner INPUT;
    private PrintWriter OUT;
    String MESSAGE = "";
    ArrayList<Socket> ConnectionArray;
    Chat_Server server;
    /**
     *
     * @param X
     * @param pConnectionArray
     */
    public Chat_Server_Return(Socket X, ArrayList<Socket> pConnectionArray, Chat_Server pServer) {
        this.SOCK = X;
        this.ConnectionArray = pConnectionArray;
        server = pServer;
    }

    /**
     * check if you are connected to the socked
     *
     * @throws IOException
     */
    public void CheckConnection() throws IOException {
        //to see if you are still logged on
        //if the socket is not connected then remove from the connection array 
        if (!SOCK.isConnected()) {
            for (int i = 1; i <= ConnectionArray.size(); i++) {
                if (ConnectionArray.get(i) == SOCK) {
                    ConnectionArray.remove(i);
                }
            }
            //if the connection array is no more then tell the user that the user has disconnected
            for (int i = 1; i <= ConnectionArray.size(); i++) {
                Socket TEMP_SOCK = (Socket) ConnectionArray.get(i - 1);
                PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
                TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName() + "disconnected");
                TEMP_OUT.flush();

                System.out.println(TEMP_SOCK.getLocalAddress().getHostName() + "disconnected");
            }
        }
    }

    /**
     * on run method check the connection
     */
    public void run() {

        try {
            INPUT = new Scanner(SOCK.getInputStream());
            OUT = new PrintWriter(SOCK.getOutputStream());
            //on run and if while is true check the connection
            while (true) {
                CheckConnection();
                //if there is no way to input
                //then return
                if (!INPUT.hasNext()) {
                    return;
                }
                //message is equalled to input and the next line 
                MESSAGE = INPUT.nextLine();
                //display the message in the console
                System.out.println("Client said: " + MESSAGE);
                //show that the message you sent has been sent and sent
                if (MESSAGE.contains("disconnected")) {
                    String remove = MESSAGE.substring(0, MESSAGE.indexOf(" "));
                    server.removeUserName(remove);
                }
                for (int i = 1; i <= ConnectionArray.size(); i++) {
                    Socket TEMP_SOCK = (Socket) ConnectionArray.get(i - 1);
                    PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
                    TEMP_OUT.println(MESSAGE);
                    TEMP_OUT.flush();
                    System.out.println("Sent to:" + TEMP_SOCK.getLocalAddress().getHostName());
                }

            }

        } catch (Exception X) {
            System.out.print(X);
        }
    }
}
