package bots.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.SQLException;

import bots.controller.MainStart;

public class RegisterClass {
 
 @FXML
 private MainStart start;
 @FXML
 private TextField nametext;
 @FXML
 private TextField surnametext;
 @FXML
 private TextField emailtext;
 @FXML
 private TextField passwordtext;
 @FXML
 private TextField usernametext;
 
 public void setStart (MainStart startx)
 {
  start = startx;
 }
 
 @FXML
 private void handleCancel ()
 {
  start.changeStageLogin();
 }
 
 @FXML
 private void handleSubmit () throws SQLException
 {
  String Username = usernametext.getText();
  String Password = passwordtext.getText();
  String Name = nametext.getText();
  String Surname = surnametext.getText();
  String Email = emailtext.getText();
 
  if(start.mySql.UserQuery.ExistUser(Username, Email))
   start.mySql.UserQuery.RegisterUser(Name, Password, Email, Surname, Username);
  
  start.changeStageLogin();
 }

}