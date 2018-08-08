import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class HUD {

    public static float HEALTH = 100;

    private float greenValue = 255;

    private int score = 0;
    private int level = 1;

    public static float KP = 0;
    public static float PlayerHealthMax = 100;

    public void tick(){
        HEALTH = Game.clamp(HEALTH, 0 ,PlayerHealthMax);

        greenValue = Game.clamp(greenValue,0,255);
        greenValue = HEALTH*2;

        score++;
    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(15,15,200,32);
        g.setColor(new Color(75,(int)greenValue,0));
        g.fillRect(15,15, (int)HEALTH * 2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(15,  15,200,32);

        g.setColor(Color.red);
        g.drawString("Health: " + HEALTH, 15, 134 );
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
        g.drawString("Kill Points: " + KP, 15, 110);
        g.drawString("PRESS E TO SHOP",15,94);

    }

    public void score(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
}
