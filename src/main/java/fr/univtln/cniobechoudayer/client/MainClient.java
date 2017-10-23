package fr.univtln.cniobechoudayer.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * MainClient class which contain the main method called by the execution of the client
 * Created by Cyril on 21/10/2017.
 */
public class MainClient {

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) throws IOException{
        int serverPort = 8080;
        String host = "localhost";

        //Start UI and connect to the server by telnet (TCP/IP) here
        Socket pingSocket = null;
        //PrintWriter out = null;
        BufferedReader in = null;

        try {
            pingSocket = new Socket(host, serverPort);
            //out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
        } catch (IOException e) {
            return;
        }

        System.out.println("ping");
        System.out.println(in.readLine());
        //out.close();
        in.close();
        pingSocket.close();

    }
}
