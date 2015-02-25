package builderUV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import outils.RecupInfoFichier;

//by Thomas Davin

public class UVBuildFromFile implements UVbuilder {

	private UV uv;
	private String fichier="";//insérer le chemin menant aux fichiers UV
	private BufferedReader input;
	
	public UVBuildFromFile(String uv){
		
		this.uv=new UVConcret();
		this.fichier=fichier+uv+".uv";
		
		try{
			input = new BufferedReader(new FileReader(fichier));
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
