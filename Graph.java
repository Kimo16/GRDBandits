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

	}

	private void retrieveVertices(String fname) {
		try {
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
	    } catch (Exception e) {
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
}