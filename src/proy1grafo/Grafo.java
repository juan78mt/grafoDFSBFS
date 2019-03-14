/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proy1grafo;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;



import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Grafo 
{
    //Grafo grafo1 = new Grafo();
    public Grafo()
    {
    }
    
    
    //parametro para el algoritmo de Barabasi
    int g = 4;       
    //creacion de grafo nodos y aristas
    //nodoi -> nodoj
    public HashMap<Integer, Nodo> nodos = new HashMap<Integer,Nodo>();
    //llave y numero de nodo
    public HashMap<Integer, Integer> aristas = new HashMap<Integer,Integer>();

    
    //generar numero de nodos
    public void generarNodos(int numNodos)
    {
        int rand_intVal;       
         //crear nodos llave valor de forma aleatorio
         Random rand = new Random();
         
         
         
        for(int i = 1; i < numNodos; i++){
              float coorx = rand.nextFloat();        
              float coory = rand.nextFloat();
              Nodo nodoi = new Nodo(i, coorx, coory); 
              
               nodoi.x = coorx;
               nodoi.y = coory;
               nodoi.id = i;
               nodos.put(i,nodoi);  
               
        }     
    }
    
    public void obtenerNodoxy(int n)
    {
        if(nodos.containsKey(n)){
            System.out.print("El codigo " + n + " corresponde a ");
            System.out.println(nodos.get(n));
            
      
        }else{
            System.out.println("El codigo introducido no existe");
        }
    }
    
    

    
    //asocia nodos
    public void crearArista(int nodoi, int nodoj)
    {       
       // aristai.nodoA = 
        //this.aristas.getOrDefault(nodo1, nodo2);   
        aristas.put(nodoi, nodoj); 
        
    }
    
    //creación del grafo 
    //Erdos
    //Gilbert
    //geo
    /*
    n-nodos
    m- aristas
    d-dirigido o no dirigido
    a-permitir ciclos o no ciclos    
    */
    public void crearGrafoErdos(int n, int m, boolean d, boolean a)
    {
        double p = 0.0;
        Random rand = new Random();
        int prob = rand.nextInt();
        Grafo grafo1 = new Grafo();
        float probNormal;
        
        grafo1.generarNodos(n);
        Random rande = new Random();
        int val1, val2;
        int i = 1;
        int aristasGeneradas = 0;
        for(int n_i = 1; n_i < n && aristasGeneradas < m ; n_i++){
            for(int n_j = 1; n_j < m; n_j++){
                probNormal = rand.nextFloat();
                System.out.println(probNormal);
                if(probNormal <0.5){
                    
                    val1 = rande.nextInt(n);
                    val2 = rande.nextInt(n);
                    
                    grafo1.crearArista(val1, val2); 
                    aristasGeneradas++;
                    System.out.println(i+ "arista creada");
                    i++;
                    if(aristasGeneradas == m) break;
                }
            }
        }
        
        grafo1.escribirArchivo(grafo1);
        grafo1.imprimirGrafo(grafo1.aristas);
       
        
        
    }//fin de ERdos
    
     /*
    n-nodos
    p- probabilidad de crear un par de nodos de generar una arista
    d-dirigido o no dirigido
    a-permitir ciclos o no ciclos    
    */    
    public void crearGrafoGilbert(int n, double p, boolean d, boolean a)
    {
        
        Random rand = new Random();
        int prob = rand.nextInt();
        Grafo grafo1 = new Grafo();
        grafo1.generarNodos(n);
        int val1, val2;
        int aristasGeneradas = 0;
        for(int n_i = 1; n_i < n; n_i++){
            for(int n_j = 1; n_j < n; n_j++){
                
                   val1 = rand.nextInt(n);
                   val2 = rand.nextInt(n);
              
                   grafo1.crearArista(val1, val2);
            }
        }
        
        grafo1.escribirArchivo(grafo1);
        grafo1.imprimirGrafo(grafo1.aristas);
        
        
        
    }
    
     /*Geografico
    n-nodos
    r-aristas
    d-dirigido o no dirigido
    a-permitir ciclos o no ciclos    
    */
    public void crearGrafoGeo(int n, float r, boolean d, boolean a)
    {
        
        Random rand = new Random();
        int prob = rand.nextInt();
        Grafo grafo1 = new Grafo();
        grafo1.generarNodos(n);
        
        int probNormal;
    
        int id;
        
        double dist;
        Nodo r1, r2, r3,r4;
        
        float ri, rj;
        
        Random randt = new Random();
        int val1, val2;
      
        //crear aristas 
         for(int n_i = 1; n_i < n; n_i++){
                for(int n_j = 1; n_j < n; n_j++){  

                
                r1 = grafo1.nodos.get(n_i);               
                r2 = grafo1.nodos.get(n_i);
                if(n_i > 1){                    
                    r3 = grafo1.nodos.get(n_i-1);
                    r4 = grafo1.nodos.get(n_i-1);
                    
                    ri = r1.x - r3.x;
                    rj = r2.y - r4.y;
                    
                    dist = Math.sqrt(Math.pow(ri, 2) + Math.pow(rj, 2)  );
                    
               // probNormal = rand.nextInt();
                    if(dist < r){
                        val1 = randt.nextInt(n);
                        val2 = randt.nextInt(n);
                        grafo1.crearArista(val1, val2);
                    }
                }
                
                //System.out.println(r1);
             
                    
            }       
        }
        
        grafo1.escribirArchivo(grafo1);
        grafo1.imprimirGrafo(grafo1.aristas);
           
    }
    
     /*
    n-nodos
    g = 4  grado
    d-dirigido o no dirigido
    a-permitir ciclos o no ciclos    
    */
    public void crearGrafoBarabasi(int n, int g, boolean d, boolean a)
    {
        Random rand = new Random();
        int prob = rand.nextInt();
        
        Grafo grafo1 = new Grafo();
        grafo1.generarNodos(n);
        //calculo de probabilidad        
        float volado;
        float p;
        int grado = 0;
        
        Random randg = new Random();
        int val1, val2;
      
        for(int n_i = 1; n_i < n; n_i++){
            for(int n_j = 1; n_j < n; n_j++){
               
                volado = rand.nextFloat();  //calcular el volado
                p = 1 - grado / g;          //obtener probabilidad
                if(volado < p){
                    val1 = randg.nextInt(n);
                    val2 = randg.nextInt(n);                    
                    grafo1.crearArista(val1, val2);
                }
                
            }
            grado++;   //suma del grado
        }
        
        grafo1.escribirArchivo(grafo1);
        grafo1.imprimirGrafo(grafo1.aristas);
              
    }
    
    //fomrato de archivo de grafo
    //concatenacion de los resultados del hashMap 
    //para el string  pasar el hashMap como 
    //nodoi -> nodoj
    @Override
    public String toString()
    {
        int a = 0;
        //cargar llave y valor como cadena
        String nodoCadena="";
        int val1, val2;
    
        String titulo = "digraph grafo1{"+"\n"; 
        for(Map.Entry grafo: aristas.entrySet()){
            //llave y valor
            val1 = (int) grafo.getKey();
            val2 = (int) grafo.getValue();
 
            nodoCadena += Integer.toString(val1)+ "->"+Integer.toString(val2)+";\n";  
            
        }
        String cabecera = "}";
        
        //cadena concatenada
        String archivogv = titulo+nodoCadena+cabecera;
        
        return archivogv;
                
        
    }//fin de metodo ToString
    
    public void escribirArchivo(Grafo grafo)
    {
        String path="C:\\Users\\mkdir91\\Desktop\\grafo1.gv";
           
        try { 
            File archivo = new File("C:\\Users\\mkdir91\\Desktop\\grafo1.gv"); 
           // if (archivo.createNewFile())
            //{
                FileWriter fw = new FileWriter(archivo);             
                //BufferedWriter bw = new BufferedWriter(fw);
                //obtener la cadena
                String cadena = grafo.toString();

                // Escribir al archivo
                fw.write(cadena);

                // Cerrar conexion
                //bw.close();
                fw.close();

                System.out.println("ARCHIVO ESCRITO EXITOSAMENTE." + archivo.getName()); 
           // } else { 
            //  System.out.println("El archivo ya existe."); 
                        

            
          //  System.out.println("ARCHIVO ESCRITO EXITOSAMENTE.");
        } catch (IOException e) {
            System.out.println("UN ERROR OCURRIO.");
            //reporte de errores
            e.printStackTrace();
       } 
    }
      
    public void imprimirGrafo(Map<Integer, Integer> grafo)  
    { 
        if (grafo.isEmpty())  
        { 
            System.out.println("el grafo esta vacio"); 
        }           
        else
        { 
            int i = 1;//contador de numero de aristas
            for(Map.Entry impGrafo:  aristas.entrySet()){
                System.out.println(i +".-"+ impGrafo);
                i++;
            }
            
        } 
    } 

 
    
}//fin de la clase Grafo
