package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.*;

import java.util.Calendar;

public class App extends Application {
    private View view;
    private static ArrayList<Machine> machines;
    private static Model model;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        view = new View();
        machines = view.getMachines();
        ScrollPane scrollPane = new ScrollPane(view.getVBox());
        // scrollPane.setStyle("-fx-background-color: #f8f3c9;");
        model = new Model();

        Scene scene = new Scene(scrollPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoo Machines Control Center");
        primaryStage.show();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
        scheduler.scheduleAtFixedRate(App::checkTimeAndExecuteMethod, 0, 1, TimeUnit.MINUTES);
    }

    private static void checkTimeAndExecuteMethod() {
        // Get the current system time
        Calendar currentTime = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd"); // format the date as "yyyyMMdd"
        String date = formatter.format(currentTime.getTime());

        try (PrintWriter writer = new PrintWriter(new FileWriter(date + "_log.txt", true))) {
            writer.println(currentTime.getTime());

            for (int i = 0; i < machines.size(); ++i ) {
                Machine curr = machines.get(i);

                // if machine is on, start comparing time
                if (curr.getStatus()){
                    int targetHour = Integer.parseInt(curr.getHr().getText());
                    int targetMinute = Integer.parseInt(curr.getMin().getText());
                    
                    // Check if the current time matches the set time
                    if (currentTime.get(Calendar.HOUR_OF_DAY) == targetHour && currentTime.get(Calendar.MINUTE) == targetMinute) {
                        writer.println(String.format("%-30s Feed", curr.getName().getText()));
                        Future<String> future = model.performRequest("GET", curr.getIP().getText());
                        // try {
                        //     String response = future.get(); // this will block until the response is available
                        //     writer.println(response);
                        // } catch (InterruptedException | ExecutionException e) {
                        //     e.printStackTrace();
                        // }
                    }
                    else {
                        writer.println(String.format("%-30s Not Feed", curr.getName().getText()));
                    }
                }
                else {
                    writer.println(String.format("%-30s Off", curr.getName().getText()));
                }
            }
            writer.println("--------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}