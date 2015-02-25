package builderUV;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;



//by Thomas Davin
@XmlRootElement
public class UVConcret implements UV {
	
	private String nom;
	private String descr;
	private List<String> session;
	
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
	public void setSessions(List<String> session) {
		// TODO Auto-generated method stub
		
		this.session=session;
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
	public List<String> getSessions() {
		// TODO Auto-generated method stub
		return session;
	}

}
