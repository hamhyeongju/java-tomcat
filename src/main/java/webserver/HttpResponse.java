package webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpResponse {

    private static final Logger log = Logger.getLogger(RequestHandler.class.getName());
    private DataOutputStream dos;

    private String statusCode;
    private final Map<String, String> headerMap = new HashMap<>();

    public HttpResponse(DataOutputStream dos) {
        this.dos = dos;
    }

    public void createStartLine() {
        try {
            String result = statusCode == null ? "200" : statusCode;
            dos.writeBytes("HTTP/1.1 " + result + " OK \r\n");
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public void createHeader() {
        try {
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            for (String s : headerMap.keySet()) {
                dos.writeBytes(s + ": " + headerMap.get(s) + " \r\n");
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public void responseBody(byte[] body) {
        try {
            dos.writeBytes("Content-Length: " + body.length + "\r\n");
            dos.writeBytes("\r\n");
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public void setHeader(String fieldName, String fieldValue) {
        headerMap.put(fieldName, fieldValue);
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}