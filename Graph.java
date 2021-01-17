import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class Graph
{


	int nbEdges; 
	int nbVertex;
	int maxDegree; 

	int [] from;
	int [] to;
	int [] edges;
	byte[][] adja;

	public Graph(String fname, int estimNbAretes)
	{

		this.from = new int[estimNbAretes];
		this.to = new int[estimNbAretes];

		retrieveVertices(fname,estimNbAretes);
		
		this.edges = buildEdgesArray();
		this.adja = new byte[edges.length + 1][edges.length + 1];

		buildAdjacencyList();
		
		this.maxDegree = getMaxDegree();
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
			int cpt_vertex = 0 ; 

			File myObj = new File(fname);
			Scanner myReader = new Scanner(myObj);
			
			while (myReader.hasNextLine() && cpt < estimNbAretes ) {
				String[] data = myReader.nextLine().split("\t+", 2);

				if( data[0].contains("#")) {
					continue;
				}

				from[cpt] = Integer.valueOf(data[0]);
				to[cpt] = Integer.valueOf(data[1]);

				cpt ++;
			}
			
			myReader.close();

	    } catch (Exception e) 
	    {
	    	System.out.println("Error");
	    	e.printStackTrace();
	    }
	}

	
	private int[] buildEdgesArray() {
		
		int[] all = new int[from.length + to.length];
		System.arraycopy(to, 0, all, 0, from.length);
		System.arraycopy(to, 0, all, from.length, to.length);
	
		int [] edgesWithOutDup = Arrays.stream(all).distinct().toArray();

		return edgesWithOutDup;
		//int number =(int) Arrays.stream(all).distinct().count();
		//return number;
	}

	public int searchEdgeIndex( int edgeID)
	{
		for(int i = 0 ; i < edges.length ; i++ )
		{
			if( edges[i] == 0 )
			{
				return -1 ; 
			}
			if( edgeID == edges[i] )
			{
				return i;
			}
		}
		return -1;

	}

	public void buildAdjacencyList(){

		for(int i = 0 ; i < from.length ; i ++ )
		{
			
			int edge_1 = searchEdgeIndex(from[i]);
			int edge_2 = searchEdgeIndex(to[i]);

			adja[edge_1][edge_1] = 1; 
			adja[edge_2][edge_1] = 1;  
		}
	}

	public int getMaxDegree()
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
	}

	public void print_adjacency_list()
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
	}
}