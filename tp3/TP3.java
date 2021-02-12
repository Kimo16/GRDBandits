import java.io.IOException;
import java.io.FileReader;

public class TP3 {
	public static void main(String[] args) throws IOException {
        
        
        String fname = args[1];    
		int m_max = Integer.parseInt(args[2]);
		
        Edges edg = new Edges();
        edg.add(new FileReader(fname), m_max);        
        
        Graph g = new Graph(edg, true);

		Triangles t = new Triangles(g);
		System.out.println(t.triangle(Integer.parseInt(args[3])));
    }
}
