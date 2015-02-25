package builderPompier;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import outils.RecupInfoFichier;

//by Thomas Davin

public class PompierBuildFromFile implements PompierBuilder{

	private Pompier pompier;
	private String fichier="";//insérer le chemin jusqu'aux fichiers des agents
	private BufferedReader input;
	
	public PompierBuildFromFile(int id){
		
		this.pompier=new Pompier();
		this.fichier=fichier+id+".pomp";
		
		try{
			 input = new BufferedReader(new FileReader(fichier));
			 input.mark(2000);	
			
			}catch(Exception e){System.out.println("Aucun fichier "+fichier+" existant!");}
		
		
	}
	
	
	@Override
	public void buildId() throws IOException{
		// TODO Auto-generated method stub
			
		try{	
			 pompier.setId(Integer.parseInt(RecupInfoFichier.chercheDsFichier(input,"id")));
		}catch(IOException e){}	 
			
	}
	
	
	@Override
	public void buildMdp() throws IOException {
		try{	
			 pompier.setMdp(RecupInfoFichier.chercheDsFichier(input,"mdp"));
		}catch(IOException e){}	 
		
	}
	
	@Override
	public void buildDirecteur(){
			
			 try {
				pompier.setDirecteur(RecupInfoFichier.chercheDsFichier(input,"directeur"));
			} catch (IOException e) {e.printStackTrace();}
			
		
		
	}
	
	@Override
	public void buildNom() throws IOException{
		
			 try {
				pompier.setNom(RecupInfoFichier.chercheDsFichier(input,"nom"));
			} catch (IOException e) {e.printStackTrace();}
			
		
	}

	@Override
	public void buildPrenom() throws IOException {
		// TODO Auto-generated method stub
		
		try{
			pompier.setPrenom(RecupInfoFichier.chercheDsFichier(input,"prenom"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildUV() throws IOException{
		// TODO Auto-generated method stub
		
		try{
			pompier.setUV(RecupInfoFichier.recupListDsFichier(input,"uv"));
		}catch(IOException e){e.printStackTrace();}	
	}

	@Override
	public void buildAccepte() throws IOException {
		
		try{
			pompier.setAccepte(RecupInfoFichier.recupListDsFichier(input,"accepte"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildEnCours() throws IOException {
		
		try{
			pompier.setEnCours(RecupInfoFichier.recupListDsFichier(input,"encours"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildAttente() throws IOException {
		// TODO Auto-generated method stub
		try{
			pompier.setAttente(RecupInfoFichier.recupListDsFichier(input,"attente"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildRefuse() throws IOException {
	
		try{
			pompier.setRefuse(RecupInfoFichier.recupListDsFichier(input,"refuse"));
		}catch(IOException e){e.printStackTrace();}
	}
	
	@Override
	public void buildGestion() throws IOException {
		try {
			pompier.setGestion(RecupInfoFichier.recupListDsFichier(input,"gestion"));
		 
			input.close();
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	@Override
	public Pompier getPompier(){
		
		return this.pompier;
	}


	


	

}
