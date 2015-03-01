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
import builderStage.StageConcret;
import builderUV.UV;
import builderUV.UVConcret;

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
	private List<UV> listeDesUVs=new ArrayList<UV>();
		
	public ServeurSAPFOR() throws URISyntaxException{
		//constructeur du serveur
		//remplissage de deux liste (hashmaps) : 1 contenant toutes les UV disponibles l'autre toutes les sessions disponibles
		
		URL dossier=getClass().getResource("/donnees/UVs"); //recherche du chemin menants aux fichiers d'UVS
		File folder = new File(dossier.toURI()); //creation chemin jusqu'au répretoire UVs
		String[] listOfUVs = folder.list();//recuperation du nom des fichiers du repertoire UV
		
		
		for (int i=0; i<listOfUVs.length; i++){	
			nomUV.put(listOfUVs[i],createUV(listOfUVs[i]));//remplissage de la HashMap avec {nomUV,UV}
		    listeDesUVs.add(createUV(listOfUVs[i]));
         }
						
		dossier=getClass().getResource("/donnees/Stages");////recherche du chemin menants aux fichiers des stages
		folder = new File(dossier.toURI()); //creation chemin jusqu'au répretoire Stage
		String[] listOfStages = folder.list();//recuperation du nom des fichiers du repertoire Stage
		
		for (int i =0; i < listOfStages.length; i++) {
			nomStage.put(listOfStages[i],createStage(listOfStages[i]));//remplissage de la  HashMap avec {nomStage,Stage}
			
		}
	}//fin constructeur
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("stage/{nomStage}")
	public Stage getStage(@PathParam("nomStage") String nomStage) throws URISyntaxException{	
		
		return  this.nomStage.get(nomStage);
		
	}//fin getStage
	
	
	
	private Pompier createPompier(int id)throws IOException, URISyntaxException {
		//met le createur d'objets pompier a disposition du serveur
		return GestionCreationObjets.creerPompier(id);
	}
		
	private UV createUV(String UV) throws URISyntaxException {
		//met le createur d'objets UV a disposition du serveur 
		return GestionCreationObjets.creerUV(UV);
	}
	
	
	private Stage createStage(String stage) throws URISyntaxException {
		//met le createur d'objets stage a disposition du serveur
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
			entrant = createPompier(idPompier);
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
		
		else{return invalide;}//objet pompier contenant seulement l'idSession : 999 = mdp faux ou login faux (idPompier n'existe pas)
		
	}//fin login

	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("candidat/{session}")
	public List <UVConcret> getUVdisponible(@PathParam("session") int session){ // cast en UVConcret necessaire au fonctionnement de JAXB
		// Fourni les listes de UV accessible en candidature pour le pompier associe a la session 
		// Filtre base uniquement sur les UV dÃ©jÃ  acquises	
		List <UVConcret> UVDisponible=new ArrayList<UVConcret>();
			
		for (int i=0; i<nomUV.size(); i++){
		UVDisponible.add((UVConcret)listeDesUVs.get(i));
		}
			
		Pompier agent=numConnection.get(session);
		List<String> UVAcquises=agent.getUV();
			
		for (int i=0; i<UVAcquises.size(); i++){
			UVDisponible.remove((UVConcret)nomUV.get(UVAcquises.get(i)));
		}
			
		return UVDisponible;
				
	}//fin getUVdisponible
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("directeur/{session}")
	public List <StageConcret> getStageAGerer(@PathParam("session") int session){ // cast en StageConcreteConcret necessaire au fonctionnement de JAXB
		// Fourni les listes de Stage gérés par le pompier associe a la session 
		// liste basé sur le contenu du fichier pompier champ Gestion	
		List <StageConcret> StageAGerer=new ArrayList<StageConcret>();
		
		Pompier agent=numConnection.get(session);
		List<String> GestionStage=agent.getGestion();
		
		for (int i=0; i<GestionStage.size(); i++){
		StageAGerer.add((StageConcret)nomStage.get(GestionStage.get(i)+".sess"));

			
		}
								
		return StageAGerer;
	}//fin getSTageAGerer
		
	
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{session}")
	public String deconnexion(@PathParam("session") int session){
		//effectue la deconnexion de l'agent 
		//met a jour le fichier d'infos du pompier
		//detruit le numero de session et l'objet Pompier cree(apres avoir ete sauvegarde)
		
		Pompier aDeco=numConnection.get(session);
		
		//String nom=aDeco.getNom();
		//String prenom=aDeco.getPrenom();
		EcrireFichier.ecrirePompier(aDeco);//ecriture/ecrasement du fichier avec les infos contenues dans l'objet pompier 
		
		numConnection.remove(session);//suppression de l'entree lie a ce numero de session
		//prenom+" "+nom+
		return "Vous etes maintenant deconnecte!";
		
	}//fin deconnexion
	
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	@Path("candidater/{session}/{nomStage}")
	public String candidater(int session, String nomStage) {
		//ajoute un stage ("nomStage") a la liste des stages "en cours" de l'objet pompier 
		//obtenu par son numero de session actuelle ("session")  
		 		
		Pompier aModif=numConnection.get(session);
		Stage actuel=this.nomStage.get(nomStage);
		
		List<String> pompierListeEnCours=aModif.getEnCours(); // extraction liste de String : stages "en cours" de l'objet pompier 
		pompierListeEnCours.add(nomStage); // ajout à cette liste de l'identifiant (String) du stage (ex:"INC1smalo25juin15")
		aModif.setEnCours(pompierListeEnCours);//remet liste des stages (a jour) dans l'objet pompier 
						
		List<String> stageListeCandidats=actuel.getCandidats(); //met a jour liste des candidats au stage
		stageListeCandidats.add(Integer.toString(aModif.getId()));
		actuel.setCandidats(stageListeCandidats);
		
		//on remet à jour le fichier du stage mainte
		
		
		return "OK";
			
	}//fin candidater
	
	
		
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	@Path("directeur/{session}/{date}")//date entrée sous la forme JJ/MM/AAAA
		public void cloturer(String date){
		
		
		
		
	}
	
	
	
}
