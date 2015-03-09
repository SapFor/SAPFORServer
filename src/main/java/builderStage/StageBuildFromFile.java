package builderStage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;


import outils.RecupInfoFichier;

//by Thomas Davin

public class StageBuildFromFile implements StageBuilder {
		
	private Stage session;
	private URL fichier;
	private BufferedReader input;
	
	public StageBuildFromFile(String label) throws URISyntaxException{
		
		String fich=label+".sess";
		
		fichier=getClass().getResource("/donnees/Stages/"+fich);
		URI cheminFich=fichier.toURI();
		
		this.session=new StageConcret();
		
		
		try{
			input = new BufferedReader(new FileReader(new File(cheminFich)));
			input.mark(2000);	
			
			}catch(IOException e){System.out.println("Aucun fichier "+fichier+" existant!");}
		
	}
	
	@Override
	public void buildNomStage() throws IOException {
		// TODO Auto-generated method stub
		
		Calendar jourJ;
		
		try{
			
			String uv=RecupInfoFichier.chercheDsFichier(input,"uv");
			String lieu=RecupInfoFichier.chercheDsFichier(input,"lieu");
			jourJ=RecupInfoFichier.chercheDateDsFichier(input,"date");
			
			
			int jour=jourJ.get(Calendar.DAY_OF_MONTH);
			int mois=jourJ.get(Calendar.MONTH);
			int annee=jourJ.get(Calendar.YEAR)%100;
			
			String moisS;
			
			switch(mois){
			case 0: moisS="janv";break;
			case 1: moisS="fev";break;
			case 2: moisS="mars";break;
			case 3: moisS="avr";break;
			case 4: moisS="mai";break;
			case 5: moisS="juin";break;
			case 6: moisS="juil";break;
			case 7: moisS="aout";break;
			case 8: moisS="sept";break;
			case 9: moisS="oct";break;
			case 10: moisS="nov";break;
			case 11: moisS="dec";break;
			default: moisS="Pbm";break;
			}
			
			String nom=uv+lieu+jour+moisS+annee;
						
			session.setNomStage(nom);
		}catch(IOException e){e.printStackTrace();}
	}
	
	
	@Override
	public void buildDate() throws IOException {
		// TODO Auto-generated method stub
		
		Calendar jourJ;
		
		try{
			
			jourJ=RecupInfoFichier.chercheDateDsFichier(input,"date");
			
			session.setDate(jourJ);
		}catch(IOException e){e.printStackTrace();}
	}
	
	@Override
	public void buildFinCandidature() throws IOException{
		
		Calendar finCandidature;
		try{
			
			finCandidature=RecupInfoFichier.chercheDateDsFichier(input,"finCandidature");
							
			
			session.setFinCandidature(finCandidature);
		}catch(IOException e){e.printStackTrace();}
	}
	
	@Override
	public void buildLieu() throws IOException {
		// TODO Auto-generated method stub
		try{
			session.setLieu(RecupInfoFichier.chercheDsFichier(input,"lieu"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildInfos() throws IOException {
		// TODO Auto-generated method stub
		try{
			session.setInfos(RecupInfoFichier.recupStringDsFichier(input,"infos"));
		}catch(IOException e){e.printStackTrace();}	
	}

	@Override
	public void buildCandidats() throws IOException {
		try{
			session.setCandidats(RecupInfoFichier.recupListDsFichier(input,"candidats"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildAccepte() throws IOException {
		try{
			session.setAccepte(RecupInfoFichier.recupListDsFichier(input,"accepte"));
		}catch(IOException e){e.printStackTrace();}	
	}

	@Override
	public void buildAttente() throws IOException {
		
		try{
			session.setAttente(RecupInfoFichier.recupListDsFichier(input,"attente"));
		}catch(IOException e){e.printStackTrace();}	
	}

	@Override
	public void buildRefuse() throws IOException {
		// TODO Auto-generated method stub
		try {
		session.setRefuse(RecupInfoFichier.recupListDsFichier(input,"refuse"));
		
			input.close();
		} catch (IOException e) {e.printStackTrace();}
	}

	@Override
	public void buildListePompierCandidat() throws Exception {
		// TODO Auto-generated method stub
		try{session.initListePompierCandidat();
		}
		catch(Exception e){e.printStackTrace();}
	}
	
	@Override
	public Stage getSession() {
		// TODO Auto-generated method stub
		return this.session;
	}

	@Override
	public void buildUV() throws IOException {
		// TODO Auto-generated method stub
		try{
			session.setUV(RecupInfoFichier.chercheDsFichier(input,"uv"));
		}catch(IOException e){e.printStackTrace();}
	}



}
