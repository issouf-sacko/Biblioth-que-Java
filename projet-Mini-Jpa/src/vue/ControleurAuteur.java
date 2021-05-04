package vue;

import application.DAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import model.Auteur;

public class ControleurAuteur {

	DAO monDao = new DAO();
	Controleur ctrlPrincipale = new Controleur();

	public ControleurAuteur() {

	}

	@FXML
	private TextField txtNom;
	@FXML
	private TextField txtPrenom;
	@FXML
	private TextField txtNationalite;

	@FXML
	private Button ajouter;
	@FXML
	private Button annuler;

	@FXML
	private void initialize() {

		txtNom.setText("");
		txtPrenom.setText("");
		txtNationalite.setText("");
	}

	@FXML
	private void ajouterAuteur() {
		String nom = txtNom.getText();
		String Prenom = txtPrenom.getText();

		String nationalite = txtNationalite.getText();

		try {
			if (pasErreur()) {
				
				Auteur unAuteur = new Auteur(nom, Prenom, nationalite);
				monDao.ajouterAuteur(unAuteur);
				txtNom.setText("");
				txtPrenom.setText("");
				txtNationalite.setText("");
				annulerAjout();
				ctrlPrincipale.initialize();
				
				
			}
		} catch (Exception e) {
			
		}
	}

	private boolean pasErreur() {

		String nom = txtNom.getText();
		String isbnL = txtPrenom.getText();
		String editeur = txtNationalite.getText();

		if (txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtNationalite.getText().isEmpty()
				|| editeur.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);

			alert.setTitle("Informations incomplètes");
			alert.setHeaderText("Compléter les champs indiqués");
			if (txtNom.getText().isEmpty())

				txtNom.setPromptText("à compléter");

			if (txtPrenom.getText().isEmpty())
				txtPrenom.setPromptText("à compléter");

			if (txtNationalite.getText().isEmpty())
				txtNationalite.setPromptText("à compléter");

			alert.showAndWait();
			return false;

		} else
			return true;
	}
	
	
	
	
	public void annulerAjout() {
		
		 // get a handle to the stage
	    Stage stage = (Stage) annuler.getScene().getWindow();
	    // do what you have to do
	    stage.close();
		
	}
	
	

}
