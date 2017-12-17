
package MiniMarket;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
/**
 *
 * @author lenovo G400S
 */
public class panelGambar extends JPanel{
 
    private Image image; 
     public panelGambar() {
        image = new ImageIcon(getClass().getResource("/Gambar/back2.jpg")).getImage();
    }
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
     
        Graphics gd = (Graphics2D) g.create();
     
        gd.drawImage(image, 0,0,getWidth(),getHeight(), this);
        gd.dispose();
    }
}