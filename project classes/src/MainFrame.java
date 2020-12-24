import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame{
    MainFrame() {
        this.add(new PathPanel());
        this.setTitle("Pathfinder Visualizers");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); //centers middle of computer

    }
}
