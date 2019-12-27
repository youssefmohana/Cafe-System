
package clinet;

import java.io.IOException;
import java.net.Socket;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import  java.util.*;
import java.io.*;
import javafx.geometry.Insets;




/**
 *
 * @author HP
 */
public class Guicafe {
    public  static Stage  primaryStage;
      public static Scene scene,sceneC,ss ;
      public   static String host="localhost";
      public  static int port=8200;
      public static  Socket s;
      public static DataOutputStream ous;
      public static DataInputStream ins;
      public static InputStream in;
      public static OutputStream out;
     public static  Scene gui(Stage stage){
       
            primaryStage=stage;
            
            
            try {
                s = new Socket(host,port);
            } catch (IOException ex) {
                Logger.getLogger(Guicafe.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Button b1=new Button ("Customer");
            
            Button b2=new Button ("Manager");
            
            Button b3=new Button ("Sales Person");
            BorderPane p =new BorderPane();
        
        try {
            
            in = s.getInputStream();
            out =s.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(Guicafe.class.getName()).log(Level.SEVERE, null, ex);
        }
            
         
           ins=new DataInputStream(in);
            ous =new DataOutputStream(out);
            
            b1.setOnAction((ActionEvent e)->{
                Button b[]= {new Button("log in"),new Button("register"),new Button("return")};
                HBox v1=new HBox();
                v1.setSpacing(10);
                v1.getChildren().addAll(b[0],b[1],b[2]);
                
                
                b[0].setOnAction((ActionEvent e1)-> {
                    
                     VBox v5=new VBox();
                    
                    TextField text1 =new TextField();
                    text1.setPromptText("username");
                    PasswordField text2= new PasswordField();
                    text2.setPromptText("password");
                    
                    Button b4 = new Button ("log in");
                    b4.setOnAction((ActionEvent e6)->{try {
                        System.out.println("before");
          
                        ous.writeInt(1);
                        System.out.println("after");
                    ous.writeUTF(text1.getText());
                    ous.writeUTF(text2.getText());
                    boolean flag=ins.readBoolean();
                    if(flag){
                        String name =ins.readUTF();
                         String phone_number =ins.readUTF();
                          String Email =ins.readUTF();
                          VBox hh=new VBox ();
                          
                          hh.getChildren().addAll(new Label(name),new Label(phone_number),new Label(Email));
                          hh.setSpacing(10);
                          Scene sss=new Scene(hh,200,200);
                          primaryStage.setScene(sss);
                    }
                    
                    
                    } catch (IOException ex) {
                        Logger.getLogger(Guicafe.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    });
                    Button b5 =new Button("Cancel");
                    b5.setOnAction((ActionEvent e4)->primaryStage.setScene(ss));
                    
                   
                    v5.setSpacing(10);
                    v5.getChildren().addAll(text1,text2,b4,b5);
                    
                    
                    BorderPane f  = new BorderPane();
                    
                    f.setCenter(v5);
                    sceneC =new Scene(f,300,300);
                    primaryStage.setScene(sceneC);
                });
                b[1].setOnAction((ActionEvent k)->{
                    
                    Label l1 =new Label("Name");
                    Label l2 =new Label("Phone Number");
                    Label l3 =new Label("Email");
                    Label l4 =new Label("Address");
                    Label l5 =new Label("User Name");
                    Label l6 =new Label("Password");
                    Label l7 =new Label("Shopping Card");
                    TextField t1=new TextField();
                    TextField t2=new TextField();
                    TextField t3=new TextField();
                    TextField t4=new TextField();
                    TextField t5=new TextField();
                    TextField t6=new TextField();
                    TextField t7=new TextField();
                    Button b11=new Button("register");
                    Button  b22=new Button("Cancel");
                    b22.setOnAction((ActionEvent l)->primaryStage.setScene(ss));
                    VBox v8 =new VBox();
                    v8.setSpacing(10);
                    v8.getChildren().addAll(l1,t1,l2,t2,l3,t3,l4,t4,l5,t5,l6,t6,l7,t7,b11,b22);
                    primaryStage.setScene(new Scene(v8,250,250));
                    
                    
                    
                });
                
                b[2].setOnAction((ActionEvent l)->primaryStage.setScene(scene) );
                
                
                
                ss= new Scene(v1,200,200);
                primaryStage.setScene(ss);
                
            }
            );
            
            
            
            
            
            HBox v1= new HBox();
            
            v1.getChildren().addAll(b1,b2,b3);
            p.setBottom(v1);
            
            
            scene = new Scene(p, 300, 300);
            
            
            
            
            return scene;
            
            
            
   
         
         
 
     }
}
