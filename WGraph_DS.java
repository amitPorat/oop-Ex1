package ex1;


import java.util.*;

public class WGraph_DS implements weighted_graph {


    private HashMap<Integer, node_info> nodes;
    private HashMap<Integer,HashMap<Integer , Edge>> edges;
    private int edges_size,nodes_size,mc;



    public WGraph_DS() {
        nodes = new HashMap<>();
        edges = new HashMap<>();
    }

    public WGraph_DS(weighted_graph myGraph) {
    }


    /**
     * return the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_info getNode(int key) {
        return nodes.get(key);
    }



    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(nodes.containsKey(node1) && nodes.containsKey(node2)) {

            return   edges.get(node1).containsKey(node2);

        }
        return false;
    }

    /**
     * return the weight if the edge (node1, node1). In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public double getEdge(int node1, int node2) {

        if (!hasEdge( node1,  node2)){
            return -1;
        }
        Edge ed =  edges.get(node1).get(node2);
        return ed.weight ;
    }

    /**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     *
     * @param key
     */
    @Override
    public void addNode(int key) {
        if (nodes.containsKey(key)){
            return;
       }
        edges.put(key,new HashMap<>());
        nodes.put(key , new NodeInfo(key));
        nodes_size++;
    }

    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     *
     * @param node1
     * @param node2
     * @param w
     */
    @Override
    public void connect(int node1, int node2, double w) {    //hVE  problems with the connections and the weight. 

        if(!(nodes.containsKey(node1) && nodes.containsKey(node2))){
            return;
        }

        if(hasEdge(node1,node2)){
            Edge ed= edges.get(node1).get(node2);
            Edge ed1= edges.get(node2).get(node1);
            ed.setWeight(w);
            ed1.setWeight(w);

            return;
        }

        node_info n1 = nodes.get(node1);
        Edge edge1 = new Edge(n1,w);
        edges.get(node2).put(node1, edge1);

        node_info n2 = nodes.get(node2);
        Edge edge2 = new Edge(n2,w);
        edges.get(node1).put(node2,edge2);

        edges_size++;

    }

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * Note: this method should run in O(1) tim
     *
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV() {     //send mail to boaz. changed the interface
        return nodes.values();
    }

    /**
     * This method returns a Collection containing all the
     * nodes connected to node_id
     * Note: this method can run in O(k) time, k - being the degree of node_id.
     *
     * @param node_id
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        ArrayList<node_info> arr = new ArrayList<>();
        if(nodes.containsValue(node_id)){
            Collection<Edge> edgesArr = edges.get(node_id).values();
            for (Edge e : edgesArr){
                arr.add(e.getDest());
            }
        }

        return arr;
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_info removeNode(int key) {
        if(!nodes.containsKey(key)){
            return null;

        }
        Set<Integer> keySet = nodes.keySet();
        for (int k: keySet) {
            removeEdge(k,key);
        }
        edges.remove(key);
        node_info n = nodes.remove(key);

        nodes_size--;
        return n;
    }

    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if(hasEdge(node1,node2)){
            edges.get(node1).remove(node2);
            edges.get(node2).remove(node1);
            edges_size--;

        }

    }

    /**
     * return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return nodes_size;
    }

    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return edges_size;
    }

    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     *
     * @return
     */
    @Override
    public int getMC() {
        return mc;
    }








    class NodeInfo implements node_info{


        private int key;
        private String metaData;
        private double tag; //like : "Boolean visted"   -1 ==false
        //private int dist;
        //private node_info pred;



        public NodeInfo() {
            this.metaData = "";
            this.tag = -1;
            //this.dist = Integer.MAX_VALUE;

        }

        public NodeInfo(int key) {
            this.key = key;
            this.metaData = "";
            this.tag = -1;
            //this.dist = Integer.MAX_VALUE;
            //this.pred = null;
        }

        public NodeInfo(node_info node) {
            this.key = node.getKey();
            this.metaData = node.getInfo();
            this.tag = node.getTag();


        }


        /**
         * Return the key (id) associated with this node.
         * Note: each node_data should have a unique key.
         *
         * @return
         */
        @Override
        public int getKey() {
            return key;
        }

        /**
         * return the remark (meta data) associated with this node.
         *
         * @return
         */
        @Override
        public String getInfo() {
            return metaData;
        }

        /**
         * Allows changing the remark (meta data) associated with this node.
         *
         * @param s
         */
        @Override
        public void setInfo(String s) {
            this.metaData = s;
        }

        /**
         * Temporal data (aka distance, color, or state)
         * which can be used be algorithms
         *
         * @return
         */
        @Override
        public double getTag() {
            return this.tag;
        }

        /**
         * Allow setting the "tag" value for temporal marking an node - common
         * practice for marking by algorithms.
         *
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(double t) {
            this.tag = t;

        }





    }





        class Edge{

            private node_info dest;
            private double weight;

            public Edge(node_info dest, double weight) {
                this.dest = dest;
                this.weight = weight;
            }

            public node_info getDest() {
                return dest;
            }

            public void setDest(node_info dest) {
                this.dest = dest;
            }

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }





        }


}
