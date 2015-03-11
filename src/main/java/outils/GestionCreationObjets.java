package outils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import builderPompier.PompierBuildFromFile;
import builderPompier.PompierBuilder;
import builderPompier.PompierConcret;
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

	
	public static Pompier creerPompier(int idPompier,String pathPomp)throws IOException, URISyntaxException{
		
		PompierBuilder agent;
		
		agent = new PompierBuildFromFile(idPompier,pathPomp);
				
		PompierDirector constPompier=new PompierDirector(agent);
		
		try {
			constPompier.makePompier();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return constPompier.getPompier();
	}
	
	public static UV creerUV(String uvNom,String pathUVs) throws URISyntaxException, MalformedURLException{
		
		UVbuilder uv=new UVBuildFromFile(uvNom,pathUVs);  
		
		UVDirector constUV=new UVDirector(uv);
		
		try {
			constUV.makeUV();
		} catch (IOException e) {e.printStackTrace();}
		
		
		return constUV.getUV();
	}
	
	public static Stage creerStage(String nomStage,String pathStag) throws URISyntaxException, MalformedURLException{
		
		StageBuilder stage=new StageBuildFromFile(nomStage,pathStag);  
		
		StageDirector constStage=new StageDirector(stage);
		
		
		constStage.makeSession();
		
		
		
		return constStage.getSession();
	}
	
	
}
