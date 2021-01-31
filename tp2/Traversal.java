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
    int max = 0;
    int farthest;
    public static final int infinity = Integer.MAX_VALUE; 
    
    Traversal(int n) {
        queue = new ArrayDeque<>();
        dist = new int [n];
        roots = new int[n];
    }

    void clear() {
        queue.clear();
        for (int i = 0; i < dist.length; ++i) { dist[i] = infinity; }
    }
    
    int getMax(){
        return this.max;
    }
    public int getFarthest(){
        return this.farthest;
    }
    int distance(int v) { return dist[v]; }

    // Bread first search, returns the number of visited nodes.
    int bfs(Graph g, int src) {
        assert(g.n <= dist.length);
        clear();

        dist[src] = 0;
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
            incr = incr - 1 ;
        }

        return current_node;
    }
}