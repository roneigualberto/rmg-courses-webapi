package com.example.rmg.infrastructure.storage;

import com.example.rmg.domain.common.exception.StorageException;
import com.example.rmg.domain.course.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class FileSystemStorageService implements StorageService {


    private final FileSystemStorageProperties storageProp;

    @Override
    public void store(String bucket, String path, InputStream content) {

        requireNonNull(content, "required");

        final Path locationPath = getLocationFilePath(bucket, path);

        try (InputStream in = content) {
            Files.createDirectories(locationPath.getParent());
            Files.copy(in, locationPath, REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new StorageException("Failed to storage file");
        }
    }

    private Path getLocationFilePath(String bucket, String path) {

        final String crossPlatformPath = bucket + File.separator + path.replace("/", File.separator);

        final Path locationPath = storageProp.getLocationPath();

        return locationPath.resolve(crossPlatformPath).normalize().toAbsolutePath();
    }


    @Override
    public InputStream get(String bucket, String path) {

        try {
            Path file = getLocationFilePath(bucket, path);
            return Files.newInputStream(file);
        } catch (IOException e) {
            throw new StorageException("Could not read file: " + path);
        }
    }
}
