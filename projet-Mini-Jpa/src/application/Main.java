package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import vue.Controleur;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Main  extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../vue/interfaceFx.fxml"));

			GridPane root = (GridPane) loader.load();
			Scene scene = new Scene(root);
			
			
			MainAuteur mainauteur = new MainAuteur (primaryStage) ;  
			Controleur controleur = loader.getController();  
			controleur.setMainAuteur(mainauteur); 
			
			MainQRCode mainQRCode = new MainQRCode (primaryStage) ;  
			Controleur controleur2 = loader.getController();  
			controleur2.setMainQRcode(mainQRCode); 
			
			
			primaryStage.setTitle("Bibliotheque");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		launch(args);
	}

}
