package de.zonlykroks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static de.zonlykroks.Constants.LOGGER;

public class Main {

    public static void main(String[] argv) {
        final List<String> args = Arrays.asList(argv);
        LOGGER.info("Starting server...");

        createNecessaryFiles();

        if (!args.contains("--noEula")) {
            eulaCheck();
        } else {
            LOGGER.info("Bypassing EULA check");
        }
        if (args.contains("--verbose")) {
            Shared.isVerboseEnabled = true;
        }

        LOGGER.info("Starting networking thread...");

        new Thread(new NetworkThread()).start();
    }

    private static void eulaCheck() {
        final File configFile = new File("./run/eula.txt");

        if(!configFile.exists()) {
            try {
                configFile.createNewFile();

                FileWriter fileWriter = new FileWriter(configFile);

                fileWriter.append("eula=false");
                fileWriter.flush();
            }catch (Exception e) {
                LOGGER.error("Failed to create eula file", e);
            }
        }

        try {
            FileInputStream fis = new FileInputStream(configFile);
            byte[] data = new byte[(int) configFile.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, StandardCharsets.UTF_8);

            if (str.contains("eula=false")) {
                LOGGER.error("You need to agree to the EULA in order to run the server. The server will now stop.");
                System.exit(0);
            }
        }catch (Exception e) {
            LOGGER.error("Failed to read eula file", e);
        }
    }

    private static void createNecessaryFiles() {
        final File runDir = new File("./run");

        if(!runDir.exists()) runDir.mkdir();

        final File worldDir = new File("./run/world");

        if(!worldDir.exists()) worldDir.mkdir();

        final File configDir = new File("./run/config");

        if(!configDir.exists()) configDir.mkdir();

        final File configFile = new File("./run/config/config.json");

        if(!configFile.exists()) {
            try {
                configFile.createNewFile();

                FileWriter fileWriter = new FileWriter(configFile);

                fileWriter.append("{\n\n}");
                fileWriter.flush();
            } catch (Exception e) {
                LOGGER.error("Failed to create config file", e);
            }
        }

        final File pluginsDir = new File("./run/plugins");

        if(!pluginsDir.exists()) pluginsDir.mkdir();
    }

}