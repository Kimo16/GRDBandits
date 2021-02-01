// data structures:
import java.util.Vector;
import java.util.ArrayDeque;
// iterators:
import java.util.stream.IntStream;
import java.util.NoSuchElementException;
import java.util.Iterator;
// file parsing:
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;

class TP2 {
    
    private final static String two_sweep = "2-sweep";
    private final static String four_sweep = "4-sweep";
    private final static String sum_sweep = "sum-sweep";
    private final static String diametre = "diametre";
    private final static String search_node = "search-node";

    public static void mem() {
        Runtime rt = Runtime.getRuntime();
        rt.gc();
        System.err.println("Allocated memory : "
                           + (rt.totalMemory() - rt.freeMemory()) / 1000000
                           + " Mb");
        System.err.flush();
    }


    /* Double Bread First Search implementation */
    private static void dBFS(int departure,Graph g){
        Traversal trav = new Traversal(g.n);

        trav.bfs(g, departure,0);
        int v = trav.getFarthest();

        trav.bfs(g,v,0);
        int w = trav.getFarthest();
        int dist = trav.distance(w);


        System.out.println( "v = " + v );
        System.out.println( "w = " + w );
        System.out.println("diam >= " + dist );
    }

    private static void fourSweep(int departure, Graph g){
        Traversal trav = new Traversal(g.n);
        trav.bfs(g,departure,0);
        int v = trav.getFarthest();

        trav.bfs(g,v,0);
        int w = trav.getFarthest();
        int dist = trav.distance(w);

        int m = trav.searchMiddleDist(w,dist);

        trav.bfs(g,m,0);
        v = trav.getFarthest();

        trav.bfs(g,v,0);
        w = trav.getFarthest();
        dist = trav.distance(w);

        System.out.println("diam >= " + dist );

    }

    private static void sumSweep(int departure , Graph g ) {

        int maxDist = -1 ;
        Traversal trav = new Traversal(g.n);

        trav.bfs(g, departure,0);
        int u = trav.getFarthest();
        int dist_uv = trav.distance(u);

        if ( maxDist < dist_uv){
            maxDist = dist_uv ;
        }

        trav.bfs(g,u,0);
        int v = trav.getFarthest();
        int dist_vw = trav.distance(v);

        if ( maxDist < dist_vw){
            maxDist = dist_vw ;
        }

        trav.bfs(g,v,0);
        int w = trav.getFarthest();
        int dist_wz = trav.distance(w);

        if ( maxDist < dist_wz){
            maxDist = dist_wz ;
        }

        trav.bfs(g,w,0);
        int z = trav.getFarthest();
        int dist_za = trav.distance(z);

        if ( maxDist < dist_za){
            maxDist = dist_za ;
        }

        System.out.println("diam >= " + maxDist );


    }

    public static void main(String[] args) throws IOException {
        
        //mem();

        // Some tests about array/vector size :

        // Size of an int array: 4 bytes per int.
        int[] arr = new int[1000000];
        
        //mem();

        // Size of a vector of integers: 16 bytes per int.
        Vector<Integer> vec = new Vector<>();
        for (int u = 0; u < 1000000; ++u) { vec.add(u); }
        //System.err.println("vector " + vec.size() + " " + vec.capacity());
        
        //mem();

        // Read edges of a graph.
        String fname = args[1];
        //System.out.println("0 : " + args[0] + "1 : "+ args[1] + " 2 : "+ args[2]);
        int m_max = Integer.parseInt(args[2]);
        Edges edg = new Edges();
        edg.add(new FileReader(fname), m_max);        // 15s for socpock
        //System.err.println("n = " + edg.n + " m = " + edg.m);

        //mem();

        // Construct adjacency lists.
        Graph g = new Graph(edg, true);


        /**
        En commentaire car non demandé dans l'affichage final.
        **/
        //System.out.println("n=" + g.n);
        //System.out.println("m=" + g.m); 
        
        //mem();

        // Check the number of arcs.
        int n = 0;
        int m = 0;
        for (int u : g) {
            ++n;
            for (int v : g.neighbors(u)) ++m;
        }
        //System.err.println("n = " + n + " m = " + m + " m/2 = " + (m/2));

        // Maximum degree:
        int degmax = 0, n_degodd = 0;
        for (int u : g) {
            int du = g.degree(u);
            if (du > degmax) degmax = du;
            if (du % 2 == 1) ++n_degodd;
        }
        //System.out.println("degmax=" + degmax);
        //System.err.println("n_degodd=" + n_degodd);

        // Distance from [src] to [dst].
        int src = Integer.parseInt(args[3]);
        //int dst = Integer.parseInt(args[3]);
        //Traversal trav = new Traversal(g.n);

        switch( args[0] ){
            case search_node :
                System.out.println("nodeId max component: "+ new Traversal(g.n).searchNodeInLargestComponent(g));
                break;

            case two_sweep :
                dBFS(src,g);
                break;

            case four_sweep : 
                fourSweep(src,g);
                break;
            case sum_sweep :
                sumSweep(src,g);
                break ;
                
            default :
                System.out.println(" Not yet implemented ! ");
        }

    }
}