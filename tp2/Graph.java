import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class Graph
{


	public int nbNoeud; 
	public int nbAretes;
	public int maxDegree; 
	public int maxNoeudId; 

	private int[] from,to;
	public int[][] adjacence;
	
	private static final int BUFFER_SIZE = 16384;

	public Graph(String fname, int estimNbAretes)
	{
		nbNoeud = -1;
		nbAretes = -1;
		maxDegree = -1;
		maxNoeudId = -1;

		readFile(fname,estimNbAretes);
		
		//mem();

		this.adjacence = new int[maxNoeudId][];
		//System.out.println("n="+this.maxNoeudId);
		//System.out.println("m="+this.nbAretes);
		buildAdjacence();

		//mem();
		
		//System.out.println("degmax=" + this.maxDegree);

	}

	public int distance(int depart, int arrive){
		//breadFirstAlgorithm
		return -1;
	}

	public int[] neighbors(int noeud){
		return this.adjacence[noeud];
	}

	private void buildAdjacence(){

		for(int i = 0; i < this.nbAretes; i++){
			if(this.adjacence[from[i]] == null){
				this.adjacence[from[i]] = new int[]{to[i]};
			}else{
				int[] temp = new int[this.adjacence[from[i]].length + 1];
				// copy
				int j;
				for(j=0; j < this.adjacence[from[i]].length; j++) temp[j] = this.adjacence[from[i]][j];
				// add last
				temp[j] = to[i];
				this.adjacence[from[i]] = temp;
			}
			if( this.adjacence[from[i]].length > this.maxDegree){
				this.maxDegree = this.adjacence[from[i]].length;
			}

			if(this.adjacence[to[i]] == null){
				this.adjacence[to[i]] = new int[]{from[i]};
			}else{
				int[] temp = new int[this.adjacence[to[i]].length + 1];
				// copy
				int j;
				for(j=0; j < this.adjacence[to[i]].length; j++) temp[j] = this.adjacence[to[i]][j];
				// add last
				temp[j] = from[i];
				this.adjacence[to[i]] = temp;
			}
			if( this.adjacence[to[i]].length > this.maxDegree){
				this.maxDegree = this.adjacence[to[i]].length;
			}
		}
	}

	private void readFile(String fname, int estimNbAretes) 
	{
		from = new int[estimNbAretes];
		to = new int[estimNbAretes];
		try 
		{
			int cpt =0;

			//File myObj = new File(fname);
			//Scanner myReader = new Scanner(myObj);
			BufferedReader reader = new BufferedReader(new FileReader(fname), BUFFER_SIZE);
			
			String line;
			while ((line = reader.readLine()) != null && cpt < estimNbAretes ) {
				String[] data = line.split("\t+", 2);

				if( data[0].contains("#")) {
					continue;
				}

				this.nbAretes++; 

				from[cpt] = Integer.valueOf(data[0]);
				
				to[cpt] = Integer.valueOf(data[1]);

				if(from[cpt] > this.maxNoeudId )
				{
					this.maxNoeudId = from[cpt];
				}

				if(to[cpt] > this.maxNoeudId )
				{
					this.maxNoeudId = to[cpt];
				}

				cpt ++;
			}

			this.nbAretes++;
			this.maxNoeudId++; 

			reader.close();

	    } catch (Exception e) 
	    {
	    	System.out.println("Error");
	    	e.printStackTrace();
	    }
		this.nbNoeud = this.maxNoeudId;
	}

	

	public static void mem() {
		Runtime rt = Runtime.getRuntime();
		rt.gc();
		System.err.println("Allocated memory : "+ (rt.totalMemory() - rt.freeMemory()) / 1000000 + " Mb");
		System.err.flush();
	}
}