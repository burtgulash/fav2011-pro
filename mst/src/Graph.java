class Graph {
    Edge[] v;

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
    
    
    void addEdge(int src, int dst, int weight) {
        addEdge(new Edge(src, dst, weight));
    }

    void addEdge(Edge e) {
        Edge dummy = v[e.source];    
        e.next = dummy.next;
        dummy.next = e;
    }
    

    int vertices() {
        return v.length;
    }

    int edges() {
        int edges = 0;
        for (int i = 0; i < vertices(); i++)
            for (Edge e = v[i].next; e != null; e = e.next)
                edges++;
        return edges;
    }
}
