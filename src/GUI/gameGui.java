package GUI;
import Logic.Action;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javax.swing.*;

/**
 *
 * @author BaydoganMirac
 */
public class gameGui {
    private Action action = new Action();
    public JPanel gameP = new JPanel();
    private ImageIcon poinBG = new ImageIcon(new ImageIcon(getClass().getResource("gameBackground.jpg")).getImage().getScaledInstance(1000, 650, Image.SCALE_DEFAULT));
    public ImageIcon greenBG = new ImageIcon(new ImageIcon(getClass().getResource("yesil.png")).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
    public ImageIcon orangeBG = new ImageIcon(new ImageIcon(getClass().getResource("turuncu.png")).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
    public ImageIcon redBG = new ImageIcon(new ImageIcon(getClass().getResource("kirmizi.png")).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
    private JLabel mainBackground = new JLabel(poinBG);
    public JLabel counterBG = new JLabel(greenBG);
    public JLabel Qpoint = new JLabel("0");
    public JLabel Tpoint = new JLabel("0");
    
    public static int questionID = 0;
    public static String answerS ="";
    public JLabel userName = new JLabel("");
    public JLabel counter = new JLabel("");
    public JLabel question  = new JLabel();
    public JLabel answer = new JLabel();
    public JButton takeLetter = new JButton("Harf Alayım");
    public JButton answerMe = new JButton("Cevap Ver");
    public static int index = 0;
    public static char ch[];
    public static int Buffer[];
    public gameGui() throws SQLException {
        gameP.setVisible(false);
        gameP.setBounds(0,0,1000,650);
        gameP.setLayout(null);
        userName.setForeground(Color.decode("#bfbfbf"));
        userName.setFont(new Font("Corbel", Font.PLAIN, 24));
        userName.setBounds(25,27, 250,30);
        question.setForeground(Color.BLACK);
        question.setFont(new Font("Corbel", Font.BOLD, 20));
        question.setBounds(38,420,650,50);
        counter.setForeground(Color.decode("#bfbfbf"));
        counter.setFont(new Font("Corbel", Font.BOLD, 30));
        counter.setBounds(200,220,650,50);
        counterBG.setBounds(100,120,250,250);
        answer.setForeground(Color.BLACK);
        answer.setFont(new Font("Corbel", Font.BOLD, 20));
        answer.setBounds(80,485,650,50);
        Qpoint.setFont(new Font("Corbel", Font.PLAIN, 24));
        Qpoint.setForeground(Color.decode("#bfbfbf"));
        Qpoint.setBounds(790,240,100,50);
        Tpoint.setFont(new Font("Corbel", Font.PLAIN, 24));
        Tpoint.setForeground(Color.decode("#bfbfbf"));
        Tpoint.setBounds(910,240,100,50);
        takeLetter.setOpaque(false);
        takeLetter.setFocusPainted(false);
        takeLetter.setCursor(new Cursor(Cursor.HAND_CURSOR));
        takeLetter.setContentAreaFilled(false);
        takeLetter.setBorder(null);
        takeLetter.setBorderPainted(false);
        takeLetter.setFont(new Font("Corbel", Font.PLAIN, 24));
        takeLetter.setForeground(Color.decode("#bfbfbf"));
        takeLetter.setBounds(765,315,210,61);
        answerMe.setOpaque(false);
        answerMe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        answerMe.setFocusPainted(false);
        answerMe.setContentAreaFilled(false);
        answerMe.setBorder(null);
        answerMe.setBorderPainted(false);
        answerMe.setFont(new Font("Corbel", Font.PLAIN, 24));
        answerMe.setForeground(Color.decode("#bfbfbf"));
        answerMe.setBounds(765,402,210,61);
        mainBackground.setBounds(0,0,1000,650);
        gameP.add(question);
        gameP.add(answer);
        gameP.add(answerMe);
        gameP.add(takeLetter);
        gameP.add(Qpoint);
        gameP.add(Tpoint);
        gameP.add(counter);
        gameP.add(counterBG);
        gameP.add(userName);
        gameP.add(mainBackground);
        action.queryQuestion(this);
        action.createBuffer(this);
        answerMe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                action.isTrue(questionID, gameGui.this);
                if(action.Flag == 1){
                    action.queryQuestion(gameGui.this);
                    action.createBuffer(gameGui.this);
                }  
            }
        });

        takeLetter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.takeLetter(questionID, gameGui.this, ch, Buffer, index);
                
            }
        });

    }
    
}
