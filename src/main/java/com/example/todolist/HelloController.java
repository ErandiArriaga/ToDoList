package com.example.todolist;

import com.example.todolist.db.DAO.TaskDao;
import com.example.todolist.models.Task;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

public class HelloController implements Initializable {
    @FXML
    private Button btnAdd, btnReset;
    @FXML
    private TextField txtName, txtDescription, txtType;
    @FXML
    TableView<Task> tblTasks;
    ObservableList<Task> tasksList = FXCollections.observableArrayList();

    Boolean editMode = false;
    Task taskSelected = null;
    Random random = new Random();
    TaskDao taskDao = new TaskDao();

    @FXML
    protected void onAddButtonClick() {
        //showMessage("TAREA AÑADIDA");
        if (editMode) {
            tasksList.get(tasksList.indexOf(taskSelected)).setName(txtName.getText());
            tasksList.get(tasksList.indexOf(taskSelected)).setDescription(txtDescription.getText());
            tasksList.get(tasksList.indexOf(taskSelected)).setDescription(txtType.getText());
            editMode = false;
            btnAdd.setText("Add");
        }else {
            Task newTask = new Task();
            newTask.setId(random.nextInt(1000));
            newTask.setName(txtName.getText());
            newTask.setDescription(txtDescription.getText());
            newTask.setType(txtType.getText());
            newTask.setStatus(false);
            tasksList.add(newTask);
        }
        tblTasks.refresh();
        onResetButtonClick();
    }

    @FXML
    protected void onResetButtonClick() {
        //showMessage("TAREA ELIMINADA");
        txtName.setText("");
        txtDescription.setText("");
        txtType.setText("");
        txtName.requestFocus();
        //onResetButtonClick();


    }

    private void showMessage(String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableInfo();
    }

    private void initTableInfo(){
        //Ini table with tasks
        for (int i = 0; i<= 5; i++){
            tasksList.add(new Task(i, "Task" + i, "Description" + i, "Type" + i));
        }
        //Autoresize table
        tasksList =FXCollections.observableArrayList(taskDao.findAll());
        tblTasks.setItems(tasksList);
        tblTasks.setColumnResizePolicy((param) -> true );
        Platform.runLater(() -> customResize(tblTasks));
        //Implements double click on tableview
        tblTasks.setOnMouseClicked((mouseEvent)->{
            if (mouseEvent.getClickCount()==2){
                //Load task info in the forms
                taskSelected = tblTasks.getSelectionModel().getSelectedItem();
                txtName.setText(taskSelected.getName());
                txtDescription.setText(taskSelected.getName());
                txtType.setText(taskSelected.getName());
                editMode = true;
                btnAdd.setText("Save");
            }
        });
    }

    public void customResize(TableView<?> view) {
        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }

}