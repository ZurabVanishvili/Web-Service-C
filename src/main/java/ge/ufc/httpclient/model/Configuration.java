package ge.ufc.httpclient.model;


import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;


public class Configuration {


    private static final String CONFIG_FILE_LOCATION = "clients.properties";
    private volatile static Configuration conf = null;
    public static URL url = null;
    protected long lastModified;

    private Setting setting;


    private Configuration(URLConnection conn) {
        this.lastModified = conn.getLastModified();
        try{
            Properties props = new Properties();
            props.load(conn.getInputStream());
            fillUserSettings(props);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }


    public static Configuration getConfiguration() throws  IOException {
        if (url == null) {
            url = Configuration.class.getClassLoader().getResource(CONFIG_FILE_LOCATION);
        }
        assert url != null;
        URLConnection connection = url.openConnection();

        if (url == null) {
            throw new IOException();
        }


        long lastModified = connection.getLastModified();
        if (conf == null || lastModified > conf.lastModified) {
            synchronized (CONFIG_FILE_LOCATION) {
                if (conf == null || lastModified > conf.lastModified) {
                    conf = new Configuration(connection);
                }
            }
        }

        return conf;
    }

    public void fillUserSettings(Properties props) {
        setting = new Setting();
        setting.setUrl1(props.getProperty("url1", ""));
        setting.setUrl2(props.getProperty("url2", ""));
        setting.setTimeout(Integer.parseInt(props.getProperty("timeout", "")));


    }


    public Setting getSetting() {
        return setting;
    }


}