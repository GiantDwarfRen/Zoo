package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        View view = new View();
        ScrollPane scrollPane = new ScrollPane(view.getVBox());
        Model model = new Model();
        Controller controller = new Controller(view, model);

        Scene scene = new Scene(scrollPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoo Machines Control Center");
        primaryStage.show();

        // TODO: fix screen not showing up issue;
        // while (true) {
        //     System.out.println("Here");
        //     try {
        //         // Thread.sleep(10000);
        //         System.out.println("Start Comparing");
        //         long systemTime = System.currentTimeMillis();
        //         for (int i = 0; i < view.getMachines().size(); ++i) {
        //             Machine mc = view.getMachines().get(i);
        //             long machineTime = Long.parseLong(mc.getHr().getText()) * 60 * 60 * 1000 + Long.parseLong(mc.getMin().getText()) * 60 * 1000;
        //             System.out.println(systemTime);
        //             System.out.println(machineTime);

        //             if (Math.abs(machineTime - systemTime) <= 60 * 1000) {
        //                 System.out.println("Less than a minute!\n");
        //             }
        //             else {
        //                 System.out.println("Longer :( \n)");
        //             }
        //         }
        //     }
        //     catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }
    }
}