package client;

import javafx.collections.ArrayChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;

public class View {
    private ArrayList<Machine> machines;
    private Button add;
    private VBox machinesVBox;

    /**
     * @require count >= 0;
     * @param count
     */
    public View() {
        machinesVBox = new VBox();
        machinesVBox.setPadding(new Insets(20, 20, 20, 20));
        //TODO: UI
        // machinesVBox.setStyle("-fx-background-color: #f8f3c9;");

        machines = new ArrayList<>();
        add = new Button("add");
        machinesVBox.getChildren().add(add);

        Label nameCol = new Label("Names");
        Label ipCol = new Label("IP");
        Label hrCol = new Label("Hour");
        Label minCol = new Label("Minute");

        nameCol.setPrefWidth(100);
        ipCol.setPrefWidth(200);
        hrCol.setPrefWidth(60);
        minCol.setPrefWidth(60);
        HBox indexHBox = new HBox();
        indexHBox.getChildren().addAll(nameCol, ipCol, hrCol, minCol);
        machinesVBox.getChildren().add(indexHBox);

        add.setOnAction(e -> {
            add();
        });
    }

    public VBox getVBox() {
        return machinesVBox;
    }

    public Button getAdd() {
        return add;
    }

    public void add() {
        Machine machine = new Machine();

        HBox machineHBox = new HBox();

        machines.add(machine);

        machineHBox.getChildren().addAll(machine.getName(), machine.getIP(), machine.getHr(), machine.getMin());
        machineHBox.getChildren().addAll(machine.getOnOffButton(), machine.getSaveRemoveButton());

        machinesVBox.getChildren().addAll(machineHBox);

        machine.getSaveRemoveButton().setOnAction(e -> {
            if (machine.getSaveRemoveButton().getText().equals("Remove")) {
                machines.remove(machine);
                machinesVBox.getChildren().remove(machineHBox);
            }
            else {
                machine.getSaveRemoveButton().setText("Remove");
                machine.getName().setEditable(false);
                machine.getName().setMouseTransparent(true);
                machine.getIP().setEditable(false);
                machine.getIP().setMouseTransparent(true);
                machine.getHr().setEditable(false);
                machine.getHr().setMouseTransparent(true);
                machine.getMin().setEditable(false);
                machine.getMin().setMouseTransparent(true);
            }
        });
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }
}