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
	private URI cheminFich;
	//private BufferedReader input;
	
	public StageBuildFromFile(String label) throws URISyntaxException{
		
		String fich=label+".sess";
		URL fichier=getClass().getResource("/donnees/Stages/"+fich);
		cheminFich=fichier.toURI();
		
		this.session=new StageConcret();
		
		
		/*try{
			input = new BufferedReader(new FileReader(new File(cheminFich)));
			input.mark(2000);	
			
			}catch(IOException e){System.out.println("Aucun fichier "+fichier+" existant!");}
	*/	
	}
	
	@Override
	public void buildNomStage() throws IOException {
		// TODO Auto-generated method stub
		
		Calendar jourJ;
		
		try{
			
			String uv=RecupInfoFichier.chercheDsFichier(cheminFich,"uv");
			String lieu=RecupInfoFichier.chercheDsFichier(cheminFich,"lieu");
			jourJ=RecupInfoFichier.chercheDateDsFichier(cheminFich,"date");
			
			
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
			jourJ=RecupInfoFichier.chercheDateDsFichier(cheminFich,"date");
			
			session.setDate(jourJ);
		}catch(IOException e){e.printStackTrace();}
	}
	
	@Override
	public void buildFinCandidature() throws IOException{
		
		Calendar finCandidature;
		try{
			
			finCandidature=RecupInfoFichier.chercheDateDsFichier(cheminFich,"finCandidature");
							
			
			session.setFinCandidature(finCandidature);
		}catch(IOException e){e.printStackTrace();}
	}
	
	@Override
	public void buildLieu() throws IOException {
		// TODO Auto-generated method stub
		try{
			session.setLieu(RecupInfoFichier.chercheDsFichier(cheminFich,"lieu"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildInfos() throws IOException {
		// TODO Auto-generated method stub
		try{
			session.setInfos(RecupInfoFichier.recupStringDsFichier(cheminFich,"infos"));
		}catch(IOException e){e.printStackTrace();}	
	}

	@Override
	public void buildCandidats() throws IOException {
		try{
			session.setCandidats(RecupInfoFichier.recupListDsFichier(cheminFich,"candidats"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildAccepte() throws IOException {
		try{
			session.setAccepte(RecupInfoFichier.recupListDsFichier(cheminFich,"accepte"));
		}catch(IOException e){e.printStackTrace();}	
	}

	@Override
	public void buildAttente() throws IOException {
		
		try{
			session.setAttente(RecupInfoFichier.recupListDsFichier(cheminFich,"attente"));
		}catch(IOException e){e.printStackTrace();}	
	}

	@Override
	public void buildRefuse() throws IOException {
		// TODO Auto-generated method stub
		try {
		session.setRefuse(RecupInfoFichier.recupListDsFichier(cheminFich,"refuse"));
		
			
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
			session.setUV(RecupInfoFichier.chercheDsFichier(cheminFich,"uv"));
		}catch(IOException e){e.printStackTrace();}
	}



}
