package ecritureFichier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import builderPompier.Pompier;
import builderStage.Stage;


/**
 * 
 * @author Thomas Davin && Frederic Lepelletier
 *
 */

public class EcrireFichier {
	
		
	/**
	 * methode qui va ecrire dans un fichier les donnees contenues dans un objet Pompier passe en parametre
	 * le chemin du fichier etant passe lui aussi en parametre
	 * 
	 * @param pompier (Pompier) objet de type Pompier a reecrire
	 * @param chemin (String) chemin menant au fichier (donne sous forme de chaine)
	 * @author 
	 */
	
	public static void ecrirePompier(Pompier pompier,String chemin){
		
		BufferedWriter output;
		String dir;
		File fichier;
		
		dir=pompier.getDirecteur();
		
		StringBuffer uvList=new StringBuffer();
		for(String uv : pompier.getUV()){uvList.append(uv+"\n");}
		StringBuffer encoursList=new StringBuffer();
		for(String encours : pompier.getEnCours()){encoursList.append(encours+"\n");}
		StringBuffer accepteList=new StringBuffer();
		for(String accepte : pompier.getAccepte()){accepteList.append(accepte+"\n");}
		StringBuffer attenteList=new StringBuffer();
		for(String attente : pompier.getAttente()){attenteList.append(attente+"\n");}
		StringBuffer refuseList=new StringBuffer();
		for(String refuse : pompier.getRefuse()){refuseList.append(refuse+"\n");}
		StringBuffer gestionList=new StringBuffer();
		for(String gestion : pompier.getGestion()){gestionList.append(gestion+"\n");}
		
		
		
		String chemFile=chemin+pompier.getId()+".pomp";
		
		fichier=new File(chemFile);
		try{
			output=new BufferedWriter(new FileWriter (fichier));
			
			
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
	
	/**
	 * methode qui va ecrire dans un fichier les donnees contenues dans un objet Stage passe en parametre
	 * le chemin du fichier etant passe lui aussi en parametre
	 * 
	 * @param stage (Stage) objet de type Stage a reecrire
	 * @param chemin (String) chemin menant au fichier (donne sous forme de chaine)
	 * @throws IOException
	 * 
	 */
	
	
	
	public static void ecrireStage(Stage stage,String chemin) throws IOException{
		
		 
		File fichier;		
		String nomFichier;
		
		
		int jourD=stage.getDate().get(Calendar.DAY_OF_MONTH);
		int moisD=stage.getDate().get(Calendar.MONTH)+1;
		int anneeD=stage.getDate().get(Calendar.YEAR);
		
				
		nomFichier=stage.getNomStage()+".sess";
				
		String jourF=String.valueOf(stage.getFinCandidature().get(Calendar.DAY_OF_MONTH));
		String moisF=String.valueOf(stage.getFinCandidature().get(Calendar.MONTH)+1);;
		String anneeF=String.valueOf(stage.getFinCandidature().get(Calendar.YEAR));;
		
		
		
		StringBuffer candyList=new StringBuffer();
		for(String candy : stage.getCandidats()){candyList.append(candy+"\n");System.out.println(candy);}
		System.out.println(candyList.toString());
		
		StringBuffer accepteList=new StringBuffer();
		for(String accepte : stage.getAccepte()){accepteList.append(accepte+"\n");}
		StringBuffer attenteList=new StringBuffer();
		for(String attente : stage.getAttente()){attenteList.append(attente+"\n");}
		StringBuffer refuseList=new StringBuffer();
		for(String refuse : stage.getRefuse()){refuseList.append(refuse+"\n");}
		
		String chemFile=chemin+nomFichier;
				
		fichier=new File(chemFile);
		
		try{
			PrintWriter writer = new PrintWriter(fichier);
		
									
			writer.print("uv\n"+stage.getUV()+"\n"
					+"modif\n"
					+"date\n"+jourD+"\n"+moisD+"\n"+anneeD+"\n"
					+"finCandidature\n"+jourF+"\n"+moisF+"\n"+anneeF+"\n"
					+"lieu\n"+stage.getLieu()+"\n"
					+"\n"
					+"infos\n"+stage.getInfos()+"\n"+"finfos\n"
					+"\n"
					+"candidats\n"
					+candyList.toString()
					+"fcandidats\n"
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
					
			);
			writer.close();
					
								
		}catch(IOException e){e.printStackTrace();}
		
		
	}
	
}
