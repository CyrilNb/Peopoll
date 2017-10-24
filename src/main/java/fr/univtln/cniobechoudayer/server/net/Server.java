package fr.univtln.cniobechoudayer.server.net;

import fr.univtln.cniobechoudayer.client.net.ClientRequest;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Class to represents the server
 * Created by Cyril on 16/10/2017.
 */
public class Server implements Runnable{
     /**
     * size of the thread pool
     */
    private static final int NTHREADS = 4;

    /**
     * int using in the name of the ClientRequests
     */
    private static int i = 1;

    /**
     * size of queue of waiting clientrequests
     */
    private static final int QSIZE = 100;

    /**
     * ThreadPoolExecutor of the server
     */
    private ThreadPoolExecutor pool;


    /**
     * Constructor of a Server
     */
    public Server() {
        this.pool = new ThreadPoolExecutor(NTHREADS, NTHREADS, 50000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(QSIZE));
    }

    /**
     * run method which will be called by the Server
     */
    @Override
    public void run() {
                try (ServerSocket serverSocket = new ServerSocket(8080)) {
                    pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
                    for(;;){
                        Socket incomingConnectionSock = serverSocket.accept();
                        SocketAddress socketAddress = incomingConnectionSock.getRemoteSocketAddress();
                        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
                        String ip = inetSocketAddress.getAddress().toString();

                        ClientRequest clientRequest = new ClientRequest("Client" + String.valueOf(i),incomingConnectionSock,ip);
                        pool.submit(clientRequest);
                        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                        Date dateConnection = new Date();
                        i++;
                    }
                }catch (IOException e){
                    System.out.println(e);

            }
    }
}
