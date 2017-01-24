import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class GameView extends JPanel{
    GameView(){
        super();
        try {
            BufferedImage myPicture = ImageIO.read(new File("GameView.PNG"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            this.add(picLabel);
        } catch (IOException ignored) {}
    }
}
