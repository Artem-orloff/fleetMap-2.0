package main.java.com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
//import ru.oniip.fleetmap.parser.util.FleetMapParser;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;




public class Controller implements Initializable {



    @FXML
    private Button fleetMapBut;

    @FXML
    private TextField fleetMapField;

    @FXML
    private Button logBut;

    @FXML
    private Button outputBut;

    @FXML
    private TextField outputField;

    @FXML
    private Label logFielder;

    @FXML
    private RadioButton individualsFileRadioButton;

    @FXML
    private RadioButton sharedFileRadioButton;

    private List<RadioButton> radioButtonsGroup = new LinkedList();

    private File selectFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(".xlsx","*.xlsx");
        fileChooser.getExtensionFilters().add(filter);
        return fileChooser.showOpenDialog(null);
    }

    private File selectDir(){
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Выберите папку");
        return dirChooser.showDialog(null);
    }



    private void showAlert(String title, String text, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();

    }
    private Boolean pathIsExist(String path){
        File file = new File(path);
        return file.exists();
    }

    private void initRadioButtonGroup(){
        radioButtonsGroup.add(individualsFileRadioButton);
        radioButtonsGroup.add(sharedFileRadioButton);
        for (RadioButton rb: radioButtonsGroup){
            rb.setOnAction(e -> {
                if (rb.isSelected()){
                    selectRadioButton(rb);
                }
            });
        }
    }

    private void unselectRadioGroup(){
        for (RadioButton rb: radioButtonsGroup){
            rb.setSelected(false);
        }
    }

    private void selectRadioButton(RadioButton rb){
        unselectRadioGroup();
        rb.setSelected(true);
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initRadioButtonGroup();
        logFielder.setText("");
        fleetMapBut.setOnAction(e -> {
            File selectedFile = selectFile();
            if (selectedFile != null){
                fleetMapField.setText(selectedFile.getPath());
            }
        });

        outputBut.setOnAction(e -> {
            File selectedFile = selectDir();
            if (selectedFile != null){
                outputField.setText(selectedFile.getPath());
            }
        });

        logBut.setOnAction(e->{
            logFielder.setText("Log");
            try{
                if (fleetMapField.getText().equals("")){
                    showAlert("Warning","fleet map", Alert.AlertType.WARNING);
                    return;
                }
                if (outputField.getText().equals("")){
                    showAlert("Warning","OutputField", Alert.AlertType.WARNING);
                    return;
                }
                if (!pathIsExist(fleetMapField.getText())){
                    showAlert("Warning","pathIsExist", Alert.AlertType.WARNING);
                    return;
                }
                parse();
            }
            catch (Exception ex){
                showAlert("Warning","fleet map", Alert.AlertType.ERROR);
            }
        });
    }

    private void parse(){
        String excelFile = fleetMapField.getText();
        String outputDir = outputField.getText();
        Boolean isManyFiles = individualsFileRadioButton.isSelected();
        parse(excelFile,outputDir,isManyFiles);
    }

    private void parse(String excelFile, String outputDir, Boolean isManyFiles){
        if (isManyFiles) {
//            FleetMapParser.createFilesIgnoredListSsi(excelFile,outputDir);
        }else {
//            FleetMapParser.createFilesIgnoredListSsi(excelFile,outputDir);
        }
    }

}