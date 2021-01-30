public class DoubleBFS
{
	private Graph graph;
	private BreadFirstSearch bfs;
	private int v;
	private int w;
	private int diametre = 0;

	private int distance = 0; // max distance between u and w


	public DoubleBFS(Graph graph){
		this.graph = graph;
		this.bfs = new BreadFirstSearch(graph);
	}


	public void doubleBFSAlgorithim(int departure){
		/* we get a departure node */
		int originEdge = departure;

		/* we compute a Bread First Search */
		bfs.breadFirstAlgorithm( originEdge );

		/* we retrieve the farthest node from the departure : ' v ' */
		this.v = bfs.getFarthestNode(); 

		
		/* we take ' v ' as departure node now */
		originEdge = this.v;

		/* we compute a Bread First Search */
		bfs.breadFirstAlgorithm( originEdge );
		
		/* we retrieve the farthest node from the departure : ' w ' */
		this.w = bfs.getFarthestNode();

		/* we get the distance between u and w as diameter */
		bfs = new BreadFirstSearch(this.graph);
		this.diametre = bfs.breadFirstAlgorithm( this.v, this.w );
	}


	public int getV(){
		return this.v;
	}

	public int getW(){
		return this.w;
	}

	public int getDiametre(){
		return this.diametre;
	}
}