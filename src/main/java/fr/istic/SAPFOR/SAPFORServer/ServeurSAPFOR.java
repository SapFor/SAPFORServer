package fr.istic.SAPFOR.SAPFORServer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import outils.EncapsulationStage;
import outils.EncapsulationUV;
import outils.GestionCreationObjets;
import builderPompier.Pompier;
import builderPompier.PompierConcret;
import builderStage.Stage;
import builderStage.StageConcret;
import builderUV.UV;
import builderUV.UVConcret;

import com.sun.jersey.spi.resource.Singleton;

import ecritureFichier.EcrireFichier;

/**
 * 
 *@author : Thomas Davin
 *@author : Lepelletier Frederic 
 *
 */
 
@Singleton
@Path("/serveur")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ServeurSAPFOR {

	
	private HashMap<Integer,Pompier> numConnection=new HashMap<Integer,Pompier>();//map contenant l'objet pompier lie a son numero de session
	private HashMap<String,Stage> nomStage=new HashMap<String,Stage>();
	private HashMap<String,UV> nomUV=new HashMap<String,UV>();
	private List<UV> listeDesUVs=new ArrayList<UV>();
	private List<Integer> listIdPompier=new ArrayList<Integer>();
		
	
	public ServeurSAPFOR() throws URISyntaxException{
		//constructeur du serveur
		//remplissage de deux liste (hashmaps) : 1 contenant toutes les UV disponibles l'autre toutes les sessions disponibles
		
		URL dossier=getClass().getResource("/donnees/UVs"); //recherche du chemin menants aux fichiers d'UVS
		File folder = new File(dossier.toURI()); //creation chemin jusqu'au répretoire UVs
		String[] listOfUVs = folder.list();//recuperation du nom des fichiers du repertoire UV
	
		String[] coupeExtension;
		
		for (int i=0; i<listOfUVs.length; i++){
			coupeExtension=listOfUVs[i].split("\\.");
			nomUV.put(coupeExtension[0],createUV(coupeExtension[0]));//remplissage de la HashMap avec {nomUV,UV}
			listeDesUVs.add(createUV(coupeExtension[0]));
         }
		
		
		dossier=getClass().getResource("/donnees/Pompiers"); //recherche du chemin menants aux fichiers d'UVS
		folder = new File(dossier.toURI()); //creation chemin jusqu'au répretoire UVs
		String[] listOfIds = folder.list();//recuperation du nom des fichiers du repertoire UV
		
		
		for (int i=0; i<listOfIds.length; i++){
			coupeExtension=listOfIds[i].split("\\.");
			listIdPompier.add(Integer.parseInt(coupeExtension[0]));
		}
		
				
		
						
		dossier=getClass().getResource("/donnees/Stages");//recherche du chemin menants aux fichiers des stages
		folder = new File(dossier.toURI()); //creation chemin jusqu'au répretoire Stage
		String[] listOfStages = folder.list();//recuperation du nom des fichiers du repertoire Stage
		
		for (int i =0; i < listOfStages.length; i++) {
			coupeExtension=listOfStages[i].split("\\.");
			nomStage.put(coupeExtension[0],createStage(coupeExtension[0]));//remplissage de la  HashMap avec {nomStage,Stage}
						
		}
		
		
		
	}//fin constructeur
	
	
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
	@Path("stage/{nomStage}")
	public synchronized Stage getStage(@PathParam("nomStage") String nomStage){	
		
		return  this.nomStage.get(nomStage);
		
	}//fin getStage
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("pompier/{idPompier}")
	public synchronized Pompier getPompier(@PathParam("idPompier") int idPompier) throws IOException, URISyntaxException{	
		
		return createPompier(idPompier);
		
	}//fin getStage
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{idPompier}/{mdp}")
	public synchronized Pompier login(@PathParam("idPompier") int idPompier,@PathParam("mdp") String mdp) throws URISyntaxException {
		// recupere le login et mdp de l'agent
		//verifie le mdp par rapport a celui indique dans le fichier de l'agent ne idPompier
		//si numero concordent => creation d'un numero de session aleatoire (apres verification de sa disponibilite)
		
		Pompier invalide=new PompierConcret();//création classe Pompier vide contenant juste l'idSession indiquant une erreur de connection
		invalide.setIdSession(999);
		
		Pompier entrant;
		
		int i=0;
		
		while(i<listIdPompier.size() && listIdPompier.get(i)!=idPompier){
			i++;
		}
		
		if(i<listIdPompier.size()){
					
			try {
				entrant = createPompier(idPompier);
			} catch (IOException e) {return invalide;}//creation du pompier a partir des donnees stockees d'apres son identifiannt 
			
			List<Integer> idExist=new ArrayList<Integer>();
			
			Iterator it = numConnection.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				System.out.println(((Pompier) pair.getValue()).getId());
				idExist.add(((Pompier) pair.getValue()).getId());
			}
			
			int j=0;
			while(j<idExist.size() && idExist.get(j)!=idPompier){j++;}
			
			if(j>=idExist.size()){
			
			
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
			}
			else{return invalide;}
			
		}
			
		return invalide;
			
	}//fin login

	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("candidat/{session}")
	public synchronized EncapsulationUV getUVdisponible(@PathParam("session") int session){ // cast en UVConcret necessaire au fonctionnement de JAXB
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
		
		EncapsulationUV UVdispoAenvoyer =new EncapsulationUV (UVDisponible);
		return UVdispoAenvoyer;
				
	}//fin getUVdisponible
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("directeur/{session}")
	public synchronized EncapsulationStage getStageAGerer(@PathParam("session") int session){ // cast en StageConcreteConcret necessaire au fonctionnement de JAXB

		List <StageConcret> StageAGerer=new ArrayList<StageConcret>();
		
		Pompier agent=numConnection.get(session);
		List<String> GestionStage=agent.getGestion();
		
		for (int i=0; i<GestionStage.size(); i++){
		StageAGerer.add((StageConcret)nomStage.get(GestionStage.get(i)));	
		}
		
		EncapsulationStage  StageAenvoyer=new EncapsulationStage (StageAGerer);
		
		return StageAenvoyer;
	}//fin getStageAGerer
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{session}")
	public synchronized String deconnexion(@PathParam("session") int session) throws URISyntaxException{
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
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("candidater/{session}/{nomStage}")
	public synchronized String candidater(@PathParam("session") int session, @PathParam("nomStage") String nomStage) {
		//ajoute un stage ("nomStage") a la liste des stages "en cours" de l'objet pompier 
		//obtenu par son numero de session actuelle ("session")  
		 		
		Pompier aModif=numConnection.get(session);
		StageConcret actuel=(StageConcret)this.nomStage.get(nomStage);
		Calendar today=Calendar.getInstance();
		
		
		
		if(actuel.getFinCandidature().after(today)){
		
			List<String> pompierListeEnCours=aModif.getEnCours(); // extraction liste de String : stages "en cours" de l'objet pompier 
			pompierListeEnCours.add(nomStage); // ajout à cette liste de l'identifiant (String) du stage (ex:"INC1smalo25juin15")
			aModif.setEnCours(pompierListeEnCours);//remet liste des stages (a jour) dans l'objet pompier 
			
			System.out.println(pompierListeEnCours.toString());
			
			List<String> stageListeCandidats=actuel.getCandidats(); //met a jour liste des candidats au stage
			stageListeCandidats.add(Integer.toString(aModif.getId()));
			actuel.setCandidats(stageListeCandidats);
			
			System.out.println(stageListeCandidats.toString());
			
			actuel.inscription(aModif);
			
			System.out.println(actuel.getListPompierCandidat().toString());
			
			//EcrireFichier.ecrireStage(actuel);
				
			return "OK";
		}
		
		else{return "KO";}
	}//fin candidater
				
	
	@GET	
	@Produces({MediaType.APPLICATION_JSON})
	@Path("directeur/{stage}/{date}")//date entrée sous la forme JJ.MM.AAAA
	public String cloturer(@PathParam("date") String date,@PathParam("stage") String stage){
		//
		String str[]=date.split("\\.");
		String jourS=str[0];
		String moisS=str[1];
		String anneeS=str[2];
				
		int jour=Integer.parseInt(jourS);
		int mois=Integer.parseInt(moisS);
		int annee=Integer.parseInt(anneeS);
		
		Calendar dateModif=Calendar.getInstance();
		
				
		dateModif.set(annee,mois-1,jour);
		
		if(dateModif.after(Calendar.getInstance().getTime())){
		
			Stage aModif=nomStage.get(stage);
		
			if(dateModif.before(aModif.getDate())){
		
				aModif.setFinCandidature(dateModif);
		
				EcrireFichier.ecrireStage(aModif);
		
				
		
				return "OK";
			}
			else{return "KO";}
		}
		else{return "KO";}
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("directeur/selection")
	public String UpdateStage(StageConcret s){ // en cours de mise en point
		
		StageConcret StageAUpdate=(StageConcret)nomStage.get(s.getNomStage());
		StageConcret StageRecu=s;
		
		System.out.println(StageAUpdate.getCandidats().toString());
		System.out.println(StageAUpdate.getAccepte().toString());
		System.out.println(StageAUpdate.getAttente().toString());
		//System.out.println(StageAUpdate.getRefuse().toString());
		
		System.out.println(s.getCandidats().toString());
		System.out.println(s.getAccepte().toString());
		System.out.println(s.getAttente().toString());
		System.out.println(s.getRefuse().toString());
		/*
		StageAUpdate.setCandidats(s.getCandidats());
		StageAUpdate.setAccepte(s.getAccepte());
		StageAUpdate.setAttente(s.getAttente());
		StageAUpdate.setRefuse(s.getRefuse());
		
		System.out.println(StageAUpdate.getCandidats().toString());
		System.out.println(StageAUpdate.getAccepte().toString());
		System.out.println(StageAUpdate.getAttente().toString());
		System.out.println(StageAUpdate.getRefuse().toString());
		*/
		//StageAUpdate.notifier();
		nomStage.put(StageAUpdate.getNomStage(), StageAUpdate);
		
		String reponse="Le stage "+StageAUpdate.getNomStage()+" a ete mis a jour";
		
		return reponse;}
	
}
