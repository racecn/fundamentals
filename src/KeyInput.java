import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class KeyInput
        extends KeyAdapter
{
    private Handler handler;
    Random r = new Random();
    private HUD hud;
    private boolean[] keyDown = new boolean[4];

    Game game;

    public KeyInput(Handler handler, Game game)
    {
        this.game = game;

        this.handler = handler;
        this.hud = this.hud;

        this.keyDown[0] = false;
        this.keyDown[1] = false;
        this.keyDown[2] = false;
        this.keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                if(key == KeyEvent.VK_UP) { tempObject.setVelY(-5); keyDown[0] = true;  }
                if(key == KeyEvent.VK_DOWN) { tempObject.setVelY(5); keyDown[1] = true; }
                if(key == KeyEvent.VK_RIGHT) { tempObject.setVelX(5); keyDown[2] = true; }
                if(key == KeyEvent.VK_LEFT) { tempObject.setVelX(-5); keyDown[3] = true; }
                if (key == KeyEvent.VK_SPACE) {
                    handler.addObject(new PlayerBullets((int) tempObject.getX() + 16, (int) tempObject.getY(), Health.Basic(), ID.PlayerBullet, handler));
                }
            }
        }
        if(key ==KeyEvent.VK_P){
            if (game.gameState == Game.STATE.Game ) {
                if (Game.paused) Game.paused = false;
               else Game.paused = true;
            }
        }
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);

        //open shop
        if(key == KeyEvent.VK_E){
            if (Game.gameState == Game.STATE.Game) Game.gameState = Game.STATE.Shop;
            else if (Game.gameState == Game.STATE.Shop) Game.gameState = Game.STATE.Game;
        }

    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                if(key == KeyEvent.VK_UP) keyDown[0] = false; //tempObject.setVelY(0);
                if(key == KeyEvent.VK_DOWN) keyDown[1] = false; //tempObject.setVelY(0);
                if(key == KeyEvent.VK_RIGHT) keyDown[2] = false; //tempObject.setVelX(0);
                if(key == KeyEvent.VK_LEFT) keyDown[3] = false; //tempObject.setVelX(0);
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }
        }
    }

}
