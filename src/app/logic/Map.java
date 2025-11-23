package app.logic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Map {
    // Extract zip folder to target directory
    public void extractZip(String zipPath, String destDir) throws IOException {
    Path dest = Paths.get(destDir);
    try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(Paths.get(zipPath)))) {
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            Path outPath = dest.resolve(entry.getName()).normalize();
            // If the required directories don't exist, create them
            if (entry.isDirectory()) {
                Files.createDirectories(outPath);
            } else {
                Files.createDirectories(outPath.getParent()); // Makes sure that the parent file exists
                try (OutputStream out = Files.newOutputStream(outPath)) {
                    zis.transferTo(out);
                }
            }
            zis.closeEntry();
            }
        }
    }

    // Zip world folder to desired location provided by user
    public void zipFolder(String srcFolder, String zipPath) throws IOException {
        Path source = Paths.get(srcFolder).toAbsolutePath();
        Path zip = Paths.get(zipPath);
        try (ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(zip))) {
            Files.walk(source).forEach(path -> {
                try {
                    // Skip directories so we can add files to the zip
                    if (Files.isDirectory(path)) {
                        return;
                    }
                    String entryName = source.relativize(path).toString();
                    out.putNextEntry(new ZipEntry(entryName));
                    try (InputStream in = Files.newInputStream(path)) {
                        in.transferTo(out);
                    }
                    out.closeEntry();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
    }

    public String copyImageToWorldFolder(String originalPath) {
        try {
        java.io.File src = new java.io.File(originalPath);
        if (!src.exists()) return originalPath;

        // Create ./state/world directory if missing
        java.io.File worldDir = new java.io.File("./state/world");
        if (!worldDir.exists()) worldDir.mkdirs();

        // Build destination path
        java.io.File dest = new java.io.File(worldDir, src.getName());

        // Copy file
        java.nio.file.Files.copy(
            src.toPath(), dest.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        // Return path you want stored in JSON
        return "./state/world/" + src.getName();

        } catch (Exception ex) {
        ex.printStackTrace();
        return originalPath; // fallback
        }
    }
}
