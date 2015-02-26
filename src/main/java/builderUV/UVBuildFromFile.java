package builderUV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import outils.RecupInfoFichier;

//by Thomas Davin

public class UVBuildFromFile implements UVbuilder {

	private UV uv;
	private URL fichier;
	private BufferedReader input;
	
	public UVBuildFromFile(String uv) throws URISyntaxException{
		
		fichier=getClass().getResource("/donnees/UVs/"+uv+".pomp");
		URI cheminFich=fichier.toURI();
		
		this.uv=new UVConcret();
		
		
		try{
			input = new BufferedReader(new FileReader(new File(cheminFich)));
			 input.mark(2000);	
			
			}catch(Exception e){System.out.println("Aucun fichier "+fichier+" existant!");}
				
	}

	@Override
	public void buildNom() throws IOException{
		try{
			uv.setNom(RecupInfoFichier.chercheDsFichier(input,"nom"));
		}catch(IOException e) {e.printStackTrace();}
	}

	@Override
	public void buildDescr() throws IOException{
		try{
		
			uv.setDescr(RecupInfoFichier.recupStringDsFichier(input,"description"));
		}catch(IOException e) {e.printStackTrace();}
	}

	@Override
	public void buildSessions() throws IOException{
		
		try{
			uv.setSessions(RecupInfoFichier.recupListDsFichier(input,"sessions"));
			
			input.close();
						
		}catch(IOException e){e.printStackTrace();}
		
	}

	@Override
	public UV getUV() {
		
		return this.uv;
	}
	
	
	
	
	
}
