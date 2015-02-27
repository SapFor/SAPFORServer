package outils;

import java.io.IOException;
import java.net.URISyntaxException;

import builderPompier.PompierConcret;
import builderPompier.PompierBuildFromFile;
import builderPompier.PompierBuilder;
import builderPompier.PompierDirector;
import builderPompier.Pompier;
import builderStage.Stage;
import builderStage.StageBuildFromFile;
import builderStage.StageBuilder;
import builderStage.StageDirector;
import builderUV.UV;
import builderUV.UVBuildFromFile;
import builderUV.UVDirector;
import builderUV.UVbuilder;


public class GestionCreationObjets {

	
	public static Pompier creerPompier(int idPompier)throws IOException, URISyntaxException{
		
		PompierBuilder agent;
		
			agent = new PompierBuildFromFile(idPompier);
		
		 
		
		PompierDirector constPompier=new PompierDirector(agent);
		
		try {
			constPompier.makePompier();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return constPompier.getPompier();
	}
	
	public static UV creerUV(String uvNom) throws URISyntaxException{
		
		UVbuilder uv=new UVBuildFromFile(uvNom);  
		
		UVDirector constUV=new UVDirector(uv);
		
		try {
			constUV.makeUV();
		} catch (IOException e) {e.printStackTrace();}
		
		
		return constUV.getUV();
	}
	
	public static Stage creerStage(String nomStage) throws URISyntaxException{
		
		StageBuilder stage=new StageBuildFromFile(nomStage);  
		
		StageDirector constStage=new StageDirector(stage);
		
		
		constStage.makeSession();
		
		
		
		return constStage.getSession();
	}
	
	public static void main(String args[]) throws URISyntaxException{
		
		try {
			Pompier nouveau=creerPompier(1);
			System.out.println(nouveau.getNom());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(){
		
		
		
	}
	
}
