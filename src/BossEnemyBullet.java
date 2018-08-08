import java.awt.*;
import java.util.Random;

public class BossEnemyBullet extends GameObject {

    private Handler handler;
    Random r = new Random();

    public BossEnemyBullet(float x, float y, float health, ID id, Handler handler) {
        super(x, y, health, id);

        this.handler = handler;

        velX = (r.nextInt( 5 - -5) + -5);
        velY = 5;

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 8,8);
    }

    public void tick() {
        x += velX;
        y += velY;

       // if ( y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        //if ( x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
        for (int i = 0; i < this.handler.object.size(); i++) {
            GameObject tempObject = (GameObject) this.handler.object.get(i);
            if (y > Game.HEIGHT) handler.removeObject(this);
        }
        // removed tahandler.addObject(new Trail((int)x,(int)y,ID.Trail,8,0.02f,8,Color.pink, handler));
        //int x, int y, ID id, int width, float life, int height,Color color, Handler handler)
    }


    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect((int)x, (int)y, 8,8);

    }
}
