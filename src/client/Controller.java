package client;

import javafx.event.ActionEvent;
import java.util.*;

public class Controller {
    private View view;
    private Model model;
    private ArrayList<Machine> machines;

    private ArrayList<String> IPs;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        machines = new ArrayList<>();

        // this.view.setPostButtonAction(this::handlePostButton);
        // this.view.setGetButtonAction(this::handleGetButton);
        // this.view.setPutButtonAction(this::handlePutButton);
        // this.view.setDeleteButtonAction(this::handleDeleteButton);
        // for (int i = 0; i < view.getCount(); ++i) {
        //     view.setSetButtonAction(view.getSetButtons().get(i), this::handlePostButton);
        // }
    }

    public void setIPs(ArrayList<String> IPs) {
        this.IPs = IPs;
    }

    // private void handlePostButton(ActionEvent event) {
    //     String language = view.getLanguage();
    //     String year = view.getYear();
    //     String response = model.performRequest("POST", language, year, null);
    //     view.showAlert("Response", response);
    // }

    // private void handleGetButton(ActionEvent event) {
    //     String query = view.getQuery();
    //     String response = model.performRequest("GET", null, null, query);
    //     view.showAlert("Response", response);
    // }

    // private void handlePutButton(ActionEvent event) {
    //     String language = view.getLanguage();
    //     String year = view.getYear();
    //     String response = model.performRequest("PUT", language, year, null);
    //     view.showAlert("Response", response);
    // }

    // private void handleDeleteButton(ActionEvent event) {
    //     String query = view.getQuery();
    //     String response = model.performRequest("DELETE", null, null, query);
    //     view.showAlert("Response", response);
    // }
}