package outils;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import builderUV.UVConcret;


@XmlRootElement
public class EncapsulationUV {
	
	public List<UVConcret> capsule; 

	
	public EncapsulationUV(){}
	
	
	public EncapsulationUV(List<UVConcret> aEncapsulerUV){
		this.capsule=aEncapsulerUV;
	}

	
}
