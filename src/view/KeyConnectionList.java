package view;

import javax.swing.AbstractListModel;

public class KeyConnectionList extends AbstractListModel{
    private String[] keyConnections;

    KeyConnectionList(String[] keyConnections){
        this.keyConnections = keyConnections;
    }

    @Override
    public int getSize() {
        return (keyConnections.length);
    }

    @Override
    public String getElementAt(int index) {
        return (keyConnections[index]);
    }

    public void update(String[] keyConnections){
        this.keyConnections = keyConnections;
        fireContentsChanged(this, 0, getSize() - 1);
    }
}
