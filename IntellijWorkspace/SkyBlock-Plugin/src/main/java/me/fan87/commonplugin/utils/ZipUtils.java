package me.fan87.commonplugin.utils;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    @SneakyThrows
    public static void zipFile(File folder, OutputStream outputStream) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        Files.walkFileTree(folder.toPath(), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                zipOutputStream.putNextEntry(new ZipEntry(folder.toPath().relativize(file).toString()));
                Files.copy(file, zipOutputStream);
                zipOutputStream.closeEntry();
                return FileVisitResult.CONTINUE;
            }
        });
        zipOutputStream.close();
    }

    @SneakyThrows
    public static void unzipFile(InputStream inputStream, File output) {
        if (output.exists()) output.delete();
        output.mkdirs();
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        while (zipEntry != null) {
            File file = new File(output, zipEntry.getName());
            if (zipEntry.isDirectory()) {
                file.mkdirs();
            } else {
                File parent = file.getParentFile();
                if (!parent.isDirectory()) {
                    parent.mkdirs();
                }

                Files.copy(zipInputStream, file.toPath());
            }
            zipEntry = zipInputStream.getNextEntry();
        }
    }

}
