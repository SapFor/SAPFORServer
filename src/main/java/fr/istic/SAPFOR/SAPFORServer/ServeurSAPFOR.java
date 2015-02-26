package fr.istic.SAPFOR.SAPFORServer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
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
	private int[] test;
	
	public ServeurSAPFOR() throws URISyntaxException{
		/*int[] tab={6,12,8};
		test=tab;
		 for(int ge: tab)System.out.println(ge);*/
		File folder = new File("donnees/UVs"); //creation chemin jusqu'au répretoire UV
		
		String[] listOfUVs = folder.list();//new java.io.File("./Projet-CAOS/SAPFORServer/src/main/resources/donnees/UVs").list( );
		
		//System.out.println(listOfUVs.toString());
		
		/*for (int i=0; i<listOfUVs.length; i++)
        {
            // Afficher le nom de chaque élément
            System.out.println(listOfUVs[i]);
	    }*/
				
				//folder.list();//recuperation des fichiers du repertoire UV
		
		//System.out.println(folder.list());
		
		//for (int i =0; i < listOfUVs.length; i++) {//remplissage HashMap avec {nomUV,UV}
		//	nomUV.put(listOfUVs[i].getName(),GestionCreationObjets.creerUV(listOfUVs[i].getName()));
		//}
	}
	
	@GET
	@Produces("text/plain")
	public String getPrint() throws URISyntaxException{	
		StringBuffer chain=new StringBuffer("");
		for(int ge: test)chain.append(ge+" ");
		
		return chain.toString();
		
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
		//permet la recup�ration des infos de l'UV par le client
		return GestionCreationObjets.creerUV(UV);
	}
	
	
	private Stage getStage(String stage) throws URISyntaxException {
		//permet de r�cup�rer les infos de chaqsue session par le client
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
			while(numConnection.containsKey(randomInt)){//v�rification si randomInt existe dans le hasmap si oui, g�n�ration d'un nouveau nombre al�atoire
				randomInt=randomGenerator.nextInt(998);
			}
			numConnection.put(randomInt,entrant);//stockage de la valeur de session + du pompier associ�
			
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
		
		EcrireFichier.ecrirePompier(numConnection.get(session));//�criture/�crasement du fichier avec les infos contenues dans l'objet pompier 
		
		numConnection.remove(session);//suppression de l'entr�e li� � ce num�ro de session
		
		return "OK";
		
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{session}/{nomStage}")
	public String candidater(int session, String nomStage) {
		//ajoute une entr�e "nomSession" dans la liste "encours" de l'objet pompier 
		//r�f�renc� par le num�ro de session "session" 
		
		numConnection.get(session);
		//nomStage
		
		return nomStage;
			
	}
	
	public void cloturer(String date){
		
	}
	
	
	
}
