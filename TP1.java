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
		System.out.println("n="+g.maxNoeudId);
		System.out.println("m="+g.nbAretes);
		System.out.println("degmax=" + g.maxDegree);
		System.out.println("dist=" +g.distance(sommetStart,sommetArrive));
		mem();

	}

    public static void mem() {
		Runtime rt = Runtime.getRuntime();
		rt.gc();
		System.err.println("Allocated memory : "+ (rt.totalMemory() - rt.freeMemory()) / 1000000 + " Mb");
		System.err.flush();
	}
}