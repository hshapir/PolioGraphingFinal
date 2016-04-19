/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poliographing;

import com.google.gson.Gson;
import static com.sun.javafx.fxml.expression.Expression.or;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import static javafx.beans.binding.Bindings.or;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import static javax.management.Query.*;

/**
 *
 * @author csstudent
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private BarChart<String,Number> chart;
    
    @FXML
    private Button filterActivator;
            
    @FXML
    private void handleButtonAction(ActionEvent event) {
       setUpGraph();
    }
    
    @FXML
    private MenuBar topMenu;
    
    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    
    private void handleAboutButtonAction(ActionEvent event){
        Alert aboutAlert = new Alert(AlertType.INFORMATION);
        aboutAlert.setTitle("About this Program");
        aboutAlert.setHeaderText(null);
        aboutAlert.setContentText("I made this program for an AP Computer Science class. It is meant to show polio vaccination percentages from 1980. If you've come across this on GitHub and you're not Mr. Wheadon, I'm not really sure why you're looking at this. Enjoy!");
        aboutAlert.showAndWait();
    }
    
    @FXML
    private MenuItem closeButton, aboutButton;
    
    @FXML
    private TextField minField, maxField;
    
    private DataSet data;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String s = "http://apps.who.int/gho/athena/data/GHO/WHS4_544.json?profile=simple&filter=YEAR:1980";
        URL myUrl = null;
        try{
            myUrl= new URL(s);
        } catch (Exception e){
            System.out.println("Something's wrong with your URL - " + s);
            System.exit(-1);
        }
        
        Scanner scan = null;
        try{
            scan = new Scanner(myUrl.openStream());
        }
        catch (Exception e){
            System.out.println("Couldn't connect to - " + s);
            System.exit(-1);
        }
        String str = new String();
        while(scan.hasNext()){
            str += scan.nextLine() + "\n";
        }
        scan.close();
        
        minField.setText("0");
        maxField.setText("100");

        Gson gson = new Gson();
        data = gson.fromJson(str, DataSet.class);
        setUpGraph();
    }    
    
    public void setUpGraph(){
        chart.getData().clear();
        int min = 0;
        try{
            min = Integer.parseInt(minField.getText());
        } catch(Exception e){
            minField.setText("0");
            sendIntError();
        }
        int max = 100;
        try{
            max = Integer.parseInt(maxField.getText());
        } catch(Exception e){
            maxField.setText("100");
            sendIntError();
        }
        
        if(min > max || max > 100 || min < 0){
            min = 0;
            max = 100;
            minField.setText("0");
            maxField.setText("100");
            sendIntError();
        }
        XYChart.Series<String, Number> countries = new XYChart.Series();
        countries.setName("% Immunized for Polio");
        Entry[] countryArray = data.getEntries();
        Arrays.sort(countryArray);
        for(Entry e : countryArray){
            String cnty = e.getDim().getLabel();
            if(cnty != null){
                int val = e.getValue();
                if(val >= min && val <= max){
                    countries.getData().add(new XYChart.Data(cnty, val));
                }
            }

        }
        chart.getData().add(countries);
    }
    
    public void sendIntError(){
        Alert intAlert = new Alert(AlertType.ERROR);
        intAlert.setTitle("Invalid integer");
        intAlert.setContentText("Please enter valid integers from 0 to 100 where the minimum is less than the maximum");
        intAlert.showAndWait();
    }
    
}
