/*package builderStage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	private List<Encapsulation> candidats;
	private List<Encapsulation> accepte;
	private List<Encapsulation> refuse;
	private List<Encapsulation> attente;
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
	}
	
	public StageEnvoi(){};
	
	public StageEnvoi(StageConcret stage){
		this.uv=stage.getUV();
		this.nomStage=stage.getNomStage();
		this.date=stage.getDate();
		this.finCandidature=stage.getFinCandidature();
		this.lieu=stage.getLieu();
		this.infos=stage.getInfos();
		
		this.candidats=new ArrayList <Encapsulation>();
		
		for (int i=0; i<stage.getCandidats().size(); i++){
			candidats.add(new Encapsulation(stage.getCandidats().get(i)));
		};
		
		this.accepte=new ArrayList <Encapsulation>();
		
		for (int i=0; i<stage.getAccepte().size(); i++){
			accepte.add(new Encapsulation(stage.getAccepte().get(i)));
		};
		
		this.refuse=new ArrayList <Encapsulation>();
		
		for (int i=0; i<stage.getRefuse().size(); i++){
			refuse.add(new Encapsulation(stage.getRefuse().get(i)));
		};
		
		this.attente=new ArrayList <Encapsulation>();
		
		for (int i=0; i<stage.getAttente().size(); i++){
			attente.add(new Encapsulation(stage.getAttente().get(i)));
		};

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

	
	public void setCandidats(List<Encapsulation> candidats) {
		// TODO Auto-generated method stub
		this.candidats=candidats;
	}

	
	public List<Encapsulation> getCandidats() {
		// TODO Auto-generated method stub
		return candidats;
	}

	
	public void setAccepte(List<Encapsulation> accepte) {
		// TODO Auto-generated method stub
		this.accepte=accepte;
	}

	
	public List<Encapsulation> getAccepte() {
		// TODO Auto-generated method stub
		return accepte;
	}

	
	public void setAttente(List<Encapsulation> attente) {
		// TODO Auto-generated method stub
		this.attente=attente;
	}

	
	public List<Encapsulation> getAttente() {
		// TODO Auto-generated method stub
		return attente;
	}

	
	public void setRefuse(List<Encapsulation> refuse) {
		// TODO Auto-generated method stub
		this.refuse=refuse;
	}

	
	public List<Encapsulation> getRefuse() {
		// TODO Auto-generated method stub
		return refuse;
	}

	
	public void inscription(Observateur o) {
		
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
		
		
	}



	


}
*/