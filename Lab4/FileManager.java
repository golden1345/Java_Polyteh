import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
  private static Path path;

  static {
    path = Paths.get(".").toAbsolutePath().normalize();
  }

  public static Boolean writeToFile(String name, String data, Boolean appendable) {
    File file = new File(path + "/" + name);
    Boolean result = false;
    if (file.exists()) {
      try (FileWriter fw = new FileWriter(file, appendable);) {
        fw.write(data);
        result = true;
      } catch (IOException e) {
        System.err.println("IOException caught");
      } catch (NullPointerException e) {
        System.err.println("NullPointerException. Path will be set to default");
        setPath(Paths.get(".").toAbsolutePath().normalize());
      }
    }
    return result;
  }

  public static Boolean rmfile(String name) {
    Boolean result = false;
    try {
      File file = new File(path + "/" + name);
      result = file.delete();
    } catch (NullPointerException e) {
      System.err.println("NullPointerException. Path will be set to default");
      setPath(Paths.get(".").toAbsolutePath().normalize());
    }
    return result;
  }

  public static Boolean mkfile(String name) {
    File file = new File(path + "/" + name);
    Boolean result = false;
    try {
      result = file.createNewFile();
    } catch (IOException e) {
      System.err.println("IOException caught");
    } catch (NullPointerException e) {
      System.err.println("NullPointerException. Path will be set to default");
      setPath(Paths.get(".").toAbsolutePath().normalize());
    }
    return result;
  }

  public static Boolean cd(String arg) {
    Boolean result = false;
    switch (arg) {
    case "..": {
      try {
        path = path.getParent();
        result = true;
      } catch (NullPointerException e) {
        System.err.println("NullPointerException. Path will be set to default");
        setPath(Paths.get(".").toAbsolutePath().normalize());
      }
      break;
    }
    default: {
      try {
        String newPath = path.toString() + '/' + arg;
        File f = new File(newPath);
        if (f.exists() && f.isDirectory()) {
          path = Paths.get(newPath);
          result = true;
        }
      } catch (NullPointerException e) {
        System.err.println("NullPointerException. Path will be set to default");
        setPath(Paths.get(".").toAbsolutePath().normalize());
      }
      break;
    }
    }
    return result;
  }

  public static List<Path> ls() {
    List<Path> result = new ArrayList<>();
    try {
      Files.list(new File(path.toString()).toPath()).forEach(result::add);
    } catch (FileNotFoundException e) {
      System.err.println("FileNotFoundException");
    } catch (IOException e) {
      System.err.println("IOException");
    } catch (NullPointerException e) {
      System.err.println("NullPointerException. Path will be set to default");
      setPath(Paths.get(".").toAbsolutePath().normalize());
    }
    return result;
  }

  public static Path getPath() {
    return path != null ? path : Paths.get("NULLLLLLL POINTERRRRR");
  }

  public static void setPath(Path path) {
    FileManager.path = path;
  }
}
