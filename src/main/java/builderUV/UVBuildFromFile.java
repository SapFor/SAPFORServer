package builderUV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import outils.RecupInfoFichier;

//by Thomas Davin

public class UVBuildFromFile implements UVbuilder {

	private UV uv;
	private String cheminFich;
	
	
	public UVBuildFromFile(String uv,String pathUVs) throws URISyntaxException, MalformedURLException{
		
		String fich=uv+".uv";
		cheminFich=pathUVs+fich;//System.getProperty("user.dir")+"/../donnees/UVs/"+fich;
		//System.out.println(chemPath);
		
		//URL fichier=new URL(chemPath);
		//cheminFich=fichier.toURI();
		
		this.uv=new UVConcret();
		
		
		/*try{
			cheminFich = new BufferedReader(new FileReader(new File(cheminFich)));
			 cheminFich.mark(2000);	
			
			}catch(Exception e){System.out.println("Aucun fichier "+fichier+" existant!");}
			*/	
	}

	@Override
	public void buildNom() throws IOException{
		try{
			uv.setNom(RecupInfoFichier.chercheDsFichier(cheminFich,"nom"));
		}catch(IOException e) {e.printStackTrace();}
	}

	@Override
	public void buildDescr() throws IOException{
		try{
		
			uv.setDescr(RecupInfoFichier.recupStringDsFichier(cheminFich,"description"));
		}catch(IOException e) {e.printStackTrace();}
	}

	@Override
	public void buildStages() throws IOException{
		
		try{
			uv.setStages(RecupInfoFichier.recupListDsFichier(cheminFich,"sessions"));
			
			
						
		}catch(IOException e){e.printStackTrace();}
		
	}

	@Override
	public UV getUV() {
		
		return this.uv;
	}
	
	
	
	
	
}
