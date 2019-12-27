package Logic;
import GUI.gameGui;
import GUI.gameoverGui;
import GUI.howtoplayGui;
import GUI.welcomeGui;
import java.awt.Color;
import java.sql.*;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author BaydoganMirac
 */
public class Action {
    private Timer timer = new Timer();
    private Timer t= new Timer();
    private TimerTask tt = null;
    private TimerTask counter = null;
    static String username;
    public static int diffCounter = -1;
    public static int difficulty = 1;
    public int Flag = 0;
    public int Qtime = 10;
    public static boolean running = false;
    public Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:DB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void resetDB(){
        String sql = "UPDATE questions SET status=0";
        difficulty = 1;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void loginScreen(gameGui gg, welcomeGui wg, gameoverGui gog){
        running = true;
        username = JOptionPane.showInputDialog(null,"Lütfen Kullanıcı Adı Giriniz", "Bilgi Ekranı", JOptionPane.QUESTION_MESSAGE);
        System.out.println(username);
        if(username == null || username.equals("")){
            JOptionPane.showMessageDialog(null,"Lütfen Kullanıcı Adı Giriniz");
            this.loginScreen(gg,wg,gog);
        }else{
        gg.userName.setText("Hoşgeldin " + username);
        changeScreen(wg.welcomeJP, gg.gameP);
        counter = new TimerTask(){
            int time = 3*60;
           @Override
           public void run(){
               if(running != false){
                    time -= 1;
                    String a = Integer.toString(time);
                    gg.counter.setText(a);
                   if(time > 100){
                     gg.counter.setForeground(Color.GREEN);
                     gg.counterBG.setIcon(gg.greenBG);
                   }else if(100 >= time && time >= 50){
                     gg.counter.setForeground(Color.ORANGE);
                     gg.counterBG.setIcon(gg.orangeBG);
                   }else{
                     gg.counter.setForeground(Color.RED);
                     gg.counterBG.setIcon(gg.redBG);
                   }
                   if(time == 0){
                       timer.cancel();
                       gameoverScreen(gg,gog);
                   }
               }
           }
        };
        tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println("4 olmadı");
                System.out.println(difficulty);
                if(difficulty ==4){
                    System.out.println(difficulty);
                    System.out.println("4 oldu");
                   gameoverScreen(gg,gog);
                   t.cancel();
               }            
            }
        };
        t.schedule(tt, 0,1000);
        timer.schedule(counter,0,1000);
        }
    }
    // Giriş Ekranından Oyun Ekranına Geçiş
    public void changeScreen(JPanel a, JPanel b){
        a.setVisible(false);
        b.setVisible(true);
    }
    public void createBuffer(gameGui gg){
        gg.Buffer = new int[gg.answerS.length()];
        gg.ch = new char[gg.answerS.length()];
        for(int i =0; i<gg.answerS.length(); i++){
            gg.Buffer[i] = i;
        }
        this.shuffleArray(gg.Buffer);
        // System.out.println("CH Boyutu" +gg.ch.length+ "\n"+ "Buffer Boyutu" +gg.Buffer.length); 
    }
 
    public void gameoverScreen(gameGui gg, gameoverGui gog){
        gg.gameP.setVisible(false);
        gog.finishLabel.setText(username + ", Topladığın Puan : " + gg.Tpoint.getText());
        gog.gameoverP.setVisible(true); 
        gog.gameoverP.add(gog.finishLabel);
        if(Integer.parseInt(gg.counter.getText()) == 0){
           gog.gameoverP.add(gog.timeisup);
           
        }else{
           gog.gameoverP.add(gog.congratulations);
           gog.finishedTime.setText(Integer.toString(180 - Integer.parseInt(gg.counter.getText()))+ " Saniye İçinde Cevapladın!");
           gog.gameoverP.add(gog.finishedTime);
        }
         gog.gameoverP.add(gog.gameoverBGL);
         
    }
    public void queryQuestion(gameGui gg){
        gg.index = 0;
        String sql = "SELECT * FROM questions WHERE status = 0 and difficulty ="+difficulty+" ORDER BY RANDOM() LIMIT 1";       
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
             gg.question.setText(rs.getString("question"));
             String DEFanswer = "";
             int strLen = rs.getString("answer").length();
             for(int i = 0; i< strLen; i++){
                 DEFanswer += " _";
             }
             gg.answer.setText(DEFanswer);
             gg.questionID = rs.getInt("id");
             gg.answerS = rs.getString("answer");
             gg.Qpoint.setText(Integer.toString(rs.getString("answer").length() * 100));
             gg.index = 0;
             diffCounter++;
             if(diffCounter%4==0){
                 difficulty++;
             }
             if(difficulty == 4){
                JOptionPane.showMessageDialog(null,"Sorularınız Bitmiştir","GAME OVER", JOptionPane.DEFAULT_OPTION);
             }
             
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void isTrue(int id, gameGui gg){
        running = false;
        int Tpoint = Integer.parseInt(gg.Tpoint.getText());
        int Qpoint = Integer.parseInt(gg.Qpoint.getText());
        String answer = JOptionPane.showInputDialog(null, "Cevabı Giriniz", "Cevap Kutusu", JOptionPane.INFORMATION_MESSAGE);
        if(answer == null ){
            System.out.println("hbujhb");
            JOptionPane.showMessageDialog(null,"Cevap Girmediğiniz İçin Puanınız 100 Puan Düşürüldü.","Bilgi Ekranı", JOptionPane.INFORMATION_MESSAGE);
            Tpoint -= 100;
            System.out.println(diffCounter);
            gg.Tpoint.setText(Integer.toString(Tpoint));
            running = true;
        }else{
            String sql = "SELECT * FROM questions WHERE id ="+id+"";
            try (Connection conn = this.connect();
                 Statement stmt  = conn.createStatement();
                 ResultSet rs    = stmt.executeQuery(sql)) {
                String donenCevap = answer.toUpperCase();
                if(donenCevap.equals(rs.getString("answer"))){
                    Flag = 1;
                    Tpoint +=Qpoint;
                    System.out.println(Qpoint);
                    System.out.println(Tpoint);
                    gg.Tpoint.setText(Integer.toString(Tpoint));
                    running = true;
                    // System.out.println("Action içinde Flag" + Flag);
                    String UpdateSQL = "UPDATE questions SET status=1 WHERE id ="+ id+"";
                    stmt.executeQuery(UpdateSQL);
                }else{
                    JOptionPane.showMessageDialog(null, "Girdiğiniz Cevap Yanlış");
                    Flag = 0;
                    running = true;
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void takeLetter(int id, gameGui gg, char ch[], int buffer[], int index){
        String sql = "SELECT * FROM questions WHERE id ="+id+"";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
             int strLen = rs.getString("answer").length();
            for(int i = 0; i<strLen; i++){
                if(ch[i] != rs.getString("answer").charAt(i)){
                    if(i == buffer[gg.index]){
                        ch[i] = rs.getString("answer").charAt(i);
                    }else{
                        ch[i] = '_';
                    }
                }
            }
            System.out.println(gg.index);
            gg.index++;
            String newAnswer = "";
            for(int i = 0; i<strLen; i++){
                newAnswer += " "+ch[i];
            }
            gg.answer.setText(newAnswer);
            if(gg.index == strLen-1){
                JOptionPane.showMessageDialog(null,"Harf Alma Hakkınız Kalmadı", "UYARI", JOptionPane.ERROR_MESSAGE);
            }
        
            int a = Integer.parseInt(gg.Qpoint.getText());
            a -=100;
            gg.Qpoint.setText(Integer.toString(a));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void howtoplayscreen(howtoplayGui htpg){
        htpg.howtoPlayJF.setVisible(true);
    }
   public static void shuffleArray(int[] ar)
     {
       Random rnd = ThreadLocalRandom.current();
       for (int i = ar.length - 1; i > 0; i--)
       {
         int index = rnd.nextInt(i + 1);
         int a = ar[index];
         ar[index] = ar[i];
         ar[i] = a;
       }
     }    
}
