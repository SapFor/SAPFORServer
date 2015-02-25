package builderUV;

import java.util.List;

//by Thomas Davin

public interface UV {

	public void setNom(String nom);
	
	public String getNom();
	
	public void setDescr(String descr);
	
	public String getDescr();
	
	public void setSessions(List<String> session);	
	
	public List<String> getSessions();
	
}
