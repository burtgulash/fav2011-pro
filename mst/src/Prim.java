import java.util.PriorityQueue;


public class Prim {
    private static final int startVertex = 0;
    private static PriorityQueue<Edge> pq;
    private static boolean[] inTree;


    private static void initQueue(Graph graph) {
        inTree    = new boolean[graph.vertices()];
        pq        = new PriorityQueue<Edge>();

        inTree[startVertex] = true;

        Edge e = graph.v[startVertex].next;
        while (e != null) {
            pq.offer(e);
            e = e.next;
        }
    }


    public static Graph findMst(Graph graph) {
        initQueue(graph);
        Graph mst = new Graph(graph.vertices());

        // add v - 1 edges
        Edge current = null;
        for (int i = 0; i < graph.vertices() - 1; i++) {
            try {
                do
                    current = pq.poll();
                while (inTree[current.destination]);
            } catch (NullPointerException ex) {
                System.err.println("Graph was not connected");
                return null;
            }

            mst.addEdge(current);
            inTree[current.destination] = true;
    
            Edge neighbor = graph.v[current.destination].next;
            while (neighbor != null) {
                if (!inTree[neighbor.destination])
                    pq.offer(neighbor);
                neighbor = neighbor.next;
            }
        }
        
        return mst;
    }
}
