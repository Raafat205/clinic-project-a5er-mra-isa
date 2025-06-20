package javaapplication2;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import static javaapplication2.JavaApplication2.department;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.nio.file.*;

class Admin {

    /////////////////////////////////////////////////EDITS
    
    void edit_doc(Stage stage,Scene scene) {
        list_doc(stage,scene);
        Label depchoose=new Label("Choose department: ");
        ObservableList<String> options = FXCollections.observableArrayList(department);
        ComboBox combobox=new ComboBox(options);
        Button back=new Button("Back");
        back.setOnAction(eh->{stage.setScene(scene);});
        Button next=new Button("Next");
        
        HBox backnext=new HBox(20,back,next);
        VBox idlabinput=new VBox(20,depchoose,combobox,backnext);
        Scene edit_doc_scene=new Scene(idlabinput);
        stage.setScene(edit_doc_scene);
        
        next.setOnAction(eh->{
        
        if (Doctor.Doctors.containsKey(combobox.getSelectionModel().getSelectedItem().toString())) {
            Label idLabel=new Label("id: ");
            TextField idInput=new TextField();
            int id=Integer.parseInt(idInput.getText());
            ArrayList<Doctor> doctors = Doctor.Doctors.get(combobox.getSelectionModel().getSelectedItem().toString());
            
            for (Doctor doctor : doctors) {
                if(doctor.doctor_id ==id){
                
                Button name=new Button("Name");
                Button Specialization=new Button("Specialization");
                Button InputBack=new Button("Back");

                VBox choices=new VBox(20,name,Specialization,InputBack);
                Scene choice=new Scene(choices);
                stage.setScene(choice);
                
                    name.setOnAction(e->{
                        Label Nnew=new Label("Enter the new name of the doctor: ");
                        TextField ntf=new TextField();
                        Button backk=new Button("Back");
                        backk.setOnAction(ehh->{stage.setScene(choice);});
                        Button update=new Button("update");
                        
                        update.setOnAction(ehh->{
                        String newName = ntf.getText();
                        doctor.doctor_name = newName;
                        showMsg("Doctor's name was updated to " + newName,stage,null);
                        });
                        
                        HBox backupdate=new HBox(20,backk,update);
                        HBox nameinput=new HBox(20,Nnew,ntf);
                        VBox namelayout=new VBox(20,backupdate,nameinput);
                        Scene namescene=new Scene(namelayout);
                        stage.setScene(namescene);
                        });
                    
                    Specialization.setOnAction(e->{
                        Label Snew=new Label("Enter the new specialization for the doctor : ");
                        TextField stf=new TextField();
                        Button backk=new Button("Back");
                        backk.setOnAction(ehh->{stage.setScene(choice);});
                        Button update=new Button("update");
                        
                        update.setOnAction(ehh->{
                        String newSpecialization = stf.getText();
                        doctor.specialization = newSpecialization;
                        showMsg("Doctor's specialization was updated to " + newSpecialization,stage,null);
                        });
                        
                        HBox backupdate=new HBox(20,backk,update);
                        HBox specinput=new HBox(20,Snew,stf);
                        VBox speclayout=new VBox(20,backupdate,specinput);
                        Scene specscene=new Scene(speclayout);
                        stage.setScene(specscene);
                        });
                            
                    InputBack.setOnAction(e->{stage.setScene(edit_doc_scene);});        
                
            try(RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\doctors.txt", "rw")){
                 raf.setLength(0);
                 raf.writeBytes(String.valueOf(JavaApplication2.doc_c) + "\n");
            for (String department :JavaApplication2.department) {
                if (Doctor.Doctors.containsKey(department)) {
                    for (Doctor z : Doctor.Doctors.get(department)) {
                        raf.writeBytes(z.doctor_id+ " " + z.doctor_name+ " " + z.specialization + "\n");
                    }
                }
            }
            }catch(Exception e){
            showAlert(e.getMessage());
        }
            break;
        }showAlert("Doctor not found.");
            }
        }else
        showAlert("Department not found.");
        });
    }

