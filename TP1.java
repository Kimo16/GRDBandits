public class TP1
{

	public static void main(String[] args)
	{
		/*recupérer les informations*/
		/*construire le graphe*/
		/*effectuer les calculs demander*/

		String fname = args[0];
        int estimNbArretes = Integer.parseInt(args[1]);
		int sommetStart = Integer.parseInt(args[2]);
		int sommetArrive = Integer.parseInt(args[3]);
		
		Graph g = new Graph(fname, estimNbArretes);
		BreadFirstSearch bfs = new BreadFirstSearch(g);
		int distance = bfs.breadFirstAlgorithm(sommetStart,sommetArrive);
		System.out.println("dist=" +distance);
		

	}

}