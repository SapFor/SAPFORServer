package observerCandidats;

import java.util.List;

public interface Sujet {
	
	public void inscription(Observateur o);
	
	public void desincription(Observateur o);
	
	public void notifier();
	
	public List<Observateur> getListPompierCandidat();

}
