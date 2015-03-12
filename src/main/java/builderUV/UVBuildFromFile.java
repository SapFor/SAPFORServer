package builderUV;

import java.io.IOException;
import java.net.URISyntaxException;


import outils.RecupInfoFichier;

//by Thomas Davin

public class UVBuildFromFile implements UVbuilder {

	private UV uv;
	private String cheminFich;
	
	
	public UVBuildFromFile(String uv,String pathUVs) throws URISyntaxException{
		
		String fich=uv+".uv";
		cheminFich=pathUVs+fich;
		
		this.uv=new UVConcret();
		
		
			
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
