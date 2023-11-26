package me.mel0102.Storage;

import me.mel0102.Controller;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StorageUtil {

    public static void saveToTable(List<String> rows, String table) {
        Optional<File> file = getFileFromResource(table + ".csv");
        System.out.println("Loading file");
        if (file.isEmpty()) {
            System.out.println("Could not load file!");
            return;
        }

        System.out.println("Loaded file, writing...");
        try (PrintWriter pw = new PrintWriter(file.get())) {
            rows.forEach(row -> {
                pw.println(row);
                System.out.println("Wrote row " + row + " to file " + file.get().getName());
            });
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<String> readTable(String table) {
        Optional<File> file = getFileFromResource(table + ".csv");
        List<String> entries = new ArrayList<>();

        if (file.isEmpty()) {
            System.out.println("Could not load file!");
            return Collections.emptyList();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file.get()))) {
            String line;

            while ((line = br.readLine()) != null) {
                entries.add(line);
            }
        } catch (Exception e) {
        }

        return entries;
    }

    public static Optional<File> getFileFromResource(String fileName) {
        ClassLoader classLoader = StorageUtil.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            return Optional.empty();
        } else {
            try {
                return Optional.of(new File(resource.toURI()));
            } catch (URISyntaxException e) {
                return Optional.empty();
            }
        }

    }
}
