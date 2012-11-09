package hoja8;
import java.util.*;

public class Graph {

	protected Vector<Node> nodes = new Vector<Node>();
	protected Vector<Edge> edges = new Vector<Edge>();
	protected boolean directed = false;
	protected boolean sortedNeighbors = false;
        protected double[][] mat;
	
	public double[][] getAdjacencyMatrix() {
		double[][] adjMatrix = new double[nodes.size()][nodes.size()];
		
		for(int i = 0; i < nodes.size(); i++)
			for(int j = 0; j < nodes.size(); j++)
				if(i == j)
					adjMatrix[i][j] = 0;
				else
					adjMatrix[i][j] = Double.POSITIVE_INFINITY;
				
		for(int i = 0; i < nodes.size(); i++) {
			Node node = nodes.elementAt(i);
			//System.out.println("Current node: " + node);
			
			for(int j = 0; j < edges.size(); j++) {
				Edge edge = edges.elementAt(j);
                                //System.out.println(edge.toString()+" "+edge.getWeight());
				if(edge.a == node) {
					int indexOfNeighbor = nodes.indexOf(edge.b);
					
					adjMatrix[i][indexOfNeighbor] = edge.weight;
				}
			}
		}
        //        imprimeMatriz(adjMatrix);
                
		return adjMatrix;
	}
	
        public void imprimeMatriz(double[][] adjMatrix){
            for(int i = 0; i < nodes.size(); i++) {
                System.out.println();
		for(int j = 0; j < nodes.size(); j++) 
                    {
                        System.out.printf("[%f]",adjMatrix[i][j]);
                    }
            }
            
        }
	public void setDirected() {
		directed = true;
	}
	
	public void setUndirected() {
		directed = false;
	}
	
	public boolean isDirected() {
		return directed;
	} 
	
	public int indexOf(Node a) {
		for(int i = 0; i < nodes.size(); i++)
			if(nodes.elementAt(i).data.equals(a.data))
				return i;
				
		return -1;
	}
	
	public Vector<Node> getNodes() {
		return nodes;
	}
	
	public Vector<Edge> getEdges() {
		return edges;
	}
	
	public Node getNodeAt(int i) {
		return nodes.elementAt(i);
	}
		
	public Vector<Node> getNeighbors(Node a) {
		Vector<Node> neighbors = new Vector<Node>();
		
		for(int i = 0; i < edges.size(); i++) {
			Edge edge = edges.elementAt(i);
			
			if(edge.a == a)
				neighbors.add(edge.b);
				
			if(!directed && edge.b == a)
				neighbors.add(edge.a);
		}
		
		if(sortedNeighbors)
			Collections.sort(neighbors);
		
		return neighbors;
	}
            
	public int addNode(Node a) {
		nodes.add(a);
		
		return nodes.size() - 1;
	}
	
	public void addEdge(Edge a) {
		edges.add(a);
		
		if(!directed)
			edges.add(new Edge(a.b, a.a, a.weight));
	}
	
	public void printNodes() {
		System.out.println(nodes);
	}
	
	public void printEdges() {
		System.out.println(edges);
	}
        public void eliminaArista(String nodo1,String nodo2){
            Node x=null;
            Node y=null;
            int indice=0;
            for(int i=0;i<nodes.size();i++){
                if(nodo1.equals(nodes.get(i).toString()))
                   x=nodes.get(i); 
                if(nodo2.equals(nodes.get(i).toString()))
                   y=nodes.get(i); 

            }
            for(int i=0;i<edges.size();i++){
                if(x.equals(edges.get(i).getOrigen()))
                {     
                    if(y.equals(edges.get(i).getDestino()))
                           edges.remove(i); 
                }   
            }                               
 
        }
        public Node getNodeByName(String name){
            for(int i=0;i<nodes.size();i++)
                if(name.equals(nodes.get(i).toString()))
                    return nodes.get(i);
            return null;
        }
        public void setMatrixPath(double[][] mat){
            this.mat = mat;
        }
        public double[][] getMatrixPath(){
            return this.mat;
        }
        public int[][] floydReconstruction(double[][] matriz ){
            double matrix[][]  = matriz;            
            int next[][] = new int[matrix.length][matrix.length];
            
            for(int k=1;k<matrix.length;k++){
               for(int i=1;i<matrix.length;i++){
                   next[k][i]=k;                       
               }
            }            
            
            for(int k=1;k<matrix.length;k++){
               for(int i=1;i<matrix.length;i++){
                   for(int j=1;j<matrix.length;j++){
                       if((matrix[i][k]+matrix[k][j])<matrix[i][j]){
                           matrix[i][j]=matrix[i][k]+matrix[k][j];
                           next[i][j] = next[k][j];                           
                           next[j][i] = next[k][i];                                                      
                       }
                   }
               }
            }   
            setMatrixPath(matrix);
            return next;
        }
        public void path(int i,int j, int[][] next){
            if(i!=j){
                path(i,next[i][j],next);
            }
            System.out.print(getNodeAt(j)+" ");
        }
        
        
}