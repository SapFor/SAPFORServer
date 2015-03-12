package builderPompier;

import java.io.IOException;
import outils.RecupInfoFichier;

//by Thomas Davin

public class PompierBuildFromFile implements PompierBuilder{

	private Pompier pompier;
	
	private String cheminFich;
			
		
	public PompierBuildFromFile(int id,String pathPomp){
		
		String fich=id+".pomp";
		cheminFich=pathPomp+fich;
				
		this.pompier=new PompierConcret();
		
	}//fin constructeur
	
	
	@Override
	public void buildId() throws IOException{
		//remplit le numero d'agent dans l'objet Pompier avec celui present dans le fichier correspondant
			
		try{	
			 pompier.setId(Integer.parseInt(RecupInfoFichier.chercheDsFichier(cheminFich,"id")));
		}catch(IOException e){}	 
			
	}
	
	
	@Override
	public void buildMdp() throws IOException {
		//remplit le mot de passe dans l'objet Pompier avec celui present dans le fichier correspondant
		try{	
			 pompier.setMdp(RecupInfoFichier.chercheDsFichier(cheminFich,"mdp"));
		}catch(IOException e){}	 
		
	}
	
	@Override
	public void buildDirecteur()throws IOException{
		//remplit l'etat directeur dans l'objet Pompier avec celui present dans le fichier correspondant
			 try {
				pompier.setDirecteur(RecupInfoFichier.chercheDsFichier(cheminFich,"directeur"));
			} catch (IOException e) {e.printStackTrace();}
			
		
		
	}
	
	@Override
	public void buildNom() throws IOException{
		//remplit le nom dans l'objet Pompier avec celui present dans le fichier correspondant
			 try {
				pompier.setNom(RecupInfoFichier.chercheDsFichier(cheminFich,"nom"));
			} catch (IOException e) {e.printStackTrace();}
			
		
	}

	@Override
	public void buildPrenom() throws IOException {
		//remplit le prenom dans l'objet Pompier avec celui present dans le fichier correspondant
		
		try{
			pompier.setPrenom(RecupInfoFichier.chercheDsFichier(cheminFich,"prenom"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildUV() throws IOException{
		//remplit la liste des UVs obtenues dans l'objet Pompier avec celui present dans le fichier correspondant
		
		try{
			pompier.setUV(RecupInfoFichier.recupListDsFichier(cheminFich,"uv"));
		}catch(IOException e){e.printStackTrace();}	
	}

	@Override
	public void buildAccepte() throws IOException {
		//remplit la liste des stages acceptes dans l'objet Pompier avec celui present dans le fichier correspondant
		try{
			pompier.setAccepte(RecupInfoFichier.recupListDsFichier(cheminFich,"accepte"));
						
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildEnCours() throws IOException {
		//remplit la liste des stages postules dans l'objet Pompier avec celui present dans le fichier correspondant
		try{
			
			pompier.setEnCours(RecupInfoFichier.recupListDsFichier(cheminFich,"encours"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildAttente() throws IOException {
		//remplit la liste des stages en liste d'attente dans l'objet Pompier avec celui present dans le fichier correspondant
		try{
			
			pompier.setAttente(RecupInfoFichier.recupListDsFichier(cheminFich,"attente"));
		}catch(IOException e){e.printStackTrace();}
	}

	@Override
	public void buildRefuse() throws IOException {
		//remplit la liste des stages refuses dans l'objet Pompier avec celui present dans le fichier correspondant
		try{
			
			pompier.setRefuse(RecupInfoFichier.recupListDsFichier(cheminFich,"refuse"));
						
		}catch(IOException e){e.printStackTrace();}
	}
	
	@Override
	public void buildGestion() throws IOException {
		//remplit la liste des stages geres dans l'objet Pompier avec celui present dans le fichier correspondant
		try {
			
			pompier.setGestion(RecupInfoFichier.recupListDsFichier(cheminFich,"gestion"));
		 

		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	@Override
	public Pompier getPompier(){
		
		return this.pompier;
	}


	


	

}
