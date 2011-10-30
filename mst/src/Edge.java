class Edge implements Comparable {
    int source, destination, weight;
    Edge next;

    Edge(int src, int dst, int w) {
        source = src;
        destination = dst;
        weight = w;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Edge))
            return -1;
        Edge other = (Edge) o;

        int cmp = weight - other.weight;
        if (cmp == 0)
            return 0;
        return cmp/Math.abs(cmp);
    }

    @Override
    public String toString() {
        return String.format("%d %d %d", source, destination, weight);
    }
}
