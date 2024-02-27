import javax.swing.*;
import java.awt.*;

public class GameFrame{

    private static final int BOARD_WIDTH=360;
    private static final int BOARD_LENGHT=640;
    private String title="BirdGame";
    public static int getBoardWidth(){
        return GameFrame.BOARD_WIDTH;
    }
    public static int getBoardLenght(){
        return GameFrame.BOARD_LENGHT;
    }

    Image chtImage;
    Character c;
    GameFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(BOARD_WIDTH,BOARD_LENGHT);
        jFrame.setTitle(title);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        chtImage= new ImageIcon(getClass().getResource("./imageCht.png")).getImage();
        c=new Character(chtImage);
        GamePanel gamePanel=new GamePanel();
        jFrame.add(gamePanel);
        jFrame.pack();
        gamePanel.requestFocus();
        jFrame.setVisible(true);
    }
}
