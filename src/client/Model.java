package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.util.concurrent.*;


public class Model {
    public Future<String> performRequest(String method, String ip) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String response = "";
                try {
                    // First request to "/H"
                    String urlString = "http://" + ip + "/H";
                    URL url = new URI(urlString).toURL();
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod(method);
                    conn.setDoOutput(true);
    
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    response = in.readLine();
                    in.close();
    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return response;
            }
        });
        executor.shutdown(); // it's important to shutdown the executor after use
        return future;
    }
}