package fr.univtln.cniobechoudayer.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * MainClient class which contain the main method called by the execution of the client
 * Created by Cyril on 21/10/2017.
 */
public class MainClient extends Application{

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) throws IOException{
        /*
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
        */
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        Scene scene = new Scene(root,1200,750);
        scene.getStylesheets().add("/css/custom.css");
        primaryStage.setTitle("Peopoll");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
