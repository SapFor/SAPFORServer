package builderStage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import builderPompier.PompierConcret;
import observerCandidats.Observateur;
import observerCandidats.Sujet;





//by Thomas Davin

@XmlRootElement
public class StageConcret implements Stage, Sujet {
	
	
	public String uv;
	//private int directeur;
	public String nomStage;
	public Calendar date;
	public Calendar finCandidature;
	public String lieu;
	public String infos;
	public List<String> candidats;
	public List<String> accepte;
	public List<String> refuse;
	public List<String> attente;
	public ArrayList<PompierConcret> ListPompierCandidat;
	
	
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
	public void setCandidats(List<String> candidat) {
		// TODO Auto-generated method stub
		this.candidats=candidat;
	}

	@Override
	public List<String> getCandidats() {
		// TODO Auto-generated method stub
		return candidats;
	}

	@Override
	public void setAccepte(List<String> acceptes) {
		// TODO Auto-generated method stub
		this.accepte=acceptes;
	}
/*
	@Override
	public List<String> getAccepte() {
		// TODO Auto-generated method stub
		return accepte;
	}*/

	@Override
	public void setAttente(List<String> attentes) {
		// TODO Auto-generated method stub
		this.attente=attentes;
	}

	@Override
	public List<String> getAttente() {
		// TODO Auto-generated method stub
		return attente;
	}

	@Override
	public void setRefuse(List<String> refuses) {
		// TODO Auto-generated method stub
		this.refuse=refuses;
	}

	@Override
	public List<String> getRefuse() {
		// TODO Auto-generated method stub
		return refuse;
	}

	@Override
	public void inscription(PompierConcret o) {
		
		ListPompierCandidat.add(o);
		
	}

	@Override
	public void desincription(PompierConcret o) {
		
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
