public class Triangles {
	

	Graph g;
	long nb_triangles;
	boolean voisins_u[];

	public Triangles(Graph g){
		this.g = g;
		voisins_u = new boolean[g.n];
		
	}

	public long triangle(int u){

		
		for (Integer v : g.neighbors(u)) {
			voisins_u[v] = true;
		}

		nb_triangles = 0;
		for (Integer v : g.neighbors(u)) {
			for (Integer v2 : g.neighbors(v)) {
				if(voisins_u[v2]){
					nb_triangles++;
				}
			}
		}

		for (Integer v : g.neighbors(u)) {
			voisins_u[v] = false;
		}

		return nb_triangles / 2;
	}

	public void cluster(){

		double sumClust = 0 ; 
		long sum_tri = 0 ;
		long nb_tri_x = 0 ;
		double calc = 0 ;
		long count = 0 ;

		for ( int i = 0 ; i < this.g.n ; i++){

			if ( this.g.deg[i] >= 2 ){

				nb_tri_x = this.triangle(i) ;

				if ( nb_tri_x >= 1 ){
				
					sum_tri += nb_tri_x ; // pour le global 

					calc = ( 2 * nb_tri_x ) ;

					calc /= (double) (this.g.deg[i] * ( this.g.deg[i] - 1 )); // local

					sumClust += calc ;

				}
			}
			count +=  this.g.deg[i] * ( this.g.deg[i] - 1 ) /2 ;
		}
		double calcglobal = sum_tri ;
		calcglobal /= (double)(count) ;
				
		System.out.printf("%.5f\n", (float)(sumClust / this.g.n ) ) ;
		System.out.printf("%.5f\n", (float)((calcglobal)) ) ;
	}
}
