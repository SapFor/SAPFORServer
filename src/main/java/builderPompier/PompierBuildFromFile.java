package builderPompier;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import outils.RecupInfoFichier;

//by Thomas Davin

public class PompierBuildFromFile implements PompierBuilder{

	private Pompier pompier;
	
	private String cheminFich;
			
		
	public PompierBuildFromFile(int id,String pathPomp){
		
		String fich=id+".pomp";
		cheminFich=pathPomp+fich;
				
		this.pompier=new PompierConcret();
	}
	
	
	@Override
	public void buildId() throws IOException{
		
			
		try{	
			 pompier.setId(Integer.parseInt(RecupInfoFichier.chercheDsFichier(cheminFich,"id")));
		}catch(IOException e){}	 
			
	}
	
	
	@Override
	public void buildMdp() throws IOException {
		try{	
			 pompier.setMdp(RecupInfoFichier.chercheDsFichier(cheminFich,"mdp"));
		}catch(IOException e){}	 
		
	}
	
	@Override
	public void buildDirecteur()throws IOException{
			
			 try {
				pompier.setDirecteur(RecupInfoFichier.chercheDsFichier(cheminFich,"directeur"));
			} catch (IOException e) {e.printStackTrace();}
			
		
		
	}
	
	@Override
	public void buildNom() throws IOException{
		
			 try {
				pompier.setNom(RecupInfoFichier.chercheDsFichier(cheminFich,"nom"));
			} catch (IOException e) {e.printStackTrace();}
			
		
	}

	@Override
	public void buildPrenom() throws IOException {
		// TODO Auto-generated method stub
		
		try{
			pompier.setPrenom(RecupInfoFichier.chercheDsFichier(cheminFich,"prenom"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildUV() throws IOException{
		// TODO Auto-generated method stub
		
		try{
			pompier.setUV(RecupInfoFichier.recupListDsFichier(cheminFich,"uv"));
		}catch(IOException e){e.printStackTrace();}	
	}

	@Override
	public void buildAccepte() throws IOException {
		
		try{
			pompier.setAccepte(RecupInfoFichier.recupListDsFichier(cheminFich,"accepte"));
						
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildEnCours() throws IOException {
		
		try{
			
			pompier.setEnCours(RecupInfoFichier.recupListDsFichier(cheminFich,"encours"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildAttente() throws IOException {
		// TODO Auto-generated method stub
		try{
			
			pompier.setAttente(RecupInfoFichier.recupListDsFichier(cheminFich,"attente"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildRefuse() throws IOException {
	
		try{
			
			pompier.setRefuse(RecupInfoFichier.recupListDsFichier(cheminFich,"refuse"));
						
		}catch(IOException e){e.printStackTrace();}
	}
	
	@Override
	public void buildGestion() throws IOException {
		try {
			
			pompier.setGestion(RecupInfoFichier.recupListDsFichier(cheminFich,"gestion"));
		 

		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	@Override
	public Pompier getPompier(){
		
		return this.pompier;
	}


	


	

}
