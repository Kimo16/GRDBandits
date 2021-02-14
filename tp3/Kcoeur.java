public class Kcoeur {



	Graph g; 
	boolean vertex_active [];
	int vertex_degree[];


	public Kcoeur(Graph g)
	{
		this.g = g;
		vertex_degree = g.deg;
		vertex_active = new boolean[g.n];

		for (int i = 0 ; i < vertex_active.length ; i++)
		{
			vertex_active[i] = true;
		}
	}


	public void decreaseNeighboorsDeg(int u)
	{
	 	vertex_active[u] = false;

		for( int v : g.neighbors(u))
		{
			vertex_degree[v] = vertex_degree[v] - 1;
		}

	}




}