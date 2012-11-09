//****************************************************************
// Autor:   Yimy Juarez 
//          Gustavo Castro
//          Ulil Cojti
// 
// Universidad del Valle de Guatemala 
// Algoritmos y estructuras de datos
// Seccion: 10
// Nombre del Archivo: Graph.java
// Fecha de Modificacion:08/11/2012
// Descripcion: 
// Clase que representa la matriz de adyacencia de un grafo
// ***************************************************************
package hoja8;
import java.util.*;

public class Graph {

	protected Vector<Node> Nodes = new Vector<Node>();
	protected Vector<Edge> Edges = new Vector<Edge>();
	protected boolean Directed = false;
	protected boolean sortedNEighbors = false;
        protected double[][] math;
	
	public double[][] getAdjacencyMatrix() {
		double[][] adjMatrix = new double[Nodes.size()][Nodes.size()];
		
		for(int i = 0; i < Nodes.size(); i++)
			for(int j = 0; j < Nodes.size(); j++)
				if(i == j)
					adjMatrix[i][j] = 0;
				else
					adjMatrix[i][j] = Double.POSITIVE_INFINITY;
				
		for(int i = 0; i < Nodes.size(); i++) {
			Node node = Nodes.elementAt(i);
			//System.out.println("Current node: " + node);
			
			for(int j = 0; j < Edges.size(); j++) {
				Edge edge = Edges.elementAt(j);
                                //System.out.println(edge.toString()+" "+edge.getWeight());
				if(edge.a == node) {
					int indexOfNeighbor = Nodes.indexOf(edge.b);
					
					adjMatrix[i][indexOfNeighbor] = edge.weight;
				}
			}
		}
        //        imprimeMatriz(adjMatrix);
                
		return adjMatrix;
	}
	
        public void imprimeMatriz(double[][] adjMatrix){
            for(int i = 0; i < Nodes.size(); i++) {
                System.out.println();
		for(int j = 0; j < Nodes.size(); j++) 
                    {
                        System.out.printf("[%f]",adjMatrix[i][j]);
                    }
            }
            
        }
	public void setDirected() {
		Directed = true;
	}
	
	public void setUndirected() {
		Directed = false;
	}
	
	public boolean isDirected() {
		return Directed;
	} 
	
	public int indexOf(Node a) {
		for(int i = 0; i < Nodes.size(); i++)
			if(Nodes.elementAt(i).data.equals(a.data))
				return i;
				
		return -1;
	}
	
	public Vector<Node> getNodes() {
		return Nodes;
	}
	
	public Vector<Edge> getEdges() {
		return Edges;
	}
	
	public Node getNodeAt(int i) {
		return Nodes.elementAt(i);
	}
		
	public Vector<Node> getNeighbors(Node a) {
		Vector<Node> neighbors = new Vector<Node>();
		
		for(int i = 0; i < Edges.size(); i++) {
			Edge edge = Edges.elementAt(i);
			
			if(edge.a == a)
				neighbors.add(edge.b);
				
			if(!Directed && edge.b == a)
				neighbors.add(edge.a);
		}
		
		if(sortedNEighbors)
			Collections.sort(neighbors);
		
		return neighbors;
	}
            
	public int addNode(Node a) {
		Nodes.add(a);
		
		return Nodes.size() - 1;
	}
	
	public void addEdge(Edge a) {
		Edges.add(a);
		
		if(!Directed)
			Edges.add(new Edge(a.b, a.a, a.weight));
	}
	
	public void printNodes() {
		System.out.println(Nodes);
	}
	
	public void printEdges() {
		System.out.println(Edges);
	}
        public void eliminaArista(String nodo1,String nodo2){
            Node x=null;
            Node y=null;
            int indice=0;
            for(int i=0;i<Nodes.size();i++){
                if(nodo1.equals(Nodes.get(i).toString()))
                   x=Nodes.get(i); 
                if(nodo2.equals(Nodes.get(i).toString()))
                   y=Nodes.get(i); 

            }
            for(int i=0;i<Edges.size();i++){
                if(x.equals(Edges.get(i).getOrigen()))
                {     
                    if(y.equals(Edges.get(i).getDestino()))
                           Edges.remove(i); 
                }   
            }                               
 
        }
        public Node getNodeByName(String name){
            for(int i=0;i<Nodes.size();i++)
                if(name.equals(Nodes.get(i).toString()))
                    return Nodes.get(i);
            return null;
        }
        public void setMatrixPath(double[][] mat){
            this.math = mat;
        }
        public double[][] getMatrixPath(){
            return this.math;
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