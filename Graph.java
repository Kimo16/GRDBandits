import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class Graph
{


	int nbEdges; 
	int nbVertex;
	int maxDegree; 
	int maxEdgeId; 

	int [] from;
	int [] to;
	int [] edges;
	int [] neighboors;
	int [] indexInNeighboors;

	public Graph(String fname, int estimNbAretes)
	{

		this.from = new int[estimNbAretes];
		this.to = new int[estimNbAretes];
		this.maxEdgeId = 0 ;
		this.nbVertex = 0; 
		retrieveVertices(fname,estimNbAretes);
		
		mem();

		this.edges = buildEdgesArray();
		this.nbEdges = edges.length;
		//this.adja = new byte[edges.length + 1][edges.length + 1];
		this.neighboors = new int[nbVertex * 2 + nbEdges + 1 ];
		this.indexInNeighboors = new int[nbEdges + 1];

		buildAdjacencyList();

		mem();
	}

	private void letcure() {
		for(int i = 0; i < from.length; i++) {
			System.out.println(from[i] +" --> " + to[i]);
		}
	}

	private void retrieveVertices(String fname, int estimNbAretes) 
	{
		try 
		{
			int cpt =0;

			File myObj = new File(fname);
			Scanner myReader = new Scanner(myObj);
			
			while (myReader.hasNextLine() && cpt < estimNbAretes ) {
				String[] data = myReader.nextLine().split("\t+", 2);

				if( data[0].contains("#")) {
					continue;
				}

				nbVertex += 1; 

				from[cpt] = Integer.valueOf(data[0]);
				to[cpt] = Integer.valueOf(data[1]);

				if(from[cpt] > maxEdgeId )
				{
					maxEdgeId = from[cpt];
				}

				if(to[cpt] > maxEdgeId )
				{
					maxEdgeId = to[cpt];
				}

				cpt ++;
			}

			maxEdgeId += 1 ; 

			myReader.close();

	    } catch (Exception e) 
	    {
	    	System.out.println("Error");
	    	e.printStackTrace();
	    }
	}

	
	private int[] buildEdgesArray() {
		
		int[] all = new int[from.length + to.length];
		System.arraycopy(from, 0, all, 0, from.length);
		System.arraycopy(to, 0, all, from.length, to.length);
	
		int [] edgesWithOutDup = Arrays.stream(all).distinct().toArray();
		
		return edgesWithOutDup;
	
	}

	public int searchEdgeIndex( int edgeID)
	{
		for(int i = 0 ; i < edges.length ; i++ )
		{
			if( edgeID == edges[i] )
			{
				return i;
			}
		}
		return -1;

	}

	public void buildAdjacencyList(){
		int currentVertex = -1; 
		int counterNeighboors = 0; 
		int maxCounterDegree = 0 ;
		int counterDegree = 0 ; 

		for(int i = 0 ; i < from.length ; i ++ )
		{

			int f = searchEdgeIndex(from[i]);
			int t = searchEdgeIndex(to[i]);
			

			if( currentVertex != f )
			{
				currentVertex = f; 

				if (counterDegree > maxCounterDegree )
				{
					maxCounterDegree = counterDegree; 
				}

				neighboors[counterNeighboors] = currentVertex; 
				indexInNeighboors[currentVertex] = counterNeighboors;
				counterDegree = 0 ;  
				

			}else{
				neighboors[counterNeighboors] = t; 
				counterDegree += 1; 
			}

			counterNeighboors += 1 ; 
				
			/*if(from != -1 || to != -1)
			{
				adja[from][to] = 1; 
				adja[edge_2][edge_1] = 1;  
			}*/
		}
		this.maxDegree = maxCounterDegree; 
	}

	/*public int getMaxDegree()
	{
		int maxDegree = 0 ;
		
		for(int edge = 0 ; edge < adja.length ; edge ++ )
		{
			int currentDegree = 0; 
			
			for(int neighboor = 0 ; neighboor < adja[edge].length ; neighboor ++ )
			{
					
				if ( adja[edge][neighboor] == 1 )
				{
					currentDegree += 1; 
				}
			}
			if ( maxDegree < currentDegree )
			{
				maxDegree = currentDegree; 
			}
		}

		return maxDegree;
	}*/

	/*public void print_adjacency_list()
	{
		for(int i = 0 ; i < adja.length ; i ++)
		{	
			System.out.print("La liste d'adjacence pour le sommet n°" + i + ":" );

			for(int j = 0 ; j < adja[i].length; j++)
			{
				if ( adja[i][j] == 1)
				{
					System.out.print(" "+ j+ " ");
				}
			}
			System.out.println();
		}
	}*/

	public static void mem() {
		Runtime rt = Runtime.getRuntime();
		rt.gc();
		System.err.println("Allocated memory : "+ (rt.totalMemory() - rt.freeMemory()) / 1000000 + " Mb");
		System.err.flush();
	}
}