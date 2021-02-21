import java.util.*;

public class Kcoeur {



	Graph g; 
	boolean vertex_active [];
	int vertex_degree[];
	PriorityQueue<Map.Entry<Integer, Integer>> pq;
	PriorityQueue<Integer> q;

	public Kcoeur(Graph g)
	{
		this.g = g;
		vertex_degree = g.deg;
		vertex_active = new boolean[g.n];

		for (int i = 0 ; i < vertex_active.length ; i++)
		{
			vertex_active[i] = true;
		}
		this.pq =  new PriorityQueue<Map.Entry<Integer, Integer>>(new Comparator<Map.Entry<Integer, Integer>>()
        {
           @Override
           public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2)
             {
             	if( entry1.getValue() < entry2.getValue()){
             		return -1;
             	}
             	if( entry1.getValue() > entry2.getValue()){
             		return 1;
             	}
             	return 0;
             }
        });
		decomposition();
	}


	public void decreaseNeighboorsDeg(int u)
	{
	 	vertex_active[u] = false;
		for( int v : g.neighbors(u))
		{
			vertex_degree[v] = vertex_degree[v] - 1;
			if(this.pq.removeIf(e -> e.getKey() == v)){
				if(vertex_degree[v] > 0){
					addToPriorityQueue(v, vertex_degree[v]);
				}
			}
		}

	}

	private void fillQueue()
	{
		for(int i = 0; i < vertex_active.length; i++)
		{
			// we add a vertex only if it is active
			if( vertex_active[i])
			{
				addToPriorityQueue(i, vertex_degree[i]);
			}
		}
	}

	public void decomposition()
	{
		int n = this.g.getN();
		fillQueue();
		int k =0;
		int pred = 0;
		for(; k < n-1; k++){
			
			if( !this.pq.isEmpty() && (this.pq.peek().getValue() < k) ){
				pred = this.pq.size(); 
				decompositionAux(k);
			
				if( this.pq.isEmpty()){
					System.out.println((k-1));
					System.out.println(pred);
					break;
				}
			}
		}
	}
	private void decompositionAux( int k)
	{
		Map.Entry<Integer, Integer> entry = this.pq.peek();	

		while(entry.getValue() < k )
		{
			decreaseNeighboorsDeg(entry.getKey());
			this.pq.remove(entry);
			if(this.pq.isEmpty())
			{
				return;
			}
			entry = this.pq.peek();
		}
	}

	

	private void addToPriorityQueue(int key, int value)
	{
		this.pq.add(new AbstractMap.SimpleEntry<Integer, Integer>(key,value));
	}

}