package ecritureFichier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;

import builderPompier.Pompier;
import builderPompier.PompierConcret;
import builderStage.Stage;

public class EcrireFichier {
	
	/*public void ecriture(Object objet,URI chemin) throws URISyntaxException, IOException{
		if(objet instanceof PompierConcret){
			PompierConcret aEcrire=(PompierConcret)objet;
			ecrirePompier(aEcrire);
		}
		
		else if (objet instanceof Stage){
			Stage aEcrire=(Stage)objet;
			System.out.println("il court, il court le SilverFox!");
			ecrireStage(aEcrire,chemin);
		}
		else{}
	}*/
	
	public static void ecrirePompier(Pompier pompier,String chemin) throws URISyntaxException{
		
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
		
		//chemin=EcrireFichier.class.getResource("/donnees/Pompiers/"+pompier.getId()+".pomp");
		
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
	
	
	
	public static void ecrireStage(Stage stage,String chemin) throws IOException, URISyntaxException{
		
		 
		File fichier;		
		String nomFichier;
		//System.out.println(chemin.toString());
		
		int jourD=stage.getDate().get(Calendar.DAY_OF_MONTH);
		int moisD=stage.getDate().get(Calendar.MONTH);
		int anneeD=stage.getDate().get(Calendar.YEAR);
		
		String moisS="";
		int anneeS=anneeD%100;
				
		switch(moisD){
		case 0: moisS="janv";break;
		case 1: moisS="fev";break;
		case 2: moisS="mars";break;
		case 3: moisS="avr";break;
		case 4: moisS="mai";break;
		case 5: moisS="juin";break;
		case 6: moisS="juil";break;
		case 7: moisS="aout";break;
		case 8: moisS="sept";break;
		case 9: moisS="oct";break;
		case 10: moisS="nov";break;
		case 11: moisS="dec";break;
		default: moisS="Pbm";break;
		}
		
		nomFichier=stage.getNomStage()+".sess";
				
		String jourF=String.valueOf(stage.getFinCandidature().get(Calendar.DAY_OF_MONTH));
		String moisF=String.valueOf(stage.getFinCandidature().get(Calendar.MONTH));;
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
		//chemin=EcrireFichier.class.getResource("/donnees/Stages/"+fich);//+nomFichier+".sess");
		//System.out.println(chemin.toString());
		
		fichier=new File(chemFile);
		//System.out.println(fichier.toString());
		//System.out.println(fichier.toString());
				//toString()+nomFichier);
		
		try{
			PrintWriter writer = new PrintWriter(fichier);
			//BufferedWriter output=new BufferedWriter(new FileWriter(fichier));
									
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
		
		//finally{output.close();}
	}
	
}
