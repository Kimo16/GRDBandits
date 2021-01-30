public class TP2
{

	private final static String two_sweep = "2-sweep";
	private final static String four_sweep = "4-sweep";
	private final static String sum_sweep = "sum-sweep";
	private final static String diametre = "diametre";


	public static void main(String[] args)
	{
		/*recupérer les informations*/
		
		String algorithm = args[0];
		String fname = args[1];
        int estimNbArretes = Integer.parseInt(args[2]);
		int sommetStart = Integer.parseInt(args[3]);
		
		/*construire le graphe*/
		
		Graph g = new Graph(fname, estimNbArretes);

		/*effectuer les calculs demander*/

		switch( algorithm ){
			case two_sweep :
				DoubleBFS d_bfs = new DoubleBFS(g);
				
				d_bfs.doubleBFSAlgorithim(sommetStart);

				System.out.println( "v = " + d_bfs.getV() );
				System.out.println( "w = " + d_bfs.getW() );
				System.out.println("diam >= " + d_bfs.getDiametre() );
				break;

			default :
				System.out.println(" Not yet implemented ! ");
		}
	}


}