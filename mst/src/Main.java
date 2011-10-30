import java.util.StringTokenizer;

import java.util.NoSuchElementException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;


public class Main {
    private static String USAGE = 
    "usage: java mst [INFILE]\n\ngraph format:\n|V| |E|\nsrc dst weight\n...";


    public static void main (String [] args) {
        InputStream inFile = null;
        if (args.length == 1) {
            if (args[0].equals("-h")) {
                System.err.println(USAGE);
                System.exit(0);
            }
            try {
                inFile = new FileInputStream(args[0]);
            } catch (FileNotFoundException fnf) {
                System.err.printf("File %s not found", args[0]);
                System.exit(1);
            }
        } else if (args.length == 0)
            inFile = System.in;    
        else {
            System.err.println(USAGE);
            System.exit(1);
        }

        Graph graph = getGraph(new BufferedReader(
                               new InputStreamReader(inFile)));
        if (graph == null)
            System.exit(1);

        Graph mst   = Prim.findMst(graph);
        mst.print();
        System.exit(0);
    }


    private static Graph getGraph(BufferedReader reader) {
        String line;
        StringTokenizer t;
        try {
            line  = reader.readLine();
        } catch (IOException ex) {
            System.err.println("Error reading file");
            return null;
        }
        t     = new StringTokenizer(line);
        int v = 0, e = 0;

        try {
            v = Integer.parseInt(t.nextToken());
            e = Integer.parseInt(t.nextToken());

            if (t.hasMoreTokens())
                throw new NoSuchElementException();
        } catch (NumberFormatException ex) {
            System.err.println("Error reading vertex or edge count");
            return null;
        } catch (NoSuchElementException ex) {
            System.err.println("Graph format error");
            return null;
        }


        Graph g = new Graph(v);
        try {
            while (e > 0) {
                line     = reader.readLine();
                t        = new StringTokenizer(line);
                int src  = Integer.parseInt(t.nextToken());
                int dst  = Integer.parseInt(t.nextToken());
                int w    = Integer.parseInt(t.nextToken());

                if (0 > src || 0 > dst || src >= v || dst >= v || w < 0) {
                    System.err.println("Graph format error");
                    return null;
                }
                    
                g.addEdge(src, dst, w);
                g.addEdge(dst, src, w);

                e--;
            }
        } catch (EOFException ex) {
            System.err.println("Not enough edges provided");
            return null;
        } catch (IOException ex) {
            System.err.println("Error reading file");
            return null;
        }

        try {
            reader.close();
        } catch (IOException ex) {
            System.err.println("Chyba pri zavirani souboru");
        }

        return g;
    }
}
