/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;
import com.chat.server.Chat_Server;
import java.awt.HeadlessException;

/**
 *
 * @author michaelborodin
 */
public class Chat_Client implements Runnable {

    /**
     * global variables
     */
    Socket SOCK;
    Scanner INPUT;
    Scanner SEND = new Scanner(System.in);
    PrintWriter OUT;

    public Chat_Client(Socket X) {
        this.SOCK = X;
    }

    @Override
    /**
     * run method
     */
    public void run() {
        try {
            INPUT = new Scanner(SOCK.getInputStream());
            OUT = new PrintWriter(SOCK.getOutputStream());
            OUT.flush();
            CheckStream();
        } catch (IOException X) {
            System.out.print(X);
        }
    }

    /**
     * disconnect method disconnect from the socket
     *
     * @throws IOException
     */
    public void DISCONNECT() throws IOException {
        OUT.println(Chat_Client_UI.UserName + " disconnected");
        OUT.flush();
        if (Chat_Client_UI.list.size() < 0){
            SOCK.close();
        }
        System.exit(0);
    }

    public void CheckStream() {
        //while true call the recieve method
        while (true) {
            RECEIVE();
        }
    }

    public void RECEIVE() {
        //recieve the input and make users apear in the list 
        if (INPUT.hasNext()) {
            String MESSAGE = INPUT.nextLine();

            if (MESSAGE.contains("#?!")) {
                String TEMP1 = MESSAGE.substring(3);
                TEMP1 = TEMP1.replace("[", "");
                TEMP1 = TEMP1.replace("]", "");

                String[] CurrentUsers = TEMP1.split(",  ");

                Chat_Client_UI.List.setListData(CurrentUsers);

            } else {
                Chat_Client_UI.txt_bodyMessage.append(MESSAGE + "\n");
            }
        }
    }

    public void SEND(String X) {
        //send what the user said 
        //and display it into the text area
        OUT.println(Chat_Client_UI.UserName + " Said: " + X);
        OUT.flush();
    }
}
