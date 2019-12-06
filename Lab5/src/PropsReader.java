import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropsReader {
  private static Properties props = new Properties();

  public static void readProperties(String file) {
    try (InputStream input = new FileInputStream(file)) {
      props.load(input);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static public void printProperties() {
    props.forEach((key, value) -> {
      System.out.println(key + " : " + value);
    });
  }
}
