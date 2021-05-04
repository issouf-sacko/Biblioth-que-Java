package vue;

import java.util.List;
import java.util.Optional;

import javax.security.auth.callback.ConfirmationCallback;

import application.DAO;
import application.MainAuteur;
import application.MainQRCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Auteur;
import model.Livre;
import application.MainAuteur;

public class Controleur {

	private DAO monDAO;

	public Controleur() {
		monDAO = new DAO(); // le contr�leur cr�e l'objet dao

	}

	@FXML
	private ComboBox<Auteur> cbAuteur;

	@FXML
	private TextField txtTitre;
	@FXML
	private TextField txtEditeur;
	@FXML
	private TextField txtISBN;
	@FXML
	private TextField txtAnnee;

	@FXML
	private ListView<Auteur> lesAuteur;

	private ObservableList<Auteur> listeAuteur = FXCollections.observableArrayList();

	@FXML
	private ListView<Livre> lesLivres;

	private ObservableList<Livre> listeLivre = FXCollections.observableArrayList();

	@FXML
	public void initialize() {

		afficherTousLesAuteurs();
		lesAuteur.setItems(listeAuteur);

		lesLivres.setItems(listeLivre);

		// Remplir combobx
		cbAuteur.setItems(listeAuteur);

		txtTitre.clear();
		txtISBN.clear();
		txtEditeur.clear();
		txtAnnee.clear();

	}

	private MainAuteur mainauteur;

	@FXML
	private void ajouterAuteur() {

		mainauteur.montrerLaVue();

	}

	// attribut � ajouter
	public void setMainAuteur(MainAuteur mainauteur) {
		// //accesseur
		this.mainauteur = mainauteur;
	}

	@FXML
	private void afficherTousLesAuteurs() {
		listeAuteur.clear();
		List<Auteur> lesAuteurs = monDAO.voirlesAuteur();
		for (Auteur i : lesAuteurs)
			listeAuteur.add(i);

	}

	// -----------------------------------------Livre------------------------------------------

	
	// afficher les livres d'un auteur
	@FXML
	private void afficherLiveUnAuteur() {

		List<Livre> mesLivres = null;
		Auteur monAuteur = (Auteur) lesAuteur.getSelectionModel().getSelectedItem();

		listeLivre.clear();
		mesLivres = monDAO.getLivreByAuteur(monAuteur);
		for (Livre unLivre : mesLivres)
			listeLivre.add(unLivre);

	}

	// Add Book
	public void ajouterLivreC() {

		String titreL = txtTitre.getText();
		String isbnL = txtISBN.getText();
		Auteur auteurL = cbAuteur.getSelectionModel().getSelectedItem();
		short annee = Short.parseShort(txtAnnee.getText());
		String editeur = txtEditeur.getText();

		try {

			if (pasErreur()) {

				if (isbnL.length() == 13) {
					monDAO.AjouterLivre(titreL, isbnL, auteurL, annee, editeur);
					initialize();
				} else {
					Alert alert = new Alert(AlertType.WARNING);

					alert.setTitle("Erreur ISBN");
					alert.setHeaderText("L'ISBN saisi est incorrecte?");

					alert.showAndWait();
				}

			}

		} catch (NumberFormatException e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText(e.getMessage());
		}
	}

	private boolean pasErreur() {

		String titreL = txtTitre.getText();
		String isbnL = txtISBN.getText();
		String editeur = txtEditeur.getText();

		if (titreL.isEmpty() || isbnL.isEmpty() || txtAnnee.getText().isEmpty() || editeur.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);

			alert.setTitle("Informations incompl�tes");
			alert.setHeaderText("Compl�ter les champs indiqu�s");
			if (titreL.isEmpty())

				txtTitre.setPromptText("� compl�ter");

			if (isbnL.isEmpty())
				txtISBN.setPromptText("� compl�ter");

			if (txtAnnee.getText().isEmpty())
				txtAnnee.setPromptText("� compl�ter");

			if (editeur.isEmpty())
				txtEditeur.setPromptText("� compl�ter");

			alert.showAndWait();
			return false;

		} else
			return true;
	}

	public void supprimerLivre() {

		try {
			
			Livre livreDel = lesLivres.getSelectionModel().getSelectedItem();
			if (livreDel != null) {
				if (avertirSuppression() == true)
					monDAO.supprimer(livreDel);
				afficherLiveUnAuteur();

			} else {

				Alert alert = new Alert(AlertType.WARNING);

				alert.setTitle("Erreur de suppression");
				alert.setHeaderText("Veulliez selectionner un livre ?");

				alert.showAndWait();

			}
			
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText(e.getMessage());
		}

		initialize();
	}

	
	public boolean avertirSuppression() {

		boolean confirm = false;
		try {

			Alert alert = new Alert(AlertType.CONFIRMATION);

			alert.setTitle("Confirmation suppression livre");
			alert.setHeaderText("�tes vous s�r de vouloir supprimer ced livre ?");

			// option != null.
			Optional<ButtonType> option = alert.showAndWait();

			if (option.get() == ButtonType.OK) {
				confirm = true;
				return confirm;
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText(e.getMessage());
		}

		return confirm;
	}

	// -----------------------------------------QRCODE------------------------------------------

	private MainQRCode mainQrCode;
	private static Livre livreAGenerer;  // Livre pour le QRCode 

	@FXML
	private void genererQrCode() {

		if (livreAGenerer != null) {
			
			mainQrCode.montrerLaVue();

		} else {

			Alert alert = new Alert(AlertType.WARNING);

			alert.setTitle("Erreur");
			alert.setHeaderText("Veulliez selectionner un livre ?");

			alert.showAndWait();

		}

		
	}

	// attribut � ajouter
	public void setMainQRcode(MainQRCode mainQrCode) {
		// //accesseur
		this.mainQrCode = mainQrCode;

	}

	/**
	 * Permet de retourner le livre selectionner dans la liste view lesLivres pour
	 * que le QRCode soit g�n�rer
	 */
	public static Livre getLivreAGenerer() {
		Livre  livreSeleccted= livreAGenerer; 
		livreAGenerer =null;
		return livreSeleccted;
	}

	// Recup�re le livre selectionner
	@FXML
	private void SelectLivreQrCode() {
		this.livreAGenerer = lesLivres.getSelectionModel().getSelectedItem();
		
	}

	public static void setLivreAGenerer() {
		Controleur.livreAGenerer = null;
	}
	
	
	

}
