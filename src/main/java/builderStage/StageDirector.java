package builderStage;

import java.io.IOException;

//by Thomas Davin

public class StageDirector {
	
private StageBuilder constructSession;
	
	public StageDirector(StageBuilder constructSession){
		this.constructSession=constructSession;
	}
	
	public Stage getSession(){
		return this.constructSession.getSession();
	}
	
	public void makeSession(){
		
		try{
			this.constructSession.buildDate();
			this.constructSession.buildLieu();
			this.constructSession.buildInfos();
			this.constructSession.buildCandidats();
			this.constructSession.buildAccepte();
			this.constructSession.buildAttente();
			this.constructSession.buildRefuse();
		}catch(IOException e){}
	}

	
}
