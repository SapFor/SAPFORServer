package builderStage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import outils.RecupInfoFichier;

//by Thomas Davin

public class StageBuildFromFile implements StageBuilder {
		
	private Stage session;
	private String fichier="";//insérer le chemin menant aux fichiers session
	private BufferedReader input;
	
	public StageBuildFromFile(String label){
		
		this.session=new StageConcrete();
		this.fichier=fichier+label+".sess";
		
		try{
			input = new BufferedReader(new FileReader(fichier));
			input.mark(2000);	
			
			}catch(IOException e){System.out.println("Aucun fichier "+fichier+" existant!");}
		
	}
	
	@Override
	public void buildDate() throws IOException {
		// TODO Auto-generated method stub
		try{
		 session.setDate(RecupInfoFichier.chercheDsFichier(input,"date"));
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
		session.setAttente(RecupInfoFichier.recupListDsFichier(input,"refuse"));
		
			input.close();
		} catch (IOException e) {e.printStackTrace();}
	}

	
	
	@Override
	public Stage getSession() {
		// TODO Auto-generated method stub
		return this.session;
	}

}
