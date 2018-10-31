package bots.Model;

import bots.Controller.AdminClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;

public class PageModel {

	public Integer PageID;
	public Image PageImage;
	public Integer PageNumber;
	public String Transcribe;
	public String LastUserModify;
	public String WorkTrsc;
	public String Opera;
	public Integer IdOpera;
	
	public Label xopera = new Label(), xnum = new Label();
	public Button xview = new Button();
	
	@FXML
	public AnchorPane Container;
	
	
	public PageModel (Integer id, Image source, Integer num, String trsc, String lastu, String work)
	{
		PageID = id;
		PageImage = source;
		PageNumber = num;
		Transcribe = trsc;
		LastUserModify = lastu;
		WorkTrsc = work;
	}
	
	public PageModel (Integer id, Integer num, String opera, Integer idopera, String trsc, String lastu, String work, AdminClass obj, Integer x, AnchorPane container)
	{
		PageID = id;
		PageNumber = num;
		Transcribe = trsc;
		LastUserModify = lastu;
		WorkTrsc = work;
		Opera = opera;
		IdOpera = idopera;
		Container = container;
		SetShow (xopera, 0, x * 30, 100, Opera);
		SetShow (xnum, 100, x * 30, 30, PageNumber.toString());
		ShowButton (xview, 130, x * 30, 100, "Revision");
		xview.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e)
	    	{
				obj.start.changeStageOpera(IdOpera, 2, PageNumber);
	    	}
		});
	}
	
	public Image GetImage ()
	{
		return PageImage;
	}
	
	public void SetShow (Label obj, Integer x, Integer y, Integer width, String text)
	{
		obj = new Label();
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		Container.getChildren().add(obj);
	}
	
	public void ShowButton (Button obj, Integer x, Integer y, Integer width, String text)
	{
		obj.setText(text);
		obj.setLayoutX(x);
		obj.setLayoutY(y);
		obj.setPrefWidth(width);
		Container.getChildren().add(obj);
	}
}
