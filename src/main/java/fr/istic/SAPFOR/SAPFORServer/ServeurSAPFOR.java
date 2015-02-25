package fr.istic.SAPFOR.SAPFORServer;

import java.util.HashMap;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import outils.GestionCreationObjets;
import builderPompier.PompierConcret;
import builderStage.Stage;
import builderUV.UV;
import ecritureFichier.EcrireFichier;

@Path("/serveur")
public class ServeurSAPFOR {

	
	private static HashMap<Integer,PompierConcret> numConnection=new HashMap<Integer,PompierConcret>();//map contenant l'objet pompier li� � son num�ro de session
	
	
	private PompierConcret getPompier(int id) {
		return GestionCreationObjets.creerPompier(id);
	}
	
	
	private void setPompier(PompierConcret pompier) {
				
	}

	
	public UV getUV(String UV) {
		//permet la r�cup�ration des infos de l'UV par le client
		return GestionCreationObjets.creerUV(UV);
	}
	
	
	public Stage getStage(String stage) {
		//permet de r�cup�rer les infos de chaqsue session par le client
		return GestionCreationObjets.creerStage(stage);
	}
	
	

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Agent login(int idPompier, String mdp) {
		// r�cup�re le login et mdp de l'agent
		//v�rifie le mdp par rapport � celui indiqu� dans le fichier de l'agent n� idPompier
		//si num�ro concordent => cr�ation d'un num�ro de session al�atoire (apr�s v�rification de sa disponibilit�)
		
		PompierConcret entrant=getPompier(idPompier);//cr�ation du pompier � partir des donn�es stock�esd'apr�s son identifiannt 
		
		Random randomGenerator = new Random();//cr�ation d'un g�n�rateur de nombre al�atoirs
		
		int randomInt=0;
		
		if(entrant.getMdp().equals(mdp)){
			while(numConnection.containsKey(randomInt)){//v�rification si randomInt existe dans le hasmap si oui, g�n�ration d'un nouveau nombre al�atoire
				randomInt=randomGenerator.nextInt(998);
			}
			numConnection.put(randomInt,entrant);//stockage de la valeur de session + du pompier associ�
			
			//trouver un moyen d'envoyer objet pompier au client 
			
			return randomInt;
		}
		else{return 999;}//valeur 999 si mdp faux ou login faux (idPompier n'existe pas)
		
	}

	
	public String deconnexion(int session){
		//effectue la deconnexion de l'agent 
		//met � jour les fichiers utilis�s
		//d�truit le num�ro de session et l'objet Pompier cr�e(apr�s avoir �t� sauvegard�)
		
		EcrireFichier.ecrirePompier(numConnection.get(session));//�criture/�crasement du fichier avec les infos contenues dans l'objet pompier 
		
		numConnection.remove(session);//suppression de l'entr�e li� � ce num�ro de session
		
		return "OK";
		
	}
	
	public String candidater(int session, String nomSession) {
		//ajoute une entr�e "nomSession" dans la liste "encours" de l'objet pompier 
		//r�f�renc� par le num�ro de session "session" 
		
		return nomSession;
			
	}
	
	public void cloturer(String date){
		
	}
	
	
	
}
