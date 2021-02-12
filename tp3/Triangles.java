public class Triangles {
	

	Graph g;
	int nb_triangles;
	boolean voisins_u[];

	public Triangles(Graph g){
		this.g = g;
		
	}

	public int triangle(int u){

		voisins_u = new boolean[g.n];
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
		return nb_triangles / 2;
	}
}
