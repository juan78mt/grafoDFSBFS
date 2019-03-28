/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proy1grafo;


import java.io.*;
import java.util.*;

public class Grafo 
{
    //numero de nodos
    public int V;
    //LinkedList para guardar el grafo por medio de representacion de lista de adyacencia
    private LinkedList<Integer> adj[];
    public LinkedList<Integer>[] adj2;
   // Grafo grafo1 = new Grafo(V);
        //grafo explorado
     //   Grafo grafo2 = new Grafo(V);
    //parametro para el algoritmo de Barabasi
    int g = 4;       
    //creacion de grafo nodos y aristas
    //nodoi -> nodoj
    public HashMap<Integer, Nodo> nodos = new HashMap<Integer,Nodo>();
    //llave y numero de nodo
    public HashMap<Integer, Integer> aristas = new HashMap<Integer,Integer>();
    
      
    public Grafo(int V){
        //grafo generado        
        this.V = V;
        adj = new LinkedList[V];
        for(int i = 0; i< V; ++i){
            adj[i] = new LinkedList();
        }
        
        adj2 = new LinkedList[V];
        for(int i = 0; i< adj2.length; i++){
            adj2[i] = new LinkedList<Integer>();
        }
    }
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
         adj[nodoi].add(nodoj);    
         adj2[nodoi].add(nodoj);    
         aristas.put(nodoi, nodoj);        
       // System.out.println(nodoi+" -"+ nodoj);        
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
       // Grafo grafo1 = new Grafo(n);
        float probNormal;
        
       // grafo1.generarNodos(n);
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
                    
