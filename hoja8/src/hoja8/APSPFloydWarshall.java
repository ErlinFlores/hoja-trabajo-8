/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hoja8;

import java.util.*;
import java.io.*;
import java.lang.String;


// Written by: Yancy Vance M. Paredes.

// Finding all-pairs shortest path using Floyd Warshall's algorithm.

public class APSPFloydWarshall {
        
    public void lecturaArchivo(Scanner iFile, Graph graph){
        		while(iFile.hasNext()) {
			Node<String> a = new Node<String>(iFile.next());
			Node<String> b = new Node<String>(iFile.next());
			
			int aPos = graph.indexOf(a);
			int bPos = graph.indexOf(b);
			
			// If a does not exist in the graph yet.
			if(aPos == -1)
				aPos = graph.addNode(a);
			
			// If b does not exist in the graph yet.
			if(bPos == -1)
				bPos = graph.addNode(b);
			
			double weight = iFile.nextDouble();
			
			Edge edge = new Edge(graph.getNodeAt(aPos), graph.getNodeAt(bPos), weight);
			
			graph.addEdge(edge);
		}

    }
    public void actualizaMatrices(double adjMatrix[][], Graph graph, String ciudad1, String ciudad2){

        for(int k = 0; k < adjMatrix.length; k++)
			for(int i = 0; i < adjMatrix.length; i++)
				for(int j = 0; j < adjMatrix.length; j++)
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
		
                int x,y;
                x = graph.indexOf(graph.getNodeByName(ciudad1));
                y = graph.indexOf(graph.getNodeByName(ciudad2));
                
                System.out.println("EL CAMINO MAS CORTO DESDE: " +ciudad1+" HASTA: "+ciudad2+" ES: "+adjMatrix[x][y]);
/**                for(int i = 0; i < adjMatrix.length; i++) {
			System.out.println("From " + graph.getNodeAt(i) + ":");
			
			for(int j = 0; j < adjMatrix.length; j++)
				System.out.println("\tShortest Path Cost to " + graph.getNodeAt(j) + " is: " + adjMatrix[i][j]);
			
			System.out.println();
		}*/

    }
    public void eliminaCamino(BufferedReader entradaEstandar, Graph graph, String nodo1, String nodo2) throws IOException{
                System.out.println("INGRESE LUGAR DE PARTIDA");
                nodo1 = entradaEstandar.readLine();
                System.out.println("INGRESE LUGAR DE LLEGADA");
                nodo2 = entradaEstandar.readLine();
                graph.eliminaArista(nodo1, nodo2);
        
    }
    public void estableceConexion(BufferedReader entradaEstandar, Graph graph) throws IOException{
              Edge arista;
              Node a,b;
              double weight;
              String inicio="",fin="",peso="";
              System.out.println("INGRESE LUGAR DE PARTIDA");
              inicio = entradaEstandar.readLine();
              System.out.println("INGRESE LUGAR DE LLEGADA");
              fin = entradaEstandar.readLine();                                       
              System.out.println("INGRESE DISTANCIA ENTRE CARRETERAS");
              peso = entradaEstandar.readLine();   
              weight = Double.parseDouble(peso);              
              a = graph.getNodeByName(inicio);
              b = graph.getNodeByName(fin);
              arista = new Edge(a,b,weight);            
              graph.addEdge(arista);              

    }
    public void pideDatos(BufferedReader entradaEstandar, Graph graph,APSPFloydWarshall aps) throws IOException{
                String interrup="",conexion="";
                String nodo1="",nodo2="";
                String inicio="",fin="";
                String resp="";
                double adjMatrix[][]; 
                do{
                    adjMatrix = graph.getAdjacencyMatrix();		                               
                    System.out.println("INGRESE LUGAR DE PARTIDA");
                    inicio = entradaEstandar.readLine();
                    System.out.println("INGRESE LUGAR DE LLEGADA");
                    fin = entradaEstandar.readLine();
                    GraphCenter centro = new GraphCenter();                                
                    aps.actualizaMatrices(adjMatrix,graph,inicio,fin);
                    System.out.println("CENTRO DEL GRAFO: "+graph.getNodeAt(centro.centroGrafo(adjMatrix)));
                    System.out.println("Hay interrupción entre carreteras s/n");
                    interrup = entradaEstandar.readLine();
                    if(interrup.equals("s")){
                           aps.eliminaCamino(entradaEstandar,graph,nodo1,nodo2);                             		                    
                    }
                    System.out.println("Establecer conexión entre ciudades s/n");
                    conexion = entradaEstandar.readLine();
                    if(conexion.equals("s")){
                        aps.estableceConexion(entradaEstandar,graph);

                    }
                    aps.actualizaMatrices(graph.getAdjacencyMatrix(),graph,inicio,fin);

                    System.out.println("CENTRO DEL GRAFO: "+graph.getNodeAt(centro.centroGrafo(adjMatrix)));
                    System.out.println("Desea continuar s/n:");
                    resp = entradaEstandar.readLine();
                }
                while(resp.equals("s"));
                
    }
    public static void main(String[] args) throws Exception {
		Scanner iFile = new Scanner(new FileReader("arch.txt"));
                BufferedReader entradaEstandar = new BufferedReader(new InputStreamReader(System.in));                    

		Graph graph = new Graph();
		graph.setDirected();		
                APSPFloydWarshall aps = new APSPFloydWarshall();
                aps.lecturaArchivo(iFile,graph);                	
                aps.pideDatos(entradaEstandar, graph,aps);
		iFile.close();	                
                
               
    }
        

}