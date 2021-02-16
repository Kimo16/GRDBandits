public class Triangles {
	

	Graph g;
	int nb_triangles;
	boolean voisins_u[];

	public Triangles(Graph g){
		this.g = g;
		voisins_u = new boolean[g.n];
		
	}

	public int triangle(int u){

		
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

		float sumClust = 0 ; 
		int sum_tri = 0 ;
		int nb_tri_x = 0 ;
		float calc = 0 ;
		int count = 0 ;
		int deg_temp= 0 ;

		for ( int i = 0 ; i < this.g.n ; i++){

			if ( this.g.deg[i] >= 2 ){

				nb_tri_x = this.triangle(i) ;

				if ( nb_tri_x >= 1 ){
				
					sum_tri += nb_tri_x ; // pour le global 

					calc = ( 2 * nb_tri_x ) ;

					calc /= (float) (this.g.deg[i] * ( this.g.deg[i] - 1 )); // local

					sumClust += calc ;

				}
			}
			
			count +=  this.g.deg[i] * ( this.g.deg[i] - 1 ) /2 ;

		}

		float calcglobal = sum_tri ;
		calcglobal /= (float)(count) ;
				
		System.out.printf("%.5f\n", (float)(sumClust / this.g.n ) ) ;
		System.out.printf("%.5f\n", (float)((calcglobal)) ) ;
	}
}
