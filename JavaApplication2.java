
package javaapplication2;

import java.util.ArrayList;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
import java.io.*;
import javafx.geometry.Pos; 
import javafx.collections.*;

 public class JavaApplication2 extends Application {
    static ArrayList<String> department = new ArrayList<>();
    public static Scanner in=new Scanner(System.in);
    public static int doc_c=0;
     
   
    public static void main(String[] args) {
        Inzialzeation();
        launch(args);
    }
    private static void Inzialzeation(){
        try{
            
            File counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\doctors.txt");
            if(counter.exists()){
            Scanner input=new Scanner(counter);
            /////////////////////////////////doctor
            if(input.hasNext())
            JavaApplication2.doc_c=input.nextInt();
            while(input.hasNext()){
                Doctor a=new Doctor();
                a.doctor_id=input.nextInt();
                a.doctor_name=input.next();
                a.specialization=input.next();
                Doctor.Doctors.putIfAbsent(a.specialization, new ArrayList<>());
                Doctor.Doctors.get(a.specialization).add(a);
                }
            input.close();
            }
            ///////////////////////////////////patients
            
            counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\patients.txt");
            if (counter.exists()){
            Scanner input = new Scanner(counter);
            while(input.hasNext()){
                patient b=new patient(input.nextInt(),input.next(),input.nextInt(),input.nextDouble());
                patient.patients.putIfAbsent(b.patient_id, new ArrayList<>());
                patient.patients.get(b.patient_id).add(b);
            }
            input.close();
            }

            /////////////////////////////////////departments
            counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\departments.txt");
            Scanner input = new Scanner(counter);
            while(input.hasNext()){
                String c=input.next();
                JavaApplication2.department.add(c);
            }
            input.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception{
    Scene homeScene,checkAdmin,checkDoctor,checkPatient;
    
     //////////////////////////////////////////////////checkAdminScene
     Label adminidlabel = new Label("Name:");
     TextField adminidtf = new TextField ();  
     Label adminnamelabel = new Label("Id:");
     TextField adminnametf = new TextField ();
     
     HBox adminlogininput=new HBox(10,adminidlabel,adminidtf,adminnamelabel,adminnametf);
     
     Button adminlogin=new Button("Login");
     adminlogin.setOnAction(eh->{
         Admin x=new Admin();
         x.openAdminStage();
         stage.close();
         });
     
     Button backloginAdmin = new Button("Back");
     VBox adminloginlayout=new VBox(10,adminlogininput,adminlogin,backloginAdmin);
     
     checkAdmin=new Scene(adminloginlayout);
     
     ////////////////////////////////////////////////checkDoctorScene
     Label doctoridlabel = new Label("Name:");
     TextField doctoridtf = new TextField ();  
     Label doctornamelabel = new Label("Id:");
     TextField doctornametf = new TextField ();
     ObservableList<String> options = FXCollections.observableArrayList(department);
     ComboBox combobox=new ComboBox(options);

     HBox doctorlogininput=new HBox(10,doctoridlabel,doctornametf,doctornamelabel,doctoridtf,combobox);
     
     Button doctorlogin=new Button("Login");
     
     doctorlogin.setOnAction(eh->{try {
        final Doctor logdoc = new Doctor(Integer.parseInt(doctoridtf.getText()),doctornametf.getText(),combobox.getSelectionModel().getSelectedItem().toString());
        boolean found = false;
        for (List<Doctor> doctorList : Doctor.Doctors.values()) {
            for (Doctor p : doctorList) {
                if (p.doctor_id == logdoc.doctor_id && p.doctor_name.equals(logdoc.doctor_name) && p.specialization.equals(logdoc.specialization)) {
                    p.openDoctorStage();
                    stage.close();
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        if (!found) {
            notfound();
        }
    } catch (Exception e) {
        System.out.println("Error during doctor login: " + e.getMessage());
    }
         });
     
     Button backloginDoctor = new Button("Back");
     VBox doctorloginlayout=new VBox(10,doctorlogininput,doctorlogin,backloginDoctor);
     
     checkDoctor=new Scene(doctorloginlayout);
     
     ///////////////////////////////////////////////checkPatientScene
     
     Label patientidlabel = new Label("Name:");
     TextField patientnametf = new TextField ();
     Label patientnamelabel = new Label("Id:");
     TextField patientidtf = new TextField ();
     
     HBox patientlogininput=new HBox(10,patientidlabel,patientnametf,patientnamelabel,patientidtf);
     
     Button patientlogin=new Button("Login");
     patientlogin.setOnAction(eh->{
     final patient logpat=new patient(Integer.parseInt(patientidtf.getText()),patientnametf.getText());
         if (patient.patients.containsKey(logpat.patient_id)){
             ArrayList<patient> patientList = patient.patients.get(logpat.patient_id);
        for (patient p : patientList) {
            if (p.patient_name.equals(logpat.patient_name)) {
                p.openPatientStage();
                stage.close();
                }else{notfound();}
            }  
         }
     });
     Button backloginPatient = new Button("Back");
     VBox patientloginlayout=new VBox(10,patientlogininput,patientlogin,backloginPatient);
     
     checkPatient=new Scene(patientloginlayout);
     
    /////////////////////////////////////////////////////homeScene
     Label loginlabel=new Label("Login into :");
     Button openAdmin = new Button("Admin");
     openAdmin.setOnAction(e -> {stage.setScene(checkAdmin);});
     Button openDoctor = new Button("Doctor");
     openDoctor.setOnAction(e -> {stage.setScene(checkDoctor);});
     Button openPatient = new Button("Patient");
     openPatient.setOnAction(e -> {stage.setScene(checkPatient);});
     Button loginExit =new Button("Exit");
     loginExit.setOnAction(e->stage.close());

     HBox chooseLayout = new HBox(10, openAdmin,openDoctor, openPatient);
     VBox mainLayout = new VBox(10,loginlabel,chooseLayout,loginExit);
        
     homeScene = new Scene(mainLayout, 300, 200);
     
    //////////////////////////////////////////////////////setup back buttons    
     backloginAdmin.setOnAction(eh->stage.setScene(homeScene));
     backloginDoctor.setOnAction(eh->stage.setScene(homeScene));
     backloginPatient.setOnAction(eh->stage.setScene(homeScene));
     
    //////////////////////////////////////////////////////start on home page
     stage.setTitle("Login");
     stage.setScene(homeScene);
     stage.show();
 }
    private void notfound(){
       Alert alert=new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Error");
        alert.setContentText("user not found");
        alert.showAndWait();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
}
