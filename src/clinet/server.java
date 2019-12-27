
package clinet;

/**
 *
 * @author HP
 */

import java.sql.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;
//import java.

public class server extends Thread {


private static  Statement stmt;
private  static ServerSocket  serversocket;
public   static int port=8200; 
 static Socket socket;
public static ResultSet rset ;
 public static  DataInputStream din;
 public static DataOutputStream dout;
 public static void  Customer_login(Socket socket) throws IOException, SQLException{
      
     String name= din.readUTF();
       String pass=din.readUTF();
       System.out.print("sadasda");
       boolean b=false;
        String query = "select * from customer"
        +"where user_name= '"+name+" ' and cpasward= ' "+pass+" ' ";
         rset = stmt.executeQuery(query);
       if(rset.next()){
           String name1 =rset.getString(2);
           String phone_number =rset.getString(3);
           String Email=rset.getString(4);
           dout.writeUTF(name1);
            dout.writeUTF(phone_number);
             dout.writeUTF(Email);
           b=true;
           
       }
       dout.writeBoolean(b);
   }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

    initializeDB();
      listenForConnection();
     
      din=new DataInputStream(socket.getInputStream());
      dout=new DataOutputStream(socket.getOutputStream());
     
      int n=din.readInt();
      if (n==1){
          System.out.println("n=1");
          Customer_login(socket);
          System.out.println("after call");
      }
      

    
    }
   
 private  static void listenForConnection(){
        try{
            serversocket=new ServerSocket(port);
        }catch(IOException e){
            e.getMessage();
        }
        System.out.println("Server start Listening for connection requests");
        while(true){
            try{
                 socket=serversocket.accept();
                 mainClient m=new mainClient();
                 
                System.out.println("connection accepted");
//               break;
            }catch(IOException e){
                e.getMessage();
            }
        }
       
    }

private static void initializeDB() {
    try {
      // Load the JDBC driver
      Class.forName("com.mysql.cj.jdbc.Driver");

      System.out.println("Driver loaded");

      // Establish a connection
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cafa", "root", "yousef65hmed#");
 
      System.out.println("Database connected");

      // Create a statement
      stmt = connection.createStatement();
    }
    catch (Exception ex) {
      ex.getMessage();
    }
  }


}
    
