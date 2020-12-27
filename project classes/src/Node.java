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

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Node)) {
            return false;
        }

        Node c = (Node) o;

        return xPos == c.xPos && yPos == c.yPos;
    }
}
