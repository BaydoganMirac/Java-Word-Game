package GUI;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author BaydoganMirac
 */
public class mainGui {
    private JFrame mainJF = new JFrame("Kelime Oyunu");
    private JPanel mainJP = new JPanel();
    private gameGui gg =null;
    private welcomeGui wg = null;
    private howtoplayGui htpg = null;
    private gameoverGui gog = null;
    public mainGui() throws SQLException {
        mainJF.setResizable(false);
        mainJF.setSize(1000,650);
        gg = new gameGui();
        htpg = new howtoplayGui();
        gog = new gameoverGui();
        wg  = new welcomeGui(gg, htpg, gog);
        mainJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainJF.setVisible(true);
        mainJF.getContentPane().add(mainJP);
        mainJP.setLayout(null);
        mainJP.add(wg.welcomeJP);
        mainJP.add(gog.gameoverP);
        mainJP.add(gg.gameP);
    }

}
