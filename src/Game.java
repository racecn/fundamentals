import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.newdawn.slick.Music;

public class Game
        extends Canvas
        implements Runnable
{
    private static final long serialVersionUID = 7541542548355040915L;
    public static final int WIDTH = 720;
    public static final int HEIGHT = 960;
    private Thread thread;
    private boolean running = false;
    public static boolean paused = false;
    public int diff = 0;
    private Random r;
    private Handler handler;
    private HUD hud;
    private Shop shop;
    private Spawn spawner;
    private Menu menu;
    private int bgY1 = 0;
    private int bgY2 = 39616;
    private BufferedImage background = null;

    public static enum STATE
    {
        Menu,  Game,  Help,  End,  Select, Shop;

        private STATE() {}
    }

    public static Game.STATE gameState = Game.STATE.Menu;

    public Game()
    {
        this.handler = new Handler();
        this.hud = new HUD();
        shop = new Shop(handler);
        this.menu = new Menu(this, this.handler, this.hud);
        addKeyListener(new KeyInput(this.handler, this));
        addMouseListener(this.menu);

       // AudioPlayer.load();
       // AudioPlayer.getMusic("music").loop();

        new Window(720, 960, "GoBeam", this);

        this.spawner = new Spawn(this.handler, this.hud);

        this.r = new Random();

        handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT), Health.Basic(),  ID.MenuParticle,handler));
        handler.addObject(new PlayerMenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT), Health.Basic(), ID.PlayerMenuParticle,handler));

    }

    public synchronized void start()
    {
        this.thread = new Thread(this);
        this.thread.start();
        this.running = true;
    }

    public synchronized void stop()
    {
        try
        {
            this.thread.join();
            this.running = false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0D;
        double ns = 1.0E9D / amountOfTicks;
        double delta = 0.0D;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (this.running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1.0D)
            {
                tick();
                delta -= 1.0D;
            }
            if (this.running) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000L)
            {
                timer += 1000L;

                frames = 0;
            }
        }
        stop();
    }

    private void tick()
    {
        if (!paused && gameState != STATE.Shop)
        {

            this.handler.tick();

            if (gameState == Game.STATE.Game)
            {
                this.hud.tick();
                this.spawner.tick();
                if (HUD.HEALTH <= 0.0F)
                {
                    HUD.HEALTH = 100.0F;
                    this.hud.setLevel(1);
                    gameState = Game.STATE.End;
                    this.handler.clearEnemies();
                    for (int i = 0; i < 25; i++){
                        handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT), Health.Basic(), ID.MenuParticle,handler));
                    } handler.addObject(new PlayerMenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT), Health.Basic(), ID.PlayerMenuParticle,handler));
                }
                if (BossEnemy.BOSSHEALTH <= 0.0F)
                {
                    BossEnemy.BOSSHEALTH = 2000.0F;
                    this.hud.setLevel(1);
                    this.hud.score(-1);
                    gameState = Game.STATE.End;
                    this.handler.clearEnemies();
                    for (int i = 0; i < 25; i++){
                        handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT), Health.Basic(), ID.MenuParticle,handler));
                    } handler.addObject(new PlayerMenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT), Health.Basic(), ID.PlayerMenuParticle,handler));
                }
            }
            else if ((gameState == Game.STATE.Menu) || (gameState == Game.STATE.End) || (gameState != Game.STATE.Select )) {
                menu.tick();
                handler.tick();
            }
        }
    }

   /* public void SaveScore() throws IOException{
        FileWriter saveFile = new FileWriter("resources/hs.txt");
        saveFile.write(Integer.toString(hud.getScore()));
        saveFile.close();

        this.highscore = hud.getScore();
    }

    public void loadScore() throws IOException{
        BufferedReader saveFile = new BufferedReader(new FileReader("resources/hs.txt"));
        int highscore = saveFile.read();
        System.out.println("HIGHSCORE: " + highscore);
        System.out.println("Current Score: " + hud.getScore());
        saveFile.close();

        if ( hud.getScore() > highscore){
            saveScore();
            System.out.println("Saved...");
        } else { this.highscore = highscore; System.out.println("Not Saved.");
    }*/

    private void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (paused)
        {
            g.setColor(Color.WHITE);
            g.drawString("PAUSED!", 250, 300);
        }

        if (gameState == Game.STATE.Game) {
            this.hud.render(g);
            handler.render(g);
        } else if ((gameState == STATE.Shop)){
            shop.render(g);
        }else if((gameState == Game.STATE.Menu) || (gameState == Game.STATE.Select) || (gameState == Game.STATE.Help) || (gameState == Game.STATE.End)){
            this.menu.render(g);

        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max)
    {
        if (var >= max) {
            return var = max;
        }
        if (var <= min) {
            return var = min;
        }
        return var;
    }

    public static void main(String[] args)
    {
        new Game();
    }
}
