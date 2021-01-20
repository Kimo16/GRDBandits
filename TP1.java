import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TP1
{

	public static void main(String[] args)
	{
		/*recupérer les informations*/
		/*construire le graphe*/
		/*effectuer les calculs demander*/

		String fname = args[0];
		int nb = getLineNumber(fname);
		int sommetStart = Integer.parseInt(args[2]);
		int sommetArrive = Integer.parseInt(args[3]);
		
		Graph g = new Graph(fname, Integer.parseInt(args[1]),sommetStart,sommetArrive);
		//BreadFirstTraversal bfs = new BreadFirstTraversal(g);
		System.out.println("n="+g.maxEdgeId);
		System.out.println("m="+g.nbVertex);
		System.out.println("degmax=" + g.maxDegree);
		System.out.println("dist="+g.dist);
		mem();
		
		
		//System.out.println("dist=" + bfs.breadthFirstTraversal(1,1000));
	}
/* INUTILE pour le rendu vu que l'on a le nombre de ligne en parametre normalement*/
	private static int getLineNumber(String fname) {
		//System.out.println("in function");
		Process p;
		String s;
		int res = -1;
		try {
			// get the line count : wc -l
			p = Runtime.getRuntime().exec("wc -l "+ fname);
			BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream()));
			
			/* single reading due to wc -l return type */
			s = br.readLine();
			res = Integer.valueOf(s.split(" ")[0]);
			p.waitFor();
			p.destroy();
		}catch( Exception e) {
			
		}
		//System.out.println("end function");
		return res;
	}

	public static void mem() {
		Runtime rt = Runtime.getRuntime();
		rt.gc();
		System.err.println("Allocated memory : "+ (rt.totalMemory() - rt.freeMemory()) / 1000000 + " Mb");
		System.err.flush();
	}
}
