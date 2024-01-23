package org.example.studentgrouplab2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> fNameColumn;
    @FXML
    private TableColumn<Student, String> lNameColumn;
    @FXML
    private TableColumn<Student, Integer> ageColumn;
    @FXML
    private TableColumn<Student, java.sql.Date> enrollmentDateColumn;
    @FXML
    private TableColumn<Student, String> majorColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField fNameField;
    @FXML
    private TextField lNameField;
    @FXML
    private TextField ageField;
    @FXML
    private DatePicker enrollmentDateField;
    @FXML
    private TextField majorField;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    private void addStudent(ActionEvent event) {
        Student newStudent = new Student(
                Integer.parseInt(idField.getText()),
                fNameField.getText(),
                lNameField.getText(),
                Integer.parseInt(ageField.getText()),
                java.sql.Date.valueOf(enrollmentDateField.getValue()),
                majorField.getText()
        );

        studentList.add(newStudent);

        // Clear input fields after adding
        clearInputFields();
    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        // Get the selected student
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            studentList.remove(selectedStudent);
        }
    }

    @FXML
    public void initialize() {
        // Initialize the table columns
        idColumn.setCellValueFactory(cellData -> cellData.getValue().studentIDProperty().asObject());
        fNameColumn.setCellValueFactory(cellData -> cellData.getValue().studentFirstNameProperty());
        lNameColumn.setCellValueFactory(cellData -> cellData.getValue().studentLastNameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().studentAgeProperty().asObject());
//        enrollmentDateColumn.setCellValueFactory(cellData -> cellData.getValue().enrollmentDateProperty());
        fNameColumn.setCellValueFactory(cellData -> cellData.getValue().studentFirstNameProperty());
        majorColumn.setCellValueFactory(cellData -> cellData.getValue().majorNameProperty());



        studentTable.setItems(studentList);
    }

    private void clearInputFields() {
        idField.clear();
        fNameField.clear();
        lNameField.clear();
        ageField.clear();
        enrollmentDateField.getEditor().clear();
        majorField.clear();
    }
}
