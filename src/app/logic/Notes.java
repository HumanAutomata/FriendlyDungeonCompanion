package app.logic;

import java.nio.file.*;

public class Notes {

    final Path file;

    public Notes() {
        // store in user home by default
        this.file = Path.of("./state/notes.txt");
    }

    public String load() {
        try {
            if (Files.exists(file)) {
                return Files.readString(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ""; // return empty if file not found
    }

    public void save(String content) {
        try {
            Files.writeString(file, content,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            System.out.println("test");
            //e.printStackTrace();
        }
    }
}