import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter{

    Handler handler;

    public Shop(Handler handler){
        this.handler = handler;
    }

    public void render(Graphics g) {
        if (Game.gameState == Game.STATE.Shop) {
            Font fnt = new Font("arial", 1, 75);
            Font fnt3 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.RED);
            g.drawString("SHOP", 120, 150);

            g.setFont(fnt3);
            g.setColor(Color.lightGray);
            g.drawString("Kill Points:" + HUD.KP, 267, 220);

            g.setFont(fnt3);
            g.setColor(Color.RED);
            g.drawString("Buy", 280, 585);
            g.setColor(Color.white);
            g.drawRect(200, 540, 300, 64);
        }
    }
    public void mousePressed(MouseEvent e){

        int mx = e.getX();
        int my = e.getY();

        if ( mouseOver(mx,my, 200,540,300,64)) {


                HUD.KP--;
                HUD.PlayerHealthMax += 50;
                HUD.HEALTH += 150;
                //AudioPlayer.getSound("menu_sound").play();
            }
        }



    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if ( mx > x && mx < x + width){
            if( my > y && my < y + height){
                return true;
            } else return false;
        }   else return false;

    }
}
