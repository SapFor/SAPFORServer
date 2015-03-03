package builderStage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import observerCandidats.Observateur;
import observerCandidats.Sujet;





//by Thomas Davin

@XmlRootElement
public class StageConcret implements Stage, Sujet {
	
	
	private String uv;
	//private int directeur;
	private String nomStage;
	private Calendar date;
	private Calendar finCandidature;
	private String lieu;
	private String infos;
	private List<String> candidats;
	private List<String> accepte;
	private List<String> refuse;
	private List<String> attente;
	private ArrayList<Observateur> ListPompierCandidat;
	
	
	/*@Override
	public void setDirecteur(int idDirecteur) {
		// TODO Auto-generated method stub
		this.directeur=idDirecteur;
	}

	@Override
	public int getDirecteur() {
		// TODO Auto-generated method stub
		return directeur;
	}*/
	
	@Override
	public void setNomStage(String nom){
		this.nomStage=nom;
		
	}
	
	@Override
	public String getNomStage(){
		return nomStage;
		
	}
	
	@Override
	public void setUV(String UV) {
		// TODO Auto-generated method stub
		this.uv=UV;
	}

	@Override
	public String getUV() {
		// TODO Auto-generated method stub
		return uv;
	}
	
	@Override
	public void setDate(Calendar date) {
		
		this.date=date;
	}

	
	@Override
	public Calendar getDate() {
		// TODO Auto-generated method stub
				
		return this.date;
	}
		
	@Override
	public Calendar getFinCandidature(){
		
		return finCandidature;
		
	}
	
	@Override
	public void setFinCandidature(Calendar date){
		
		this.finCandidature=date;
	}
	
	@Override
	public void setLieu(String lieu) {
		
		this.lieu=lieu;
		
	}

	@Override
	public String getLieu() {
		// TODO Auto-generated method stub
		return lieu;
	}

	@Override
	public void setInfos(String infos) {
		
		
		this.infos=infos;
		
	}

	@Override
	public String getInfos() {
		// TODO Auto-generated method stub
		return infos;
	}

	@Override
	public void setCandidats(List<String> candidats) {
		// TODO Auto-generated method stub
		this.candidats=candidats;
	}

	@Override
	public List<String> getCandidats() {
		// TODO Auto-generated method stub
		return candidats;
	}

	@Override
	public void setAccepte(List<String> accepte) {
		// TODO Auto-generated method stub
		this.accepte=accepte;
	}

	@Override
	public List<String> getAccepte() {
		// TODO Auto-generated method stub
		return accepte;
	}

	@Override
	public void setAttente(List<String> attente) {
		// TODO Auto-generated method stub
		this.attente=attente;
	}

	@Override
	public List<String> getAttente() {
		// TODO Auto-generated method stub
		return attente;
	}

	@Override
	public void setRefuse(List<String> refuse) {
		// TODO Auto-generated method stub
		this.refuse=refuse;
	}

	@Override
	public List<String> getRefuse() {
		// TODO Auto-generated method stub
		return refuse;
	}

	@Override
	public void inscription(Observateur o) {
		
		ListPompierCandidat.add(o);
		
	}

	@Override
	public void desincription(Observateur o) {
		
		ListPompierCandidat.remove(o);
		
	}

	@Override
	public void notifier() {
		
	     {
             for(int i=0;i<ListPompierCandidat.size();i++)
             {
                     Observateur o = ListPompierCandidat.get(i);
                     o.actualiser(this);
             }
     }
		
		
	}



	

}
