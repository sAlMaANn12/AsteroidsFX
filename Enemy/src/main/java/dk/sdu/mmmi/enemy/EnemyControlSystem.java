package dk.sdu.mmmi.enemy;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import java.util.stream.Collectors;


import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {





    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            moveEntity(enemy);
            if (shouldFireBullet(System.currentTimeMillis(), (Enemy) enemy)) {
                spawnBullet((Enemy) enemy, gameData, world);
            }
            wrapAroundScreen(enemy, gameData);
        }
    }

    private void moveEntity(Entity entity) {
        double changeX = Math.cos(Math.toRadians(entity.getRotation()));
        double changeY = Math.sin(Math.toRadians(entity.getRotation()));
        entity.setX(entity.getX() + changeX);
        entity.setY(entity.getY() + changeY);
    }

    private boolean shouldFireBullet(long currentTime, Enemy enemy) {
        long fireNewBulletTime = enemy.getBulletsFired() + 1000;
        if (enemy.getBulletsFired() == 0 || currentTime >= fireNewBulletTime) {
            enemy.setBulletsFired(currentTime);
            return true;
        }
        return false;
    }

    private void spawnBullet(Enemy enemy, GameData gameData, World world) {
        for (BulletSPI bullet : getBulletSPIs()) {
            Entity bulletEntity = bullet.createBullet(enemy, gameData);
            bulletEntity.setRotation(0.1 + Math.random() * 359.9);
            enemy.addBullet((Bullet) bulletEntity);
            world.addEntity(bulletEntity);
        }
    }

    private void wrapAroundScreen(Entity entity, GameData gameData) {
        double displayWidth = gameData.getDisplayWidth();
        double displayHeight = gameData.getDisplayHeight();

        if (entity.getX() < 0) {
            entity.setX(displayWidth);
        } else if (entity.getX() > displayWidth) {
            entity.setX(0);
        }

        if (entity.getY() < 0) {
            entity.setY(displayHeight);
        } else if (entity.getY() > displayHeight) {
            entity.setY(0);
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}

