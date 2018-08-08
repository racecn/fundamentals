import java.awt.*;
import java.util.Random;

public class BossEnemy extends GameObject {

    private Handler handler;

    public static float BOSSHEALTH = 500;

    Random r = new Random();


    private float greenValue = 255;

    private int timer = 50; //how far it moves
    private int timer2 = 50;

    public BossEnemy(float x, float y, float health, ID id, Handler handler) {
        super(x, y, health, id);

        this.handler = handler;

        velX = 0;
        velY = 2;

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 300,300);
    }

    public void tick() {
        BOSSHEALTH = Game.clamp(BOSSHEALTH, 0 ,100);

        greenValue = Game.clamp(greenValue,0,255);
        greenValue = BOSSHEALTH*2;



        x += velX;
        y += velY;

        if ( timer <= 0){
            velY = 0;
        } else timer --;

        if ( timer <= 0 ) timer2--;

        if (timer2 <= 0)
        {
            if(velX ==0) velX = 2;

            if(velX > 0)
                velX += 0.05f;
            else if ( velX < 0)
                velX -= 0.05f;

            velX = Game.clamp(velX,-10,10);

            int spawn = r.nextInt(7);
            if ( spawn == 0) handler.addObject( new BossEnemyBullet((int)x + 150, (int)y + 150, 0 ,ID.BasicEnemy,handler));

            for ( int i = 0; i < handler.object.size(); i++){

                GameObject tempObject = handler.object.get(i);
                if ((tempObject.getId() == ID.PlayerBullet) && (getBounds().intersects(tempObject.getBounds()))) {
                    BOSSHEALTH -= 2.0F;
                    handler.removeObject(tempObject);
                }
            }
        }

        //if ( y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if ( x <= 0 || x >= Game.WIDTH - 300) velX *= -1;

        // no trail handler.addObject(new Trail((int)x,(int)y,ID.Trail,96,0.008f,96,Color.red, handler));
        //int x, int y, ID id, int width, float life, int height,Color color, Handler handler)
    }


    public void render(Graphics g) {

        g.setColor(Color.GRAY);
        g.fillRect(300,15,200,32);
        g.setColor(new Color(75,(int)greenValue,0));
        g.fillRect(300,15, (int)BOSSHEALTH * 2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(300,  15,200,32);

        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 300,300);

    }
}
