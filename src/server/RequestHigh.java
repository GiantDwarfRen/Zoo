package server;

import com.sun.javafx.scene.control.EmbeddedTextContextMenuContent;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class RequestHigh implements HttpHandler{
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("GET")) {
              response = handleGet(httpExchange);
            }else {
              throw new Exception("Not Valid Request Method");
            }
          } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
          }
       
        //Sending back response to the client
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();
    }

    private String handleGet(HttpExchange httpExchange) throws IOException {
        String response = "Machine turn high";
        try {
            System.out.println("Turn high");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
