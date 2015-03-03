package observerCandidats;

public interface Sujet {
	
	public void inscription(Observateur o);
	
	public void desincription(Observateur o);
	
	public void notifier();

}
