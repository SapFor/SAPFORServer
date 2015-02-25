package outils;

import java.io.IOException;

import builderPompier.PompierConcret;
import builderPompier.PompierBuildFromFile;
import builderPompier.PompierBuilder;
import builderPompier.PompierDirector;
import builderStage.Stage;
import builderStage.StageBuildFromFile;
import builderStage.StageBuilder;
import builderStage.StageDirector;
import builderUV.UV;
import builderUV.UVBuildFromFile;
import builderUV.UVDirector;
import builderUV.UVbuilder;


public class GestionCreationObjets {

	
	public static PompierConcret creerPompier(int idPompier){
		
		PompierBuilder agent=new PompierBuildFromFile(idPompier);  
		
		PompierDirector constPompier=new PompierDirector(agent);
		
		constPompier.makePompier();
				
		return constPompier.getPompier();
	}
	
	public static UV creerUV(String uvNom){
		
		UVbuilder uv=new UVBuildFromFile(uvNom);  
		
		UVDirector constUV=new UVDirector(uv);
		
		try {
			constUV.makeUV();
		} catch (IOException e) {e.printStackTrace();}
		
		
		return constUV.getUV();
	}
	
	public static Stage creerStage(String nomStage){
		
		StageBuilder stage=new StageBuildFromFile(nomStage);  
		
		StageDirector constStage=new StageDirector(stage);
		
		
		constStage.makeSession();
		
		
		
		return constStage.getSession();
	}
	
	
}
