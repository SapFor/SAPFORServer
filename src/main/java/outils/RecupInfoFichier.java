package outils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


//by Thomas Davin

public class RecupInfoFichier {
	
	/**
	 * cherche le "motCle" dans un fichier et lit la chaine placee a la suite
	 * 
	 * @param cheminFich (String) chemin menant au fichier
	 * @param motCle (String) mot cle permettant d'acceder a un type d'info dans le fichier
	 * @return String rend une chaine de caractere(un mot)
	 * @throws IOException
	 */
	
	public static String chercheDsFichier(String cheminFich, String motCle) throws IOException{
		
		//****
		// recupere la chaine de caractere positionnee juste en dessous du "motCle" dans le fichier
		//****
		
		String res="";
		
		try{
			BufferedReader input = new BufferedReader(new FileReader(new File(cheminFich)));
				
			while((res=input.readLine())!=null && !res.equals(motCle) ){
				//cherche le "motCle"
				
			}
			
			res=input.readLine();//assigne la chaine positionnee sous le "motCle" dans la variable res
			
			input.close();
		}catch(IOException e){e.printStackTrace();}
		
		return res;
		
	} //fin chercheDsFichier
	
	
	/**
	 * recherche le "motCle" dans le fichier situe dans le repertoire indique par son chemin et renvoit la date 
	 * qui y est presente
	 * 
	 * @param cheminFich (String) chemin menant au fichier
	 * @param motCle (String) mot cle permettant d'acceder a un type d'info dans le fichier
	 * @return Calendar rend une date sous la forme d'un objet Calendar
	 * @throws IOException
	 */
	
	public static Calendar chercheDateDsFichier(String cheminFich, String motCle) throws IOException{
		
		//****
		//recupere une date dans le fichier, coupee en 3 parties (jour,mois,annee) dans le fichier
		//positionnees sous le "motCle"
		//****
		
		Calendar res=Calendar.getInstance(); 
		
		String chercheur="";
		String jour="",mois="",annee="";
		
		try{
			BufferedReader input = new BufferedReader(new FileReader(new File(cheminFich)));
			
			
			
			while((chercheur=input.readLine())!=null && !chercheur.equals(motCle) ){
				//cherche le "motCle"
				
			}
			
			jour=input.readLine();//assigne la 1ere chaine positionnee sous le "motCle" dans la variable jour
			mois=input.readLine();//assigne la 2eme chaine positionnee sous le "motCle" dans la variable mois
			annee=input.readLine();//assigne la 3eme chaine positionnee sous le "motCle" dans la variable annee
			
			
			input.close();
			
		}catch(IOException e){e.printStackTrace();}
		
		res.set(Integer.parseInt(annee),Integer.parseInt(mois)-1,Integer.parseInt(jour));
				
		
		return res;
		
	}
	
	
	/**
	 * recupere une liste de chaine presente entre deux bornes la premiere etant defini par "motCle"
	 * 
	 * @param cheminFich (String) chemin menant au dossier ou est contenu le fichier 
	 * @param motCle (String) mot cle permettant de reperer la liste qui nous interesse
	 * @return
	 * @throws IOException
	 */
	
	public static List<String> recupListDsFichier(String cheminFich, String motCle)throws IOException{
		
		//****
		//recupere une liste de string comprise ente le "motCle" et f+"motCle"
		//****
		
		List<String> res=new ArrayList<String>();
		
		String cle="";
		
		try{
			
			BufferedReader input = new BufferedReader(new FileReader(new File(cheminFich)));
			
			
			
			while((cle=input.readLine())!=null && !cle.equals(motCle)){
				//cherche le mot cle
			}
			
			while((cle=input.readLine())!=null && !cle.equals("f"+motCle)){//v�rifie si f+"mot cl�" pas atteint
			
				res.add(cle);//ajoute chacun des String sous le mot dans une liste
			}
			
			
			input.close();
			
		}catch(IOException e){e.printStackTrace();}
		
		//if(res.isEmpty()){res.add(" ");}
		
				
		return res;
				
		
	}//fin recupListDsFichier
	
	
	/**
	 * 
	 * @param cheminFich
	 * @param motCle
	 * @return
	 * @throws IOException
	 */
		
	public static String recupStringDsFichier(String cheminFich, String motCle) throws IOException{
		//recupere une chaine de caractere longue (sur plusieurs ligne) comprise entre mot cle et f+"mot cle"
	
		StringBuffer res=new StringBuffer();
		
		String cle="";
		
		try{
			
			BufferedReader input = new BufferedReader(new FileReader(new File(cheminFich)));
			
			
			
			while((cle=input.readLine())!=null && !cle.equals(motCle)){
				//cherche mot cle
			}
			
			while((cle=input.readLine())!=null && !cle.equals("f"+motCle) /*&& cle.length()==0*/){
			
				res.append(cle);
			}
			
			
			input.close();
		}catch(IOException e){e.printStackTrace();}
		
						
		return res.toString();
		
		
		
	}//fin recupStringDsFichier
	
	
	
	
}
