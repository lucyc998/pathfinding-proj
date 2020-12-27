import javax.swing.JFrame;
import java.awt.*;

public class MainFrame extends JFrame{

    MainFrame() {
        this.setTitle("Pathfinder Visualizer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setLayout(new BorderLayout(50,100));
        this.add(new Grid(), BorderLayout.CENTER);
        this.add(new SelectionPanel(), BorderLayout.PAGE_END);
        this.pack();

        this.setVisible(true);
        this.setLocationRelativeTo(null); //centers middle of computer

    }
}
