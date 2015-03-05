package outils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Encapsulation {
	
	public List<String> liste;  
	
	
	public Encapsulation(){}
	
	
	public Encapsulation(List<String> nouvListe){
		this.liste=nouvListe;
	}
	
	
	
	
}
