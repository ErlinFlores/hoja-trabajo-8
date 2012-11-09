//****************************************************************
// Autor:   Yimy Juarez 
//          Gustavo Castro
//          Ulil Cojti
// 
// Universidad del Valle de Guatemala 
// Algoritmos y estructuras de datos
// Seccion: 10
// Nombre del Archivo: principal.java
// Fecha de Modificacion:08/11/2012
// Descripcion: 
// Clase que representa la conexion entre nodos y su peso 
// ***************************************************************

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hoja8;

public class Edge {

	protected Node a, b;
	protected double weight;
	
	public Edge(Node a, Node b) {
		this(a, b, Double.POSITIVE_INFINITY);
	}
	
	public Edge(Node a, Node b, double weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
                
	}
	
	public double getWeight() {
		return weight;
	}
	
	public String toString() {
		return a + " ==> " + b;
	}
        public Node getOrigen(){
            return a;
        }
        public Node getDestino(){
            return b;
        }
        

}