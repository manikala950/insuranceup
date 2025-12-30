package com.insurance.www.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class FileStorageUtil {

    private static final String BASE_DIR = "uploads/customers";

    public static String save(MultipartFile file) {
        try {
            File dir = new File(BASE_DIR);
            if (!dir.exists()) dir.mkdirs();

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(BASE_DIR, filename);
            Files.copy(file.getInputStream(), path);

            return path.toString();
        } catch (Exception e) {
            throw new RuntimeException("File upload failed");
        }
    }
}
