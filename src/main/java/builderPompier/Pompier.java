package builderPompier;

import java.util.List;

import observerCandidats.Observateur;


//by Thomas Davin

public interface Pompier extends Observateur {
	
	//interface de l'objet pompier
	
	
	/**
	 * 	setId replaces the pompier object's id number with the one given in parameter
	 * @param id
	 */
	
	public void setIdSession(int idSession);
	
	public int getIdSession();
	
	public void setId(int id);
	
	public int getId();
	
	public void setMdp(String mdp);
	
	public String getMdp();
	
	public void setDirecteur(String directeur);
	
	public String getDirecteur();
	
	
	/**
	 * setNom replaces the pompier object's nom field with the one given in parameter
	 * @param nom name of pompier
	 */
		
	public void setNom(String nom);
	
	public String getNom();
	
	/**
	 * setPrenom replaces the pompier object's prenom field with the one given in parameter
	 * 
	 * @param prenom first name of pompier
	 */
	
	public void setPrenom(String prenom);
	
	public String getPrenom();
	
	/**
	 *setUV replaces the pompier object's liste of UV by the one given in parameter 
	 * @param UV
	 */
	
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
