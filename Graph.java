import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

public class Graph
{


	int nbEdges; 
	int nbVertex;
	int maxDegree; 
	int maxEdgeId; 
	int dist ;

	int [] from;
	int [] to;
	int [] edges;
	//byte[][] adja;

	int voisins[];
	int indices[];
	
	int sommetdepart ;
	int sommetarrive ;
	
	

	HashMap<Integer, ArrayList<Integer>> adjacences;

	public Graph(String fname, int estimNbAretes, int start , int end )
	{

		this.from = new int[estimNbAretes];
		this.to = new int[estimNbAretes];
		this.nbVertex = 0; 
		this.sommetdepart = start ;
		this.sommetarrive = end ;
		retrieveVertices(fname,estimNbAretes);
		
		this.edges = buildEdgesArray();
		//mem() ;
		this.maxEdgeId = -1;
		
		mem() ;
		//this.indices = new int[this.edges.length];
		//this.voisins = new int[this.indices.length + this.nbVertex];
		this.adjacences = new HashMap<Integer, ArrayList<Integer>>();
		voisinnage();
		this.maxDegree = getMaxVoisinsQ();
		

		//mem();
		
		//this.maxDegree = getMaxDegree();
		//System.out.println("Avant BFS.");
		this.dist = BFS(this.adjacences,this.sommetdepart,this.sommetarrive);
	}
	
	/* remplis les tableaux de voisinnage et des indices */
	private void voisinnage(){
		int currentNode= -1;

		int cpt = 0;
		//int cpt_ind = 0;
		boolean end = false;
		for(int i = 0; i < from.length; i ++){
			if( from[i] != currentNode){
				/*on vient de changer de noeud de départ */
				currentNode = from[i];
				//this.indices[cpt_ind++] = cpt;
				//this.voisins[cpt] = currentNode;
				cpt++;
				adjacences.putIfAbsent(currentNode, new ArrayList<>());
			}

			if(!end){
					adjacences.get(currentNode).add(to[i]);
				adjacences.putIfAbsent(to[i], new ArrayList<>());
					adjacences.get(to[i]).add(currentNode);
				
			}

			if( from[i] == 0 && to[i] == 0){
				end = true;
			}
			//if( cpt < (this.edges.length + this.nbVertex)){
				//this.voisins[cpt++] = to[i];

				//if( !adjacences.get(currentNode).contains(to[i]) ){
				//}

				//if( !adjacences.get(to[i]).contains(currentNode)){
				//}
			//}
		}
	}
	
	public static int BFS(HashMap<Integer, ArrayList<Integer>> adjLst, int start , int arrive) {
	    Queue<Integer> queue = new ArrayDeque<>();
	    HashSet<Integer> seen = new HashSet<>();
	    queue.add(start);
	    int count = 0 ;
	    while(0 != queue.size()){
	    	Integer vertex = queue.poll();
	        if(!seen.contains(vertex)){
	            //System.out.print(vertex + " ");
	            queue.addAll(adjLst.get(vertex)); // Add all neighbors of 'vertex' to the queue
	            seen.add(vertex);
	        }
	        if ( seen.contains(arrive))
	        	return count ;
	        
	    }
	    
	    System.out.println("\nBFS terminé .");
	    return Integer.MAX_VALUE ;
	}
	

	/* renvoie le plus grand nombre de voisin qu'un noeud peut avoir dans le graphe*/
	/*private int getMaxVoisins(){
		int max = -1;
		for(int i = 1; i < this.indices.length; i++ ){
			//int s;
			if( this.indices[i] - this.indices[i-1] > max ){
				max = this.indices[i] - this.indices[i-1];
			}
		}
		return max;
	}


	private int getMaxVoisins_2(){
		int max = -1;
		int ind = -1;
		for(int i = 1; i < this.indices.length; i++ ){
			//int s;
			if( this.indices[i] - this.indices[i-1] > max ){
				max = this.indices[i] - this.indices[i-1];
				ind = i-1;
			}
		}

		int cpt = 0;
		for(int i = 0; i < this.voisins.length; i++){
			// on saute tout les u 
			if( i == this.indices[cpt++]){
				continue;
			}

			if( this.voisins[i] == this.voisins[ind]){
				max++;
			}
		}


		return max;
	}
*/
	private int getMaxVoisinsQ(){
		// for { for{}}
		int max = -1;
		int maxId = -1;
		for( Integer i : this.adjacences.keySet()){
			if( adjacences.get(i).size() > max){
			//if( (int) adjacences.get(i).stream().distinct().count() > max){
				//max = Arrays.stream(adjacences.get(i)).distinct().count();
				max =(int) adjacences.get(i).stream().distinct().count();
				if( adjacences.get(i).contains(i) ){
					max++;
				}

				max = adjacences.get(i).size();
			}

			if( maxId < i){
				maxId = i;
			}
		}

		this.maxEdgeId = maxId +1;
		return max;
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

				cpt ++;
			}

			//maxEdgeId += 1 ; 

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
		Integer []keys = this.adjacences.keySet().toArray(new Integer[this.adjacences.keySet().size()]);
		for(Integer i = 0 ; i < keys.length; i++ )
		{
			if( edgeID == (int) keys[i] )
			{
				return i;
			}
		}
		return -1;

	}

	public static void mem() {
		Runtime rt = Runtime.getRuntime();
		rt.gc();
		System.err.println("Allocated memory : "+ (rt.totalMemory() - rt.freeMemory()) / 1000000 + " Mb");
		System.err.flush();
	}
}