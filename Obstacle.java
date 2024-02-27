import java.awt.*;

public class Obstacle {
    private static final int WIDTH=64;
    private static final int LENGHT=512;
    private int x=GameFrame.getBoardWidth();
    private int y=0;
    private int velocityX=-2;
    private Image image;
    private boolean scored=false;
    public Obstacle(Image image){
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
    public static int getLENGHT() {
        return LENGHT;
    }
    public static int getWIDTH() {
        return WIDTH;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getVelocityX(){
        return this.velocityX;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean getScored(){
        return this.scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }
}
