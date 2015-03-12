package builderUV;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;



//by Thomas Davin

@XmlRootElement
public class UVConcret implements UV {
	
	private String nom;//nom de l'UV
	
	private String descr;//description de l'UV
	
	private List<String> stage;//liste des stages disponibles pour l'UV
	
	
	@Override
	public void setNom(String nom) {
		// TODO Auto-generated method stub
		
		this.nom=nom;
	}
	
	
	@Override
	public void setDescr(String descr) {
		// TODO Auto-generated method stub
		
		this.descr=descr;
	}

	@Override
	public void setStages(List<String> session) {
		// TODO Auto-generated method stub
		
		this.stage=session;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public String getDescr() {
		// TODO Auto-generated method stub
		return descr;
	}

	@Override
	public List<String> getStages() {
		// TODO Auto-generated method stub
		return stage;
	}

}
