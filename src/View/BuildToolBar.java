package View;

import javax.swing.JButton;
import javax.swing.JToolBar;

class BuildToolBar extends JToolBar{
    BuildToolBar(){
        super("Build Mode");
        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove");
        JButton rotateBtn = new JButton("Rotate");
        JButton moveBtn = new JButton("Move");
        JButton connectBtn = new JButton("Connect");

        setFloatable(false);
        setRollover(true);

        add(addBtn);
        add(removeBtn);
        add(rotateBtn);
        add(moveBtn);
        add(connectBtn);
    }
}
