import java.awt.*;

public class Node {
    int xPos;
    int yPos;
    int size;
    Rectangle box;

    public Node(int xPos, int yPos, int size) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.box = new Rectangle(xPos, yPos, size, size);
    }
}
