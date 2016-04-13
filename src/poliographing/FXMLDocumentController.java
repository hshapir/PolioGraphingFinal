/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poliographing;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author csstudent
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private BarChart<String,Number> chart;
            
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //.setText("Dhev Does!");
    }
    
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

        Gson gson = new Gson();
        DataSet data = gson.fromJson(str, DataSet.class);
        System.out.println(data.toString());
        
        XYChart.Series<String, Number> countries = new XYChart.Series();
        countries.setName("Country");
        for(Entry e : data.getEntries()){
            String cnty = e.getDim().getLabel();
            if(cnty != null){
                int val = e.getValue();
                countries.getData().add(new XYChart.Data(cnty, val));
            }

        }
        chart.getData().add(countries);
    }    
    
}
