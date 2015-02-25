package ecritureFichier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import builderPompier.Pompier;
import builderPompier.PompierConcret;
import builderStage.Stage;
import builderUV.UV;

public class EcrireFichier {

	public void ecriture(Object objet){
		if(objet instanceof PompierConcret){
			PompierConcret aEcrire=(PompierConcret)objet;
			ecrirePompier(aEcrire);
		}
		else if(objet instanceof UV){
			UV aEcrire=(UV)objet;
			ecrireUV(aEcrire);
		}
		else if (objet instanceof Stage){
			Stage aEcrire=(Stage)objet;
			ecrireStage(aEcrire);
		}
		else{}
	}
	
	public static void ecrirePompier(Pompier pompier){
		
		BufferedWriter output;
		String dir;
		
		if(pompier.getDirecteur()){dir="oui";}
		else{dir="non";}
		StringBuffer uvList=new StringBuffer();
		for(String uv : pompier.getUV()){uvList.append(uv+"\n");}
		StringBuffer encoursList=new StringBuffer();
		for(String encours : pompier.getEnCours()){encoursList.append(encours+"\n");}
		StringBuffer accepteList=new StringBuffer();
		for(String accepte : pompier.getEnCours()){accepteList.append(accepte+"\n");}
		StringBuffer attenteList=new StringBuffer();
		for(String attente : pompier.getEnCours()){encoursList.append(attente+"\n");}
		StringBuffer refuseList=new StringBuffer();
		for(String refuse : pompier.getEnCours()){refuseList.append(refuse+"\n");}
		StringBuffer gestionList=new StringBuffer();
		for(String gestion : pompier.getEnCours()){encoursList.append(gestion+"\n");}
		
		String fichier=""+pompier.getId()+".pomp";//indiquer le "path" des fichiers entre ""
		try{
			output=new BufferedWriter(new FileWriter (fichier,false));
			
			
			output.write(
					"id\n"+pompier.getId()+"\n"
					+"mdp\n"+pompier.getMdp()+"\n"
					+"directeur\n"+dir+"\n"
					+"nom\n"+pompier.getNom()+"\n"
					+"prenom\n"+pompier.getPrenom()+"\n"
					+"\n"
					+"uv\n"
					+uvList.toString()
					+"fuv\n"
					+"\n"
					+"encours\n"
					+encoursList.toString()
					+"fencours\n"
					+"\n"
					+"accepte\n"
					+accepteList.toString()
					+"faccepte\n"
					+"\n"
					+"attente\n"
					+attenteList.toString()
					+"fattente\n"
					+"\n"
					+"refuse\n"
					+refuseList.toString()
					+"frefuse\n"
					+"\n"
					+"gestion\n"
					+gestionList.toString()
					+"fgestion\n"
			);
			
			output.close();		
								
		}catch(IOException e){e.printStackTrace();}
		
		
		
	}
	
	

	private void ecrireUV(UV objet){
		
	}

	private void ecrireStage(Stage objet){
	
	}
	
}
