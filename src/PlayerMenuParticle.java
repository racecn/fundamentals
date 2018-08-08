
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class PlayerMenuParticle extends GameObject{
    private Handler handler;
    private GameObject player;
    public PlayerMenuParticle(float x, int y, float health, ID id, Handler handler){
        super(x,y, health, id);
        this.handler = handler;
        for(int i = 0; i < handler.object.size(); i++ ){
            if(handler.object.get(i).getId() == ID.MenuParticle) player = handler.object.get(i);
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
       // handler.addObject(new Trail((int)x,(int)y,ID.Trail,16,0.02f,16,Color.orange, handler));
    }
    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect((int)x,(int)y,48,48);
    }
}