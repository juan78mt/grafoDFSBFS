/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proy1grafo;

/**
 *
 * @author labcic
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class Nodo
{
    public int id;

    Nodo(int id,float x, float y ) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public char Nombre;
    public char datos;
    
    public float x;
    public float y;

 
    
  

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
   

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
 
    
    
    
}//fin de clase Nodo
