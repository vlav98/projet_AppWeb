// Jean-Fran�ois Brette 01/01/2018

package mediatheque;

public interface Document {
// Jean-Fran�ois Brette 01/01/2018
	
	void emprunter(Utilisateur a) throws EmpruntException;
	void retour();
	Object[] affiche();
	
}
