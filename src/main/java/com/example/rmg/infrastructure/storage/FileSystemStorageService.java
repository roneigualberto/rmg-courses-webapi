package com.example.rmg.infrastructure.storage;

import com.example.rmg.domain.common.exception.StorageException;
import com.example.rmg.domain.course.storage.StorageService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.requireNonNull;

@Component
public class FileSystemStorageService implements StorageService {

    @Override
    public void store(String bucket, String path, InputStream content) {

        requireNonNull(content, "required");

        final String tmpDir = System.getProperty("java.io.tmpdir");

        final String crossPlatformPath = bucket + File.separator + path.replace("/", File.separator);

        final Path locationPath = Paths.get(tmpDir);

        final Path destinationPath = locationPath.resolve(crossPlatformPath).normalize().toAbsolutePath();

        try (InputStream in = content) {
            Files.createDirectories(destinationPath.getParent());
            Files.copy(in, destinationPath, REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new StorageException("Failed to storage file");
        }
    }
}
