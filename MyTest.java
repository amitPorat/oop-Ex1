package ex1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTest {
    @Test
    void nodeSize() {              //mini test for the 'addNode' and 'remove node'
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        int s = g.nodeSize();
        System.out.println("num of nodes:" +s);
       // g.removeNode(2);
        //g.removeNode(1);
        s = g.nodeSize();
        System.out.println("num of nodes:" +s);
    }

    @Test
    void edgeSize() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);

       g.connect(0,1,2.5);
       g.connect(0,2,2);

        int e_size =  g.edgeSize();
        System.out.println("num of edges:" +e_size);
        double w01 = g.getEdge(0,1);
        System.out.println("edge (0,1) size:" +w01);
    }


}
