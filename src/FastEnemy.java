import java.awt.*;

public class FastEnemy extends GameObject {

    private Handler handler;

    public FastEnemy(float x, float y, float health, ID id, Handler handler) {
        super(x, y,health, id);

        this.handler = handler;

        velX = 3;
        velY = 9;

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16,16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if ( y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if ( x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        for ( int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);



            if (tempObject.getId() == ID.PlayerBullet && getBounds().intersects(tempObject.getBounds())) {
                this.health -= 2;
            }
            if ( health <= 0 ){
                handler.removeObject(this);
                health = 1;
                HUD.KP += 2;
            }
        }

        handler.addObject(new Trail((int)x,(int)y,0,ID.Trail,16,0.02f,16,Color.CYAN, handler));
        //int x, int y, ID id, int width, float life, int height,Color color, Handler handler)
    }


    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int)x, (int)y, 16,16);

    }
}
