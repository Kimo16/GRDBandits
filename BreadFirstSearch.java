import java.util.ArrayDeque;

public class BreadFirstSearch
{

	private static final byte WHITE = 0; 
	private static final byte GRAY  = 1; 
	private static final byte BLACK = 2;

	private byte[] colors ; 
	private int[] distances; 
	private Graph graph;
	private ArrayDeque<Integer> fifo; 

	public BreadFirstSearch(Graph graph)
	{
		this.graph 		= graph; 
		this.colors 	= new byte[graph.edges.length + 1];
		this.distances 	= new int[graph.edges.length  + 1];
		this.fifo 		= new ArrayDeque<Integer>();
	}



	public int breadFirstAlgorithm( int originEdge , int endEgde )
	{
		if ( graph.searchEdgeIndex(endEgde) == -1 )
		{
			return Integer.MAX_VALUE;
		}
	
		originEdge = graph.searchEdgeIndex(originEdge);
		colors[originEdge] = GRAY;
	
		/*initialise data for every edges except the origin*/

		for(int i = 0 ; i < distances.length ; i ++ )
		{
			if ( i != originEdge )
			{
				distances[i] = Integer.MAX_VALUE;
				colors[i] = WHITE;
			}
			
		}

		fifo.add(originEdge);
		
		while( ! fifo.isEmpty() )
		{
			int currentEdge = fifo.pollFirst();
			if( graph.edges[currentEdge] == endEgde ) 
			{
				break; 
			}
			
			int start = graph.indexInNeighboors[currentEdge] + 1;
			int end   = graph.indexInNeighboors[currentEdge + 1];

			for(int i = start ; i < end ; i ++ )
			{
				int neighboor = graph.neighboors[i];
				if(colors[ neighboor ] == WHITE )
				{
					colors[ neighboor ]	 	= GRAY;
					distances [ neighboor ] = distances[ currentEdge ] + 1;
					fifo.add( neighboor ); 
				}
			}

			colors[ currentEdge ] = BLACK;
		}

		return distances[graph.searchEdgeIndex(endEgde)]; /* à changer*/
	}

}