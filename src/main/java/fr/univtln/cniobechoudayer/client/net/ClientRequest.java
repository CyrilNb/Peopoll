package fr.univtln.cniobechoudayer.client.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Class which represent a client request
 * Created by Cyril on 16/10/2017.
 */
public class ClientRequest implements Runnable{
    /**
     * Socket member
     */
    Socket socket;

    /**
     * name of the ClientRequest
     */
    private String name;

    /**
     * ip of the ClientRequest
     */
    private String ip;

    /**
     * Getter to return the IP
     * @return
     */
    public String getIp(){
        return this.ip;
    }

    /**
     * Getter to return the name
     * @return
     */
    public String getName(){
        return this.name;
    }


    /**
     * Constructor
     * @param name of the client request
     * @param sock socket of the client request
     * @param ip of the client request
     */
    public ClientRequest(String name,Socket sock,String ip)
    {
        this.name = name;
        this.socket = sock;
        this.ip = ip;
    }

    /**
     * run method which will be called by this class
     */
    @Override
    public void run() {
        try{
            //set up streams for bidirectionnal transfer across connection socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println("You are connected to Peopoll \n");
            out.println("Type Exit to quit the server \n");
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String test = in.readLine();
            if(!test.isEmpty()){
                if(test.compareToIgnoreCase("Exit")!=0){
                    System.out.println("success");
                }
                socket.close();
            }
            socket.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
