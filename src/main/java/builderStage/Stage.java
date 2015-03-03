package builderStage;

import java.util.Calendar;
import java.util.List;

import observerCandidats.Sujet;



//by Thomas Davin


public interface Stage extends Sujet {
	
	public String getNomStage();
	
	public void setNomStage(String nom);
	
	public void setUV(String UV);
	
	public String getUV();
	
	/*public int getDirecteur();
	
	public void setDirecteur(int idDirecteur);*/
	
	public void setDate(Calendar date);
	
	public Calendar getDate();
	
	public Calendar getFinCandidature();
	
	public void setFinCandidature(Calendar date);
	
	public void setLieu(String lieu);
	
	public String getLieu();
	
	public void setInfos(String infos);
	
	public String getInfos();
	
	public void setCandidats(List<String> candidats);
	
	public List<String> getCandidats();
	
	public void setAccepte(List<String> accepte);
	
	public List<String> getAccepte();
	
	public void setAttente(List<String> attente);
	
	public List<String> getAttente();
	
	public void setRefuse(List<String> refuse);
	
	public List<String> getRefuse();
	
}
