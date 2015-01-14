package MarauderPNS.simulation;

import MarauderPNS.map.Field;
import MarauderPNS.map.Wall;
import MarauderPNS.user.Position;
import MarauderPNS.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Quentin on 14/01/2015.
 */
public class AStarSimulation {
    private Field map;
    private List<Node> openList;
    private List<Node> closeList;
    private List<Node> result;
    private final int COST_STRAIGHT = 100;
    private final int COST_DIAGONAL = 140;
    private int row;
    private int column;

    public AStarSimulation(Field map) {
        result = new ArrayList<>();
        this.map = map;
        this.row = map.getHeight();
        this.column = map.getWidth();
        openList = new ArrayList<Node>();
        closeList = new ArrayList<Node>();
    }
    /**
     *  search for the path between two positions.
     *
     * @param
     * @param
     * @param
     * @param
     * @return -1 error,0 non found, 1 found
     */
    public int search(User user,Position position ) {
        int x1 = user.getPosition().getX();
        int y1 = user.getPosition().getY();
        int x2 = position.getX();
        int y2 = position.getY();
        if (x1 < 0 || x1 >= row || x2 < 0 || x2 >= row || y1 < 0
                || y1 >= column || y2 < 0 || y2 >= column) {
            return -1;
        }
        if (map.getMyTable()[0][x1][y1].getClass().equals(Wall.class) || map.getMyTable()[0][x2][y2].getClass().equals(Wall.class)) {
            return -1;
        }
        Node sNode = new Node(x1, y1, null);
        Node eNode = new Node(x2, y2, null);
        //put the start node into the openlist.
        openList.add(sNode);
        List<Node> resultList = search(sNode, eNode);
        if (resultList.size() == 0) {
            return 0;
        }
        // memorize the trace.
    //    for (Node node : resultList) {
  //          map[node.getX()][node.getY()] = -1;
//        }
        result = resultList;
        return 1;
    }
    /**
     *  A star algorithm.
     *
     * @param sNode
     * @param eNode
     * @return
     */
    private List<Node> search(Node sNode, Node eNode) {
        List<Node> resultList = new ArrayList<Node>();
        boolean isFind = false;
        Node node = null;
        while (openList.size() > 0) {
            //get the first value in the openlist which has the lowest value F.
            node = openList.get(0);
            //judge if the end node has found.
            if (node.getX() == eNode.getX() && node.getY() == eNode.getY()) {
                isFind = true;
                break;
            }
            //top
            if ((node.getY() - 1) >= 0) {
                checkPath(node.getX(), node.getY() - 1, node, eNode,
                        COST_STRAIGHT);
            }
            //down
            if ((node.getY() + 1) < column) {
                checkPath(node.getX(), node.getY() + 1, node, eNode,
                        COST_STRAIGHT);
            }
            //left
            if ((node.getX() - 1) >= 0) {
                checkPath(node.getX() - 1, node.getY(), node, eNode,
                        COST_STRAIGHT);
            }
            //right
            if ((node.getX() + 1) < row) {
                checkPath(node.getX() + 1, node.getY(), node, eNode,
                        COST_STRAIGHT);
            }
            //left top
            if ((node.getX() - 1) >= 0 && (node.getY() - 1) >= 0) {
                checkPath(node.getX() - 1, node.getY() - 1, node, eNode,
                        COST_DIAGONAL);
            }
            //left down
            if ((node.getX() - 1) >= 0 && (node.getY() + 1) < column) {
                checkPath(node.getX() - 1, node.getY() + 1, node, eNode,
                        COST_DIAGONAL);
            }
            //right top
            if ((node.getX() + 1) < row && (node.getY() - 1) >= 0) {
                checkPath(node.getX() + 1, node.getY() - 1, node, eNode,
                        COST_DIAGONAL);
            }
            //right down
            if ((node.getX() + 1) < row && (node.getY() + 1) < column) {
                checkPath(node.getX() + 1, node.getY() + 1, node, eNode,
                        COST_DIAGONAL);
            }
            //delete from the openlist
            //add it into the closelist
            closeList.add(openList.remove(0));
            //sort the openlist, ordered by the value F
            Collections.sort(openList, new NodeFComparator());
        }
        if (isFind) {
            getPath(resultList, node);
        }
        return resultList;
    }
    /**
     * To find if this way can go.
     * @param x
     * @param y
     * @param parentNode
     * @param eNode
     * @param cost
     * @return
     */
    private boolean checkPath(int x, int y, Node parentNode, Node eNode,
                              int cost) {
        Node node = new Node(x, y, parentNode);
        //To find if there is a wall.
        if (map.getMyTable()[0][x][y].getClass().equals(Wall.class)) {
            closeList.add(node);
            return false;
        }
        //To find if it is in the closelist.
        if (isListContains(closeList, x, y) != -1) {
            return false;
        }
        int index = -1;
        //To find if it is in the openlist.
        if ((index = isListContains(openList, x, y)) != -1) {
            //To find if it is necessary to update the value of G and F.
            if ((parentNode.getG() + cost) < openList.get(index).getG()) {
                node.setParentNode(parentNode);
                countG(node, eNode, cost);
                countF(node);
                openList.set(index, node);
            }
        } else {
            // To add it into the openlist.
            node.setParentNode(parentNode);
            count(node, eNode, cost);
            openList.add(node);
        }
        return true;
    }
    /**
     * To find if there is some element in the list.
     * @param list
     * @param x
     * @param y
     * @return
     */
    private int isListContains(List<Node> list, int x, int y) {
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            if (node.getX() == x && node.getY() == y) {
                return i;
            }
        }
        return -1;
    }
    /**
     *  Go back to the start point from the end point and get the path.
     * @param resultList
     * @param node
     */
    private void getPath(List<Node> resultList, Node node) {
        if (node.getParentNode() != null) {
            getPath(resultList, node.getParentNode());
        }
        resultList.add(node);
    }

    private void count(Node node, Node eNode, int cost) {
        countG(node, eNode, cost);
        countH(node, eNode);
        countF(eNode);
    }

    private void countG(Node node, Node eNode, int cost) {
        if (node.getParentNode() == null) {
            node.setG(cost);
        } else {
            node.setG(node.getParentNode().getG() + cost);
        }
    }

    private void countH(Node node, Node eNode) {
        node.setF(Math.abs(node.getX() - eNode.getX())
                + Math.abs(node.getY() - eNode.getY()));
    }

    private void countF(Node node) {
        node.setF(node.getG() + node.getF());
    }

    public List<Node> getResult() {
        return result;
    }

    public void setResult(List<Node> result) {
        this.result = result;
    }
}
