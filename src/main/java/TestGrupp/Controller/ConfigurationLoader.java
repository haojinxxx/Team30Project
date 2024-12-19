package TestGrupp.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {
    private static Properties gameProperties = new Properties();

    private ConfigurationLoader() {
    }
    static {
        String configPath = "src/main/resources/config.properties";
        try (FileInputStream propsInput = new FileInputStream(configPath)) {
            gameProperties.load(propsInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getProperty(String key) {
        return Integer.parseInt(gameProperties.getProperty(key));
    }
}