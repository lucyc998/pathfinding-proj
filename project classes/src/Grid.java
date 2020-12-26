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
    // static final int GAME_UINTS = (GRID_WIDTH * GRID_HEIGHT) / UNIT_SIZE;
    // ArrayList<Node> nodeArrayList = new ArrayList<>();
    Node[][] nodeArray = new Node[GRID_WIDTH/UNIT_SIZE][GRID_HEIGHT/UNIT_SIZE];
    Node nodeToFill;
    // static final int DELAY = 75;
    // Timer timer;

    private class MyListener extends MouseInputAdapter {
        public void mousePressed(MouseEvent e) {
            int x = e.getX() / UNIT_SIZE;
            int y = e.getY() / UNIT_SIZE;
            System.out.printf("(%d, %d)\n", x,y);
            nodeToFill = nodeArray[x][y];
            repaint();
        }
    }

    Grid() {
        this.setPreferredSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));
       // this.setFocusable(true); //figure out what this means
        MyListener myListener = new MyListener();
        addMouseListener(myListener);
        addMouseMotionListener(myListener);
        //   timer = new Timer(DELAY, this);
        //   timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        if (nodeToFill != null) {
            fillBox(g, nodeToFill, Color.CYAN);
        }
    }

    public void drawGrid(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i < GRID_WIDTH / UNIT_SIZE; i++) {
            for (int j = 0; j < GRID_HEIGHT / UNIT_SIZE; j++) {
                Node addNode = new Node(i * UNIT_SIZE, j * UNIT_SIZE, UNIT_SIZE);
                nodeArray[i][j] = addNode;
                // nodeArrayList.add(addNode);
                g.drawRect(
                        (int) addNode.box.getX(),
                        (int) addNode.box.getY(),
                        (int) addNode.box.getWidth(),
                        (int) addNode.box.getHeight()
                );
                //g.drawRect(i * UNIT_SIZE, j * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
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