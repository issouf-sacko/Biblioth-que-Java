package application;
import java.util.List;

import application.DAO;
import model.Auteur;
import model.Livre;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DAO monDAO = new DAO();
		
		//Auteur unAuteur = new Auteur("malienne", "Seyba", "Sacko");
		
		//System.out.println(monDAO.ajouterLivre("tatat", "AA-141-GYY24", unAuteur, 1998, "Jacob"));
		
//		monDAO.ajouterCheminQRcode(1, null);
//		monDAO.ajouterCheminQRcode(2, null);
//		monDAO.ajouterCheminQRcode(6, null);
		
		//System.out.println(monLivre);
		
//		List<Livre> lstLivre = monDAO.getLivreQRCode();
//		
//		for (Livre livre : lstLivre) {
//			System.out.println(livre);
//		}
		
		System.out.println(monDAO.chercherLivre(1));
		
	}

}
