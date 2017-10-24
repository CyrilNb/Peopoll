package fr.univtln.cniobechoudayer.server;

import fr.univtln.cniobechoudayer.server.net.Server;

/**
 * MainServer class which contain the main method called by the execution of the server
 * Created by Cyril on 20/10/2017.
 */
public class MainServer {

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {

     //Start server
     Server server = new Server();
     Thread threadserv = new Thread(server);
     threadserv.start();

    }


}
