/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hoja8;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author miriam
 */
public class GraphCenter {
    private Double[][] matriz;
    private ArrayList<Double> array = new ArrayList<Double>();
    private ArrayList<Double> maximos = new ArrayList<Double>();

    public GraphCenter(){
    }
    public int centroGrafo(double[][] matrix)
    {
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix.length;j++)            
            {   
                array.add(matrix[j][i]);
            }
            maximos.add(Collections.max(array));
            array.clear();
        }
       Double tempo=Collections.min(maximos);
       int min=0;
       for(int i=0;i<matrix.length;i++)
           if(tempo.equals(maximos.get(i)))
               min = i;
       return min;
     }
    
    
}

