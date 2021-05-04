package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vue.Controleur;

public class MainAuteur {
	private Stage primaryStage; // cet attribut = fenêtre principale

	public MainAuteur(Stage stage) {
		primaryStage = stage;
	}

	public void montrerLaVue() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controleur.class.getResource("../vue/VueAuteur.fxml"));
			GridPane page = (GridPane) loader.load();
			// Création du formulaire comme fenêtre modale.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Ajout d'un auteur");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			// La méthode suivante montre le formulaire puis attend que l'utilisateur le
			// ferme. // Fermeture effectuée dans le contrôleur du formulaire lorsque
			// l'utilisateur clique // sur un des 2 boutons.
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	

	}

	
}
