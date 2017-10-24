package fr.univtln.cniobechoudayer.server.utils;

import fr.univtln.cniobechoudayer.server.exceptions.ConfigImportException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Class representing the configuration reader
 *
 * Cette classe lit des lignes de la forme
 * nom:"valeur"
 * dans le fichier src/main/resources/config.xml
 * pour en faire des System Properties
 *
 * Created by Cyril on 21/10/2017.
 */
public class ConfigReader {
    private static Logger logger = Logger.getLogger(ConfigReader.class.getName());

    public static void importConfig() throws ConfigImportException {
        //Le flux sera fermé automatiquement
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(ConfigReader.class.getResourceAsStream("/config.conf")
                        ,Charset.defaultCharset()))) {
            String lineFromFile;
            while ((lineFromFile = reader.readLine()) != null) {
                String[] parameter = lineFromFile.split("=");
                String value = parameter[1].substring(1, parameter[1].length() - 1);
                System.setProperty(parameter[0], value);
                //On emet un entrée de log
                logger.config(parameter[0] + " : " + System.getProperty(parameter[0]));
            }

            //Chargement des paramètres du logger
            LogManager.getLogManager()
                    .readConfiguration(ConfigReader.class.getResourceAsStream(System.getProperty("java.util.logging.config.file")));


        } catch (IOException exception) {
            throw new ConfigImportException(exception);
        }
    }
}
