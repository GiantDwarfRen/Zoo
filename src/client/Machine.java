package client;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

public class Machine {
    private TextField name;
    private TextField ip;
    private TextField hr;
    private TextField min;
    private Button SaveRemoveButton;
    private Button OnOffButton;
    private boolean status;

    public Machine() {
        SaveRemoveButton = new Button("Save");
        OnOffButton = new Button("Off");
        status = false;
        name = new TextField("Example");
        ip = new TextField("Example");
        hr = new TextField("0");
        min = new TextField("0");

        name.setPrefWidth(100);
        ip.setPrefWidth(200);
        hr.setPrefWidth(60);
        min.setPrefWidth(60);

        OnOffButton.setOnAction(e -> {
            // TODO: compare feed time.
            status = !status;
            if (status) OnOffButton.setText("On");
            else OnOffButton.setText("Off");
        });

        SaveRemoveButton.setOnAction(e -> {
            if (SaveRemoveButton.getText().equals("Remove")) {
                // TODO: remove this machine
            }
            else {
                SaveRemoveButton.setText("Remove");
                name.setEditable(false);
                name.setMouseTransparent(true);
                ip.setEditable(false);
                ip.setMouseTransparent(true);
                hr.setEditable(false);
                hr.setMouseTransparent(true);
                min.setEditable(false);
                min.setMouseTransparent(true);
            }
        });
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public void setIP(TextField ip) {
        this.ip = ip;
    }

    public void setHr(TextField hr) {
        this.hr = hr;
    }

    public void setMin(TextField min) {
        this.min = min;
    }

    public TextField getName() {
        return name;
    }

    public TextField getIP() {
        return ip;
    }

    public TextField getHr() {
        return hr;
    }

    public TextField getMin() {
        return min;
    }

    public Button getSaveRemoveButton() {
        return SaveRemoveButton;
    }

    public Button getOnOffButton() {
        return OnOffButton;
    }

    public String feed() {
        try {
            String urlString = "http://" + ip.getText() + "/H";

            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine() + "\n\n";

            // TODO: change to 1min in real practice
            Thread.sleep(3000);

            urlString = "http://" + ip.getText() + "/L";
            url = new URI(urlString).toURL(); 
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            response += in.readLine() + "\n\n\n";

            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
}
