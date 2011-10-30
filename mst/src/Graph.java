/**
 * Graph data structure
 *
 * Stores undirected weighted graph
 */
class Graph {
    Edge[] v;

    /**
     * Construct Graph where |V| == numVertices
     */ 
    Graph(int numVertices) {
        v = new Edge[numVertices];

        for (int i = 0; i < v.length; i++)
            v[i] = new Edge(i, i, -1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(vertices() + " " + edges() + "\n");

        for (int i = 0; i < vertices(); i++)
            for (Edge e = v[i].next; e != null; e = e.next)
                builder.append(e.toString() + "\n");

        return builder.toString();
    }
    
    /**
      * Returns total cost of graph
     */
    // use only for output of Prim, bidirectional edges might be counted twice
    int totalCost() {
        int total = 0;
        for (int i = 0; i < vertices(); i++)
            for (Edge e = v[i].next; e != null; e = e.next)
                total += e.weight;
        return total;
    }
    
    /**
     * Adds edge to graph
     */
    void addEdge(int src, int dst, int weight) {
        addEdge(new Edge(src, dst, weight));
    }

    /**
     * Adds edge to graph
     */
    void addEdge(Edge e) {
        Edge dummy = v[e.source];    
        e.next = dummy.next;
        dummy.next = e;
    }
    

    /** 
     * return number of vertices
     */ 
    int vertices() {
        return v.length;
    }

    /** 
     * return number of edges
     */ 
    int edges() {
        int edges = 0;
        for (int i = 0; i < vertices(); i++)
            for (Edge e = v[i].next; e != null; e = e.next)
                edges++;
        return edges;
    }
}
