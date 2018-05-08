package com.Program;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;



public class MainWindowController implements Initializable {


    @FXML
    private TableView table;

    @FXML
    private TextField firstName;
    @FXML
    private TextField secondName;
    @FXML
    private TextField book;
    @FXML
    private MenuButton group;
    @FXML
    public MenuButton gender;
    @FXML
    private TextField author;
    @FXML
    private TextField idBook;

    @FXML
    private MenuItem kn_group;
    @FXML
    private MenuItem kb_group;

    @FXML
    public MenuItem genderMale;
    @FXML
    public MenuItem genderFamale;

    @FXML
    public TextField fileName;

    public void initialize(URL location, ResourceBundle resources) {

        TableColumn firstName = new TableColumn("First name");
        TableColumn secondName = new TableColumn("Second Name");
        TableColumn group = new TableColumn("Group");
        TableColumn gender = new TableColumn("Gender");
        TableColumn book = new TableColumn("Book");
        TableColumn author = new TableColumn("Author");
        TableColumn idBook = new TableColumn("Id book");


        table.getColumns().addAll(firstName, secondName, group, gender, book, author, idBook);


        firstName.setCellValueFactory(
                new PropertyValueFactory<Student, String>("firstName")
        );

        secondName.setCellValueFactory(
                new PropertyValueFactory<Student, String>("secondName")
        );

        group.setCellValueFactory(
                new PropertyValueFactory<Student, String>("Group")
        );

        gender.setCellValueFactory(
                new PropertyValueFactory<Student, String>("gender")
        );

        book.setCellValueFactory(
                new PropertyValueFactory<Student, String>("Book")
        );

        author.setCellValueFactory(
                new PropertyValueFactory<Student, String>("Author")
        );

        idBook.setCellValueFactory(
                new PropertyValueFactory<Student, String>("idBook")
        );

    }

    ObservableList<Student> data = FXCollections.observableArrayList();



    @FXML
    public void btnClick() {
        Student student = new Student(firstName.getText(), secondName.getText(), group.getText(), gender.getText(), book.getText(), author.getText(), idBook.getText());
        data.addAll(student);
        table.setItems(data);
        firstName.setText("");
        secondName.setText("");
        book.setText("");
        author.setText("");
        idBook.setText("");
        group.setText("Group");
        gender.setText("Gender");
    }

    @FXML
    public void aboutBtn() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About me");
        alert.setHeaderText("Developer: Oleg Rosinskiy | Group 107-КН.");
        alert.setContentText("The variant of the task number is 39.");
        Optional<ButtonType> result = alert.showAndWait();
        try {
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } catch (NoSuchElementException e) {
            System.out.println(e + " (Программа закрыта)");
        }
    }

    @FXML
    public void btnDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting cells");
        alert.setHeaderText("Select the cell to delete.");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() ==  ButtonType.OK) {
            data.removeAll(table.getSelectionModel().getSelectedItems());
            table.setItems(data);
        }
    }

    @FXML
    public void groupBtnOne() {
        group.setText(kn_group.getText());
    }

    @FXML
    public void groupBtnTwo() {
        group.setText(kb_group.getText());
    }

    @FXML
    public void genderBtnOne() {
        gender.setText(genderMale.getText());
    }

    @FXML
    public void genderBtnTwo() {
        gender.setText(genderFamale.getText());
    }

    @FXML
    public void saveBtn() {
        Stage saveStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save file");
        fileChooser.setInitialFileName(fileName.getText());
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if (data.isEmpty()) {
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "EMPTY TABLE", ButtonType.OK);
            emptyTableAlert.showAndWait();
            if (emptyTableAlert.getResult() == ButtonType.OK) {
                emptyTableAlert.close();
            }
        } else {
            File file = fileChooser.showSaveDialog(saveStage);
            if (file != null) {
                saveFile(table.getItems(), file);
            }
        }
    }

    @FXML
    public void saveFile(ObservableList<Student> data, File file) {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));

            for (Student student : data) {
                outWriter.write(student.toString());
                outWriter.newLine();
            }
            System.out.println(data.toString());
            outWriter.close();
        } catch (IOException e) {
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "ERR", ButtonType.OK);
            ioAlert.showAndWait();
            if (ioAlert.getResult() == ButtonType.OK) {
                ioAlert.close();
            }
        }
    }

    @FXML
    public void openBtn() throws IOException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);

        try {
            List<String> list = MainWindowController.read(file.getAbsolutePath());
            String[] point;
            for (String line : list) {
                Student student = new Student(null, null, null, null, null, null, null);
                point = line.split(";");
                student.setFirstName(point[0]);
                student.setSecondName(point[1]);
                student.setGroup(point[2]);
                student.setGender(point[3]);
                student.setBook(point[4]);
                student.setAuthor(point[5]);
                student.setIdBook(point[6]);
                data.addAll(student);
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No file selected");
            alert.setHeaderText("Select a file.");
            alert.setContentText("Are you ok with this?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                alert.close();
            }
        }
        table.setItems(data);

    }

    public static List<String> read(String path) throws  IOException {
        return Files.readAllLines(Paths.get(path));
    }


}

