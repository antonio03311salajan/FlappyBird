import java.awt.*;

public class Character {
    private Image image;
    private int x=GameFrame.getBoardWidth()/6;
    private int y=GameFrame.getBoardLenght()/4;
    private int velocityY=0;
    private int gravity=1;
    private static final int WIDTH=40;
    private static final int LENGHT=30;

    Character(Image image){
        this.image=image;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getLENGHT() {
        return LENGHT;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getGravity() {
        return gravity;
    }
}
