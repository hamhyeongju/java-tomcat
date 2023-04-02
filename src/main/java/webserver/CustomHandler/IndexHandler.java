package webserver.CustomHandler;

import http.util.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IndexHandler implements CustomHandler{

    private final File file = new File("webapp/index.html");

    @Override
    public byte[] process() throws IOException {
        BufferedReader fr = new BufferedReader(new FileReader(file));
        String fileData = IOUtils.readData(fr, (int) file.length());
        return fileData.getBytes();
    }
}