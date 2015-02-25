package builderStage;

import java.util.List;



//by Thomas Davin


public interface Stage {

	public void setUV(String UV);
	
	public String getUV();
	
	public void setDate(String date);
	
	public String getDate();
	
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
