package MarauderPNS.simulation;

import java.util.Comparator;

public class NodeFComparator implements Comparator<Node> {

    @Override
    public int compare(Node n1, Node n2) {
        return n1.getF()-n2.getF();
    }

}
