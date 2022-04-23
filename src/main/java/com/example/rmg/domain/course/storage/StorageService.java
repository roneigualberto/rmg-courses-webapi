package com.example.rmg.domain.course.storage;

import java.io.InputStream;

public interface StorageService {

    void store(String bucket, String path, InputStream in);

}
