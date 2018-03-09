package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import mediatheque.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-François Brette 01/01/2018
	
//	Liste de tous les Documents
	private List<Document> docs;
	
	private Connection c;
	private Statement statement;
	
	
	static {
		try {
			Mediatheque.getInstance().setData(new MediathequeData());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}				
		try {			
//			Enregistrement d'un pilote			
			Class.forName("com.mysql.jdbc.Driver");			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private MediathequeData() throws SQLException {
		try{
			c = DriverManager.getConnection("jdbc:mysql://localhost:81/projet_appweb"
					,"root", "root");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> tousLesDocuments() {
		return docs;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		
		Utilisateur retour = null;
		try {
			PreparedStatement pstm = c.prepareStatement(
					"SELECT * FROM Utilisateur WHERE login=? AND mdp =?");
			pstm.setString(1,login);
			pstm.setString(2, password);
			ResultSet resultats = pstm.executeQuery();
			if(resultats.next())
			{
				retour = new Utilisateur(resultats.getString("Login"),resultats.getString("Mdp"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return retour;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		Document doc = null;
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM Document WHERE idDoc="+numDocument);
			if(res.next()){
				doc = new Document(res.getString("Type"), res.getString("NomDoc"), res.getString("Auteur"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
		PreparedStatement pstm = null;
		String titre = (String) args[0];
		String auteur = (String) args[1];
		try {			
			switch(type) {
			case 1: 
				pstm = c.prepareStatement(
						"INSERT INTO Document(Type, titre, Auteur) VALUES('Livre', ?,?)");
				break;
			case 2:
				pstm = c.prepareStatement(
						"INSERT INTO Document(Type, titre, Auteur) VALUES('CD', ?,?)");
				break;
			case 3:
				pstm = c.prepareStatement(
						"INSERT INTO Document(Type, titre, Auteur) VALUES('DVD', ?,?)");
				break;
			}
			pstm.setString(1, titre);
			pstm.setString(2, auteur);
			pstm.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
