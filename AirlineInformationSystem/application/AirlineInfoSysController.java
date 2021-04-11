package application;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class AirlineInfoSysController implements Initializable {
	
    @FXML
    private Label searchLabel;

    @FXML
    private RadioButton airlineName;

    @FXML
    private ToggleGroup airline;

    @FXML
    private RadioButton airlineNum;

    @FXML
    private RadioButton departure;

    @FXML
    private Button searchBtn;

    @FXML
    private Button closeBtn;
    
    @FXML
    private Label airlineLabel;

    @FXML
    private Label airlineNumLabel;

    @FXML
    private Label deptLabel;

    @FXML
    private Label arrivalLabel;

    @FXML
    private ChoiceBox<String> airlineChoiceBox;

    @FXML
    private ChoiceBox<String> airlineNumChoiceBox;

    @FXML
    private ChoiceBox<String> deptChoiceBox;

    @FXML
    private ChoiceBox<String> arrivalChoiceBox;
    
    @FXML
    private ListView<String> airlineInfo;    

    @FXML
    private RadioButton arrival;

    @FXML
    void closeApp(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void searchFile(ActionEvent event) { 
    	
    	airlineInfo.getItems().clear();    		
		ObservableList<String> data = FXCollections.observableArrayList("Airline \t AirlineNo\tDeparture\tArrival");
		airlineInfo.setItems(data); 
    	
    	if(airlineName.isSelected()) {      	  
    		
    		try {
    			
    			String value = airlineChoiceBox.getSelectionModel().getSelectedItem();    			
    			String s = "";
    			String[] s1 = new String[4];
    			String delm = ",";
    			Path file = Paths.get("flight.txt");
    			InputStream inp = Files.newInputStream(file);    			
    			BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
    			while((s=reader.readLine()) != null) {    
    				s1 = s.split(delm);
    				if(value.equals(s1[0])) {
    					String val = s1[0]+"\t"+s1[1]+"\t"+s1[2]+"\t"+s1[3];
    					airlineInfo.getItems().add(val);
    				}
    			}    			    			    		
    			
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}     		
    		
    	} else if(airlineNum.isSelected()) {    		 		    		      		
    		
    		try {
    			
    			String value = airlineNumChoiceBox.getSelectionModel().getSelectedItem();    			
    			String s = "";
    			String[] s1 = new String[4];
    			String delm = ",";
    			Path file = Paths.get("flight.txt");
    			InputStream inp = Files.newInputStream(file);    			
    			BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
    			while((s=reader.readLine()) != null) { 
    				s1 = s.split(delm);
    				if(value.equals(s1[1])) {
    					String val = s1[0]+"\t"+s1[1]+"\t"+s1[2]+"\t"+s1[3];
    					airlineInfo.getItems().add(val);
    				}
    			}    			    			    		
    			
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
    		    		
    	} else if(departure.isSelected()) {
    		
    		try {    		
	
    			String value = deptChoiceBox.getSelectionModel().getSelectedItem();    			
    			String s = "";
    			String[] s1 = new String[4];
    			String delm = ",";
    			Path file = Paths.get("flight.txt");
    			InputStream inp = Files.newInputStream(file);    			
    			BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
    			while((s=reader.readLine()) != null) {  
    				s1 = s.split(delm);
    				if(value.equals(s1[2])) {
    					String val = s1[0]+"\t"+s1[1]+"\t"+s1[2]+"\t"+s1[3];
    					airlineInfo.getItems().add(val);
    				}    				
    			}    			    			    		
    			
    			} catch (FileNotFoundException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				e.printStackTrace();
    			} 
    		    		
    	} else if(arrival.isSelected()) {
    		try {    		
    			
    			String value = arrivalChoiceBox.getSelectionModel().getSelectedItem();    			
    			String s = "";
    			String[] s1 = new String[4];
    			String delm = ",";
    			Path file = Paths.get("flight.txt");
    			InputStream inp = Files.newInputStream(file);    			
    			BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
    			while((s=reader.readLine()) != null) {  
    				s1 = s.split(delm);
    				if(value.equals(s1[3])) {
    					String val = s1[0]+"\t"+s1[1]+"\t"+s1[2]+"\t"+s1[3];
    					airlineInfo.getItems().add(val);
    				}    			
    			}       			
    			
    			} catch (FileNotFoundException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		    		    		
    	}
    }               
    
    @FXML
    void airlineRdBtn(ActionEvent event) {
    	
    	airlineChoiceBox.getItems().clear();
    	airlineChoiceBox.setValue("Select Airline");
    	
    	airlineChoiceBox.setDisable(false);
    	airlineNumChoiceBox.setDisable(true);
		deptChoiceBox.setDisable(true);
		arrivalChoiceBox.setDisable(true);
		
		String s1="", s2="";    
		
    	try {
    	    
    	    String delimiter = ",";
    		String fileLine;    
    		String[] s = new String[4];
    		
    		Path file = Paths.get("flight.txt");
    		InputStream input;     	
			input = new BufferedInputStream(Files.newInputStream(file));		 
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
					
			while((fileLine = br.readLine()) != null) {
				s = fileLine.split(delimiter);				
				s1 += (s[0]+"\n");								
			}						
			
			 String strArray[] = s1.split("\n");	
			 
			 Arrays.sort(strArray);
			 int len = strArray.length;
			 
			 for(int i=0; i<strArray.length-1; i++) {
				 if(!(strArray[i].equals(strArray[i+1]))) {
					 s2 += strArray[i]+"\n";
				 }
			 }
			 s2 += strArray[len-1];
			 
			 String[] newArray = s2.split("\n");
			 airlineChoiceBox.getItems().addAll(newArray);			
    	} catch (IOException e) {			
			e.printStackTrace();
		}	
		
    }
    
    @FXML
    void airlineNumRdBtn(ActionEvent event) {
    	
    	airlineNumChoiceBox.getItems().clear();
    	airlineNumChoiceBox.setValue("Select Airline Number");    	
    	
    	airlineNumChoiceBox.setDisable(false);
    	airlineChoiceBox.setDisable(true);
		deptChoiceBox.setDisable(true);
		arrivalChoiceBox.setDisable(true);
    	
    	String s1="", s2="";    
		
    	try {
    	    
    	    String delimiter = ",";
    		String fileLine;    
    		String[] s = new String[4];
    		
    		Path file = Paths.get("flight.txt");
    		InputStream input;     	
			input = new BufferedInputStream(Files.newInputStream(file));		 
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
					
			while((fileLine = br.readLine()) != null) {
				s = fileLine.split(delimiter);				
				s1 += (s[1]+"\n");								
			}						
			
			 String strArray[] = s1.split("\n");	
			 
			 Arrays.sort(strArray);
			 int len = strArray.length;
			 
			 for(int i=0; i<strArray.length-1; i++) {
				 if(!(strArray[i].equals(strArray[i+1]))) {
					 s2 += strArray[i]+"\n";
				 }
			 }
			 s2 += strArray[len-1];
			 
			 String[] newArray = s2.split("\n");
			 airlineNumChoiceBox.getItems().addAll(newArray);			
    	} catch (IOException e) {			
			e.printStackTrace();
		}	
				
    } 
      
    @FXML
    void deptRdBtn(ActionEvent event) {
    	
    	deptChoiceBox.getItems().clear();
    	deptChoiceBox.setValue("Select Depature Airport");    	
		 
		deptChoiceBox.setDisable(false);
    	airlineChoiceBox.setDisable(true);
		airlineNumChoiceBox.setDisable(true);
		arrivalChoiceBox.setDisable(true);		
		
		String s1="", s2="";    
		
    	try {
    	    
    	    String delimiter = ",";
    		String fileLine;    
    		String[] s = new String[4];
    		
    		Path file = Paths.get("flight.txt");
    		InputStream input;     	
			input = new BufferedInputStream(Files.newInputStream(file));		 
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
					
			while((fileLine = br.readLine()) != null) {
				s = fileLine.split(delimiter);				
				s1 += (s[2]+"\n");								
			}						
			
			 String strArray[] = s1.split("\n");	
			 
			 Arrays.sort(strArray);
			 int len = strArray.length;
			 
			 for(int i=0; i<strArray.length-1; i++) {
				 if(!(strArray[i].equals(strArray[i+1]))) {
					 s2 += strArray[i]+"\n";
				 }
			 }
			 s2 += strArray[len-1];
			 
			 String[] newArray = s2.split("\n");
			 deptChoiceBox.getItems().addAll(newArray);			
    	} catch (IOException e) {			
			e.printStackTrace();
		}
		
    }
    
    @FXML
    void arrivalRdBtn(ActionEvent event) {
    	
    	arrivalChoiceBox.getItems().clear();
    	arrivalChoiceBox.setValue("Select Arrival Airport");  
    	
    	arrivalChoiceBox.setDisable(false);	
    	airlineChoiceBox.setDisable(true);
		airlineNumChoiceBox.setDisable(true);
		deptChoiceBox.setDisable(true);
		    	
    	String s1="", s2="";    
		
    	try {
    	    
    	    String delimiter = ",";
    		String fileLine;    
    		String[] s = new String[4];
    		
    		Path file = Paths.get("flight.txt");
    		InputStream input;     	
			input = new BufferedInputStream(Files.newInputStream(file));		 
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
					
			while((fileLine = br.readLine()) != null) {
				s = fileLine.split(delimiter);				
				s1 += (s[3]+"\n");								
			}						
			
			 String strArray[] = s1.split("\n");	
			 
			 Arrays.sort(strArray);
			 int len = strArray.length;
			 
			 for(int i=0; i<strArray.length-1; i++) {
				 if(!(strArray[i].equals(strArray[i+1]))) {
					 s2 += strArray[i]+"\n";
				 }
			 }
			 s2 += strArray[len-1];
			 
			 String[] newArray = s2.split("\n");
			 arrivalChoiceBox.getItems().addAll(newArray);			
    	} catch (IOException e) {			
			e.printStackTrace();
		}	
		      			
    }         

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		  ObservableList<String> data = FXCollections.observableArrayList("Airline \t AirlineNo\tDeparture\tArrival");
		  airlineInfo.setItems(data);

		  airlineChoiceBox.setValue("Select Airline");

		  airlineNumChoiceBox.setValue("Select Airline Number");

		  deptChoiceBox.setValue("Select Depature Airport");

		  arrivalChoiceBox.setValue("Select Arrival Airport");
		
	}

}
