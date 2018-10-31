package bots.GUIController;

import java.io.IOException;
import java.sql.SQLException;

import bots.Controller.MainStart;
import bots.Controller.ViewerClass;
import bots.Model.OperaModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GuiViewer {

	@FXML
	private TextField PageNumber;
	@FXML
	private ImageView Page;
	@FXML
	private TextArea Transcribe;
	@FXML
	private Label Title;
	@FXML 
	private Button back;
	@FXML
	private Button download;
	@FXML
	private Button trsc;
	@FXML
	private Button cancel;
	@FXML
	private Button apply;
	@FXML
	private AnchorPane Container;
	
	
	@FXML
	private Button Rev_a = new Button();
	@FXML
	private Button Rev_d = new Button();
	
	@FXML
	private Button button1, button2, button3, button4;
	@FXML
	private TextField button5;
	
	private ViewerClass controller;
	
	public void setStart(OperaModel opera, Integer method, Integer page) throws SQLException
	{
		try {
			controller = new ViewerClass(opera, method, page);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Transcribe.setEditable(false);
		cancel.setVisible(false);
		apply.setVisible(false);
		PageNumber.setEditable(false);
		if (method == 2)
			PageNumber.setText(page.toString());
		else
			PageNumber.setText("1");
		Title.setText(controller.LoadOpera.Title);
		Page.setImage(controller.LoadOpera.GetPage(page).PageImage);
		if (method == 2)
			Transcribe.setText(controller.LoadOpera.GetPage(page).WorkTrsc);
		else
			Transcribe.setText(controller.LoadOpera.GetPage(page).Transcribe);
		if (method == 0)
			download.setVisible(MainStart.ConnectedUser.Download.equals("1"));
		else
			download.setVisible(false);
		if (method == 0)
			trsc.setVisible(controller.CheckPermission());
		else
			trsc.setVisible(false);
		
		ControlButton(method);
	}
	
	public void ControlButton(Integer Method)
	{
		if (Method == 1)
		{
			Rev_a.setText("Accept");
			Rev_a.setLayoutX(400);
			Rev_a.setLayoutY(600);
			Rev_a.setPrefWidth(80);
			Rev_a.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
					controller.AcceptOperaRevision();
					MainStart.GUI.changeStageAdmin();
		    	}
			});
			Container.getChildren().add(Rev_a);
			Rev_d.setText("Refuse");
			Rev_d.setLayoutX(500);
			Rev_d.setLayoutY(600);
			Rev_d.setPrefWidth(80);
			Rev_d.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
					controller.DenyOperaRevision();
					MainStart.GUI.changeStageAdmin();
		    	}
			});
			Container.getChildren().add(Rev_d);
		}
		
		if (Method == 2)
		{
			Rev_a.setText("Accept");
			Rev_a.setLayoutX(400);
			Rev_a.setLayoutY(600);
			Rev_a.setPrefWidth(80);
			Rev_a.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
					controller.AcceptTrscRevision();
					MainStart.GUI.changeStageAdmin();
		    	}
			});
			Container.getChildren().add(Rev_a);
			Rev_d.setText("Refuse");
			Rev_d.setLayoutX(500);
			Rev_d.setLayoutY(600);
			Rev_d.setPrefWidth(80);
			Rev_d.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e)
		    	{
					controller.DenyTrscRevision();
					MainStart.GUI.changeStageAdmin();
		    	}
			});
			Container.getChildren().add(Rev_d);
		}
	}
	
	@FXML
	public void handleBackSearch ()
	{
		if (controller.Method == 0)
			MainStart.GUI.changeStageSearch();
		else
			MainStart.GUI.changeStageAdmin();
	}
	
	@FXML
	public void HandleForward () throws SQLException
	{
		controller.Forward();
		Page.setImage(controller.LoadOpera.GetPage(controller.PageNum).PageImage);
		Transcribe.setText(controller.LoadOpera.GetPage(controller.PageNum).Transcribe);
		PageNumber.setText(controller.PageNum.toString());
		if (controller.Method == 0)
			trsc.setVisible(controller.CheckPermission());
		else
			trsc.setVisible(false);
	}
	
	@FXML
	public void HandleBackward () throws SQLException
	{
		controller.Backward();
		Page.setImage(controller.LoadOpera.GetPage(controller.PageNum).PageImage);
		Transcribe.setText(controller.LoadOpera.GetPage(controller.PageNum).Transcribe);
		PageNumber.setText(controller.PageNum.toString());
		if (controller.Method == 0)
			trsc.setVisible(controller.CheckPermission());
		else
			trsc.setVisible(false);
	}
	
	@FXML
	public void HandleStart () throws SQLException
	{
		controller.Start();
		Page.setImage(controller.LoadOpera.GetPage(controller.PageNum).PageImage);
		Transcribe.setText(controller.LoadOpera.GetPage(controller.PageNum).Transcribe);
		PageNumber.setText(controller.PageNum.toString());
		if (controller.Method == 0)
			trsc.setVisible(controller.CheckPermission());
		else
			trsc.setVisible(false);
	}
	
	@FXML
	public void HandleEnd () throws SQLException
	{
		controller.End();
		Page.setImage(controller.LoadOpera.GetPage(controller.PageNum).PageImage);
		Transcribe.setText(controller.LoadOpera.GetPage(controller.PageNum).Transcribe);
		PageNumber.setText(controller.PageNum.toString());
		if (controller.Method == 0)
			trsc.setVisible(controller.CheckPermission());
		else
			trsc.setVisible(false);
	}
	
	
	@FXML
	public void handleTrsc2 () throws SQLException
	{
		if (MainStart.mySql.PageQuery.GetLock(controller.LoadOpera.GetPage(controller.PageNum).PageID))
		{
			MainStart.mySql.PageQuery.SetLock(controller.LoadOpera.GetPage(controller.PageNum).PageID, "1");
			Transcribe.setEditable(true);
			cancel.setVisible(true);
			apply.setVisible(true);
			ShowCursor(false);
		}
	}
	
	@FXML
	public void handleApply2 () throws SQLException
	{
		MainStart.mySql.PageQuery.SetWork(MainStart.ConnectedUser.ID, Transcribe.getText(), controller.LoadOpera.GetPage(controller.PageNum).PageID);
		cancel.setVisible(false);
		apply.setVisible(false);
		Transcribe.setEditable(false);
		ShowCursor(true);
	}
	
	@FXML
	public void handleCancel2 () throws SQLException
	{
		MainStart.mySql.PageQuery.SetLock(controller.LoadOpera.GetPage(controller.PageNum).PageID, "0");
		cancel.setVisible(false);
		apply.setVisible(false);
		Transcribe.setEditable(false);
		ShowCursor(true);
	}
	
	@FXML
	public void handleDownload () throws IOException
	{
		controller.Download();
	}
	
	public void ShowCursor (boolean value)
	{
		button1.setVisible(value);
		button2.setVisible(value);
		button3.setVisible(value);
		button4.setVisible(value);
		PageNumber.setVisible(value);
		trsc.setVisible(value);
	}	
}