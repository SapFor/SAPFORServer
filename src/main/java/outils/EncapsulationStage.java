package outils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import builderStage.StageConcret;


@XmlRootElement
public class EncapsulationStage {
	
	public List<StageConcret> capsule; 

	
	public EncapsulationStage(){}
	
	
	public EncapsulationStage(List<StageConcret> aEncapsulerStage){
		this.capsule=aEncapsulerStage;
	}

	
}
