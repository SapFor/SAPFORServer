package builderPompier;

import java.util.List;

import observerCandidats.Observateur;


//by Thomas Davin

public interface Pompier extends Observateur {
	
	//*****
	//interface de l'objet pompier
	//*****
	
		
	public void setIdSession(int idSession);
			
	public int getIdSession();
	
	public void setId(int id);
	
	public int getId();
	
	public void setMdp(String mdp);
	
	public String getMdp();
	
	public void setDirecteur(String directeur);
	
	public String getDirecteur();
	
	public void setNom(String nom);
	
	public String getNom();
	
	public void setPrenom(String prenom);
	
	public String getPrenom();
	
	public void setUV(List<String> UV);
	
	public List<String> getUV();
	
	public void setEnCours(List<String> enCours);
	
	public List<String> getEnCours();
	
	public void setAccepte(List<String> accepte);
	
	public List<String> getAccepte();
	
	public void setAttente(List<String> attente);
	
	public List<String> getAttente();
	
	public void setRefuse(List<String> refuse);
	
	public List<String> getRefuse();
	
	public void setGestion(List<String> gestion);
	
	public List<String> getGestion();
	
}
