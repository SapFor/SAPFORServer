package observerCandidats;

import builderPompier.PompierConcret;

public interface Sujet {
	
	public void inscription(PompierConcret o);
	
	public void desincription(PompierConcret o);
	
	public void notifier();


}
