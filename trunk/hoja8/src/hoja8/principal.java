/*
 * Universidad del Valle de Guatemala 
 * Algoritmos y estructuras de datos
 * 
 */
package hoja8;

import java.util.*;
import java.io.*;
import java.lang.String;



public class principal {
        
    public void lecturaArchivo(Scanner iFile, Graph graph){
        		while(iFile.hasNext()) {
			Node<String> a = new Node<String>(iFile.next());
			Node<String> b = new Node<String>(iFile.next());
			
			int aPos = graph.indexOf(a);
			int bPos = graph.indexOf(b);
			
			if(aPos == -1)
				aPos = graph.addNode(a);
			
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
    public void pideDatos(BufferedReader entradaEstandar, Graph graph,principal aps) throws IOException{
                String interrup="",conexion="";
                String nodo1="",nodo2="";
                String inicio="",fin="";
                String resp="";
                double adjMatrix[][],tempo[][]; 
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
                    adjMatrix=graph.getAdjacencyMatrix();
                    tempo = adjMatrix;
                    graph.path(graph.indexOf(graph.getNodeByName(inicio)), graph.indexOf(graph.getNodeByName(fin)), graph.floydReconstruction(tempo));                                        
 
//                    graph.imprimeMatriz(tempo);
                    aps.actualizaMatrices(adjMatrix,graph,inicio,fin);

//                    graph.imprimeMatriz(adjMatrix);
                    System.out.println("CENTRO DEL GRAFO: "+graph.getNodeAt(centro.centroGrafo(adjMatrix)));
                    System.out.println("Desea continuar s/n:");
                    resp = entradaEstandar.readLine();
                    
                    System.out.println();
                    
                }
                while(resp.equals("s"));
                
    }
    public static void main(String[] args) throws Exception {
		Scanner iFile = new Scanner(new FileReader("arch.txt"));
                BufferedReader entradaEstandar = new BufferedReader(new InputStreamReader(System.in));                    

		Graph graph = new Graph();
		graph.setDirected();		
                principal aps = new principal();
                aps.lecturaArchivo(iFile,graph);                	
                aps.pideDatos(entradaEstandar, graph,aps);
		iFile.close();	                               
               
    }
        

}