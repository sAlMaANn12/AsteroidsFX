package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Player extends Entity {

    private ArrayList<Bullet> bullets = new ArrayList<>();
    private long bulletsFired;
    private int hits;


    public void handleCollision(GameData gameData, World world, Entity collidingEntity) {
        if(collidingEntity.getClass() == Bullet.class) {
            if(hits < 3) {
                world.removeEntity(collidingEntity);
                hits += 1;
            } else {
                world.removeEntity(collidingEntity);
                world.removeEntity(this);
            }
        }
    }






    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void addBullet(Bullet bullet) {
        this.bullets.add(bullet);
    }


    public long getBulletsFired() {
        return bulletsFired;
    }

    public void setBulletsFired(long bulletsFired) {
        this.bulletsFired = bulletsFired;
    }
}

