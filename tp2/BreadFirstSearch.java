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

	private int farthest_node;
	private int max_distance;

	public BreadFirstSearch(Graph graph)
	{
		this.graph 		= graph; 
		this.colors 	= new byte[graph.maxNoeudId + 1];
		this.distances 	= new int[graph.maxNoeudId  + 1];
		this.fifo 		= new ArrayDeque<Integer>();
	}

	public void breadFirstAlgorithm( int originEdge )
	{
		farthest_node = originEdge;
		max_distance = 0;

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
	
			for(int i = 0 ; i < graph.adjacence[currentEdge].length ; i ++ )
			{
				int neighboorId = graph.adjacence[currentEdge][i];
				if(colors[ neighboorId ] == WHITE )
				{
					colors[ neighboorId ]	 	= GRAY;
					distances [ neighboorId ] 	= distances[ currentEdge ] + 1;
					fifo.add( neighboorId ); 
				}
			}

			colors[ currentEdge ] = BLACK;

			/* we recover the max distance*/
			if( distances[ currentEdge ] > max_distance ){
				max_distance = distances[ currentEdge ];
				farthest_node = currentEdge;
			}
		}

	}

	public int breadFirstAlgorithm( int originEdge , int endEgde )
	{
		
	
		colors[originEdge] = GRAY;
	
		//initialise data for every edges except the origin 

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
			if( currentEdge == endEgde ) 
			{
				break; 
			}
			
			for(int i = 0 ; i < graph.adjacence[currentEdge].length ; i ++ )
			{
				int neighboorId = graph.adjacence[currentEdge][i];
				if(colors[ neighboorId ] == WHITE )
				{
					colors[ neighboorId ]	 	= GRAY;
					distances [ neighboorId ] 	= distances[ currentEdge ] + 1;
					fifo.add( neighboorId ); 
				}
			}

			colors[ currentEdge ] = BLACK;
		}

		return distances[endEgde]; // à changer
	}

	public int getFarthestNode(){
		return this.farthest_node;
	}

	public int getMaxDistance(){
		return this.max_distance;
	}

}
