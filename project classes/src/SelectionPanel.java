import javax.swing.*;

public class SelectionPanel extends JPanel{
    private final JPanel selectionPanel = new JPanel();
    private final JButton button = new JButton("Start");;
    SelectionPanel() {
        selectionPanel.add(button);
    }
}
