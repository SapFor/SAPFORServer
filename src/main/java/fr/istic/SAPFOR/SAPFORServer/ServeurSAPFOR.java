package fr.istic.SAPFOR.SAPFORServer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
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


		chemData=System.getProperty("user.dir")+"/../DonneeServer/DonneesServer/"; //Path windows (a modifier)
		
						
		//chemData=System.getProperty("user.home")+"/Projet-CAOS/donnees/";//mode linux (a modifier)
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
	
	/**
	 * 
	 * @param id
	 * @return Objet Pompier
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private Pompier createPompier(int id)throws IOException, URISyntaxException {
		//met le createur d'objets pompier a disposition du serveur
		return GestionCreationObjets.creerPompier(id,pathPomp);
	}
	
	/**
	 * 
	 * @param UV
	 * @return Objet UV
	 * @throws URISyntaxException
	 * @throws MalformedURLException
	 */
	private UV createUV(String UV) throws URISyntaxException, MalformedURLException {
		//met le createur d'objets UV a disposition du serveur 
		return GestionCreationObjets.creerUV(UV,pathUVs);
	}
	
	/**
	 * 
	 * @param stage
	 * @return Objet Stage
	 * @throws URISyntaxException
	 * @throws MalformedURLException
	 */
	private Stage createStage(String stage) throws URISyntaxException, MalformedURLException {
		//met le createur d'objets stage a disposition du serveur
		return GestionCreationObjets.creerStage(stage,pathStag);
	}
	
	
	/**
	 * 
	 * @param nomStage
	 * @return Objet Stage
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("stage/{nomStage}")
	public synchronized Stage getStage(@PathParam("nomStage") String nomStage){	
		
		return  this.nomStage.get(nomStage);
		
	}//fin getStage
	/**
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
		
		return createPompier(idPompier);
		
	}//fin getStage
	
	/**
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

	/**
	 * 
	 * @param session
	 * @return Objet EncapsulationUV
	 */
	
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
	
	/**
	 * 
	 * @param session
	 * @return Objet EncapsulationUV
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("UVcandidat/{session}")
	public synchronized EncapsulationUV getUVdispoCandidat(@PathParam("session") int session){
		
		List <UVConcret> UVCandidatable=new ArrayList<UVConcret>();
			
		Pompier agent=numConnection.get(session);
				
		for (int i=0; i<listeDesUVs.size();i++){
			
			if (peutCandidater(agent,listeDesUVs.get(i))){
				UVCandidatable.add((UVConcret)listeDesUVs.get(i));
			}
		}
		
		EncapsulationUV UVsCandidatablesAenvoyer =new EncapsulationUV (UVCandidatable);
		return UVsCandidatablesAenvoyer;
		
	}
	
	private boolean peutCandidater(Pompier pomp, UV uv){ 
		//retourne vrai si le pompier peut être candidat à l'UV
		// Condition pour candidater :ne pas l'avoir valider et avoir acquis les niveaux inferieurs. ex: pour FDF2 il faut avoir validé FDF1
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
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("UVformateur/{session}")
	public synchronized EncapsulationUV getUVdispoFormateur(@PathParam("session") int session){
		
		List <UVConcret> UVCandidatable=new ArrayList<UVConcret>();
		
		Pompier agent=numConnection.get(session);
				
		for (int i=0; i<listeDesUVs.size();i++){
			
			if (peutFormer(agent,listeDesUVs.get(i))){
				UVCandidatable.add((UVConcret)listeDesUVs.get(i));
			}
		}
		
		EncapsulationUV UVsCandidatablesAenvoyer =new EncapsulationUV (UVCandidatable);
		return UVsCandidatablesAenvoyer;	
	}
	// fin de getUVdispoFormateur
	
	private boolean peutFormer(Pompier pomp, UV uv){
		//retourne vrai si le pompier peut être dispenser la formation de l'UV
		//Condition pour dispenser la formation : avoir l'UV FORM avec le bon niveau et avoir acquis l'UV ciblé.
		//ex: pour dispenser l'UV FDF2 il faut avoir validé FORM2 et FDF2
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
	}
	// fin de peutFormer
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("UV/{nameUV}")
	public synchronized EncapsulationStage getStageDeUV(@PathParam("nameUV") String nameUV){ // cast en StageConcreteConcret necessaire au fonctionnement de JAXB

		List <StageConcret> StageDeUV=new ArrayList<StageConcret>();
		
		UV uvDemande=nomUV.get(nameUV);
		List<String> stageDeUVdemande=uvDemande.getStages();
		
		for (int i=0; i<stageDeUVdemande.size(); i++){
		StageDeUV.add((StageConcret)nomStage.get(stageDeUVdemande.get(i)));	
		}
		
		EncapsulationStage  StageAenvoyer=new EncapsulationStage (StageDeUV);
				
		return StageAenvoyer;
	}//fin getStageDeUV
	
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
		EcrireFichier.ecrirePompier(aDeco,pathPomp);//ecriture/ecrasement du fichier avec les infos contenues dans l'objet pompier 
		
		numConnection.remove(session);//suppression de l'entree lie a ce numero de session
		//prenom+" "+nom+
		return "OK";
		
	}//fin deconnexion
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("candidater/{session}/{nomStage}")
	public synchronized String candidater(@PathParam("session") int session, @PathParam("nomStage") String nomStage) throws IOException, URISyntaxException {
		//ajoute un stage ("nomStage") a la liste des stages "en cours" de l'objet pompier 
		//obtenu par son numero de session actuelle ("session")  
		 		
		Pompier aModif=numConnection.get(session);
		Stage actuel=this.nomStage.get(nomStage);
		Calendar today=Calendar.getInstance();
		
		
		
		if(actuel.getFinCandidature().after(today)){
			
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
				
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("desincription/{session}/{nomStage}")
	public synchronized String desincrire(@PathParam("session") int session, @PathParam("nomStage") String nomStage) throws URISyntaxException, IOException{
		
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
			
			String nomFich=actuel.getNomStage()+".sess";
			
			URL chemPath=getClass().getResource("/donnees/Stages/"+nomFich);
			URI chemin=chemPath.toURI();
						
			EcrireFichier.ecrireStage(actuel,pathStag);
				
			return "OK";
		}
		
		else{return "KO";}
		
	}//fin de desinscrire
	
	@GET	
	@Produces({MediaType.APPLICATION_JSON})
	@Path("directeur/{stage}/{date}")//date entrée sous la forme JJ.MM.AAAA
	public String cloturer(@PathParam("date") String date,@PathParam("stage") String stage) throws IOException, URISyntaxException{
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
				
				String nomFich=aModif.getNomStage()+".sess";
				
				URL chemPath=getClass().getResource("/donnees/Stages/"+nomFich); 
				
				URI chemin=chemPath.toURI();
				
				EcrireFichier.ecrireStage(aModif,pathStag);
		
				
		
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
	public synchronized String UpdateStage(StageConcret s) throws IOException, URISyntaxException{ // fonctionne malgré probleme d'affichage de certaine liste (pointeur null) lors des controles.
		
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
		nomStage.put(StageAUpdate.getNomStage(), StageAUpdate);
		
		String nomFich=StageAUpdate.getNomStage()+".sess";
		
		URL chemPath=getClass().getResource("/donnees/Stages/"+nomFich); 
		
		URI chemin=chemPath.toURI();
				
		EcrireFichier.ecrireStage(StageAUpdate,pathStag);
		
		
		String reponse="Le stage "+StageAUpdate.getNomStage()+" a ete mis a jour";
		
		return reponse;}
	
}
