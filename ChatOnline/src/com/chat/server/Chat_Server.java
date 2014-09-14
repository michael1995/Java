/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author michaelborodin
 */
public class Chat_Server implements Runnable {

    /**
     * add the array list
     */
    public ArrayList<Socket> connectionArray = new ArrayList<>();
    public ArrayList<String> CurrentUsers = new ArrayList<>();
    //make the port equl to one before we get the value passed from the client
    static int PORT = 0;

    /**
     * make a constructor for this class and pass the port variable to be used
     * down bellow
     *
     * @param port
     */
    public Chat_Server(int port) {
        //give the global port variable the value of the passed value of the paremeter
        //in the constructor passed by the port variable in the interface class
        //that calls this class
        //so we can have more than one port 
        //in the same server
        PORT = port;
    }

    public void run() {
        try {
            //set up a server socket vairble and give it the currrent port
            ServerSocket SERVER = new ServerSocket(PORT);
            System.out.println("Waiting for clients....");
            //while true
            //get the sockect to accept the server
            while (true) {
                Socket SOCK = SERVER.accept();
                //add the sockects to the connection array
                connectionArray.add(SOCK);
                System.out.println("Client connected from: " + SOCK.getLocalAddress().getHostName());
                //add the user names to the socket
                AddUserName(SOCK);
                //start a thread 
                //through the other class called chat server return
                Chat_Server_Return CHAT = new Chat_Server_Return(SOCK, connectionArray, this);
                Thread X = new Thread(CHAT);
                //start the thread
                X.start();
            }
        } catch (Exception X) {
            System.out.println(X);
        }
    }

    /**
     * Add user Method add the users to the list and to the socket
     *
     * @param X
     * @throws IOException
     */
    public void AddUserName(Socket X) throws IOException {
        //add the user name to the current usernames
        Scanner INPUT = new Scanner(X.getInputStream());
        String UserName = INPUT.nextLine();
        CurrentUsers.add(UserName);
        //display the current user after connecting 
        for (int i = 1; i <= connectionArray.size(); i++) {
            Socket TEMP_SOCK = (Socket) connectionArray.get(i - 1);
            PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
            OUT.println("#?!" + CurrentUsers);
            OUT.flush();
        }
    }

    public void removeUserName(String pstrUserName) throws IOException {
        for (int i = 0; i < CurrentUsers.size(); i++) {
            if (CurrentUsers.get(i).equals(pstrUserName)) {
                CurrentUsers.remove(i);
            }
        }
        for (int i = 1; i <= connectionArray.size(); i++) {
            Socket TEMP_SOCK = (Socket) connectionArray.get(i - 1);
            PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
            OUT.println("#?!" + CurrentUsers);
            OUT.flush();
        }
        
    }

}