    void edit_patient(Stage stage,Scene scene) {
        Label patId=new Label("What is the ID of the patient you want to edit?");
        TextField patIdTf=new TextField();

        Button back=new Button("Back");
        back.setOnAction(eh->{stage.setScene(scene);});
        Button next=new Button("Next");
        
        HBox backnext=new HBox(20,back,next);
        VBox idlabinput=new VBox(20,patId,patIdTf,backnext);
        Scene edit_doc_scene=new Scene(idlabinput);
        stage.setScene(edit_doc_scene);
        
        next.setOnAction(eh->{
            
        
    if (patient.patients.containsKey(Integer.parseInt(patIdTf.getText()))) {
        ArrayList<patient> patients = patient.patients.get(Integer.parseInt(patIdTf.getText()));

        for (patient patient : patients) {
                Button name=new Button("Name");
                Button ID=new Button("ID");
                Button InputBack=new Button("Back");

                VBox choices=new VBox(20,name,ID,InputBack);
                Scene choice=new Scene(choices);
                stage.setScene(choice);
                
                    name.setOnAction(e->{
                        Label Nnew=new Label("Enter the new name of the patient: ");
                        TextField ntf=new TextField();
                        Button backk=new Button("Back");
                        backk.setOnAction(ehh->{stage.setScene(choice);});
                        Button update=new Button("update");
                        
                        update.setOnAction(ehh->{
                        String newName = ntf.getText();
                        patient.patient_name = newName;
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        showMsg("Patient's name updated to " + newName,stage,null);
                        });
                        
                        HBox backupdate=new HBox(20,backk,update);
                        HBox nameinput=new HBox(20,Nnew,ntf);
                        VBox namelayout=new VBox(20,backupdate,nameinput);
                        Scene namescene=new Scene(namelayout);
                        stage.setScene(namescene);
                        });
                    
                    ID.setOnAction(e->{
                        Label inew=new Label("Enter the new ID for the patient: ");
                        TextField itf=new TextField();
                        Button backk=new Button("Back");
                        backk.setOnAction(ehh->{stage.setScene(choice);});
                        Button update=new Button("update");
                        
                        update.setOnAction(ehh->{
                        int newId = Integer.parseInt(itf.getText());
                        if (!patient.patients.containsKey(newId)) {
                        patient.patients.remove(Integer.parseInt(patIdTf.getText()));
                        patient.patient_id = newId;  
                        patient.patients.put(newId, new ArrayList<>(List.of(patient))); 
                        showMsg("Patient's ID was updated to " + newId,stage,null);
                        } else {
                        showAlert("New ID already exists. Please choose a different ID.");
                        }
                        });
                        HBox backupdate=new HBox(20,backk,update);
                        HBox idinput=new HBox(20,inew,itf);
                        VBox idlayout=new VBox(20,backupdate,idinput);
                        Scene idscene=new Scene(idlayout);
                        stage.setScene(idscene);
                        });

                InputBack.setOnAction(e->{stage.setScene(edit_doc_scene);}); 
        }

        
        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\patients.txt", "rw")) {
            raf.setLength(0);

            for (Map.Entry<Integer, ArrayList<patient>> entry : patient.patients.entrySet()) {
                for (patient patient : entry.getValue()) {
                    raf.writeBytes(patient.patient_id + " " + patient.patient_name + "\n");
                }
            }
        } catch (Exception e) {
            showAlert("Error updating the file: " + e.getMessage());
        }
    } else {
        showAlert("Patient with ID " + patIdTf.getText() + " not found.");
    }
        });
}
    
    void edit_department(Stage stage,Scene scene) {
    Label depLabel=new Label("What is the name of the department you want to edit?");
    TextField departmentName =  new TextField();;
    
    Button next=new Button("Next");
                        
   
    Button back=new Button("Back");
    HBox dep=new HBox(20,depLabel,departmentName);
    HBox backnext=new HBox(20,back,next);
    VBox edit_dep_layout=new VBox(20,dep,backnext);
    Scene edit_dep_scene=new Scene(edit_dep_layout);
    stage.setScene(edit_dep_scene);
    back.setOnAction(eh->{stage.setScene(scene);});
    next.setOnAction(ehh->{
    if (JavaApplication2.department.contains(departmentName)) {
                Label depnamelabel=new Label("Enter new department name: ");
                TextField newDepartmentName = new TextField();
                Button backk=new Button("Back");
                backk.setOnAction(e->{stage.setScene(edit_dep_scene);});
                Button update=new Button("update");
                
                update.setOnAction(e->{
                int index = JavaApplication2.department.indexOf(departmentName.getText());
                JavaApplication2.department.set(index, newDepartmentName.getText());
                showMsg("Department name updated to " + newDepartmentName.getText(),stage,null);
                });
                
                HBox backupdate=new HBox(20,backk,update);
                HBox depinput=new HBox(20,depnamelabel,newDepartmentName);
                VBox deplayout=new VBox(20,backupdate,depinput);
                Scene depscene=new Scene(deplayout);
                stage.setScene(depscene);
                        
        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\departments.txt", "rw")) {
            raf.setLength(0);

            for (String dept : JavaApplication2.department) {
                raf.writeBytes(dept + "\n");
            }
        } catch (Exception e) {
            showAlert("Error updating the file: " + e.getMessage());
        }
    } else {
        showAlert("Department " + departmentName.getText() + " not found.");
    }
    });
}

     /////////////////////////////////////////////////ADDS
    
    void add_doc(Stage stage,Scene scene) {
        Label name=new Label("Doctor's name: ");
        TextField doctornametf = new TextField ();
        Label id=new Label("Doctor's Id: ");
        TextField doctoridtf = new TextField (); 
        ObservableList<String> options = FXCollections.observableArrayList(department);
        ComboBox combobox=new ComboBox(options);

        HBox doctoraddinput=new HBox(10,name,doctornametf,id,doctoridtf,combobox);

                
        Button doctoradd=new Button("Add");
        Button doctoraddback=new Button("Back");
        doctoraddback.setOnAction(eh->{stage.setScene(scene);});
        
        HBox backaddlayout=new HBox(20,doctoraddback,doctoradd);
        VBox docaddlayout=new VBox(20,doctoraddinput,backaddlayout);
        
        doctoradd.setOnAction(eh->{
        
         if(combobox.getSelectionModel().getSelectedItem() == null){
        showAlert("Please select a department before proceeding.");
        return;
     }
     if(doctornametf.getText()==""){
         showAlert("Please enter a name before proceeding.");
         return;
     }
     if(doctoridtf.getText()==""){
         showAlert("Please enter an id before proceeding.");
         return;
     }     
        Doctor doctor = new Doctor(Integer.parseInt(doctoridtf.getText()), doctornametf.getText(), combobox.getSelectionModel().getSelectedItem().toString());
        Doctor.Doctors.get(combobox.getSelectionModel().getSelectedItem().toString()).add(doctor);
        JavaApplication2.doc_c++;
        try{      
            File counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\doctors.txt");
            RandomAccessFile raf = new RandomAccessFile(counter, "rw");
            raf.seek(0);
            raf.writeBytes(String.valueOf(JavaApplication2.doc_c) + "\n");
            raf.seek(raf.length());
            raf.writeBytes(doctor.doctor_id + " " + doctor.doctor_name + " " + doctor.specialization + "\n");
            raf.close();
         showMsg("Doctor added successfully.",stage,null);
          try {
            String folder="C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\pending appointments\\"+doctor.doctor_id;
            Path folderPath=Paths.get(folder);
            Files.createDirectories(folderPath);
            showMsg("Nested folders created successfully at: " + folderPath,stage,null);
        } catch (Exception e) {
            showAlert("Error creating nested folders: " + e.getMessage());
        }
        }catch(Exception e){
            showAlert(e.getMessage());
        }
        });
        Scene add_docscene=new Scene(docaddlayout);
        stage.setScene(add_docscene);
    }

    void add_patient(Stage stage,Scene scene) {
        Label name=new Label("Patient's Name: ");
        TextField p_n =new TextField();
        Label id=new Label("What is the patient's Id: ");
        TextField p_id = new TextField();
        Button add=new Button("Add");
        Button back=new Button("Back");
        
        back.setOnAction(eh->{stage.setScene(scene);});
        
        HBox addinput=new HBox(10,name,p_n,id,p_id);
        HBox backaddlayout=new HBox(20,back,add);
        VBox addlayout=new VBox(20,addinput,backaddlayout);
        
        add.setOnAction(eh->{
        patient patient = new patient(Integer.parseInt(p_id.getText()), p_n.getText());
        if (patient.patients.containsKey(p_id)) {
                showAlert("id is already exists");
            }else{
            patient.patients.put(Integer.parseInt(p_id.getText()), new ArrayList<>());
            patient.patients.get(Integer.parseInt(p_id.getText())).add(patient);
        try{      
            File counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\patients.txt");
            RandomAccessFile raf = new RandomAccessFile(counter, "rw");

            raf.seek(raf.length());
            raf.writeBytes(p_id.getText() + " " + p_n.getText() + " " + "\n");
            
            raf.close();
        }catch(Exception e){
            showAlert(e.getMessage());
        }
         showMsg("Patient added successfully.",stage,null);
            }
        });
        Scene add_patscene=new Scene(addlayout);
        stage.setScene(add_patscene);
    }

    void add_department(Stage stage,Scene scene) {
        Label depname=new Label("What is the department's name: ");
        TextField d_n=new TextField();
        Button add=new Button("Add");
        Button back=new Button("Back");
        
        HBox addinput=new HBox(10,depname,d_n);
        HBox backaddlayout=new HBox(20,back,add);
        VBox addlayout=new VBox(20,addinput,backaddlayout);
        add.setOnAction(eh->{
            if (!d_n.getText().isEmpty()){
                if(department.contains(d_n.getText()))
                {
            try{      
            File counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\departments.txt");
            RandomAccessFile raf = new RandomAccessFile(counter, "rw");
            raf.seek(raf.length());
            raf.writeBytes(d_n.getText() + "\n");
            raf.close();
            department.add(d_n.getText());
         showMsg("Department added successfully.",stage,null);
        }catch(Exception e){
            showAlert(e.getMessage());
        }
                }
        }else
            showAlert("Write the Department name you want to add");
        });
       Scene add_depscene=new Scene(addlayout);
       stage.setScene(add_depscene);
    }
    
     /////////////////////////////////////////////////DELETES

    void delete_doc(Stage stage,Scene scene) {
    list_doc(stage,scene);
        Label depchoose=new Label("Choose department: ");
        ObservableList<String> options = FXCollections.observableArrayList(department);
        ComboBox combobox=new ComboBox(options);
        Button next=new Button("Next");
        next.setOnAction(eh->{
    Label identry=new Label("Enter the Doctor's ID to delete: ");
    TextField identrytf=new TextField();
    int d_id = Integer.parseInt(identrytf.getText());
    boolean doctorFound = false;
    String doc_name="";
    if (Doctor.Doctors.containsKey(combobox.getSelectionModel().getSelectedItem().toString())) {
        List<Doctor> doctorsList = Doctor.Doctors.get(combobox.getSelectionModel().getSelectedItem().toString());
        for (Doctor doctor : doctorsList) {
            if (doctor.doctor_id== d_id) {
                doctorsList.remove(doctor);
                doc_name=doctor.doctor_name;
                doctorFound = true;
                break;
            }
        }
    }

    if (doctorFound) {
        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\doctors.txt", "rw")) {
            JavaApplication2.doc_c--;
            raf.setLength(0);
            raf.writeBytes(String.valueOf(JavaApplication2.doc_c) + "\n");
            for (String department :JavaApplication2.department) {
                if (Doctor.Doctors.containsKey(department)) {
                    for (Doctor doctor : Doctor.Doctors.get(department)) {
                        raf.writeBytes(doctor.doctor_id+ " " + doctor.doctor_name+ " " + doctor.specialization + "\n");
                    }
                }
            }
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
        
        File folderapp = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\appointments");
        try{
            if (folderapp.exists() && folderapp.isDirectory()) {
            File[] matchingFiles = folderapp.listFiles(file -> file.isFile() && file.getName().startsWith(Integer.toString(d_id)));
            if (matchingFiles != null && matchingFiles.length > 0) {
                for (File file : matchingFiles) {
                    File delmmsg= new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\Deleted\\"+file.getName());
                    try (FileWriter msg = new FileWriter(delmmsg, true)) {
                    msg.append("Doctor " + doc_name + " has been deleted.\n Appointment price refunded.");
                    }
             File folderpendapp = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\pending appointments\\"+d_id);
        if (folderpendapp.exists() && folderpendapp.isDirectory()) 
            folderpendapp.delete();
        
                    file.delete();
                }
            }
        }
        }catch(Exception e){
            showAlert(e.getMessage());
        }
        showMsg("Doctor " + doc_name + " has been successfully deleted.",stage,scene);
    } else {
        showAlert("Doctor with ID " + d_id + " not found in the selected department.");
    }
});
    VBox layout=new VBox(20,depchoose,combobox,next);
    Scene deleteScene = new Scene(layout, 400, 300);
    stage.setScene(deleteScene);
    }

    void delete_patient(Stage stage,Scene scene) {
    System.out.print("Enter the patient's ID to delete: ");
    int p_id = JavaApplication2.in.nextInt();

    if (patient.patients.containsKey(p_id)) {
        patient.patients.remove(p_id);

        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\patients.txt", "rw")) {
            raf.setLength(0);

            for (Map.Entry<Integer, ArrayList<patient>> entry : patient.patients.entrySet()) {
                for (patient patient : entry.getValue()) {
                    raf.writeBytes(patient.patient_id + " " + patient.patient_name +patient.credit_card.num + " " + patient.credit_card.balance+ "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating the file: " + e.getMessage());
        }

        System.out.println("Patient with ID " + p_id + " has been successfully deleted.");
    } else {
        System.out.println("Patient with ID " + p_id + " not found.");
    }
}
    
    void delete_department(Stage stage,Scene scene) {
    System.out.print("Enter the department's name to delete: ");
    String d_n = JavaApplication2.in.next();

    if (JavaApplication2.department.contains(d_n)) {
        JavaApplication2.department.remove(d_n);

        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\departments.txt", "rw")) {
            raf.setLength(0);

            for (String department : JavaApplication2.department) {
                raf.writeBytes(department + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error updating the file: " + e.getMessage());
        }

        System.out.println("Department " + d_n + " has been successfully deleted.");
    } else {
        System.out.println("Department " + d_n + " not found.");
    }
}
    
     /////////////////////////////////////////////////VIEWS

    void list_doc(Stage stage,Scene scene) {showMsg(Doctor.Doctors.toString(),stage,scene);}

    void list_patient(Stage stage,Scene scene) {showMsg(patient.patients.toString(),stage,scene);}
    
    void list_department(Stage stage,Scene scene) {showMsg(JavaApplication2.department.toString(),stage,scene);}
    
    /////////////////////////////////////////////////REPORTS
    
    void view_report(Stage stage,Scene scene){
        ArrayList<File> records=new ArrayList<>();
        try{
        File folder = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\Reports");
        if (folder.exists() && folder.isDirectory()) {
            for(File f:folder.listFiles(File::isFile)){
                if(!f.getName().equals("ReportsCounters.txt"))
                records.add(f);}
            }
        }catch(Exception e){
            System.out.println(e);
        }
    ObservableList<File> options = FXCollections.observableArrayList(records);
        ComboBox<File> combobox=new ComboBox(options);
    Button medicrecback=new Button("Back");
        medicrecback.setOnAction(eh->{stage.setScene(scene);});
        
        Button view=new Button("View");
        
        HBox medicrecbuttons=new HBox(20,medicrecback,view);
        medicrecbuttons.setAlignment(Pos.CENTER);
        
        VBox adminreclayout=new VBox(20,combobox,medicrecbuttons);
        adminreclayout.setAlignment(Pos.CENTER);
        
        Scene adminrecscene=new Scene(adminreclayout);
        
        stage.setScene(adminrecscene);
    
     view.setOnAction(eh->{
            if (combobox.getSelectionModel().getSelectedItem() == null) {
                showAlert("Please select a record to view.");
                return;
            }
            String streport="";
            try{
                Scanner input=new Scanner(combobox.getSelectionModel().getSelectedItem());
                while(input.hasNext()){
                streport+=input.next();
                }
            }catch(Exception e){
                showAlert(e.getMessage());
            }
            String name=combobox.getSelectionModel().getSelectedItem().getName();
            patient.showRecord(streport.toString(),name.substring(0,name.lastIndexOf(".")));
        });
    }
    
     void openAdminStage() {
        Stage AdminStage = new Stage();
        Button ViewRep = new Button("View Reports");
        Button DoctorWin = new Button("Doctor");
        Button PatientWin = new Button("Patient");
        Button DeparmentsWin = new Button("Department");
        VBox editLayout = new VBox(20,DoctorWin,PatientWin,DeparmentsWin,ViewRep);
        editLayout.setAlignment(Pos.CENTER);
        Scene editScene = new Scene(editLayout, 500, 300);
        ViewRep.setOnAction(e -> view_report(AdminStage,editScene));
        DoctorWin.setOnAction(eh -> openAdminDoc(AdminStage,editScene));
        PatientWin.setOnAction(eh -> openAdminPat(AdminStage,editScene));
        DeparmentsWin.setOnAction(eh -> openAdminDep(AdminStage,editScene));
        AdminStage.setTitle("Admin");
        AdminStage.setScene(editScene);
        AdminStage.show();
    }
    
    void openAdminDoc(Stage stage,Scene scene){
       Button Edit = new Button("Edit");
       Button Add = new Button("Add");
       Button Delete = new Button("Delete");
       Button List = new Button("List");
       Button Back = new Button("Back");
       Back.setOnAction(eh->{stage.setScene(scene);});
       
       VBox layout=new VBox(20,Edit,Add,Delete,List,Back);
       layout.setAlignment(Pos.CENTER);
       
       Scene tscene=new Scene(layout,400,500);
       
       Edit.setOnAction(eh->edit_doc(stage,tscene));
       Add.setOnAction(eh->add_doc(stage,tscene));
       Delete.setOnAction(eh->delete_doc(stage,tscene));
       List.setOnAction(eh->list_doc(stage,tscene));
       stage.setTitle("Doctor");
       stage.setScene(tscene);
   }
     void openAdminPat(Stage stage,Scene scene){
       Button Edit = new Button("Edit");
       Button Add = new Button("Add");
       Button Delete = new Button("Delete");
       Button List = new Button("List");
       Button Back = new Button("Back");
       Back.setOnAction(eh->{stage.setScene(scene);});
       
       VBox layout=new VBox(20,Edit,Add,Delete,List,Back);
       layout.setAlignment(Pos.CENTER);
       
       Scene tscene=new Scene(layout,400,500);
       
       Edit.setOnAction(eh->edit_patient(stage,tscene));
       Add.setOnAction(eh->add_patient(stage,tscene));
       Delete.setOnAction(eh->delete_patient(stage,tscene));
       List.setOnAction(eh->list_patient(stage,tscene));
       stage.setTitle("Patient");
       stage.setScene(tscene);
}
    void openAdminDep(Stage stage,Scene scene){
       Button Edit = new Button("Edit");
       Button Add = new Button("Add");
       Button Delete = new Button("Delete");
       Button List = new Button("List");
       Button Back = new Button("Back");
       Back.setOnAction(eh->{stage.setScene(scene);});
       
       VBox layout=new VBox(20,Edit,Add,Delete,List,Back);
       layout.setAlignment(Pos.CENTER);
       
       Scene tscene=new Scene(layout,400,500);
       
       Edit.setOnAction(eh->edit_department(stage,tscene));
       Add.setOnAction(eh->add_department(stage,tscene));
       Delete.setOnAction(eh->delete_department(stage,tscene));
       List.setOnAction(eh->list_department(stage,tscene));
       stage.setTitle("Department");    
       stage.setScene(tscene);
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
}
