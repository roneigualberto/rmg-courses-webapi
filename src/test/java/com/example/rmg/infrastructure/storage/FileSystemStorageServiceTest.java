package com.example.rmg.infrastructure.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FileSystemStorageServiceTest {


    public static final String FOLDER = "some-folder.txt";
    public static final String FILE_NAME = "some-file.txt";
    private FileSystemStorageService fileSystemStorageService;
    private FileSystemStorageProperties storageProp;

    @BeforeEach
    void setUp() {
        storageProp = new FileSystemStorageProperties();
        fileSystemStorageService = new FileSystemStorageService(storageProp);
    }


    @Test
    void shouldStorageFile() {

        String contentFile = "Content File";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(contentFile.getBytes(StandardCharsets.UTF_8));


        fileSystemStorageService.store(FOLDER, FILE_NAME, inputStream);

        final Path dirPath = storageProp.getLocationPath().resolve(FOLDER);

        boolean existFile = Files.exists(dirPath.resolve(FILE_NAME));

        assertTrue(existFile);
    }
}