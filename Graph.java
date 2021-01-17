import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class Graph
{


	int nbEdges; 
	int nbVertex;

	int []from;
	int []to;

	int[][] adja;

	public Graph(String fname, int estimNbAretes)
	{

		from = new int[estimNbAretes];
		to = new int[estimNbAretes];

		retrieveVertices(fname);
		getNumEdge();
		build_adjacency_list();
	}

	private void retrieveVertices(String fname) 
	{
		try 
		{
			int cpt =0;
			File myObj = new File(fname);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String[] data = myReader.nextLine().split("\t+", 2);

				if( data[0].contains("#")) {
					continue;
				}
				from[cpt] = Integer.valueOf(data[0]);
				to[cpt++] = Integer.valueOf(data[1]);
				
			}
			myReader.close();

	    } catch (Exception e) 
	    {
	    	System.out.println("Error");
	    	e.printStackTrace();
	    }
	}

	private long getNumEdge() {
		
		int[] all = new int[from.length + to.length];
		System.arraycopy(to, 0, all, 0, from.length);
		System.arraycopy(to, 0, all, from.length, to.length);
		
		System.out.println("all length: "+ all.length);
		
		long number = Arrays.stream(all).distinct().count();
		System.out.println("distinct count : "+ number);
		return number;

	}


	public void build_adjacency_list(){
		adja = new int[nbEdges + 1][nbEdges + 1];

		for(int i = 0 ; i < from.length ; i ++ )
		{
			int edge_1 = from[i];
			int edge_2 = to[i];

			adja[edge_1][edge_2] = 1; 
			adja[edge_2][edge_1] = 1;  
		}
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