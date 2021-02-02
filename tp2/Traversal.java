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

// Data-structure for searching a graph.
class Traversal {
    ArrayDeque<Integer> queue;
    
    int[] dist;
    int[] roots;
    int[] connect_component;

    int max = 0;
    int farthest;
    public static final int infinity = Integer.MAX_VALUE; 
    
    Traversal(int n) {
        queue = new ArrayDeque<>();
        dist = new int [n];
        roots = new int[n];
        connect_component = new int[n];
    }

    void clear() {
        queue.clear();
        for (int i = 0; i < dist.length; ++i) { 
            dist[i] = infinity; 
            roots[i] = -1; 
        }
        max = 0 ; 
        farthest = 0;
    }
    
    int getMax(){
        return this.max;
    }
    public int getFarthest(){
        return this.farthest;
    }
    int distance(int v) { return dist[v]; }

    // Bread first search, returns the number of visited nodes.
    int bfs(Graph g, int src, int component_id) {
        assert(g.n <= dist.length);
        clear();

        dist[src] = 0;
        roots[src] = src; 
        connect_component[src] = component_id;

        queue.add(src);
        int n = 0;
        
        while ( ! queue.isEmpty()) {
            int u = queue.poll(); // FIFO
            int d = dist[u];
            ++n;
            for (int v : g.neighbors(u)) {
                if (dist[v] == infinity) { // first discovery of v
                    dist[v] = d + 1;
                    roots[v] = u ;
                    
                    if(connect_component[v] == 0){
                        connect_component[v] = component_id;
                    }

                    if( (d + 1) > max){
                        max = d +1;
                        this.farthest = v;
                    }
                    queue.add(v);
                }
            }
        }

        return n;
    }

    int searchMiddleDist(int v, int distance){
        int incr = distance;
        int current_node = v ;  
        int father = -1 ; 

        while (incr > distance / 2 )
        {   
            father = roots[current_node];
            current_node = father;

            if( current_node == -1 )
            {
                System.out.println("Error when trying to find root node");
                System.exit(1);
            }
            incr = incr - 1 ;
        }

        return current_node;

    }

    int searchNodeInLargestComponent(Graph g){
        assert(g.n <= dist.length);
        clear();
        int component_id = 1;

        for(int i = 0 ; i < connect_component.length ; i++)
        {
            if(connect_component[i] == 0 ){
                bfs(g,i,component_id++);
            }
        }


        int [] component_size = new int[component_id + 1];

        for( int i = 0 ; i < connect_component.length ; i++ )
        {
            component_size[connect_component[i]] += 1;
        }

        System.out.println();
        int max_size = 0; 
        int max_component_id = 0; 
        
        for ( int i = 1 ; i < component_size.length ; i++ )
        {
            if( max_size < component_size[i])
            {
                max_size = component_size[i];
                max_component_id = i ;
            }
        }

        for(int i = 0 ; i < connect_component.length ; i++ )
        {
            if(connect_component[i] == max_component_id)
            {
                return i; 
            }
        }
        return -1;
    }
    int[] get_connex(Graph g, int sommet){

        int n = this.bfs(g, sommet, 0);

        int[] cnx = new int[n];
        int j=0;
        for (int i = 0; i < g.n; i++) {
            if(this.dist[i] != infinity){
                cnx[j] = i;
                j++;
                
            }
        }
        return cnx;
    }
}