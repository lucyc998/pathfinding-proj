import java.util.ArrayList;
import java.lang.Math;

public class AStar {
    Node[][] gridNodes;
    ArrayList<Node> closed;
    ArrayList<Node> open;
    Node start;
    Node end;

    AStar(Node[][] gridNodes, ArrayList<Node> closed, Node start, Node end) {
        this.gridNodes = gridNodes;
        this.closed = closed;
        this.start = start;
        this.end = end;
    }

    /**
     * Helper function to perform heuristics calculation
     * using Pythagorean theorem between current node
     * and end node
     *
     * @param current
     */

    public void setHScore(Node current) {
        current.hScore = Math.sqrt(Math.pow(current.xPos - end.xPos, 2) + Math.pow(current.yPos - end.yPos, 2));
    }

    /**
     * Helper function to calculate gScore of current
     * node depending on whether it's diaganol to its parent
     *
     * @param current
     * @param diagonal
     */

    public void setGScore(Node current, boolean diagonal) {
        double distance = 10;
        if (diagonal) {
            distance = 14;
        }
        current.gScore = current.previous.gScore + distance;
    }

    /**
     * Helper function that adds gScore and hScore to get fScore
     *
     * @param current
     */

    public void setFScore(Node current) {
        current.fScore = current.gScore + current.hScore;
    }

    /**
     *  Helper function to find the index of the Node in ArrayList,
     *  returns -1 if Node is not found
     *
     * @param list ArrayList of Nodes
     * @param nodeCheck
     * @return index of Node
     */

    public int inArrayListCheck(ArrayList<Node> list, Node nodeCheck) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(nodeCheck)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Helper function to get the node with the lowest
     * fCost from open list
     *
     * @return index of Node
     */

    public int getLowestCostInd() {
        double min = start.fScore;
        int minIndex = 0;
        for (int i = 0; i < open.size(); i++) {
            if (open.get(i).fScore < min) {
                min = open.get(i).fScore;
                minIndex = i;
            }
            else if (open.get(i).fScore == min) {
                if (open.get(i).hScore < open.get(minIndex).hScore) {
                    min = open.get(i).fScore;
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    public void startSearch() {
        open.add(start);
        while (true) {
            int currentInd = getLowestCostInd();
            Node current = open.get(currentInd);
            open.remove(currentInd);
            closed.add(current);

            if (current.equals(end)) {
                return;
            }

            ArrayList<Node> neighbors = new ArrayList<>();
            int x = current.xPos;
            int y = current.yPos;
            setGScore(gridNodes[x-1][y], false); //north
            neighbors.add(gridNodes[x-1][y]);
            setGScore(gridNodes[x+1][y], false); //south
            neighbors.add(gridNodes[x+1][y]);
            setGScore(gridNodes[x][y+1], false); //east
            neighbors.add(gridNodes[x][y+1]);
            setGScore(gridNodes[x][y-1], false); //west
            neighbors.add(gridNodes[x][y-1]);
            setGScore(gridNodes[x-1][y+1], true); //northeast
            neighbors.add(gridNodes[x-1][y+1]);
            setGScore(gridNodes[x-1][y-1], true); //northwest
            neighbors.add(gridNodes[x-1][y-1]);
            setGScore(gridNodes[x+1][y+1], true); //southeast
            neighbors.add(gridNodes[x+1][y+1]);
            setGScore(gridNodes[x+1][y-1], true); //southwest
            neighbors.add(gridNodes[x+1][y-1]);

            for (int i = 0; i < neighbors.size(); i++) {
                if (inArrayListCheck(closed, neighbors.get(i)) == -1) {
                    //skips nodes that are in the closed list

                    continue;
                }

                int openInd = inArrayListCheck(open, neighbors.get(i));
                if (openInd != -1) {
                    if (open.get(openInd).fScore < neighbors.get(i).fScore) {
                        //skips node if it's already in the open list and its
                        //new fscore is higher than the old

                        continue;
                    }
                }

                //sets scores of nodes that are either not in the open list
                //or are in the open list, but with a lower fscore

                setHScore(neighbors.get(i));
                setFScore(neighbors.get(i));
                neighbors.get(i).previous = current;
                if (openInd == -1) {
                    //only adds new nodes to open list

                    open.add(neighbors.get(i));
                }
            }


        }
    }

}
