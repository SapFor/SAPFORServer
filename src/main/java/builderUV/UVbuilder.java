package builderUV;

import java.io.IOException;

//by Thomas Davin

public interface UVbuilder {

	public void buildNom() throws IOException;
	
	public void buildDescr() throws IOException;
	
	public void buildStages() throws IOException;
	
	
	
	public UV getUV();
	
}
