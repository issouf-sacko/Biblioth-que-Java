package application;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.text.StyledEditorKit.BoldAction;

import model.Auteur;
import model.Livre;

public class DAO {

	private EntityManager em;

	public DAO() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("projet-Mini-Jpa");
		em = factory.createEntityManager();
	}

	public void ajouterAuteur(Auteur unAuteur) {
		em.getTransaction().begin();
		em.persist(unAuteur);
		em.getTransaction().commit();
	}

	public List<Auteur> voirlesAuteur() {
		TypedQuery<Auteur> query = em.createQuery("SELECT g FROM Auteur g", Auteur.class);
		List<Auteur> liste = query.getResultList();
		return liste;
	}

	public Auteur getAuteurByNum(int numAuteur) {
		Auteur unAt = null;
		try {
			em.getTransaction().begin();
			unAt = em.find(Auteur.class, numAuteur);
			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return unAt;

	}

	public void supprimer(Auteur unAuteur) {
		try {
			em.getTransaction().begin();
			em.remove(unAuteur);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean veriiferExistAuteur(Auteur unAuteur) {
		List<Auteur> unelisteAuteur = voirlesAuteur();

		for (Auteur auteur : unelisteAuteur) {
			if (auteur == unAuteur) {
				return true;
			}
		}
		return false;
	}

	// Livre

	public List<Livre> voirlesLivre() {
		TypedQuery<Livre> query2 = em.createQuery("SELECT l FROM Livre l", Livre.class);
		List<Livre> listeLivre = query2.getResultList();
		return listeLivre;
	}

	public List<Livre> getLivreByAuteur(Auteur unAteur) {
		List<Livre> listeLivre = new ArrayList<Livre>();
		try {

			String req = "select l from Livre l where l.auteur=?1";
			Query q = em.createQuery(req);
			q.setParameter(1, unAteur);
			listeLivre = q.getResultList();

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listeLivre;
	}
	
	//Afiicher les livres qui ont un QRCode
	
	public List<Livre> getLivreQRCode() {
		List<Livre> listeLivre = new ArrayList<Livre>();
		try {

			String req = "select l from Livre l where l.qr_Code!=?1";
			Query q = em.createQuery(req);
			q.setParameter(1, "IS NOT NULL");
			listeLivre = q.getResultList();

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listeLivre;
	}
	
	public boolean AjouterLivre(String titre, String isbn, Auteur auteurVe, short annee, String editeur) {

		if (isbn.length() == 13 && auteurVe != null) {
			Livre unLivre = new Livre(titre, isbn, auteurVe, annee, editeur);
			em.getTransaction().begin();
			em.persist(unLivre);
			em.getTransaction().commit();
			return true;
		}

		return false;
	}

	public void supprimer(Livre unLivre) {

		try {

			em.getTransaction().begin();
			em.remove(unLivre);
			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public boolean supprimerLivresByAuteur(Auteur unAuteur) {

		boolean result = false;

		try {
			// Suprimer les livre d'un auteur avec une requête JPL
			String requete = "delete from Livre l where l.numAuteur=:n ";

			Query q4 = em.createQuery(requete);
			q4.setParameter("n", unAuteur.getNum());

			// Il faudrait l'executer dans une trasaction

			em.getTransaction().begin();
			int n = q4.executeUpdate();
			em.getTransaction().commit();

			if (n == 1)
				result = true;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}
	
	public Livre chercherLivre(int numLivre) {
		
		Livre unLivre = em.find(Livre.class, numLivre);
		em.refresh(unLivre);
		return unLivre;
	}
	
	
	public void ajouterCheminQRcode(int numLivre, String qrChemin) {
		
		
		Livre unLivre = null;
		try {

			em.getTransaction().begin();
			unLivre = em.find(Livre.class, numLivre);
			unLivre.setQr_Code(qrChemin);
			em.getTransaction().commit();
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}


}
