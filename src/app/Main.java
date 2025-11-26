package app;
import java.nio.file.*;

public class Main {
  public static void main(String[] args) throws Exception{
    new MainFrame();
    Path dirPath = Paths.get("./state");
    if (!Files.exists(dirPath)) {
      Files.createDirectory(dirPath);
    }
    Path worldPath = Paths.get("./state/world");
    if (!Files.exists(worldPath)) {
      Files.createDirectory(worldPath);
    }
  }
}
