package builderPompier;

import java.io.IOException;

//by Thomas Davin

public interface PompierBuilder {

	public void buildId() throws IOException;
	
	public void buildMdp() throws IOException;
	
	public void buildDirecteur() throws IOException;
	
	public void buildNom() throws IOException;
	
	public void buildPrenom() throws IOException;
	
	public void buildUV() throws IOException;
	
	public void buildAccepte() throws IOException;
	
	public void buildEnCours() throws IOException;
	
	public void buildAttente() throws IOException;
	
	public void buildRefuse() throws IOException;
	
	public void buildGestion() throws IOException;
	
	public Pompier getPompier();
	
}
