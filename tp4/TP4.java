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
                Integer[] e = exempleFunc(deg);

                for ( int i = 0 ; i < e.length ; i+=2 )
                {
                    System.out.println(""+e[i]+'\t'+e[i+1]); 
                }

                break;

            case "racine":
                Integer[] r = racineFunc(Integer.parseInt(args[1]));
                
                for ( int i = 0 ; i < r.length ; i+=2 )
                {
                    System.out.println(""+r[i]+'\t'+r[i+1]);
                }
                break;


            case "puissance":
                int n = Integer.parseInt(args[1]);
                double gamma = Double.parseDouble(args[2]);

                Integer[] p = puissanceFunc(n,gamma);

                for ( int i = 0 ; i < p.length ; i+=2 )
                {
                    System.out.println(""+p[i]+'\t'+p[i+1]);
                }
                break;
               
            default :
                System.out.println(" Not yet implemented ! ");
        }

	
    }



    public static Integer[] exempleFunc( int [] deg)
    {
        int sum = 0 ;
        
        for ( int elt : deg )
        {
            sum += elt ;
        }

        Integer[] E = new Integer[sum];
        int cpt = 0  ;

        for ( int j = 0  ; j < deg.length ; j++ )
        {
            for ( int i = 0 ; i < deg[j] ;i++ )
            {
                E[cpt] = j ;
                cpt++ ;
            }
        }

        List<Integer> intList = Arrays.asList(E);

        Collections.shuffle(intList);

        

        return intList.toArray(E);

    }


    public static Integer[] racineFunc(int n )
    {
        int[] deg = new int [n];
        
        for ( int i = 0 ; i < deg.length ; i++)
        {
            deg[i] = ((int) Math.sqrt(i + 1.));
        }

        int sum = 0;
        
        for( int elt : deg)
        {
            sum += elt;
        }

        if( sum %2 == 1 )
        {
            deg[deg.length -1 ] += 1 ;
        }

        Integer[] e = exempleFunc(deg);

        return e ;
    }
    public static Integer[] puissanceFunc(int n, double gamma){
        int[] deg = new int[n];

        double[] nb_occ_deg_k = new double[n];

        double sum=0;

        for(int i=1; i<nb_occ_deg_k.length;i++){
            
            nb_occ_deg_k[i] = Math.pow(i, -gamma);

            sum+=nb_occ_deg_k[i];
            
        }
        double c = 1 / sum;
        for (int i = 0; i < nb_occ_deg_k.length; i++) {
            
            nb_occ_deg_k[i] *= c;
        }

        int sum2=0;
        for (int i = 1; i < nb_occ_deg_k.length; i++) {

            nb_occ_deg_k[i] = (int) Math.round(nb_occ_deg_k[i]*n);
            
            sum2+=nb_occ_deg_k[i];
        }

        int sum3=0;
        int cpt = 0  ;
        for(int k=1; k < nb_occ_deg_k.length;k++){
            
            for( int i=0; i < nb_occ_deg_k[k]; i++){
                deg[cpt] = k;
                sum3+= k;
                cpt++;
            }
        }
        if( sum3 %2 == 1 )
        {   
            sum3++;
            deg[cpt]++;
        }


        
        Integer[] E = new Integer[sum3];
        cpt = 0  ;

        for ( int j = 0  ; j < deg.length ; j++ )
        {
            for ( int i = 0 ; i < deg[j] ;i++ )
            {
                E[cpt] = j ;
                cpt++ ;
            }
        }

        List<Integer> intList = Arrays.asList(E);

        Collections.shuffle(intList);

        

        return intList.toArray(E);
        
    }
}
