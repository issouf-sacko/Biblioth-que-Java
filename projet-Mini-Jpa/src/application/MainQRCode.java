package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vue.Controleur;

public class MainQRCode {

	private Stage primaryStage; // cet attribut = fen�tre principale

	public MainQRCode(Stage stage) {
		primaryStage = stage;
	}

	public void montrerLaVue() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controleur.class.getResource("../vue/VueQRcode.fxml"));
			GridPane page = (GridPane) loader.load();
			// Cr�ation du formulaire comme fen�tre modale.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("G�n�rer QRCODE");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			// La m�thode suivante montre le formulaire puis attend que l'utilisateur le
			// ferme. // Fermeture effectu�e dans le contr�leur du formulaire lorsque
			// l'utilisateur clique // sur un des 2 boutons.
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
