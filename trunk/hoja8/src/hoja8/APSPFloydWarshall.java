/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hoja8;

import java.util.*;
import java.io.*;

// Written by: Yancy Vance M. Paredes.

// Finding all-pairs shortest path using Floyd Warshall's algorithm.

public class APSPFloydWarshall {
	
	public static void main(String[] args) throws Exception {
		Scanner iFile = new Scanner(new FileReader("arch.txt"));
		Graph graph = new Graph();
		graph.setDirected();
		
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
		
	
		double adjMatrix[][] = graph.getAdjacencyMatrix();
		
		for(int k = 0; k < adjMatrix.length; k++)
			for(int i = 0; i < adjMatrix.length; i++)
				for(int j = 0; j < adjMatrix.length; j++)
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
		
		for(int i = 0; i < adjMatrix.length; i++) {
			System.out.println("From " + graph.getNodeAt(i) + ":");
			
			for(int j = 0; j < adjMatrix.length; j++)
				System.out.println("\tShortest Path Cost to " + graph.getNodeAt(j) + " is: " + adjMatrix[i][j]);
			
			System.out.println();
		}
		
		iFile.close();	
                GraphCenter centro = new GraphCenter();
                
                System.out.println("CENTRO DEL GRAFO: "+graph.getNodeAt(centro.centroGrafo(adjMatrix)));
               
	}
        

}