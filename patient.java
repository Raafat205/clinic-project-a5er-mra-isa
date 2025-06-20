package javaapplication2;
import java.io.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import static javaapplication2.Doctor.Doctors;
import static javaapplication2.JavaApplication2.department;

 class Visa {
    int num;
    double balance;
    void withdraw(double value){
        balance-=value;
    }
    void deposit(double value){
        balance+=value;
    }
}


    class appointment{
    static ArrayList<File> pedningappointments=new ArrayList<>();
    void saveappointment(int id,String date,int patient_id,String patient_name,Stage stage,Scene scene,String alert){
        
            
        try {
        int c=0;
        String appid=patient_id+"_"+patient_name+".txt";
        File schedule = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\pending appointments\\"+id+"\\"+appid); 
        if(!schedule.exists()){
        FileWriter app=new FileWriter(schedule, true);     
        app.append("Patient info is : " + "\nID :" +patient_id+"  NAME : "+patient_name +"\n Is schedualling an appoinment in: " +date+"\n"+"\n");
        app.close();
        pedningappointments.add(schedule);
        patient.showMsg(alert,stage,scene);
        }else 
            patient.showAlert("An appointment with this doctor is already requested wait for the doctor to respond for the first request");
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
}

    class patient extends appointment
{
    int patient_id;
    String patient_name;
    Visa credit_card=new Visa();
    static Map<Integer ,ArrayList<patient>> patients = new HashMap<>();
    ArrayList<String> billstoremove=new ArrayList<>();
    @Override
    public String toString() {
        return "patient id: " + patient_id + ", patient name: " + patient_name +"patient card num: " + credit_card.num + ", patient card balance: " + credit_card.balance + "\n";
    }
    public patient(){}
    
    public patient(int patient_id, String patient_name,int credit_card_num,double credit_card_bal) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        credit_card.num = credit_card_num;
        credit_card.balance = credit_card_bal;
    }
    
    public patient(int patient_id, String patient_name) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
    }
    
     void requestappointment(Stage stage,Scene home){
        Scene reqappscene1,reqappscene2;
        ObservableList<String> reqappoptions = FXCollections.observableArrayList(department);
        ComboBox reqappcombobox=new ComboBox(reqappoptions);
        Button reqback = new Button("Back");
        Button reqnext = new Button("Next");
        HBox backnextreq=new HBox(20,reqback,reqnext);
        backnextreq.setAlignment(Pos.CENTER);
        
        VBox reqappscene1layout =new VBox(20,reqappcombobox,backnextreq);
        reqappscene1layout.setAlignment(Pos.CENTER);
        
        reqappscene1 = new Scene(reqappscene1layout, 500, 300);
        stage.setScene(reqappscene1);
        ObservableList<Doctor> reqAppOptions = FXCollections.observableArrayList();
        ComboBox<Doctor> doctorComboBox = new ComboBox<>(reqAppOptions);
        Button req2back = new Button("Back");
        req2back.setOnAction(eh->requestappointment(stage,home));
        Button reqDone = new Button("Request");
        
        HBox backDonereq=new HBox(20,req2back,reqDone);
        Label date=new Label("Date");
        TextField datetf=new TextField();
        HBox docdate=new HBox(20,doctorComboBox,date,datetf);
        
        VBox reqappscene2layout = new VBox(20, docdate,backDonereq);
        reqappscene2layout.setAlignment(Pos.CENTER);
        
        reqappscene2 = new Scene(reqappscene2layout, 500, 300);
        
        reqappcombobox.setOnAction(eh -> {
        String selectedDepartment = reqappcombobox.getSelectionModel().getSelectedItem().toString();
        for (ArrayList<Doctor> doctorList : Doctors.values()) {
            for (Doctor doctor : doctorList) {
                if (doctor.specialization.equals(selectedDepartment))
                    reqAppOptions.add(doctor);
                }
            }
        });

        reqnext.setOnAction(eh -> {
    if (reqappcombobox.getSelectionModel().getSelectedItem() != "") 
        stage.setScene(reqappscene2);
     else 
        showAlert("Please select a department before proceeding.");
   });
        appointment z=new appointment();
        reqDone.setOnAction(eh->{
     if(doctorComboBox.getSelectionModel().getSelectedItem() == null){
        showAlert("Please select a doctor before proceeding.");
        return;
     }
     if(datetf.getText() == ""||datetf.toString().trim().isEmpty()){
         showAlert("Please enter a date before proceeding.");
         return;
     }
        if (doctorComboBox.getSelectionModel().getSelectedItem() != null&&datetf.getText() != null) 
        z.saveappointment(doctorComboBox.getSelectionModel().getSelectedItem().doctor_id,datetf.getText(),patient_id,patient_name,stage,home,"Appointment Requested");
        });
        reqback.setOnAction(eh->{stage.setScene(home);});
    }
    
     void Accessing_Medical_Record(Stage stage,Scene home){
    try{
        StringBuffer streport=new StringBuffer("");
        
        ArrayList<File> records=new ArrayList<>();
            for (List<Doctor> doctorList : Doctor.Doctors.values()) {
                for (Doctor p : doctorList) {   
                    File report = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\Reports\\"+p.doctor_id+"_"+patient_id+"_"+patient_name+"_Report.txt"); 
                    if(report.exists()){
                    records.add(report);
                    }
                }
            }

        
        
        ObservableList<File> options = FXCollections.observableArrayList(records);
        ComboBox<File> combobox=new ComboBox(options);
        
        Button medicrecback=new Button("Back");
        medicrecback.setOnAction(eh->{stage.setScene(home);});
        
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
            showRecord(streport.toString(),name.substring(0,name.lastIndexOf(".")));
        });
        
        }catch(Exception e){
            showAlert(e.getMessage());
        }
    }
    
     void Accessing_Billing(Stage stage,Scene home){
        Scene pay;
        ArrayList<String> bills=new ArrayList<>();
        int c=0;
        for (List<Doctor> doctorList : Doctor.Doctors.values()) {
           for (Doctor p : doctorList) {
        String appid=p.doctor_id+"_"+patient_id+"_"+patient_name;
        try{
        File bill=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\Bills\\"+appid+"_bill.txt");
        if(bill.exists()){
          bills.add(appid);
        Scanner input=new Scanner(bill);
            while(input.hasNextInt()){
                c+=input.nextInt();
            }
            input.close();
            }
        }catch(Exception e){
            showAlert(e.getMessage());
        }}}
        
            
        if(!bills.isEmpty()){ 
        ObservableList<String> options = FXCollections.observableArrayList(bills);
        ComboBox<String> combobox=new ComboBox(options);
        Button back=new Button("Back");
        Button paybutton=new Button("Pay");
        final int billcost=c;
        paybutton.setOnAction(eh->{
            if (combobox.getSelectionModel().getSelectedItem() == null) {
        showAlert("Please select a bill to pay.");
        return;
    }
        if(credit_card.balance>=billcost){
            
            
            try{
            File bill=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\Bills\\"+combobox.getSelectionModel().getSelectedItem()+"_bill.txt");
            Scanner input=new Scanner(bill);
            int costperbill=0;
            while(input.hasNextInt()){
               costperbill=input.nextInt();
            }
            input.close();
            bills.remove(combobox.getSelectionModel().getSelectedItem());
            credit_card.withdraw(costperbill);
            bill.delete();
            File response =new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\doctorResponse\\"+combobox.getSelectionModel().getSelectedItem()+".txt");
            if(response.exists()){response.delete();}
            options.remove(combobox.getSelectionModel().getSelectedItem());
            
            }catch(Exception e){
            showAlert(e.getMessage());
        } 
        
            try {
            double value=credit_card.balance;
            File file = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\patients.txt");
            ArrayList<String> lines = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith(String.valueOf(patient_id))) {
                    String[] parts = line.split(" ");
                    if (parts.length >= 4) {
                        parts[3] =String.valueOf(value);
                        line = String.join(" ", parts);
                    }
                }
                lines.add(line);
            }
            scanner.close();
            PrintWriter writer = new PrintWriter(file);
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
            
          if(!bills.isEmpty())
            showMsg("Bill Payed",stage,null);
            else
            showMsg("All Bills Payed",stage,home);  
            
        }else
           showAlert("There is no enough money");
        });
        
        HBox paybacknext=new HBox(20,back,paybutton);
        paybacknext.setAlignment(Pos.CENTER);
        VBox pay1layout=new VBox(20,combobox,paybacknext);
        pay1layout.setAlignment(Pos.CENTER);
        
        pay=new Scene(pay1layout,400,500);
       
        stage.setScene(pay);
        back.setOnAction(eh->{stage.setScene(home);});
    }else
        showMsg("There is Nothing to be payed",stage,home);
    }
     
     void openPatientStage() {
            ///////////////////////////////checkmsgs
            
           for (List<Doctor> doctorList : Doctor.Doctors.values()) {
           for (Doctor p : doctorList) {{
           File response =new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\doctorResponse\\"+p.doctor_id+"_"+patient_id+"_"+patient_name+".txt");
           File deletemsg=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\Deleted\\"+p.doctor_id+"_"+patient_id+"_"+patient_name+".txt");
           if(response.exists()){
           try{
           String resmsg=new String();
           Scanner resinput=new Scanner(response);
           if(resinput.hasNext()){
           while(resinput.hasNextLine()){
                resmsg+=resinput.nextLine();
            }
            resinput.close();
            doctorpatient(resmsg);
            }else 
              resinput.close(); 
            billstoremove.add("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\doctorResponse\\"+p.doctor_id+"_"+patient_id+"_"+patient_name+".txt");
           }catch(Exception e){
               showAlert(e.toString());
                            }
                        }
           if(deletemsg.exists()){
           try{
           String delmsg=new String();
           Scanner delinput=new Scanner(deletemsg);
           if(delinput.hasNext()){
           while(delinput.hasNextLine()){
                delmsg+=delinput.nextLine();
            }
            delinput.close();
            doctorpatient(delmsg);
            deletemsg.delete();
            credit_card.deposit(150);
            try {
            double value=credit_card.balance;
            File file = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\patients.txt");
            ArrayList<String> lines = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith(String.valueOf(patient_id))) {
                    String[] parts = line.split(" ");
                    if (parts.length >= 4) {
                        parts[3] =String.valueOf(value);
                        line = String.join(" ", parts);
                    }
                }
                lines.add(line);
            }
            scanner.close();
            PrintWriter writer = new PrintWriter(file);
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
            }else 
              delinput.close();
           }catch(Exception e){
               showAlert(e.toString());
           }    
           }
           File confres=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\doctorResponse\\reschedules\\"+p.doctor_id+"_"+patient_id+"_"+patient_name+".txt");
           if(confres.exists()){
            try{
            String resmsg=new String();
           Scanner confresinput=new Scanner(confres);
           if(confresinput.hasNext()){
           while(confresinput.hasNextLine()){
                resmsg+=confresinput.nextLine();
            }
            confresinput.close();
           showConfirmation(resmsg,Integer.toString(p.doctor_id));
           } }catch(Exception e){
               showAlert(e.toString());
                            }
                        }
                    }
                }
            }
            
           /////////////////////////////////////////////////////////////////////////
        Stage PatientStage = new Stage();
        Scene PatientScene,reportsscene,billsscene;
        ////////////////////////////////////////////////////////////////////////////////
        Button patreportback = new Button("Back");
        reportsscene = new Scene(patreportback, 500, 300);
        ////////////////////////////////////////////////////////////////////////////////
        Button patbillback = new Button("Back");
        billsscene = new Scene(patbillback, 500, 300);
        ///////////////////////////////////////////////////////////////////////////////
        Button RequestAppoinment = new Button("Request Appoinment");
        
        Button ViewReports = new Button("View Reports");
        
        Button ViewBills= new Button("View Bills");
        
        Button closePatientWindow = new Button("Exit");
        closePatientWindow.setOnAction(e -> PatientStage.close());

        Label welcomepateint=new Label("Welcome "+patient_name+", what to do you want to do?");
        
        HBox PatientchooseLayout=new HBox(10,RequestAppoinment,ViewReports,ViewBills);
        PatientchooseLayout.setAlignment(Pos.CENTER);
        
        VBox PatientLayout=new VBox(10,welcomepateint,PatientchooseLayout,closePatientWindow);
        PatientLayout.setAlignment(Pos.CENTER);
        
        PatientScene = new Scene(PatientLayout, 500, 300);
        //////////RequestAppoinmentButtonAction
        RequestAppoinment.setOnAction(eh->requestappointment(PatientStage,PatientScene));
        //////////ViewReportsButtonAction
        ViewReports.setOnAction(eh->Accessing_Medical_Record(PatientStage,PatientScene));
        //////////ViewBillsButtonAction
        ViewBills.setOnAction(eh->Accessing_Billing(PatientStage,PatientScene));
        
        PatientStage.setTitle("Patient");
        PatientStage.setScene(PatientScene);
        PatientStage.show();
    }
     
    static void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
     static void showRecord(String message,String recOF) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Record");
    alert.setHeaderText(recOF);
    alert.setContentText(message);
    alert.showAndWait();
}
    static void showMsg(String message,Stage stage,Scene scene) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setContentText(message);
    alert.showAndWait();
    if(scene != null){
    if(alert.getResult()==ButtonType.OK){
        stage.setScene(scene);
    }
    }
}
    void showConfirmation(String message,String doc){
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setContentText(message);
    alert.showAndWait();
    String appid=patient_id+"_"+patient_name;
    if(alert.getResult()==ButtonType.OK){
        ////////////////////////////////////////
         try {
        int c=0;
        File bill=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\Bills\\"+appid+"_bill.txt");
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
        
        File confres=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\doctorResponse\\reschedulesDocMsgs\\"+doc+"_"+appid+".txt");
        PrintWriter confreswrite=new PrintWriter(confres);   
        confreswrite.print("Appointment Reschedule of "+patient_id+"_"+patient_name+" with "+doc+" is accepted");
        confreswrite.close();
        
        ///////////////////////////////////////////
        File save=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\appointments\\"+doc+"_"+appid+".txt");
        PrintWriter appsave=new PrintWriter(save);   
        appsave.print("Appointment of "+patient_id+"_"+patient_name+" with "+doc);
        appsave.close();
        
        }catch(Exception e){
            System.out.println(e);
        }
    }
    if(alert.getResult()==ButtonType.CANCEL){
        try{
        File confres=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\doctorResponse\\reschedulesDocMsgs\\"+doc+"_"+appid+".txt");
        PrintWriter confreswrite=new PrintWriter(confres);   
        confreswrite.print("Appointment Reschedule of "+patient_id+"_"+patient_name+" with "+doc+" is declined");
        confreswrite.close();
        }catch(Exception e){
            showAlert(e.toString());
        }
        }
    File todel=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\doctorResponse\\reschedules\\"+doc+"_"+appid+".txt");
    todel.delete();
    }
    
    void doctorpatient(String message){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setContentText(message);
    alert.showAndWait();
    }
}

