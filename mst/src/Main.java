import java.util.StringTokenizer;

import java.util.NoSuchElementException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;



public class Main {
    private static String USAGE = 
    "usage: java mst [-o OUTFILE] [INFILE]\n\n" + 
    "  graph format:\n    |V| |E|\n    src dst weight\n    ...";


    public static void main (String [] args) {
        InputStream  inFile   = System.in;
        OutputStream outFile  = System.out;

        if (args.length == 1 && args[0].equals("-h")) {
            System.err.println(USAGE);
            System.exit(0);
        }

        boolean outFileSet = false;
        boolean inFileSet  = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-o") && args.length > i + 1) {
                if (outFileSet) {
                    System.err.println("Output file already set");
                    System.exit(1);
                }
                    
                try {
                    outFile = new FileOutputStream(args[i + 1]);
                } catch (FileNotFoundException fnf) {
                    System.err.printf("Can not open %s for writing\n", 
                                       args[i + 1]);
                    System.exit(1);
                } catch (SecurityException se) {
                    System.err.printf("Don't have permission to write to %s\n",
                                       args[i + 1]);
                    System.exit(1);
                }
                outFileSet = true;
                i++;
            } else if (inFileSet) {
                System.err.println("Input file already set");
                System.exit(1);
            } else {
                try {
                    inFile = new FileInputStream(args[i]);
                } catch (FileNotFoundException fnf) {
                    System.err.printf("Can not open %s for reading\n", 
                                       args[i]);
                    System.exit(1);
                } catch (SecurityException se) {
                    System.err.printf("Don't have permission to read from %s\n",
                                       args[i]);
                    System.exit(1);
                }
                inFileSet = true;
            }
        }
        Graph graph = getGraph(new BufferedReader(
                               new InputStreamReader(inFile)));

        PrintWriter writer = new PrintWriter(outFile);

        if (graph == null)
            System.exit(1);

        Graph mst   = Prim.findMst(graph);
        if (mst == null)
            System.exit(1);

        writer.print(mst);
        writer.flush();
        writer.close();

		System.err.printf("Weight of tree: %d\n", mst.totalCost());
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
        int v = 0, e = 0;

        try {
            t     = new StringTokenizer(line);
            v = Integer.parseInt(t.nextToken());
            e = Integer.parseInt(t.nextToken());

            if (t.hasMoreTokens())
                throw new NoSuchElementException();
        } catch (NullPointerException ex) {
            System.err.println("Error reading vertex or edge count");
            return null;
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
        } catch (NullPointerException ex) {
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
