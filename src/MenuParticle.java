import java.awt.*;

public class MenuParticle extends GameObject {

    private Handler handler;

    public MenuParticle(float x, float y, float health, ID id, Handler handler) {
        super(x, y, health, id);

        this.handler = handler;

        velX = 5/2;
        velY = 5/2;

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16,16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if ( y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if ( x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail((int)x,(int)y,0,ID.Trail,16,0.02f,16,Color.red, handler));
        //int x, int y, ID id, int width, float life, int height,Color color, Handler handler)
    }


    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 16,16);

    }
}
