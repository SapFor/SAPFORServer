package builderPompier;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import builderStage.Stage;
import observerCandidats.Observateur;



//by Thomas Davin
@XmlRootElement
public class PompierConcret implements Pompier, Observateur {
	
	//implémentation de la structure Pompier
	//permet la création d'un objet pompier  a partir d'un fichier avec le meme numero d'agent
	
	private int idSession;//n° de la session ouverte papr le client
	
	private int id;//n° d'agent du pompier
	
	private String mdp;// mot de passe du pompier
	
	private String directeur;//indication si le pompier est directeur/responsable de stage ou non
	
	private String nom;//nom du pompier
	
	private String prenom;//prenom du pompier
	
	private List<String> UV;//liste des UVs obtenues par le pompier
	
	private List<String> accepte;//liste des stages pour lesquels le pompier a ete accepte
	
	private List<String> attente;//liste des statges dans lesquels le pompier est en file d'attente
	
	private List<String> enCours;//liste des stages dans lesquels le pompier a candidater (en attente de decision du responsable de stage)
	
	private List<String> refuse;//liste des stages pour lesquels le pompier a ete refuse
	
	private List<String> gestion;//liste des stages geres par le pompier si celui-ci est responsable de stage
	
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
	public void setDirecteur(String directeur) {this.directeur=directeur;}
		
		
	@Override
	public String getDirecteur(){return this.directeur;}
	

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
			accepte.add(s.getNomStage());
		}
		else if (this.estDansListe(CandidatRefuse)){
			refuse.add(s.getNomStage());
		}
		else {attente.add(s.getNomStage());}
		
		this.getEnCours().remove(s.getNomStage());
		
		
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
