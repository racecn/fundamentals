
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class SmartEnemy extends GameObject{
    private Handler handler;
    private GameObject player;
    //private GameObject smart;
    public SmartEnemy(float x, int y, float health, ID id, Handler handler){
        super(x,y, health, id);
        this.handler = handler;
        for(int i = 0; i < handler.object.size(); i++ ){
            if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
            //if(handler.object.get(i).getId() == ID.SmartEnemy) smart = handler.object.get(i);
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int) x,(int) y, 16, 16);
    }
    public void tick(){
        x += velX;
        y += velY;
        float diffX = x - player.getX() - 16;
        float diffY = y - player.getY() - 16;
        float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY()));
        velX = ((-1 / distance) * diffX);
        velY = ((-1 / distance) * diffY);

        //if ( y <= 0 || y >= smart.getY() - 32) velY *= -1;
        //if ( x <= 0 || x >= smart.getX() - 16) velX *= -1;

        for ( int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);



            if (tempObject.getId() == ID.PlayerBullet && getBounds().intersects(tempObject.getBounds())) {
                this.health -= 2;
            }
            if ( health <= 0 ){
                handler.removeObject(this);
                health = 1;
                HUD.KP += 3;
            }
        }

        handler.addObject(new Trail((int)x,(int)y,0,ID.Trail,16,0.02f,16,Color.orange, handler));
    }
    public void render(Graphics g){
        g.setColor(Color.orange);
        g.fillRect((int)x,(int)y,16,16);
    }
}