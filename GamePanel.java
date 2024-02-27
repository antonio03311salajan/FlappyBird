import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private static final int LENGHT=GameFrame.getBoardLenght();
    private static final int WIDTH=GameFrame.getBoardWidth();

    Image chtImage;
    Image obstImageUp;
    Image obstImageDown;
    Character c;
    Timer gameLoop;
    Timer drawObstFreq;
    ArrayList<Obstacle> burittos;

    int highScore;

    float score=0;

    boolean game=true;

    GamePanel(){
        highScore=0;
        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH,LENGHT));
        setBackground(new Color(180,255,255));
        chtImage= new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        obstImageUp= new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
        obstImageDown= new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        c=new Character(chtImage);
        burittos=new ArrayList<Obstacle>();
        drawObstFreq=new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawObstacles();
            }
        });
        drawObstFreq.start();
        gameLoop=new Timer(1000/120,this);
        gameLoop.start();
    }

    public void drawObstacles(){
        Obstacle topObst=new Obstacle(obstImageDown);
        topObst.setY((int)(topObst.getY()-Obstacle.getLENGHT()/4-Math.random()*(Obstacle.getLENGHT()/2)));
        burittos.add(topObst);

        Obstacle bottomObst=new Obstacle(obstImageUp);
        bottomObst.setY(topObst.getY()+GameFrame.getBoardLenght()/4 +Obstacle.getLENGHT());
        burittos.add(bottomObst);
    }
    public void motion(){
        c.setVelocityY(c.getVelocityY()+c.getGravity());
        c.setY(c.getY()+c.getVelocityY());
        c.setY(Math.max(c.getY(),0));
        for(int i=0;i<burittos.size();i++){
            Obstacle burrito=burittos.get(i);
            burrito.setX(burrito.getX()+burrito.getVelocityX());
            if(!burrito.getScored() && c.getX() > burrito.getX() + Obstacle.getWIDTH()){
                burrito.setScored(true);
                score+=0.5;
            }
            if(collision(c,burrito)){
                if(Math.max(score,highScore)==score) {
                    highScore = (int)score;
                    game = false;
                }
            }
        }
        if(c.getY()>GameFrame.getBoardLenght()){
            game=false;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(c.getImage(),c.getX(),c.getY(),Character.getWIDTH(),Character.getLENGHT(),null);
        for(int i=0;i<burittos.size();i++){
            Obstacle burrito=burittos.get(i);
            g.drawImage(burrito.getImage(),burrito.getX(),burrito.getY(),Obstacle.getWIDTH(),Obstacle.getLENGHT(),null);
        }
        g.setColor(Color.black);
        g.setFont(new Font(Font.SERIF, Font.PLAIN,  50));
        if(!game){
            g.drawString("Game over: " +String.valueOf((int)score),15,40);
            g.drawString("Highscore: " +String.valueOf((int)highScore),15,80);
        }else{
            g.drawString(String.valueOf((int)score),15,40);
        }

    }

    boolean collision(Character a, Obstacle b) {
        return a.getX() < b.getX() + Obstacle.getWIDTH() &&
                a.getX() + Character.getWIDTH() > b.getX() &&
                a.getY() < b.getY() + Obstacle.getLENGHT() &&
                a.getY() + Character.getLENGHT() > b.getY();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        motion();
        repaint();
        if(game==false){
            drawObstFreq.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            c.setVelocityY(-9);
        }
        if (!game){
            c.setY(GameFrame.getBoardLenght()/4);
            c.setVelocityY(0);
            burittos.clear();
            score=0;
            game=true;
            gameLoop.start();
            drawObstFreq.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
