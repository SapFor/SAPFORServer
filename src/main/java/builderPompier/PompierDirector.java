package builderPompier;

import java.io.IOException;

//by Thomas Davin

public class PompierDirector {

	private PompierBuilder constructPompier;
	
	public PompierDirector(PompierBuilder constructPompier){
		this.constructPompier=constructPompier;
	}
	
	public Pompier getPompier(){
		return this.constructPompier.getPompier();
	}
	
	public void makePompier(){
		
		try{
			this.constructPompier.buildId();
			this.constructPompier.buildMdp();
			this.constructPompier.buildDirecteur();
			this.constructPompier.buildNom();
			this.constructPompier.buildPrenom();
			this.constructPompier.buildUV();
			this.constructPompier.buildEnCours();
			this.constructPompier.buildAccepte();
			this.constructPompier.buildAttente();
			this.constructPompier.buildRefuse();
			this.constructPompier.buildGestion();
		}catch(IOException e){e.printStackTrace();}
	}
}
