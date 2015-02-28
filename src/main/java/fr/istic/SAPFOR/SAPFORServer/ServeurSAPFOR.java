package fr.istic.SAPFOR.SAPFORServer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import outils.GestionCreationObjets;
import builderPompier.Pompier;
import builderPompier.PompierConcret;
import builderStage.Stage;
import builderUV.UV;

import com.sun.jersey.spi.resource.Singleton;

import ecritureFichier.EcrireFichier;

//by Lepelletier Frederic && Thomas Davin

@Singleton
@Path("/serveur")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ServeurSAPFOR {

	
	private HashMap<Integer,Pompier> numConnection=new HashMap<Integer,Pompier>();//map contenant l'objet pompier lie a son numero de session
	private HashMap<String,Stage> nomStage=new HashMap<String,Stage>();
	private HashMap<String,UV> nomUV=new HashMap<String,UV>();
	private String[] test;
	
	public ServeurSAPFOR() throws URISyntaxException{
		
		URL dossier=getClass().getResource("/donnees/UVs");
		File folder = new File(dossier.toURI()); //creation chemin jusqu'au répretoire UVs
		String[] listOfUVs = folder.list();//recuperation du nom des fichiers du repertoire UV
		test=listOfUVs;
		
		for (int i=0; i<listOfUVs.length; i++)
        {	
						
            nomUV.put(listOfUVs[i],getUV(listOfUVs[i]));//remplissage HashMap avec {nomUV,UV}
            
	    }
						
		dossier=getClass().getResource("/donnees/Stages");//creation chemin jusqu'au répretoire Stages
		folder = new File(dossier.toURI()); //creation chemin jusqu'au répretoire UVs
		String[] listOfStages = folder.list();//recuperation du nom des fichiers du repertoire UV
		
		for (int i =0; i < listOfStages.length; i++) {
			
			nomStage.put(listOfStages[i],getStage(listOfStages[i]));
		}
	}
	
	@GET
	@Path("/print")
	@Produces({MediaType.APPLICATION_JSON})
	public Stage getStage() throws URISyntaxException{	
		
		
		return nomStage.get("stmalo12juin14.sess");
	}
		    
		/*File folder = new File("donnees/Stages"); //creation chemin jusqu'au répretoire UV
		File[] listOfStages = folder.listFiles();//recuperation des fichiers du repertoire UV
		
		for (int i = 0; i < listOfStages.length; i++) {//remplissage HashMap avec {nomUV,UV}
			nomUV.put(listOfStages[i].getName(),GestionCreationObjets.creerUV(listOfStages[i].getName()));
		} 
		
		return listOfStages.toString();
		
	}*/
	
	
	private Pompier getPompier(int id)throws IOException, URISyntaxException {
		
		return GestionCreationObjets.creerPompier(id);
	}
		
	private UV getUV(String UV) throws URISyntaxException {
		//permet la recuperation des infos de l'UV par le client
		return GestionCreationObjets.creerUV(UV);
	}
	
	
	private Stage getStage(String stage) throws URISyntaxException {
		//permet de recuperer les infos de chaqsue session par le client
		return GestionCreationObjets.creerStage(stage);
	}
	
	

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{idPompier}/{mdp}")
	public Pompier login(@PathParam("idPompier") int idPompier,@PathParam("mdp") String mdp) throws URISyntaxException {
		// recupere le login et mdp de l'agent
		//verifie le mdp par rapport a celui indique dans le fichier de l'agent ne idPompier
		//si numero concordent => creation d'un numero de session aleatoire (apres verification de sa disponibilite)
		
		Pompier invalide=new PompierConcret();//création classe Pompier vide contenant juste l'idSession indiquant une erreur de connection
		invalide.setIdSession(999);
		
		Pompier entrant;
		try {
			entrant = getPompier(idPompier);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return invalide;
		}//creation du pompier a partir des donnees stockees d'apres son identifiannt 
				
		if(entrant.getMdp().equals(mdp)){
			int randomInt=0;
			Random randomGenerator = new Random();//creation d'un generateur de nombre aleatoires
			while(numConnection.containsKey(randomInt)){//verification si randomInt existe dans le hasmap si oui, generation d'un nouveau nombre aleatoire
				randomInt=randomGenerator.nextInt(998);
			}
			numConnection.put(randomInt,entrant);//stockage de la valeur de session + du pompier associe
			
			entrant.setIdSession(randomInt); 
			
			return entrant;
		}
		else{return invalide;}//return 999;}//valeur 999 si mdp faux ou login faux (idPompier n'existe pas)
		
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{session}")
	public String deconnexion(@PathParam("session") int session){
		//effectue la deconnexion de l'agent 
		//met a jour le fichier d'infos du pompier
		//detruit le numero de session et l'objet Pompier cree(apres avoir ete sauvegarde)
		
		EcrireFichier.ecrirePompier(numConnection.get(session));//ecriture/ecrasement du fichier avec les infos contenues dans l'objet pompier 
		
		numConnection.remove(session);//suppression de l'entree lie a ce numero de session
		
		return "OK";
		
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{session}/{nomStage}")
	public String candidater(int session, String nomStage) {
		//ajoute une entr�e "nomSession" dans la liste "encours" de l'objet pompier 
		//r�f�renc� par le num�ro de session "session" 
		
		Pompier aModif=numConnection.get(session);
		Stage actuel=this.nomStage.get(nomStage);
		
		List<String> sessionEnCours=aModif.getEnCours();
		sessionEnCours.add(nomStage); // ne serait-ce pas :  sessionEnCours.add(actuel); ???
		aModif.setEnCours(sessionEnCours);
		
				
		return null;
			
	}
	
	
		
	
	
	
	public void cloturer(String date){
		
		
	}
	
	
	
}
