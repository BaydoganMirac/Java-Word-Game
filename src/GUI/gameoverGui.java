package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author BaydoganMirac
 */
public class gameoverGui {
    public JPanel gameoverP = new JPanel();
    public JLabel finishLabel = new JLabel("", SwingConstants.LEFT);
    private ImageIcon timeisupBG = new ImageIcon(new ImageIcon(getClass().getResource("timeisup.jpg")).getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT));
    private ImageIcon congratulationsBG = new ImageIcon(new ImageIcon(getClass().getResource("congratulations.jpg")).getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT));
    public JLabel finishedTime = new JLabel("", SwingConstants.LEFT);
    public JLabel timeisup = new JLabel(timeisupBG);
    public JLabel congratulations = new JLabel(congratulationsBG);
    private ImageIcon gameoverBG = new ImageIcon(new ImageIcon(getClass().getResource("gameoverguiBG.jpg")).getImage().getScaledInstance(1000, 650, Image.SCALE_DEFAULT));
    public JLabel gameoverBGL = new JLabel(gameoverBG);

    public gameoverGui() {
        gameoverP.setVisible(false);
        gameoverP.setLayout(null);
        gameoverP.setBounds(0,0,1000,650);
        gameoverBGL.setBounds(0, 0, 1000, 650);
        finishLabel.setBounds(380, 288, 650, 150);
        finishLabel.setForeground(Color.decode("#bfbfbf"));
        finishLabel.setFont(new Font("Corbel", Font.PLAIN, 24));
        finishedTime.setBounds(380, 320, 650, 150);
        finishedTime.setForeground(Color.decode("#bfbfbf"));
        finishedTime.setFont(new Font("Corbel", Font.PLAIN, 24));
        congratulations.setBounds(300,130, 400,250);
        timeisup.setBounds(300,130, 400,250);

    }
    
    
    
}
