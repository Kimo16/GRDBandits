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
}
