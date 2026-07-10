package com.helperhub.config;

import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FileUploadConfig {

    public static final String UPLOAD_DIR = "uploads/";

    static {

        File folder = new File(UPLOAD_DIR);

        if (!folder.exists()) {
            folder.mkdirs();
        }

    }

}