package outils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

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
	
	/**
	 * renvoi un objet Pompier a partir de son numero d'agent et du chemin ou est situe le fichier
	 * 
	 * @param idPompier (in) n° d'agent
	 * @param pathPomp (String) chemin menant au repertoire contenant le fichier
	 * @return objet Pompier rend un objet de type Pompier 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	public static Pompier creerPompier(int idPompier,String pathPomp)throws IOException, URISyntaxException{
		
		PompierBuilder agent;
		
		agent = new PompierBuildFromFile(idPompier,pathPomp);
				
		PompierDirector constPompier=new PompierDirector(agent);
		
		try {
			constPompier.makePompier();
		} catch (IOException e) {e.printStackTrace();}
				
		return constPompier.getPompier();
	}
	
	/**
	 * renvoi un objet UV a partir de son nom et du chemin ou est situe le fichier
	 * 
	 * @param uvNom (String) nom de l'UV (son acronyme)
	 * @param pathUVs (String) chemin menant au repertoire contenant le fichier
	 * @return objet UV rend un objet de type UV
	 * @throws URISyntaxException
	 * @throws MalformedURLException
	 */
	
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
