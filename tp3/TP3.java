import java.io.IOException;
import java.io.FileReader;

public class TP3 {

	private final static String triangles = "triangles";
	private final static String cluster = "clust";
	private final static String kcoeur = "k-coeur";


	public static void main(String[] args) throws IOException {
        
        
        String fname = args[1];    
		int m_max = Integer.parseInt(args[2]);
		
        Edges edg = new Edges();
        edg.add(new FileReader(fname), m_max);        
        
        Graph g = new Graph(edg, true);

        Triangles t = new Triangles(g);

        Kcoeur k = new Kcoeur(g);        

        switch( args[0] ){
            case triangles :
                System.out.println(t.triangle(Integer.parseInt(args[3])));
                break;
            case cluster :
            	t.cluster();
                break;
            case kcoeur : 
            	k.decomposition();
            	break;
            default :
                System.out.println(" Not yet implemented ! ");
        }


		
    }
}
