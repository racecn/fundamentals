import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

public class Player
        extends GameObject
{
    Random r = new Random();
    Handler handler;

    public Player(float x, float y, float health, ID id, Handler handler)
    {
        super(x, y, health, id);
        this.handler = handler;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)this.x, (int)this.y, 32, 32);
    }

    public void tick()
    {
        this.x += this.velX;
        this.y += this.velY;

        if ( !Spawn.RedBoss) {
            this.x = Game.clamp(this.x, 0.0F, 683.0F);
            this.y = Game.clamp(this.y, 0.0F, 900.0F);
        } else if (Spawn.RedBoss) {
            this.x = Game.clamp(this.x, 0.0F, 683.0F);
            this.y = Game.clamp(this.y, 200.0F, 900.0F);
        }
        collision();
    }

    private void collision()
    {
        for (int i = 0; i < this.handler.object.size(); i++)
        {
            GameObject tempObject = (GameObject)this.handler.object.get(i);
            if ((tempObject.getId() == ID.BasicEnemy) && (getBounds().intersects(tempObject.getBounds()))) {
                HUD.HEALTH -= 2.0F;
            } else if ((tempObject.getId() == ID.SmartEnemy) && (getBounds().intersects(tempObject.getBounds()))) {
                HUD.HEALTH -= 2.0F;
            } else if ((tempObject.getId() == ID.BigEnemy) && (getBounds().intersects(tempObject.getBounds()))) {
                HUD.HEALTH -= 2.0F;
            } else if ((tempObject.getId() == ID.FastEnemy) && (getBounds().intersects(tempObject.getBounds()))) {
                HUD.HEALTH -= 2.0F;
            } else if ((tempObject.getId() == ID.BossEnemy) && (getBounds().intersects(tempObject.getBounds()))) {
                HUD.HEALTH -= 10.0F;
            }
        }
    }

    public void render(Graphics g)
    {
        if (this.id == ID.Player) {
            g.setColor(Color.lightGray);
        }
        g.setColor(Color.white);
        g.fillRect((int)this.x, (int)this.y, 32, 32);
    }
}
