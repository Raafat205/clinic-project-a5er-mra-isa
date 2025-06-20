package javaapplication2;
import java.io.*;
import java.util.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import static javaapplication2.patient.pedningappointments;

   class Doctor{
     int doctor_id;
    String doctor_name;
    String specialization;
    static Map<String, ArrayList<Doctor>> Doctors = new HashMap<>();
    public Doctor(int doctor_id, String doctor_name, String specialization) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.specialization = specialization;
    }
    
    public Doctor() {}
    
    @Override
    public String toString() {
        return "Doctor's ID=" + doctor_id + ", Doctor's Name :" + doctor_name + "\n"+"\n";
    } 
    
     void Managing_Appointments(Stage stage,Scene scene){
         
        if(!pedningappointments.isEmpty()){
        Button back=new Button("Back");
        back.setOnAction(eh->{stage.setScene(scene);});
        Button Next=new Button("Next");
        
        ObservableList<File> options = FXCollections.observableArrayList(pedningappointments);
        ComboBox<File> combobox=new ComboBox(options);
        
        HBox hlay=new HBox(20,Next,back);
        hlay.setAlignment(Pos.CENTER);
        VBox lay=new VBox(20,combobox,hlay);
        lay.setAlignment(Pos.CENTER);
        
        Scene manage1=new Scene(lay,400,500);
        
        
        StringBuffer labelapp=new StringBuffer();
        
        Label theapp=new Label();
        
        Button accept=new Button("Confirm");
        accept.setOnAction(eh->{
        pedningappointments.remove(combobox.getSelectionModel().getSelectedItem());
        combobox.getSelectionModel().getSelectedItem().delete();
        String appid=combobox.getSelectionModel().getSelectedItem().getName();
        appid=appid.substring(0, appid.lastIndexOf("."));
        response(appid,"Accepted","");
        
        
        try {
        int c=0;
        File bill=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\Bills\\"+doctor_id+"_"+appid+"_bill.txt");
        if(bill.exists()){
            Scanner input=new Scanner(bill);
            while(input.hasNextInt()){
                c=input.nextInt();
            }
        }
        c+=150;
        PrintWriter count=new PrintWriter(bill);   
        count.print(c);
        count.close();
        
        File save=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\appointments\\"+doctor_id+"_"+appid+".txt");
        PrintWriter appsave=new PrintWriter(save);   
        appsave.print("Appointment of "+appid+": ");
        appsave.close();
        
        }catch(Exception e){
            System.out.println(e);
        } 
        showMsg("Appoinment accepted succesfuly",stage,scene);
        });
        
        Button refuse=new Button("Refuse");
        refuse.setOnAction(eh->{
        pedningappointments.remove(combobox.getSelectionModel().getSelectedItem());
        combobox.getSelectionModel().getSelectedItem().delete();
         String appid=combobox.getSelectionModel().getSelectedItem().getName();
        appid=appid.substring(0, appid.lastIndexOf("."));
        response(appid,"refused","");
        showMsg("Appoinment refuted succesfuly",stage,scene);
        });
        
        TextField ndate=new TextField();
        Button reschedule=new Button("Reschedule");
        reschedule.setOnAction(eh->{
        pedningappointments.remove(combobox.getSelectionModel().getSelectedItem());
        combobox.getSelectionModel().getSelectedItem().delete();
        String appid=combobox.getSelectionModel().getSelectedItem().getName();
        appid=appid.substring(0, appid.lastIndexOf("."));
        response(appid,"Reschedueled",ndate.getText());
        showMsg("Appoinment Reschedueled to "+ndate.getText()+" succesfuly",stage,scene);
        });
        
        HBox reschlayout=new HBox(20,reschedule,ndate);
        reschlayout.setAlignment(Pos.CENTER);
        
        VBox finishlayout=new VBox(20,theapp,accept,refuse,reschlayout);
        finishlayout.setAlignment(Pos.CENTER);
        
        Scene finish=new Scene(finishlayout);
        
        stage.setScene(manage1);
        
        Next.setOnAction(eh->{
            if(combobox.getSelectionModel().getSelectedItem() != null){
            labelapp.setLength(0);
            try{
            Scanner labelappinput=new Scanner(combobox.getSelectionModel().getSelectedItem());
            while(labelappinput.hasNextLine()){
                labelapp.append(labelappinput.nextLine());
            }
            labelappinput.close();
            }catch(Exception e){
            System.out.println(e);
            } 
            theapp.setText(labelapp.toString());
            stage.setScene(finish);
        }else
        showAlert("Please select an appoinment before proceeding.");
        });
         }else 
             showMsg("No Appoinments Exists",stage,scene);
}
    
    public void Prescribe_medications (Stage stage,Scene scene){
        try{
        StringBuffer streport=new StringBuffer("");
        
        ArrayList<String> records=new ArrayList<>();
            for (List<patient> patientList : patient.patients.values()) {
                for (patient p : patientList) {   
                    File report = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\appointments\\"+doctor_id+"_"+p.patient_id+"_"+p.patient_name+".txt"); 
                    if(report.exists()){
                    records.add(report.getAbsoluteFile().toString());
                    }
                }
            }

        
        
        ObservableList<String> options = FXCollections.observableArrayList(records);
        ComboBox<String> combobox=new ComboBox(options);
        
        Button medicrecback=new Button("Back");
        medicrecback.setOnAction(eh->{stage.setScene(scene);});
        
        Button edit=new Button("Proceed");
        
        HBox medicrecbuttons=new HBox(20,medicrecback,edit);
        medicrecbuttons.setAlignment(Pos.CENTER);
        
        VBox medicreclayout=new VBox(20,combobox,medicrecbuttons);
        medicreclayout.setAlignment(Pos.CENTER);
        
        Scene medicrecscene=new Scene(medicreclayout);
        
        stage.setScene(medicrecscene);
        
        edit.setOnAction(eh->{
            if (combobox.getSelectionModel().getSelectedItem() == null) {
                showAlert("Please select a record");
                return;
            }
            
            try{
                Scanner input=new Scanner(combobox.getSelectionModel().getSelectedItem());
                while(input.hasNext()){
                streport.append(input.next());
                }
            }catch(Exception e){
                showAlert(e.getMessage());
            }
            
            TextField prescribtion=new TextField();
            
            Button editback=new Button("Back");
            editback.setOnAction(e->{stage.setScene(medicrecscene);});
            
            Button prescribeSave=new Button("Save");
            prescribeSave.setOnAction(e->{
                
                String name=combobox.getSelectionModel().getSelectedItem();
                name=name.substring(name.lastIndexOf("\\") + 1, combobox.getSelectionModel().getSelectedItem().lastIndexOf("."));
                
                showMsg("Record Saved",stage,scene);
                streport.append("\n"+prescribtion.getText()+"\n");
                try{
                FileWriter editing=new FileWriter("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\Reports\\"+name+"_Report.txt",true);
                editing.append("\n"+streport);
                editing.close();
                combobox.getItems().clear();
                File delfile=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\appointments\\"+name+".txt");
                if (delfile.exists()) 
                delfile.delete();
                }catch(Exception ex){
                    showAlert(ex.getMessage());
                }
            });
            
            HBox presSaveBack=new HBox(20,editback,prescribeSave);
            VBox prescribelayout=new VBox(prescribtion,presSaveBack);
            Scene editscene=new Scene(prescribelayout,400,500);
            stage.setScene(editscene);
            
            
            
        });
        
        }catch(Exception e){
            showAlert(e.getMessage());
        }
    }
    
    public void view_reports(Stage stage,Scene scene){
         try{
        StringBuffer streport=new StringBuffer("");
        
        ArrayList<File> records=new ArrayList<>();
            for (List<patient> patientList : patient.patients.values()) {
                for (patient p : patientList) {   
                    File report = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\Reports\\"+doctor_id+"_"+p.patient_id+"_"+p.patient_name+"_Report.txt"); 
                    if(report.exists()){
                    records.add(report);
                    }
                }
            }

        
        
        ObservableList<File> options = FXCollections.observableArrayList(records);
        ComboBox<File> combobox=new ComboBox(options);
        
        Button medicrecback=new Button("Back");
        medicrecback.setOnAction(eh->{stage.setScene(scene);});
        
        Button view=new Button("View");
        
        HBox medicrecbuttons=new HBox(20,medicrecback,view);
        medicrecbuttons.setAlignment(Pos.CENTER);
        
        VBox medicreclayout=new VBox(20,combobox,medicrecbuttons);
        medicreclayout.setAlignment(Pos.CENTER);
        
        Scene medicrecscene=new Scene(medicreclayout);
        
        stage.setScene(medicrecscene);
        
        view.setOnAction(eh->{
            if (combobox.getSelectionModel().getSelectedItem() == null) {
                showAlert("Please select a record to view.");
                return;
            }
            
            try{
                Scanner input=new Scanner(combobox.getSelectionModel().getSelectedItem());
                while(input.hasNext()){
                streport.append(input.next());
                }
            }catch(Exception e){
                showAlert(e.getMessage());
            }
            String name=combobox.getSelectionModel().getSelectedItem().getName();
            patient.showRecord(streport.toString(),name.substring(0,name.lastIndexOf(".")));
        });
        
        }catch(Exception e){
            showAlert(e.getMessage());
        }
    }
    
    void openDoctorStage() {
        
      for (List<patient> patientlist : patient.patients.values()) {
           for (patient p : patientlist) {{
           File response =new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\doctorResponse\\reschedulesDocMsgs\\"+doctor_id+"_"+p.patient_id+"_"+p.patient_name+".txt");
           if(response.exists()){
           try{
           String resmsg=new String();
           Scanner resinput=new Scanner(response);
           if(resinput.hasNext()){
           while(resinput.hasNextLine()){
                resmsg+=resinput.nextLine();
            }
            resinput.close();
            reschres(resmsg);
            }else 
              resinput.close(); 
           }catch(Exception e){
               showAlert(e.toString());
                }
           }
           response.delete();
                    }
                }
            }  
        ///////////////////////////////////pending appoinments
        File folder = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\pending appointments\\"+doctor_id);
        if (folder.exists() && folder.isDirectory()) {
            for(File f:folder.listFiles(File::isFile)){
                patient.pedningappointments.add(f);}
            }
        
        Stage DoctorStage = new Stage();
        Button MngAppoinments = new Button("Manage Appoinments");
        Button prsMedications = new Button("Prescribe Medications");
        Button viewReports = new Button("View Reports");
        Label welcomedoctor=new Label("Welcome "+doctor_name+", what to do you want to do?");
        HBox DOCLayout = new HBox(20,MngAppoinments,prsMedications,viewReports);
        DOCLayout.setAlignment(Pos.CENTER);
        VBox vDoclayout=new VBox(20,welcomedoctor,DOCLayout);
        vDoclayout.setAlignment(Pos.CENTER);
        Scene DocScene = new Scene(vDoclayout,500, 300);
        MngAppoinments.setOnAction(e -> Managing_Appointments(DoctorStage,DocScene));
        prsMedications.setOnAction(e -> Prescribe_medications(DoctorStage,DocScene));
        viewReports.setOnAction(e -> view_reports(DoctorStage,DocScene));
        DoctorStage.setTitle("Delete Window");
        DoctorStage.setScene(DocScene);
        DoctorStage.show();
    }
    
    void showMsg(String message,Stage stage,Scene scene) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setContentText(message);
    alert.showAndWait();
    if(alert.getResult()==ButtonType.OK){
        stage.setScene(scene);
    }
    }
    void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    void response(String name,String msg,String date){
        String resmsg=new String();
        File response =new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\doctorResponse\\"+doctor_id+"_"+name+".txt");
        if(date!=""){
        msg+=" to: "+date;
        response =new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\doctorResponse\\reschedules\\"+doctor_id+"_"+name+".txt");
        }
        try{
            FileWriter resinput = new FileWriter(response, false);
            resmsg = "Your appointment with doctor " + doctor_name + " is " + msg + "\n";
            resinput.append(resmsg);
            resinput.close();
           }catch(Exception e){
               System.out.print(e);
        }
    }
    void reschres(String message){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setContentText(message);
    alert.showAndWait();
    }
}

