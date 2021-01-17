import java.util.ArrayDeque;

public class BreadFirstSearch
{

	private static final byte WHITE = 0; 
	private static final byte GRAY  = 1; 
	private static final byte BLACK = 2;

	private byte[] colors ; 
	private int[] origins; 
	private int[] distances; 
	private Graph graph;
	private ArrayDeque<Integer> fifo; 

	public BreadFirstSearch(Graph graph)
	{
		this.graph 		= graph; 
		this.colors 	= new byte[graph.maxEdgeId + 1];
		this.distances 	= new int[graph.maxEdgeId  + 1];
		this.origins   	= new int[graph.maxEdgeId  + 1];
		this.fifo 		= new ArrayDeque<Integer>();
	}



	public int breadFirstAlgorithm( int originEdge , int endEgde )
	{
		colors[originEdge] = GRAY;
		origins[originEdge] = -1 ; 
	
		/*initialise data for every edges except the origin*/

		for(int i = 0 ; i < distances.length ; i ++ )
		{
			if ( i != originEdge )
			{
				distances[i] = Integer.MAX_VALUE;
				origins[i] = -1; 
				colors[i] = WHITE;
			}
			
		}

		fifo.add(originEdge);
		
		while( ! fifo.isEmpty() )
		{
			int currentEdge = fifo.pollFirst();
			if( currentEdge == endEgde ) 
			{
				break; 
			}
			
			for(int i = 0 ; i < graph.adja[currentEdge].length ; i ++ )
			{
				if(colors[ i] == WHITE && graph.adja[currentEdge][i] == 1 )
				{
					colors[ i ]	 = GRAY;
					distances [ i ] = distances[ currentEdge ] + 1;
					origins [ i ]	 = currentEdge;
					fifo.add( i ); 
				}
			}

			colors[ currentEdge ] = BLACK;
		}

		graph.print_adjacency_list();

		return distances[endEgde];
	}
}