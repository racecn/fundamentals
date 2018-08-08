import java.awt.*;

public abstract class GameObject {

    //protected means that it can only be accessed by a child object "extends GameObject"
    protected float x, y;
    protected ID id;
    protected float velX, velY;
    protected float health;

    public GameObject( float x, float y, float health,ID id){
        this.x = x;
        this.y = y;
        this.health = health;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setId(ID id){
        this.id = id;
    }
    public ID getId(){
        return id;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }
    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }


}