                    crearArista(val1, val2); 
                    aristasGeneradas++;
                    System.out.println(i+ "arista creada");
                    i++;
                    if(aristasGeneradas == m) break;
                }
            }
        }
        
        escribirArchivo();
        imprimirGrafo(aristas);
       
        
        
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
        //Grafo grafo1 = new Grafo(n);
        //grafo1.generarNodos(n);
        int val1, val2;
       // int aristasGeneradas = 0;
        for(int n_i = 1; n_i < n; n_i++){
            for(int n_j = 1; n_j < n; n_j++){
                
                   val1 = rand.nextInt(n);
                   val2 = rand.nextInt(n);
              
                   crearArista(val1, val2);
            }
        }
        
        escribirArchivo();
        imprimirGrafo(aristas);
    }
    
     /*Geografico
    n-nodos
    r-aristas
    d-dirigido o no dirigido
    a-permitir ciclos o no ciclos    
    */
    public Grafo crearGrafoGeo(int n, float r, boolean d, boolean a)
    {        
        Random rand = new Random();
        int prob = rand.nextInt();
        Grafo grafo1 = new Grafo(n);
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
        
    grafo1.escribirArchivoGeo(grafo1);
    grafo1.imprimirGrafoGeo(grafo1.aristas, grafo1);
    
    return grafo1;
           
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
        
      //  Grafo grafo1 = new Grafo(n);
      //  grafo1.generarNodos(n);
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
                    crearArista(val1, val2);
                }
                
            }
            grado++;   //suma del grado
        }
        
    escribirArchivo();
    imprimirGrafo(aristas);
              
    }
    
   //GRAFO EXPLORADO

    //nodo origen s
    void BFS(int s) 
    {   
        boolean visited[] = new boolean[V]; 
        Grafo grafo2 = new Grafo(V);
       // System.out.println("s"+ s + "V" + V);

        LinkedList<Integer> queue = new LinkedList<Integer>(); 
      
        visited[s]=true; 
        queue.add(s); 
  
        while (!queue.isEmpty()){
               
            s = queue.poll(); 
            System.out.print(s+" "); 

            Iterator<Integer> i =adj[s].listIterator();
            //int i2 = 0;         
            while (i.hasNext()){
              //  System.out.println("i.hasNext  "+ s+" "); 
                int n = i.next(); 
                if (!visited[n]){
                //    System.out.println("visited  "+ s+" "); 
                    visited[n] = true; 
                    queue.add(n);
                    grafo2.crearArista(s, n);
                   // System.out.println(s+ "--"+ n);
                } 
            } 
        }
       // System.out.println("fin de while");
        
        escribirArchivo();
        imprimirGrafo(aristas);   
    } //fin BFS 
    

    
    public void DFSiterativo(int s)
    {        
        Vector<Boolean> visited = new Vector<Boolean>(V); 
        for (int i = 0; i < V; i++) 
            visited.add(false); 
        Grafo grafo2 = new Grafo(V);

        Stack<Integer> stack = new Stack<>(); 
        stack.push(s); 

        while(stack.empty() == false)
        { 
            s = stack.peek(); 
            stack.pop(); 
         
            if(visited.get(s) == false) 
            {
                System.out.println(s + " ");
                visited.set(s,true);
           
            } 

            Iterator<Integer> itr= adj2[s].iterator(); 

            while(itr.hasNext()){
                
                int vn = itr.next(); 
                if(!visited.get(vn)){ 
                    stack.push(vn);
                    grafo2.crearArista(s, vn); 
                }
            } 

        }//fin while
        
    escribirArchivo();
    imprimirGrafo(aristas);      
     
    }//fin DFS iterativo 
    

    
    void recursionDFS(int v,boolean visited[]) 
    {       
        visited[v] = true; 
        System.out.print(v+" "); 
        
        Grafo grafo2 = new Grafo(V);
       
        Iterator<Integer> i = adj[v].listIterator(); 
        while (i.hasNext()){            
            int n = i.next();            
            if (!visited[n]){                              
                recursionDFS( n, visited);
                crearArista(n, v); 
            } 
        }
        
      escribirArchivo();
      imprimirGrafo(aristas);
        
    }//fin de DFS recursivo 
  
    void DFSrecursivo(int v) 
    {      
        boolean visited[] = new boolean[V]; 
 
        recursionDFS(v, visited); 
    } 
    
    //--------------------ALGORITMOS BFS DFSi  y DFSr usados para el grafo geometrico-----------------------------------------
    
     //nodo origen s
    void BFSGEO(int s,Grafo grafoa) 
    {   
        boolean visited[] = new boolean[V]; 
        Grafo grafo2 = new Grafo(V);
       // System.out.println("s"+ s + "V" + V);

        LinkedList<Integer> queue = new LinkedList<Integer>(); 
      
        visited[s]=true; 
        queue.add(s); 
  
        while (!queue.isEmpty()){
               
            s = queue.poll(); 
            System.out.print(s+" "); 

            Iterator<Integer> i = grafoa.adj[s].listIterator();
            //int i2 = 0;         
            while (i.hasNext()){
              //  System.out.println("i.hasNext  "+ s+" "); 
                int n = i.next(); 
                if (!visited[n]){
                //    System.out.println("visited  "+ s+" "); 
                    visited[n] = true; 
                    queue.add(n);
                    grafo2.crearArista(s, n);
                   // System.out.println(s+ "--"+ n);
                } 
            } 
        }
       // System.out.println("fin de while");
        
        grafo2.escribirArchivoGeo(grafo2);
        grafo2.imprimirGrafoGeo(grafo2.aristas, grafo2); 
    } //fin BFS 
    

    
    public void DFSiterativoGEO(int s,Grafo grafoa)
    {        
        Vector<Boolean> visited = new Vector<Boolean>(V); 
        for (int i = 0; i < V; i++) 
            visited.add(false); 
        Grafo grafo2 = new Grafo(V);

        Stack<Integer> stack = new Stack<>(); 
        stack.push(s); 

        while(stack.empty() == false)
        { 
            s = stack.peek(); 
            stack.pop(); 
         
            if(visited.get(s) == false) 
            {
                System.out.println(s + " ");
                visited.set(s,true);
           
            } 

            Iterator<Integer> itr= grafoa.adj2[s].iterator(); 

            while(itr.hasNext()){
                
                int vn = itr.next(); 
                if(!visited.get(vn)){ 
                    stack.push(vn);
                    grafo2.crearArista(s, vn); 
                }
            } 

        }//fin while
        
    grafo2.escribirArchivoGeo(grafo2);
    grafo2.imprimirGrafoGeo(grafo2.aristas, grafo2);      
     
    }//fin DFS iterativo 
    

    
    void recursionDFSGEO(int v,boolean visited[],Grafo grafoa, Grafo grafo2) 
    {       
        visited[v] = true; 
        System.out.println(v+" "); 
        
        
       
        Iterator<Integer> i = grafoa.adj[v].listIterator(); 
        while (i.hasNext()){            
            int n = i.next();            
            if (!visited[n]){                              
                recursionDFSGEO( n, visited, grafoa, grafo2);
                grafo2.crearArista(n, v); 
            } 
        }     
        
    }//fin de DFS recursivo 
    
    void imprimirDFSGEO(Grafo grafo2)
    {
        grafo2.escribirArchivoGeo(grafo2);
        grafo2.imprimirGrafoGeo(grafo2.aristas, grafo2); 
    }
  
    void DFSrecursivoGEO(int v, Grafo grafoa) 
    {      
        boolean visited[] = new boolean[V]; 
        Grafo grafo2 = new Grafo(V);
 
        recursionDFSGEO(v, visited, grafoa, grafo2); 
        imprimirDFSGEO(grafo2);
    } 
    
    
    
    
    
    
    
    //------------------------------------------------------------------------------------
    
    
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
       // System.out.println("\n\n GRAFO IMPRESO." + adjList + "\n\n"); 
        
        return archivogv;
                
        
    }//fin de metodo ToString
    
    public void escribirArchivo()
    {
        String path="C:\\Users\\JUAN\\Desktop\\grafo1.gv";
           
        try { 
            File archivo = new File("C:\\Users\\JUAN\\Desktop\\grafo1.gv"); 
           // if (archivo.createNewFile())
            //{
                FileWriter fw = new FileWriter(archivo);             
                //BufferedWriter bw = new BufferedWriter(fw);
                //obtener la cadena
                String cadena = toString();

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
    
    
    public void escribirArchivoGeo(Grafo grafo)
    {
        String path="C:\\Users\\JUAN\\Desktop\\grafo1.gv";
           
        try { 
            File archivo = new File("C:\\Users\\JUAN\\Desktop\\grafo1.gv"); 
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
   
    public static void imprimirArbol(LinkedList list) 
    {       
        System.out.println("Arbol generado" + list);          
    } 
          
    public void imprimirGrafo(Map<Integer, Integer> Mapgrafo)  
    {
        //public void imprimirGrafo(Map<Integer, Integer> grafo)  
        if (Mapgrafo.isEmpty())  
        { 
            System.out.println("el grafo esta vacio"); 
        }           
        else
        {
            System.out.println("Valores de la adjunta");
            for(int i = 0; i <adj.length; i++)
                System.out.println(adj[i]); 
            System.out.println();
            
            int i = 1;//contador de numero de aristas
            System.out.println("grafo obtenido");
            for(Map.Entry impGrafo:  aristas.entrySet()){
                System.out.println(i +".-"+ impGrafo);
                i++;
            }
            
        } 
    }
    
    public void imprimirGrafoGeo(Map<Integer, Integer> Mapgrafo, Grafo objGrafo)  
    {
        //public void imprimirGrafo(Map<Integer, Integer> grafo)  
        if (Mapgrafo.isEmpty())  
        { 
            System.out.println("el grafo esta vacio"); 
        }           
        else
        {
            System.out.println("Valores de la adjunta");
            for(int i = 0; i < objGrafo.adj.length; i++)
                System.out.println(adj[i]); 
            System.out.println();
            
            int i = 1;//contador de numero de aristas
            System.out.println("grafo obtenido");
            for(Map.Entry impGrafo:  aristas.entrySet()){
                System.out.println(i +".-"+ impGrafo);
                i++;
            }
            
        } 
    } 

 
    
}//fin de la clase Grafo
