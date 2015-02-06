
public class OverlapGraph {
	private static final OverlapGraph instance = new OverlapGraph();
	
	public static synchronized OverlapGraph getInstance()
	{
	    return instance;
	}
}
