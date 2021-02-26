import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.FileReader;

public class TP4 {

	public static void main(String[] args) throws IOException {
        
        
		
        //Edges edg = new Edges();
        //edg.add(new FileReader(fname), Integer.MAX_VALUE );        
        
        //Graph g = new Graph(edg, true);

        switch( args[0] ){

            case "exemple" :
                int[] deg = {1, 2, 1, 4};
                int sum = 0 ;
                for ( int elt : deg )
                    sum += elt ;
                Integer[] E = new Integer[sum];
                int cpt = 0  ;

                for ( int j = 0  ; j < deg.length ; j++ ){
                    for ( int i = 0 ; i < deg[j] ;i++ ){
                        E[cpt] = j ;
                        cpt++ ;
                    }
                }
                List<Integer> intList = Arrays.asList(E);

		        Collections.shuffle(intList);

		        intList.toArray(E);

		        //System.out.println(Arrays.toString(E));

                for ( int i = 0 ; i < E.length ; i+=2 )
                    System.out.println(""+E[i]+'\t'+E[i+1]); 


                

                break;
               
            default :
                System.out.println(" Not yet implemented ! ");
        }

	
    }
}
