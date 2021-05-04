package vue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import application.DAO;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Auteur;
import model.Livre;

public class ControleurQRCode {

	@FXML
	private Label lblAuteur;

	@FXML
	private Label lblLivre;

	@FXML
	private TextArea txtArLivre;

	@FXML
	private Label message;

	@FXML
	private ImageView imageQRCode;

	DAO monDAO = new DAO();
	Controleur mainControleur = new Controleur();
	Livre livreQRCode;
	Auteur auteurLivre;

	@FXML
	public void getLivre() {
		try {

			livreQRCode = mainControleur.getLivreAGenerer(); // Recuperation du livre Selectionné

			lblLivre.setText("");
			auteurLivre = livreQRCode.getAuteur();
			lblAuteur.setText("");
			lblAuteur.setText(auteurLivre.toString());
			lblLivre.setText("");
			lblLivre.setText(livreQRCode.getTitre());
			mainControleur.setLivreAGenerer();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FXML
	public void initialize() {

		getLivre();

		afficherImage();
	}

	private static void writeImage(String outputFileName, String imageFormat, BitMatrix bitMatrix) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFileName));
			MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static BitMatrix generateMatrix(String data, int size) {
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return bitMatrix;
	}

	public void genererQRCode() {
		try {

			String data = lblAuteur.getText() + ", " + lblLivre.getText() + ", " + txtArLivre.getText();
			int size = 400;

			BitMatrix bitMatrix = generateMatrix(data, size);
			String imageFormat = "png";
			String nomLivre = lblLivre.getText().replace(" ", "-");

			String nomQRCode = nomLivre + "-" + auteurLivre.getNom().trim();

			String outputFileName = "C:/Users/seyba/Desktop/imagesMiniProjet/" + nomQRCode + "." + imageFormat;

			writeImage(outputFileName, imageFormat, bitMatrix);

			afficherImage();

			monDAO.ajouterCheminQRcode(livreQRCode.getNum(), outputFileName);

			afficherImage();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void afficherImage() {

		try {
			int numLivre = livreQRCode.getNum();
			Livre li = monDAO.chercherLivre(numLivre);
			
			if (li.getQr_Code().isEmpty()) {
				message.setText("Pas de QRCODE pour cette œuvre");
			} else {

				InputStream input = new FileInputStream(li.getQr_Code());

				Image image = new Image(input);
				imageQRCode.setImage(image);
				message.setText("");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@FXML
	public void imprimerLivre() {

		if (message.getText().isEmpty()) {

			PrinterJob job = PrinterJob.createPrinterJob();
			if (job != null) {
				Printer printer = Printer.getDefaultPrinter();
				PageLayout pageLayout = job.getJobSettings().getPageLayout();
				boolean success = job.printPage(imageQRCode);
				if (success) {
					job.endJob();
				}
			}
		}

	}

}