package fr.univtln.cniobechoudayer;

import fr.univtln.cniobechoudayer.client.views.HomeViewController;
import fr.univtln.cniobechoudayer.client.views.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PatternLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * Hello world!
 */
public class App extends Application {
    @SuppressWarnings("unused")
    private static final Class[] shadeHack = {org.apache.log4j.RollingFileAppender.class,
            org.apache.log4j.ConsoleAppender.class,
            PatternLayout.class};

    //Set the logger with the real class name.
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static MainController mc = new MainController();

    public static void main(String[] args) {
        BasicConfigurator.configure();
        logger.info("App started.");
        logger.debug("About to talk :");
        System.out.println("Hello world !");

        /*
        Call and display the homeView
         */
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Peopoll");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
