package observerCandidats;

import java.util.List;

public interface Sujet {
	
	public void inscription(Observateur o);
	
	public void desincription(Observateur o);
	
	public void notifier();

	List<Observateur> getListPompierCandidat();

}
