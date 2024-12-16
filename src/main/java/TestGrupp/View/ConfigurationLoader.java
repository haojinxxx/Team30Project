package TestGrupp.View;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {
    private Properties gameProperties;

    public ConfigurationLoader(String configPath) {
        gameProperties = new Properties();
        try {
            FileInputStream propsInput = new FileInputStream(configPath);
            gameProperties.load(propsInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getProperty(String key) {
        return Integer.parseInt(gameProperties.getProperty(key));
    }
}
