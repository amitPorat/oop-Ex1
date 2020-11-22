package ex1;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms {

    private weighted_graph gr;

    public WGraph_Algo(weighted_graph myGraph) {
        this.gr = myGraph;
    }

    public WGraph_Algo() {
        gr = new WGraph_DS();
    }

    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        gr = g;
    }

    /**
     * Return the underlying graph of which this class works.
     *
     * @return
     */
    @Override
    public weighted_graph getGraph() {
        return gr;
    }

    /**
     * Compute a deep copy of this weighted graph.
     *
     * @return
     */
    @Override
    public weighted_graph copy() {
        return new WGraph_DS(gr);
    }

    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume ubdirectional graph.
     *
     * @return
     */
    @Override
    public boolean isConnected() {               //think about it
        if (gr.getV().isEmpty()) { return true;}
        node_info tempNode= gr.getV().iterator().next();
        HashSet<Integer> tempsNodes = new HashSet<>();
        tempsNodes.add(tempNode.getKey());
        Stack<node_info> sta = new Stack<>();
        sta.add(tempNode);
        boolean b = BFSCONACTIVITY(sta , tempNode , tempsNodes);
        return b;
    }
    private boolean BFSCONACTIVITY(Stack<node_info> sta, node_info tempNode, HashSet<Integer> tempsNodes) {
        while (!sta.isEmpty()) {
            tempNode =sta.pop();
            for (node_info node : gr.getV(tempNode.getKey())) {
                if (!tempsNodes.contains(node.getKey())) {
                    sta.add(node);
                    tempsNodes.add(node.getKey());
                }
            }
        }
        if (tempsNodes.size()==gr.nodeSize() ) {return true;}
        return false;
    }

    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        return 5.1;
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        return null;
    }

    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        try {
            FileOutputStream st = new FileOutputStream(file);
            ObjectOutputStream qw = new ObjectOutputStream(st);
            qw.writeObject(gr);
            st.close();
            qw.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            FileInputStream pw = new FileInputStream(file);
            ObjectInputStream re = new ObjectInputStream(pw);
            gr = (WGraph_DS) re.readObject();
            re.close();
            pw.close();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

//    public void Dijkstra (WGraph_DS.NodeInfo[] G, WGraph_DS.NodeInfo s){
//        PriorityQueue<WGraph_DS.NodeInfo> Q = new PriorityQueue<>();
//        s.setDist(0);
//        for (int i = 0; i<= G.length-1; i++){     //add to our queue the all nodes
//            Q.add(G[i]);
//        }
//        Q.toArray();
//
//        while (!Q.isEmpty()){
//            WGraph_DS.NodeInfo u = Q.poll();
//            for(int i=0; i <=u.ge)
//        }
   

}


