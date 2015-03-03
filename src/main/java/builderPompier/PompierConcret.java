package builderPompier;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import builderStage.Stage;
import observerCandidats.Observateur;
import observerCandidats.Sujet;


//by Thomas Davin
@XmlRootElement
public class PompierConcret implements Pompier, Observateur {
	
	//implémentation de la structure Pompier
	//permet la création d'un objet pompier  � partir d'un fichier avec le m�me numero d'agent
	
	private int idSession;
	
	private int id;
	
	private String mdp;
	
	private boolean directeur;
	
	private String nom;
	
	private String prenom;
	
	private List<String> UV;
	
	private List<String> accepte;
	
	private List<String> attente;
	
	private List<String> enCours;
	
	private List<String> refuse;
	
	private List<String> gestion;
	
	@Override
	public void setIdSession(int idSession){
		this.idSession=idSession;
	}
	
	@Override
	public int getIdSession(){
		return idSession;
	}
	
	@Override
	public void setId(int id) {
				
		this.id=id;
		
	}
	@Override
	public int getId(){return id;}
	
	@Override
	public void setMdp(String mdp) {
				
		this.mdp=mdp;
		
	}
	@Override
	public String getMdp(){return mdp;}
	
	@Override
	public void setDirecteur(String directeur) {
		
		if(directeur.equals("oui")){this.directeur=true;}
		else{this.directeur=false;}
		
	}
		
	@Override
	public boolean getDirecteur(){return directeur;}
	

	@Override
	public void setNom(String nom) {
		// TODO Auto-generated method stub
		this.nom=nom;
	}
	
	@Override
	public String getNom(){return nom;}

	@Override
	public void setPrenom(String prenom) {
		// TODO Auto-generated method stub
		this.prenom=prenom;
	}

	@Override
	public String getPrenom(){return prenom;}
	
	@Override
	public void setUV(List<String> UV) {
		
		this.UV=UV;
	}
	
	@Override
	public List<String> getUV(){return UV;}
	
	@Override
	public void setEnCours(List<String> enCours) {
		// TODO Auto-generated method stub
		
		this.enCours=enCours;
		
	}
	
	@Override
	public List<String> getEnCours(){return enCours;}

	@Override
	public void setAccepte(List<String> accepte) {
		// TODO Auto-generated method stub
		
		this.accepte=accepte;
		
	}
	
	@Override
	public List<String> getAccepte(){return accepte;}

	@Override
	public void setAttente(List<String> attente) {
		// TODO Auto-generated method stub
		
		this.attente=attente;
		
	}
	
	@Override
	public List<String> getAttente(){return attente;}

	@Override
	public void setRefuse(List<String> refuse) {
		// TODO Auto-generated method stub
		
		this.refuse=refuse;
		
	}
	
	@Override
	public List<String> getRefuse(){return refuse;}

	@Override
	public void setGestion(List<String> gestion) {
		// TODO Auto-generated method stub
		
		this.gestion=gestion;
		
	}
	
	@Override
	public List<String> getGestion(){return gestion;}

	@Override
	public void actualiser(Stage s) {
		
		List <String> CandidatAccepte;
		List <String> CandidatRefuse;
		//List <String> CandidatEnAttente;
		
		CandidatAccepte=s.getAccepte();
		CandidatRefuse=s.getRefuse();
		//CandidatEnAttente=s.getAttente();
		
		if (this.estDansListe(CandidatAccepte)){
			accepte.add(null);
		}
		else if (this.estDansListe(CandidatRefuse)){
			refuse.add(null);
		}
		else {attente.add(null);}
		
		enCours.remove(null);
		
		
	}
	
	private boolean estDansListe (List<String> l){
		
		boolean trouve=false;
		int i=0;
		while (trouve==false && i<l.size()){
			if (this.id !=Integer.parseInt(l.get(i))){i++;}
			else {trouve=true;}
		}
		
		return trouve;		
	}




	
}
