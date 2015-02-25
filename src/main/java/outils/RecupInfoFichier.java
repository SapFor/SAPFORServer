package outils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//by Thomas Davin

public class RecupInfoFichier {

	public static String chercheDsFichier(BufferedReader input, String motCle) throws IOException{
		// r�cup�re la chaine de caract�re en dessous du mot cl� donn� dans le fichier
		
		String res="";
		
		try{
			
			input.reset();//s'assure que la recherche s'effectue depuis le d�but du buffer content le fichier
			
			while((res=input.readLine())!=null && !res.equals(motCle) ){
				//cherche le mot cl�
				
			}
			
			res=input.readLine();//met la String sous le mot cle dans res
			
		}catch(IOException e){e.printStackTrace();}
		
		return res;
		
	} //fin chercheDsFichier
	
	
	
	public static List<String> recupListDsFichier(BufferedReader input, String motCle)throws IOException{
		//r�cup�re une liste de string comprise ente le mot cl� et f+"mot cl�"
		
		List<String> res=new ArrayList<String>();
		
		String cle="";
		
		try{
			input.reset();//s'assure que la recherche s'effectue depuis le d�but du buffer content le fichier
			
			while((cle=input.readLine())!=null && !cle.equals(motCle)){
				//cherche le mot cl�
			}
			
			while((cle=input.readLine())!=null && !cle.equals("f"+motCle)){//v�rifie si f+"mot cl�" pas atteint
			
				res.add(cle);//ajoute chacun des String sous le mot dans une liste
			}
			
		}catch(IOException e){e.printStackTrace();}
		
				
		return res;
				
		
	}//fin recupListDsFichier
	
	
public static String recupStringDsFichier(BufferedReader input, String motCle) throws IOException{
		//r�cup�re une chaine de caract�re longue (sur plusieurs ligne) comprise entre mot cl� et f+"mot cl�"
	
		StringBuffer res=new StringBuffer();
		
		String cle="";
		
		try{
			input.reset();//s'assure que la recherche s'effectue depuis le d�but du buffer content le fichier
			
			while((cle=input.readLine())!=null && !cle.equals(motCle)){
				//cherche mot cl�
			}
			
			while((cle=input.readLine())!=null && !cle.equals("f"+motCle) /*&& cle.length()==0*/){
			
				res.append(cle);
			}
			
		}catch(IOException e){e.printStackTrace();}
		
				
		return res.toString();
		
		
		
	}//fin recupStringDsFichier
	
	
	
	
}