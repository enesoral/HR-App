package com.enesoral.simplehr.services;

import java.io.File;

public interface FileService {
    boolean uploadResume(File file, String fileName);
    File byteArrayToFile(byte[] fileArray);
}
