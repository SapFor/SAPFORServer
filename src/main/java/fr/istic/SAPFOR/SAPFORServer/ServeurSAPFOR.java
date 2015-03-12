package fr.istic.SAPFOR.SAPFORServer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

	private String chemData;
	private String pathPomp;
	private String pathUVs;
	private String pathStag;
	private HashMap<Integer,Pompier> numConnection=new HashMap<Integer,Pompier>();//map contenant l'objet pompier lie a son numero de session
	private HashMap<String,Stage> nomStage=new HashMap<String,Stage>();
	private HashMap<String,UV> nomUV=new HashMap<String,UV>();
	private List<UV> listeDesUVs=new ArrayList<UV>();
	private List<Integer> listIdPompier=new ArrayList<Integer>();
		
	/**
	 * 
	 * @throws URISyntaxException
	 * @throws MalformedURLException
	 */
	
	public ServeurSAPFOR() throws URISyntaxException, MalformedURLException{
		//constructeur du serveur
		
				
		//***************
		//creation du chemin menant aux donnees
		//***************
			
		//***************************************************************************************
		//*chemin general menant au dossier contenant les dossiers "Pompiers", "Stages" et "UVs"* 
		//*A MODIFIER AVANT DE DEMARRER LE SERVEUR***********************************************
		//***************************************************************************************************	
		
		//chemData=System.getProperty("user.dir")+"/../DonneeServer/DonneesServer/"; //Path windows (a modifier)
		
						
		chemData=System.getProperty("user.home")+"/Projet-CAOS/donnees/";//mode linux (a modifier)
		
		//***************************************************************************************************
		
		
		pathPomp=chemData+"Pompiers/";
		pathUVs=chemData+"UVs/";
		pathStag=chemData+"Stages/";
		
		
		//**************
		//remplissage de deux liste (hashmaps) : 1 contenant toutes les UV disponibles l'autre toutes les stages disponibles
		//***************
		

		File folder = new File(pathUVs);//dossier.toURI()); //creation chemin jusqu'au répretoire UVs
		String[] listOfUVs = folder.list();//recuperation du nom des fichiers du repertoire UV
		
		String[] coupeExtension;
		
		for (int i=0; i<listOfUVs.length; i++){
			coupeExtension=listOfUVs[i].split("\\.");
			nomUV.put(coupeExtension[0],createUV(coupeExtension[0]));//remplissage de la HashMap avec {nomUV,UV}
			listeDesUVs.add(createUV(coupeExtension[0]));
         }
		
		
		
		folder = new File(pathPomp);//creation chemin jusqu'au répretoire pompiers
		String[] listOfIds = folder.list();//recuperation du nom des fichiers du repertoire UV
		
		
		for (int i=0; i<listOfIds.length; i++){
			coupeExtension=listOfIds[i].split("\\.");
			listIdPompier.add(Integer.parseInt(coupeExtension[0]));
		}
		
				
		
					
		folder = new File(pathStag);//creation chemin jusqu'au répretoire Stage
		String[] listOfStages = folder.list();//recuperation du nom des fichiers du repertoire Stage
		
		for (int i=0; i < listOfStages.length; i++) {
			coupeExtension=listOfStages[i].split("\\.");
			nomStage.put(coupeExtension[0],createStage(coupeExtension[0]));//remplissage de la  HashMap avec {nomStage,Stage}
						
		}
		
		
	}//fin constructeur
	
	

	
	private Pompier createPompier(int id)throws IOException, URISyntaxException {
		//****
		//met le createur d'objets pompier a disposition du serveur
		//****
		return GestionCreationObjets.creerPompier(id,pathPomp);
	}
	
	
	private UV createUV(String UV) throws URISyntaxException, MalformedURLException {
		//****
		//met le createur d'objets UV a disposition du serveur
		//****
		return GestionCreationObjets.creerUV(UV,pathUVs);
	}
	
	
		
	private Stage createStage(String stage) throws URISyntaxException, MalformedURLException {
		//****
		//met le createur d'objets stage a disposition du serveur
		//****
		return GestionCreationObjets.creerStage(stage,pathStag);
	}
	
	
	/**
	 * fournit au client un objet Stage a partir de son nom 
	 *  
	 * @param nomStage (String)
	 * @return Objet Stage
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("stage/{nomStage}")
	public synchronized Stage getStage(@PathParam("nomStage") String nomStage){	
		// ***
		// fourni l'objet Stage associé au label "nomStage"
		// ***
		return  this.nomStage.get(nomStage);
		
	}//fin getStage
	
	
	/**
	 * fournit au client un objet pompier a partir d'un numero d'agent
	 * 
	 * @param idPompier
	 * @return Objet Pompier
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("pompier/{idPompier}")
	public synchronized Pompier getPompier(@PathParam("idPompier") int idPompier) throws IOException, URISyntaxException{	
		// ***
		// fourni l'objet Pompier associé à l'idPompier fourni
		// ***
		return createPompier(idPompier);
		
	}//fin getStage
	

	/**
	 * gere la connexion d'un pompier au niveau du client rend un objet Pompier contenant un numero de session
	 * si n°agent et mot de passe sont valides sinon rend un objet Pompier contenant le numero de erreur session 
	 * "erreur" : 999
	 * 
	 * @param idPompier
	 * @param mdp
	 * @return Objet Pompier
	 * @throws URISyntaxException
	 */
			
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{idPompier}/{mdp}")
	public synchronized Pompier login(@PathParam("idPompier") int idPompier,@PathParam("mdp") String mdp) throws URISyntaxException {
		
		//****
		// recupere le login et mdp de l'agent
		//verifie le mdp par rapport a celui indique dans le fichier de l'agent 
		//si numero concordent => creation d'un numero de session aleatoire (apres verification de sa disponibilite)
		//et renvoi un objet Pompier contenant toutes les infos ou un objet Pompier (Invalide contenant un n° de session "erreur") 
		//****
		
		Pompier invalide=new PompierConcret();
		//création classe Pompier vide contenant juste l'idSession indiquant une erreur de connection
		invalide.setIdSession(999);
		
		Pompier entrant;
		
		//****
		//verifie si le pompier est deja connecte
		//****
		
		List<Integer> idExist=new ArrayList<Integer>();
		
		
		Iterator<Map.Entry<Integer,Pompier>> it = numConnection.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer,Pompier> pair = (Map.Entry<Integer,Pompier>)it.next();
			idExist.add(((Pompier) pair.getValue()).getId());
		}
		
		int j=0;
		while(j<idExist.size() && idExist.get(j)!=idPompier){j++;}
		
		//****
		//si le pompier n'est pas encore connecte
		//****
		
		if(j>=idExist.size()){	
			
			try {
				entrant = createPompier(idPompier);//creation du pompier a partir des donnees stockees d'apres son identifiant
			} catch (IOException e) {return invalide;} 
			
			
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
		
				else{return invalide;}//objet pompier contenant seulement l'idSession : 999 = mdp ou login faux (idPompier n'existe pas)
			}
		
			//****
			// si le pompier est deja connecte
			//****
			
			else{return invalide;}
			
		
			
	}//fin login

	
	/**
	 * fournit une liste des UV disponibles pour le candidat identifie par son n° de session 
	 * 
	 * @param session
	 * @return Objet EncapsulationUV
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("candidat/{session}")
	public synchronized EncapsulationUV getUVdisponible(@PathParam("session") int session){ // cast en UVConcret necessaire au fonctionnement de JAXB
		//****
		// Fourni les listes de UV accessible en candidature pour le pompier associe a la session 
		// Filtre base uniquement sur les UV deja  acquises
		//****
		
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
	
	
	/**
	 * fournit au client une liste d'UV, disponibles pour le candidat, encapsulees dans un objet EncapsulationUV
	 * 
	 * @param session
	 * @return Objet EncapsulationUV
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("UVcandidat/{session}")
	public synchronized EncapsulationUV getUVdispoCandidat(@PathParam("session") int session){
		// ***
		// Creer un objet EncapsulationUV contenant une liste d'objet UV.
		// Les objets UVs stockés dans la liste encapsulée sont toutes les UVs non acquises par le pompier (sans filtre)
		// ***
		
		
		List <UVConcret> UVCandidatable=new ArrayList<UVConcret>();
			
		Pompier agent=numConnection.get(session);
				
		for (int i=0; i<listeDesUVs.size();i++){
			
			if (peutCandidater(agent,listeDesUVs.get(i))){
				UVCandidatable.add((UVConcret)listeDesUVs.get(i));
			}
		}
		
		EncapsulationUV UVsCandidatablesAenvoyer=new EncapsulationUV (UVCandidatable);
		return UVsCandidatablesAenvoyer;
		
	}
	
	
	
	private boolean peutCandidater(Pompier pomp, UV uv){ 
		//****
		//retourne vrai si le pompier peut être candidat à l'UV
		// Condition pour candidater :ne pas l'avoir valider et avoir acquis les niveaux inferieurs. ex: pour FDF2 il faut avoir validé FDF1
		//***
		boolean reponse;
		
		String nomUv=uv.getNom();
		List<String> UVAcquises=pomp.getUV();
		String ConditionUV=nomUv.substring(0, nomUv.length()-1)+ (Integer.parseInt((nomUv.substring(nomUv.length()-1)))-1);
		
		if (!UVAcquises.contains(nomUv)){
			if (Integer.parseInt((ConditionUV.substring(ConditionUV.length()-1)))==0){
				reponse=true;			
			}
			else if (UVAcquises.contains(ConditionUV)){reponse=true;}
			
			else{reponse=false;}
				
			}
		else {reponse=false;}
		
	return reponse;
	}
	//fin de peutCandidater
	
	
	/**
	 * 
	 * @param session
	 * @return Objet EncapsulationUV
	 */
		
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("UVformateur/{session}")
	public synchronized EncapsulationUV getUVdispoFormateur(@PathParam("session") int session){
		// ***
		// Creer un objet EncapsulationUV contenant une liste d'objet UV.
		// Les objets UVs stockés dans la liste encapsulée sont toutes les UVs disponible etant que candidat formateur
		// ***
		
		List <UVConcret> UVCandidatable=new ArrayList<UVConcret>();
		
		Pompier agent=numConnection.get(session);
				
		for (int i=0; i<listeDesUVs.size();i++){
			
			if (peutFormer(agent,listeDesUVs.get(i))){
				UVCandidatable.add((UVConcret)listeDesUVs.get(i));
			}
		}
		
		EncapsulationUV UVsCandidatablesAenvoyer =new EncapsulationUV (UVCandidatable);
		return UVsCandidatablesAenvoyer;	
	}// fin de getUVdispoFormateur
	
	
	private boolean peutFormer(Pompier pomp, UV uv){
		//****
		//retourne vrai si le pompier peut être dispenser la formation de l'UV
		//Condition pour dispenser la formation : avoir l'UV FORM avec le bon niveau et avoir acquis l'UV ciblé.
		//ex: pour dispenser l'UV FDF2 il faut avoir validé FORM2 et FDF2
		//****
		
		boolean reponse;
				
		String nomUv=uv.getNom();
		List<String> UVAcquises=pomp.getUV();
		
		String conditionFORM="FORM"+(Integer.parseInt((nomUv.substring(nomUv.length()-1))));
		
		if (UVAcquises.contains(conditionFORM)){
			if (nomUv.compareTo(conditionFORM)==0){
			reponse=UVAcquises.contains("FORM"+(1+Integer.parseInt((nomUv.substring(nomUv.length()-1)))));
			}
			
			else if (UVAcquises.contains(nomUv)){
			reponse=true;			
			}
			
			else{reponse=false;}
		}
		
		else {reponse=false;}
			
						
		return reponse;
	}// fin de peutFormer
	
	
	/**
	 * 
	 * @param nameUV
	 * @return
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("UV/{nameUV}")
	public synchronized EncapsulationStage getStageDeUV(@PathParam("nameUV") String nameUV){ 
		//****
		//donne au client un objet EncapsulationStage contenant la liste des stageConcrets 
		//contenus dans l'UV donné "nomUV"
		//****
		
		List <StageConcret> StageDeUV=new ArrayList<StageConcret>();
		
		UV uvDemande=nomUV.get(nameUV);
		List<String> stageDeUVdemande=uvDemande.getStages();
		
		for (int i=0; i<stageDeUVdemande.size(); i++){
		StageDeUV.add((StageConcret)nomStage.get(stageDeUVdemande.get(i)));// cast en StageConcreteConcret necessaire au fonctionnement de JAXB	
		}
		
		EncapsulationStage  StageAenvoyer=new EncapsulationStage (StageDeUV);
				
		return StageAenvoyer;
	}//fin getStageDeUV
	
	
	/**
	 * envoi une liste de StageConcret par le biais du numero de session de l'agent connecte
	 * envoi la liste des stages geres par ce pompier
	 * 
	 * @param session (int) numero de session active de l'agent
	 * @return objet EncapsulationStage
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("directeur/{session}")
	public synchronized EncapsulationStage getStageAGerer(@PathParam("session") int session){ 
		//****
		//donne au client un objet EncapsulationStage contenant la liste des stageConcrets 
		//les stages de la liste sont les stages gérés en tant que directeur
		//cast en StageConcret necessaire au fonctionnement de JAXB
		//****

		List <StageConcret> StageAGerer=new ArrayList<StageConcret>();
		
		Pompier agent=numConnection.get(session);
		List<String> GestionStage=agent.getGestion();
		
		for (int i=0; i<GestionStage.size(); i++){
		StageAGerer.add((StageConcret)nomStage.get(GestionStage.get(i)));	
		}
		
		EncapsulationStage  StageAenvoyer=new EncapsulationStage (StageAGerer);
		
		return StageAenvoyer;
	}//fin getStageAGerer
	
	
	/**
	 * 
	 * @param session
	 * @return String
	 *
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{session}")
	public synchronized String deconnexion(@PathParam("session") int session){
		//***
		//effectue la deconnexion de l'agent 
		//met a jour le fichier d'infos du pompier
		//detruit le numero de session et l'objet Pompier cree(apres avoir ete sauvegarde)
		//***
		
		Pompier aDeco=numConnection.get(session);
		
		EcrireFichier.ecrirePompier(aDeco,pathPomp);//ecriture/ecrasement du fichier avec les infos contenues dans l'objet pompier 
		
		numConnection.remove(session);//suppression de l'entree lie a ce numero de session
		
		return "OK";
		
	}//fin deconnexion
	
	
	/**
	 * 
	 * @param session
	 * @param nomStage
	 * @return String
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("candidater/{session}/{nomStage}")
	public synchronized String candidater(@PathParam("session") int session, @PathParam("nomStage") String nomStage) throws IOException, URISyntaxException {
		//****
		//ajoute un stage ("nomStage") a la liste des stages "en cours" de l'objet pompier 
		//obtenu par son numero de session actuelle ("session")  
		//***
		
		Pompier aModif=numConnection.get(session);
		Stage actuel=this.nomStage.get(nomStage);
		Calendar today=Calendar.getInstance();
				
		if(actuel.getFinCandidature().after(today)&&!actuel.getCandidats().contains(aModif.getId())){
			
			List<String> pompierListeEnCours=aModif.getEnCours(); // extraction liste de String : stages "en cours" de l'objet pompier 
			pompierListeEnCours.add(nomStage); // ajout à cette liste de l'identifiant (String) du stage (ex:"INC1smalo25juin15")
			aModif.setEnCours(pompierListeEnCours);//remet liste des stages (a jour) dans l'objet pompier 
						
			List<String> stageListeCandidats=actuel.getCandidats(); //met a jour liste des candidats au stage
			stageListeCandidats.add(Integer.toString(aModif.getId()));
			actuel.setCandidats(stageListeCandidats);
									
			actuel.inscription(aModif);
			
			EcrireFichier.ecrireStage(actuel,pathStag);
			
				
			return "OK";
		}
		
		else{return "KO";}
	}//fin candidater
	
	
	/**
	 * retire la candidature d'un pompier perer par son numero de session 
	 * 
	 * @param session
	 * @param nomStage
	 * @return String
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("desincription/{session}/{nomStage}")
	public synchronized String desinscrire(@PathParam("session") int session, @PathParam("nomStage") String nomStage) throws URISyntaxException, IOException{
		
		//***
		//retire de la liste getEnCours le stage ciblé
		//retire le pompier de la liste des candidats du stage
		//met a jour les fichiers de donnees
		//***
		
		Pompier aModif=numConnection.get(session);
		Stage actuel=this.nomStage.get(nomStage);
		
		
		if(actuel.getCandidats().contains(aModif.getId())){
			
			List<String> pompierListeEnCours=aModif.getEnCours(); // extraction liste de String : stages "en cours" de l'objet pompier 
			pompierListeEnCours.remove(nomStage); // ajout à cette liste de l'identifiant (String) du stage (ex:"INC1smalo25juin15")
			aModif.setEnCours(pompierListeEnCours);//remet liste des stages (a jour) dans l'objet pompier 
			
			List<String> stageListeCandidats=actuel.getCandidats(); //met a jour liste des candidats au stage
			stageListeCandidats.remove(Integer.toString(aModif.getId()));
			actuel.setCandidats(stageListeCandidats);
			
			actuel.desincription(aModif);
			

			EcrireFichier.ecrireStage(actuel,pathStag);
				
			return "OK";
		}
		
		else{return "KO";}
		
	}//fin de desinscrire
	
	
	/**
	 * change la date de fin de candidature d'un stage grace au nom de ce stage et la date de cloture desiree  
	 * 
	 * @param date (String) envoye sous la forme "JJ.MM.AAAA"
	 * @param stage (String) nom du stage tel qu'il est defini dans l'objet Stage
	 * @return (String)
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	@GET	
	@Produces({MediaType.APPLICATION_JSON})
	@Path("directeur/{stage}/{date}")//date entrée sous la forme JJ.MM.AAAA
	public String cloturer(@PathParam("date") String date,@PathParam("stage") String stage) throws IOException, URISyntaxException{
		//***
		// Cloture (rend inaccessible en candidature) la stage indiqué dans le path
		//***
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

				EcrireFichier.ecrireStage(aModif,pathStag);
			
				return "OK";
			}
			else{return "KO";}
		}
		else{return "KO";}
	}
	
	
	/**
	 * mise a jour de l'objet Stage apres modification par un responsable de stage (version finale) 
	 * 
	 * @param s (StageConcret) objet de type StageConcret
	 * @return (String)
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("directeur/selection")

	public synchronized String UpdateStage(StageConcret s) throws IOException{ 
		// fonctionne malgré probleme d'affichage de certaine liste (pointeur null) lors des controles.

		
		StageConcret StageAUpdate=(StageConcret)nomStage.get(s.getNomStage());
		
		if (s.getCandidats()==null) {s.setCandidats(new ArrayList<String>());}
		
		if (s.getAccepte()==null) {s.setAccepte(new ArrayList<String>());}
		
		if (s.getAttente()==null) {s.setAttente(new ArrayList<String>());}
		
		if (s.getRefuse()==null) {s.setRefuse(new ArrayList<String>());}
		
		
		StageAUpdate.setCandidats(s.getCandidats());
		StageAUpdate.setAccepte(s.getAccepte());
		StageAUpdate.setAttente(s.getAttente());
		StageAUpdate.setRefuse(s.getRefuse());
		
		StageAUpdate.notifier();
		nomStage.put(StageAUpdate.getNomStage(),StageAUpdate);
		
		EcrireFichier.ecrireStage(StageAUpdate,pathStag);
		
		
		String reponse="Le stage "+StageAUpdate.getNomStage()+" a ete mis a jour";
		
		return reponse;
		
	}//fin UpdateStage
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("directeur/sauvegarde")
	public synchronized String SauvegardeStage(StageConcret s) throws IOException{ // fonctionne malgré probleme d'affichage de certaine liste (pointeur null) lors des controles.
		
		StageConcret StageSauvegarde=(StageConcret)nomStage.get(s.getNomStage());
		
		if (s.getCandidats()==null) {s.setCandidats(new ArrayList<String>());}
		
		if (s.getAccepte()==null) {s.setAccepte(new ArrayList<String>());}
		
		if (s.getAttente()==null) {s.setAttente(new ArrayList<String>());}
		
		if (s.getRefuse()==null) {s.setRefuse(new ArrayList<String>());}
		
		
		StageSauvegarde.setCandidats(s.getCandidats());
		StageSauvegarde.setAccepte(s.getAccepte());
		StageSauvegarde.setAttente(s.getAttente());
		StageSauvegarde.setRefuse(s.getRefuse());
		
		nomStage.put(StageSauvegarde.getNomStage(), StageSauvegarde);
		
		EcrireFichier.ecrireStage(StageSauvegarde,pathStag);
		
		String reponse="Le stage "+StageSauvegarde.getNomStage()+" a été sauvegardé";
		
		return reponse;
		
	}//fin SauvegardeStage
	
}

