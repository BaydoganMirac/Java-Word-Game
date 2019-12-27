package GUI;
import Logic.Action;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author BaydoganMirac
 */
public class welcomeGui{
    public JPanel welcomeJP = new JPanel();
    private Action actions = new Action();
    private ImageIcon Wimage = new ImageIcon(new ImageIcon(getClass().getResource("welcomeBG.jpg")).getImage().getScaledInstance(1000, 650, Image.SCALE_DEFAULT));
    private JLabel welcomeBGL = new JLabel(Wimage);
    public String userName ;
    private JButton howToplay = new JButton("Nasıl Oynanır ❓");
    private JButton login = new JButton("Oyuna Başla");
    public welcomeGui(gameGui game, howtoplayGui htpg, gameoverGui gog) {
        
        welcomeJP.setLayout(null);
        welcomeJP.setBounds(0,0,1000,650);
        welcomeBGL.setBounds(0, 0, 1000, 650);
        login.setForeground(Color.decode("#bfbfbf"));
        login.setBounds(346,380,300,120);
        login.setFont(new Font("Corbel", Font.PLAIN, 45));
        login.setOpaque(false);
        login.setContentAreaFilled(false);
        login.setBorder(null);
        login.setFocusPainted(false);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        howToplay.setForeground(Color.decode("#bfbfbf"));
        howToplay.setBounds(380,530,240,50);
        howToplay.setFont(new Font("Corbel", Font.PLAIN, 25));
        howToplay.setOpaque(false);
        howToplay.setContentAreaFilled(false);
        howToplay.setBorder(null);
        howToplay.setFocusPainted(false);
        howToplay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actions.resetDB();
                actions.queryQuestion(game);  
                actions.loginScreen(game, welcomeGui.this, gog);
            }           
        });
        howToplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actions.howtoplayscreen(htpg);
            }
        });
        welcomeJP.add(login);
        welcomeJP.add(howToplay);
        welcomeJP.add(welcomeBGL);  

    }
    
    
}
