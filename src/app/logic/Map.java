package app.logic;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Map {
  /** Extracts a ZIP file to a target directory. */
    public void extractZip(String zipPath, String destDir) throws Exception {
        java.util.zip.ZipInputStream zis =
                new java.util.zip.ZipInputStream(new java.io.FileInputStream(zipPath));
        java.util.zip.ZipEntry entry;

        while ((entry = zis.getNextEntry()) != null) {
            java.io.File outFile = new java.io.File(destDir, entry.getName());

            if (entry.isDirectory()) {
                outFile.mkdirs();
            } else {
                outFile.getParentFile().mkdirs();
                java.io.FileOutputStream fos = new java.io.FileOutputStream(outFile);

                byte[] buffer = new byte[4096];
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zis.closeEntry();
        }
        zis.close();
    }

    /** Creates a ZIP of a folder (recursive). */
    public void zipFolder(String srcFolder, String zipPath) throws Exception {
        java.util.zip.ZipOutputStream zos =
                new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(zipPath));
        java.io.File folder = new java.io.File(srcFolder);

        zipFolderRecursive(folder, folder.getAbsolutePath(), zos);
        zos.close();
    }

    public void zipFolderRecursive(java.io.File file, String rootPath,
                                    java.util.zip.ZipOutputStream zos) throws Exception {
        if (file.isDirectory()) {
            for (java.io.File child : file.listFiles()) {
                zipFolderRecursive(child, rootPath, zos);
            }
        } else {
            String relativePath = file.getAbsolutePath().substring(rootPath.length() + 1);
            java.util.zip.ZipEntry entry = new java.util.zip.ZipEntry(relativePath);
            zos.putNextEntry(entry);

            java.io.FileInputStream fis = new java.io.FileInputStream(file);
            byte[] buffer = new byte[4096];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            fis.close();
            zos.closeEntry();
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
