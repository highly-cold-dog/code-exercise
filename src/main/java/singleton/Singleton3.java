package singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * @author dlf
 * @date 2020/12/27 16:52
 */
public class Singleton3 {
    public static final Singleton3 SINGLETON_3;
    private String info;

    static {
        Properties properties = new Properties();
        try {
            properties.load(Singleton3.class.getClassLoader().getResourceAsStream("singleton.properties"));
            SINGLETON_3 = new Singleton3(properties.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private Singleton3(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton3{" +
                "info='" + info + '\'' +
                '}';
    }
}
