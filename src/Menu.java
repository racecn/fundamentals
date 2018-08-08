import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter{

    private Game game;
    private Random r = new Random();
    private Handler handler;
    private HUD hud;

    public Menu(Game game, Handler handler,HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu){

            //play button
            if ( mouseOver(mx, my,200,400,300,64)){
               // game.gameState = Game.STATE.Game;
               // hud.score(0);
                //create player character
               // handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                //handler.clearEnemies();
               // handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.BasicEnemy, handler));

                game.gameState = Game.STATE.Select;

               // AudioPlayer.getSound("menu_sound").play();
                return;
            }
            //quit
            if( mouseOver(mx,my,200,540,300,64)){
                System.exit(1);
            }
            //help
            if(mouseOver(mx,my,200,470,300,64)){
                game.gameState = Game.STATE.Help;
                //AudioPlayer.getSound("menu_sound").play();
            }
        }
        if (game.gameState == Game.STATE.Select){

            //easy
            if ( mouseOver(mx, my,200,400,300,64)){
                game.gameState = Game.STATE.Game;
                hud.score(0);
                HUD.KP = 0;
                //create player character
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, Health.Basic(), ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Basic(), ID.BasicEnemy, handler));

                //game.diff = 0;
                //AudioPlayer.getSound("menu_sound").play();
            }
            //normal
            if( mouseOver(mx,my,200,540,300,64)){
                game.gameState = Game.STATE.Game;
                hud.score(0);
                HUD.KP = 0;
                //create player character
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, Health.Basic(), ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Basic(), ID.BasicEnemy, handler));

                //game.diff = 1;
            }
            //extreme
            if(mouseOver(mx,my,200,470,300,64)){
                game.gameState = Game.STATE.Game;
                hud.score(0);
                HUD.KP = 0;
                //create player character
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, Health.Basic(), ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Basic(), ID.BasicEnemy, handler));

               // game.diff = 2;
            }
            if(mouseOver(mx,my,200,470,300,64)){
                game.gameState = Game.STATE.Menu;
                //AudioPlayer.getSound("menu_sound").play();
                return;

            }
        }


        //backbutton for help
        if (game.gameState == Game.STATE.Help){
            if ( mouseOver(mx,my,200,540,300,64)){
                game.gameState = Game.STATE.Menu;
                //AudioPlayer.getSound("menu_sound").play();
                return;

            }
        }

        if ( game.gameState == Game.STATE.End){
            //retry
            if ( mouseOver(mx,my, 200,540,300,64)){
                game.gameState = Game.STATE.Menu;
                hud.score(0);
                HUD.KP = 0;
                HUD.HEALTH = HUD.PlayerHealthMax;
                //AudioPlayer.getSound("menu_sound").play();
            }
        }

    }

    public void mouseRelease(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if ( mx > x && mx < x + width){
            if( my > y && my < y + height){
                return true;
            } else return false;
        }   else return false;

    }

    public void tick(){

    }

    public void render(Graphics g) {
        if (game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", 1, 75);
            Font fnt2 = new Font("arial", 1, 75);
            Font fnt3 = new Font("arial", 1, 30);
            Font fnt4 = new Font("arial", 1, 15);

            g.setFont(fnt);
            g.setColor(Color.blue);
            g.drawString("GoBeam", 200, 150);
            g.setFont(fnt2);
            g.setColor(Color.CYAN);
            g.drawString("GoBeam", 203, 150);
            g.setFont(fnt4);
            g.setColor(Color.RED);
            g.drawString("Development Version 0.47", 235, 180);

            g.setFont(fnt3);
            g.setColor(Color.WHITE);
            g.drawString("Play", 315, 442);
            g.setFont(fnt3);
            g.setColor(Color.WHITE);
            g.drawString("Help", 315, 515);
            g.setFont(fnt3);
            g.setColor(Color.WHITE);
            g.drawString("Quit", 315, 583);
            g.setFont(fnt3);
            g.setColor(Color.WHITE);
            g.drawString("© Nathan Race 2018", 215, 640);

            //play
            g.setColor(Color.white);
            g.drawRect(200, 400, 300, 64);
            //help
            g.setColor(Color.white);
            g.drawRect(200, 470, 300, 64);
            //exit
            g.setColor(Color.white);
            g.drawRect(200, 540, 300, 64);
        } else if (game.gameState == Game.STATE.Select) {
            Font fnt = new Font("arial", 1, 75);
            Font fnt2 = new Font("arial", 1, 75);
            Font fnt3 = new Font("arial", 1, 30);
            Font fnt4 = new Font("arial", 1, 15);

            g.setFont(fnt);
            g.setColor(Color.blue);
            g.drawString("Difficulty", 200, 150);
            g.setFont(fnt2);
            g.setColor(Color.CYAN);
            g.drawString("Difficulty", 203, 150);
            g.setFont(fnt3);
            g.setColor(Color.green);
            g.drawString("Easy", 315, 442);
            g.setFont(fnt3);
            g.setColor(Color.orange);
            g.drawString("Normal", 300, 515);
            g.setFont(fnt3);
            g.setColor(Color.red);
            g.drawString("EXTREME", 280, 583);
            g.setColor(Color.WHITE);
            g.drawString("Back", 310, 650);

            //easy
            g.setColor(Color.white);
            g.drawRect(200, 400, 300, 64);
            //normal
            g.setColor(Color.white);
            g.drawRect(200, 470, 300, 64);
            //extreme
            g.setColor(Color.white);
            g.drawRect(200, 540, 300, 64);
            //back
            g.setColor(Color.white);
            g.drawRect(200, 610, 300, 64);
        } else if( game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial", 1, 75);
            Font fnt3 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.ORANGE);
            g.drawString("HELP", 255, 150);

            //for why will you be there

            g.setFont(fnt3);
            g.setColor(Color.WHITE);
            g.drawString("Back", 310, 583);
            g.setColor(Color.WHITE);
            g.drawString("Use the arrow keys to move your player.", 85, 283);
            g.drawString("Press ESC to quit.",85,320);
            g.setColor(Color.white);
            g.drawRect(200, 540, 300, 64);
        } else if( game.gameState == Game.STATE.End){
            if (hud.getScore() <= -1){
                Font fnt = new Font("arial", 1, 75);
                Font fnt3 = new Font("arial", 1, 30);
                g.setFont(fnt);
                g.setColor(Color.YELLOW);
                g.drawString("CONGRATULATIONS", 100, 120);
                g.drawString("YOU WIN!", 120, 150);

                g.setFont(fnt3);
                g.setColor(Color.RED);
                g.drawString("Play Again!", 280, 585);
                g.setColor(Color.white);
                g.drawRect(200, 540, 300, 64);

            } else {
                Font fnt = new Font("arial", 1, 75);
                Font fnt3 = new Font("arial", 1, 30);
                g.setFont(fnt);
                g.setColor(Color.RED);
                g.drawString("GAME OVER", 120, 150);

                g.setFont(fnt3);
                g.setColor(Color.lightGray);
                g.drawString("Score:" + hud.getScore(), 267, 220);

                g.setFont(fnt3);
                g.setColor(Color.RED);
                g.drawString("Try Again", 280, 585);
                g.setColor(Color.white);
                g.drawRect(200, 540, 300, 64);
            }
        } else if( game.gameState == Game.STATE.Shop){

                Font fnt = new Font("arial", 1, 75);
                Font fnt3 = new Font("arial", 1, 30);
                g.setFont(fnt);
                g.setColor(Color.RED);
                g.drawString("SHOP", 120, 150);

                g.setFont(fnt3);
                g.setColor(Color.lightGray);
                g.drawString("Kill Points:" + HUD.KP, 267, 220);

                g.setFont(fnt3);
                g.setColor(Color.RED);
                g.drawString("Buy", 280, 585);
                g.setColor(Color.white);
                g.drawRect(200, 540, 300, 64);

        }
    }
}
