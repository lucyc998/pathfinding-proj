import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Grid extends JPanel implements ActionListener {
    static final int GRID_WIDTH = 600;
    static final int GRID_HEIGHT = 400;
    static final int UNIT_SIZE = 25;

    ArrayList<Node> nodeWall = new ArrayList<>();
    Node[][] nodeArray = new Node[GRID_WIDTH/UNIT_SIZE][GRID_HEIGHT/UNIT_SIZE];
    Node nodeToFill;
    Node start;
    Node end;

    private class MyListener extends MouseInputAdapter {
        public void mousePressed(MouseEvent e) {
            int x = e.getX() / UNIT_SIZE;
            int y = e.getY() / UNIT_SIZE;
            System.out.println("mouse pressed");
            System.out.printf("(%d, %d)\n", x,y);
            nodeToFill = nodeArray[x][y];
            repaint();
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


        public void mouseDragged(MouseEvent e) {
            int x = e.getX() / UNIT_SIZE;
            int y = e.getY() / UNIT_SIZE;
            System.out.printf("(%d, %d)\n", x,y);
            int nodeIndex = inArrayListCheck(nodeWall, nodeArray[x][y]);

            if (SwingUtilities.isRightMouseButton(e)) {
                // erases node wall on right click

               if (nodeIndex != -1) {
                   nodeWall.remove(nodeIndex);
               }
            }
            else {
                // adds node to node wall

                if (nodeWall.size() == 0) {
                    nodeWall.add(nodeArray[x][y]);
                }
                if (nodeIndex == -1) {
                    // only adds new nodes to node wall

                    nodeWall.add(nodeArray[x][y]);
                }
            }
            repaint();
        }
    }

    Grid() {
        this.setPreferredSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));
       // this.setFocusable(true); //figure out what this means
        MyListener myListener = new MyListener();
        addMouseListener(myListener);
        addMouseMotionListener(myListener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    public void drawGrid(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i < GRID_WIDTH / UNIT_SIZE; i++) {
            for (int j = 0; j < GRID_HEIGHT / UNIT_SIZE; j++) {
                Node addNode = new Node(i * UNIT_SIZE, j * UNIT_SIZE, UNIT_SIZE);
                nodeArray[i][j] = addNode;
                g.drawRect(
                        (int) addNode.box.getX(),
                        (int) addNode.box.getY(),
                        (int) addNode.box.getWidth(),
                        (int) addNode.box.getHeight()
                );
            }
        }
        if (nodeWall.size() != 0) {
            System.out.printf("wall size: %d\n", nodeWall.size());
            for (int i = 0; i < nodeWall.size(); i++) {
                fillBox(g, nodeWall.get(i), Color.GRAY);
            }
        }
    }
    public void fillBox(Graphics g, Node fillNode, Color fillColor) {
        g.setColor(fillColor);
        g.fillRect(
                (int) fillNode.box.getX(),
                (int) fillNode.box.getY(),
                (int) fillNode.box.getWidth(),
                (int) fillNode.box.getHeight()
        );
    }
    public void actionPerformed(ActionEvent e) {

    }
}