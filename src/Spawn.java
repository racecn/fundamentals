import java.util.Random;

public class Spawn {

    public static boolean RedBoss = false;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        scoreKeep++;

        //level change cap
        if (scoreKeep % 500 == 0) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            RedBoss = false;

           if (hud.getLevel() == 2) {
               handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Basic(), ID.BasicEnemy, handler));
               handler.addObject(new HealthBlob(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50,Health.Basic(), ID.Health, handler));
            }
            else if (hud.getLevel() == 3) {

               handler.addObject(new HealthBlob(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50,Health.Basic(), ID.Health, handler));
               handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50,Health.Basic(), ID.FastEnemy, handler));
            }
            else if (hud.getLevel() == 4) {

               handler.addObject(new HealthBlob(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50,Health.Basic(), ID.Health, handler));
               handler.addObject(new BigEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Big(), ID.BigEnemy, handler));
            }
            else if (hud.getLevel() == 5) {

                handler.addObject(new HealthBlob(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Basic(), ID.Health, handler));
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Smart(), ID.SmartEnemy, handler));
            }
            else if (hud.getLevel() == 6) {
               handler.addObject(new HealthBlob(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50,Health.Basic(), ID.Health, handler));
               handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Smart(), ID.SmartEnemy, handler));
            }
            else if (hud.getLevel() == 7) {
               handler.addObject(new HealthBlob(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Basic(), ID.Health, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Basic(), ID.BasicEnemy, handler));
            }
            else if (hud.getLevel() == 8) {
               handler.addObject(new HealthBlob(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Basic(), ID.Health, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Basic(), ID.BasicEnemy, handler));
            }
            else if (hud.getLevel() == 9) {
               handler.addObject(new HealthBlob(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Basic(), ID.Health, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, Health.Basic(), ID.BasicEnemy, handler));
            }
            else if (hud.getLevel() == 10) {
               RedBoss = true;
               handler.clearEnemies();

               handler.addObject(new BossEnemy((Game.WIDTH/2) - 150, -120, Health.Basic(), ID.BossEnemy, handler));
               handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH) - 50, 500, Health.Smart(), ID.SmartEnemy, handler));
           } else if ( hud.getLevel() == 11){
               handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH) - 50, 500, Health.Smart(), ID.SmartEnemy, handler));
           } else if ( hud.getLevel() == 12){
               handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH) - 50, 500, Health.Smart(), ID.SmartEnemy, handler));
           }

        }
    }


}
