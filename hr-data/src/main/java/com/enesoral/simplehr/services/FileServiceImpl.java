package com.enesoral.simplehr.services;

import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    public static String uploadDirectory = System.getProperty("user.dir") + "/uploads/";

    @Override
    public File byteArrayToFile(byte[] fileArray) {
        File file = new File("resume");
        try {
            FileUtils.writeByteArrayToFile(file, fileArray);
        } catch (IOException e) {
            return null;
        }
        return file;
    }

    @Override
    public boolean uploadResume(File file, String fileName) {
        return isValidForResume(file) && uploadFile(file, fileName);
    }

    private boolean uploadFile(File file, String fileName) {
        Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
        try {
            Files.write(fileNameAndPath, FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private boolean isValidForResume(File file) {
        try {
            Tika tika = new Tika();
            String detectedType = tika.detect(FileUtils.readFileToByteArray(file));
            return detectedType
                    .matches("application/pdf|application/msword|application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        } catch(IOException e) {
            return false;
        }
    }
}
