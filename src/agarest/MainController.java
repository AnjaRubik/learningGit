/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agarest;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Andrew
 */
public class MainController implements Initializable {
    //with@FXML annotation, var name must be the same as fx:id in FXML document
    @FXML private GridPane paneNewChar;
    @FXML private GridPane paneStatus;
    @FXML private ListView listViewChar;
    @FXML private TableView historyTable;
    @FXML private TextField txtNewName;
    @FXML private TextField txtNewLevel;
    @FXML private TextField txtNewSTR;
    @FXML private TextField txtNewVIT;
    @FXML private TextField txtNewAGI;
    @FXML private TextField txtNewINT;
    @FXML private TextField txtNewWIS;
    @FXML private TextField txtNewLUK;
    @FXML private Label labelCharName;
    @FXML private ComboBox nextSTR;
    @FXML private ComboBox nextVIT;
    @FXML private ComboBox nextAGI;
    @FXML private ComboBox nextINT;
    @FXML private ComboBox nextWIS;
    @FXML private ComboBox nextLUK;
    @FXML private ComboBox additionalSTR;
    @FXML private ComboBox additionalVIT;
    @FXML private ComboBox additionalAGI;
    @FXML private ComboBox additionalINT;
    @FXML private ComboBox additionalWIS;
    @FXML private ComboBox additionalLUK;
    @FXML private TextField costSTR;
    @FXML private TextField costVIT;
    @FXML private TextField costAGI;
    @FXML private TextField costINT;
    @FXML private TextField costWIS;
    @FXML private TextField costLUK;
    @FXML private TextField costAddSTR;
    @FXML private TextField costAddVIT;
    @FXML private TextField costAddAGI;
    @FXML private TextField costAddINT;
    @FXML private TextField costAddWIS;
    @FXML private TextField costAddLUK;
    
    List<String> listCharName;
    ObservableList obListName;
    
    @FXML private void handleAddCharacterMenuItem(ActionEvent event) { 
        paneStatus.setVisible(false);
        paneNewChar.setVisible(true);
        paneNewChar.toFront();        
    }
    
    @FXML private void handleCloseMenuItem(ActionEvent event) {
        paneStatus.setVisible(true);
        paneNewChar.setVisible(false);
        paneStatus.toFront();
    }
    
     @FXML private void handleAddCharacterButton(ActionEvent event) {
        //add items to ListView
        listCharName = new ArrayList<>();
        listCharName.add(txtNewName.getText());
        obListName = FXCollections.observableList(listCharName);
        listViewChar.setItems(obListName);
        
        //save to db
        MySQLAccess mysql = new MySQLAccess();
        try {
            String result = mysql.addCharacter(txtNewName.getText(), Integer.valueOf(txtNewLevel.getText()), Integer.valueOf(txtNewSTR.getText()), Integer.valueOf(txtNewVIT.getText()), Integer.valueOf(txtNewAGI.getText()), Integer.valueOf(txtNewINT.getText()), Integer.valueOf(txtNewWIS.getText()), Integer.valueOf(txtNewLUK.getText()));
            System.out.println(result);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //reset textField value
        txtNewName.setText("");
        txtNewLevel.setText("");
        txtNewSTR.setText("");
        txtNewVIT.setText("");
        txtNewAGI.setText("");
        txtNewINT.setText("");
        txtNewWIS.setText("");
        txtNewLUK.setText("");
    }
    
    @FXML private void handleLevelUpButton(ActionEvent event) {
        System.out.println("You clicked me!");
        labelCharName.setText("Hello World!");
    }
    
    @FXML private void handleSaveNotesButton(ActionEvent event) {
        System.out.println("You clicked me!");
        labelCharName.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //initialize comboBox value
        Stream<Integer> streamOfInteger = Stream.of(0,1,2,3,4,5,6,7,8,9);
        List<Integer> listOfStream = streamOfInteger.collect(Collectors.toList());
        ObservableList obList = FXCollections.observableList(listOfStream);
        nextSTR.setItems(obList);
        nextVIT.setItems(obList);
        nextAGI.setItems(obList);
        nextINT.setItems(obList);
        nextWIS.setItems(obList);
        nextLUK.setItems(obList);
        additionalSTR.setItems(obList);
        additionalVIT.setItems(obList);
        additionalAGI.setItems(obList);
        additionalINT.setItems(obList);
        additionalWIS.setItems(obList);
        additionalLUK.setItems(obList);
        //set comboBox default selected value
        nextSTR.getSelectionModel().select(0);
        nextVIT.getSelectionModel().select(0);
        nextAGI.getSelectionModel().select(0);
        nextINT.getSelectionModel().select(0);
        nextWIS.getSelectionModel().select(0);
        nextLUK.getSelectionModel().select(0);
        additionalSTR.getSelectionModel().select(0);
        additionalVIT.getSelectionModel().select(0);
        additionalAGI.getSelectionModel().select(0);
        additionalINT.getSelectionModel().select(0);
        additionalWIS.getSelectionModel().select(0);
        additionalLUK.getSelectionModel().select(0);
        //initialize ListView
        listCharName = new ArrayList<>();
        obListName = FXCollections.observableList(listCharName);
        listViewChar.setItems(obListName);
    }    
    
}
