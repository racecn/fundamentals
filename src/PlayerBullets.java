import java.awt.*;
import java.util.Random;

/**
 * Created by Maciej on 2015-05-09.
 */
public class PlayerBullets extends GameObject {

    private Handler handler;

    public PlayerBullets(int x, int y, float health, ID id, Handler handler) {
        super(x, y, health, id);
        velX = 0;
        velY = -5;
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 4, 4);
    }

    public void tick() {
        x += velX;
        y += velY;


        if(y <= 0) handler.removeObject(this);

        for ( int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);



            if (tempObject.getId() == ID.BasicEnemy && getBounds().intersects(tempObject.getBounds()) || tempObject.getId() == ID.BigEnemy && getBounds().intersects(tempObject.getBounds()) || tempObject.getId() == ID.FastEnemy && getBounds().intersects(tempObject.getBounds()) || tempObject.getId() == ID.SmartEnemy && getBounds().intersects(tempObject.getBounds()) || tempObject.getId() == ID.Health && getBounds().intersects(tempObject.getBounds()) ) {
                handler.removeObject(this);
            }
        }

        //handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 4, 4, 0.02f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval( (int)x,(int)y,4,4);
    }
}