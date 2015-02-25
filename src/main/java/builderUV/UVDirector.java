package builderUV;

import java.io.IOException;

//by Thomas Davin

public class UVDirector {

		private UVbuilder constructUV;
		
		public UVDirector(UVbuilder constructUV){
			this.constructUV=constructUV;
		}
		
		public UV getUV(){
			return this.constructUV.getUV();
		}
		
		public void makeUV() throws IOException{
			try{
				this.constructUV.buildNom();
				this.constructUV.buildDescr();
				this.constructUV.buildSessions();
			}catch(IOException e){}
		}
	

	
}
