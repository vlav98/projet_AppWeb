package persistantdata;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class Livre implements Document {

	private Utilisateur emprunteur;
	private String titre;
	private String auteur;
	
	public Livre(){
	}
	
	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		// TODO Auto-generated method stub
		this.emprunteur = a;
	}

	@Override
	public void retour() {
		// TODO Auto-generated method stub
		this.emprunteur = null;
	}

	@Override
	public Object[] affiche() {
		// TODO Auto-generated method stub
		Object[] donnees = {this.titre,this.emprunteur.getLogin()};
		return donnees;
	}

}
