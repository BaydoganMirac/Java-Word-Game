package GUI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author BaydoganMirac
 */
public class howtoplayGui {
    public JFrame howtoPlayJF = new JFrame("Nasıl Oynanır ?");
    private JPanel howtoPlayJP = new JPanel();
    private ImageIcon HTPimage = new ImageIcon(new ImageIcon(getClass().getResource("howToplay.jpg")).getImage().getScaledInstance(1000, 650, Image.SCALE_DEFAULT));
    private JLabel HTP = new JLabel(HTPimage);
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public howtoplayGui() {
        howtoPlayJF.setResizable(false);
        howtoPlayJF.setSize(1000,650);
        howtoPlayJF.setLocation(dim.width/2-howtoPlayJF.getSize().width/2, dim.height/2-howtoPlayJF.getSize().height/2);
        howtoPlayJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        howtoPlayJF.setVisible(false);
        howtoPlayJF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        howtoPlayJF.getContentPane().add(howtoPlayJP);
        HTP.setBounds(0, 0, 1000, 650);
        howtoPlayJP.add(HTP);
    }
    
}
