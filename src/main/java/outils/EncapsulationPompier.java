package outils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import builderPompier.PompierConcret;


@XmlRootElement
public class EncapsulationPompier {
	
	public List<PompierConcret> capsule; 

	
	public EncapsulationPompier(){}
	
	
	public EncapsulationPompier(List<PompierConcret> aEncapsulerStage){
		this.capsule=aEncapsulerStage;
	}

	
}
