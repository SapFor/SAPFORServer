package builderStage;

import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

import outils.Encapsulation;
import observerCandidats.Observateur;

@XmlRootElement
public class StageEnvoi {
		
	private String uv;
	//private int directeur;
	private String nomStage;
	private Calendar date;
	private Calendar finCandidature;
	private String lieu;
	private String infos;
	private Encapsulation candidats;
	private Encapsulation accepte;
	private Encapsulation refuse;
	private Encapsulation attente;
	//private ArrayList<Observateur> ListPompierCandidat;
	
	
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
	
	public StageEnvoi(){};
	
	public StageEnvoi(StageConcret stage){
		this.uv=stage.getUV();
		this.nomStage=stage.getNomStage();
		this.date=stage.getDate();
		this.finCandidature=stage.getFinCandidature();
		this.lieu=stage.getLieu();
		this.infos=stage.getInfos();
		this.candidats=new Encapsulation(stage.getCandidats());
		this.accepte=new Encapsulation(stage.getAccepte());
		this.refuse=new Encapsulation(stage.getRefuse());
		this.attente=new Encapsulation(stage.getAttente());
		//this.ListPompierCandidat=(ArrayList<Observateur>) stage.getListPompierCandidat();
	};
	
	public void setNomStage(String nom){
		this.nomStage=nom;
		
	}
	
	
	public String getNomStage(){
		return nomStage;
		
	}
	
	
	public void setUV(String UV) {
		// TODO Auto-generated method stub
		this.uv=UV;
	}

	
	public String getUV() {
		// TODO Auto-generated method stub
		return uv;
	}
	
	
	public void setDate(Calendar date) {
		
		this.date=date;
	}

	
	
	public Calendar getDate() {
		// TODO Auto-generated method stub
				
		return this.date;
	}
		
	
	public Calendar getFinCandidature(){
		
		return finCandidature;
		
	}
	
	
	public void setFinCandidature(Calendar date){
		
		this.finCandidature=date;
	}
	
	
	public void setLieu(String lieu) {
		
		this.lieu=lieu;
		
	}

	
	public String getLieu() {
		// TODO Auto-generated method stub
		return lieu;
	}

	
	public void setInfos(String infos) {
		
		
		this.infos=infos;
		
	}

	
	public String getInfos() {
		// TODO Auto-generated method stub
		return infos;
	}

	
	public void setCandidats(Encapsulation candidats) {
		// TODO Auto-generated method stub
		this.candidats=candidats;
	}

	
	public Encapsulation getCandidats() {
		// TODO Auto-generated method stub
		return candidats;
	}

	
	public void setAccepte(Encapsulation accepte) {
		// TODO Auto-generated method stub
		this.accepte=accepte;
	}

	
	public Encapsulation getAccepte() {
		// TODO Auto-generated method stub
		return accepte;
	}

	
	public void setAttente(Encapsulation attente) {
		// TODO Auto-generated method stub
		this.attente=attente;
	}

	
	public Encapsulation getAttente() {
		// TODO Auto-generated method stub
		return attente;
	}

	
	public void setRefuse(Encapsulation refuse) {
		// TODO Auto-generated method stub
		this.refuse=refuse;
	}

	
	public Encapsulation getRefuse() {
		// TODO Auto-generated method stub
		return refuse;
	}

	
	/*public void inscription(Observateur o) {
		
		ListPompierCandidat.add(o);
		
	}

	
	public void desincription(Observateur o) {
		
		ListPompierCandidat.remove(o);
		
	}

	
	public void notifier() {
		
	     {
             for(int i=0;i<ListPompierCandidat.size();i++)
             {
                     Observateur o = ListPompierCandidat.get(i);
                     o.actualiser(this);
             }
     }
		
		
	}*/



	


}
