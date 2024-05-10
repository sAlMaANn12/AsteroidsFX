package dk.sdu.mmmi.enemy;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {


            for (Entity enemy : world.getEntities(Enemy.class)) {
                if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                    enemy.setRotation(enemy.getRotation() - 5);
                }
                if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                    enemy.setRotation(enemy.getRotation() + 5);
                }
                if (gameData.getKeys().isDown(GameKeys.UP)) {
                    double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                    double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                    enemy.setX(enemy.getX() + changeX);
                    enemy.setY(enemy.getY() + changeY);
                }
                if(gameData.getKeys().isDown(GameKeys.SPACE)) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {world.addEntity(spi.createBullet(enemy, gameData));}
                    );
                }

                if (enemy.getX() < 0) {
                   enemy.setX(1);
                }

                if (enemy.getX() > gameData.getDisplayWidth()) {
                    enemy.setX(gameData.getDisplayWidth()-1);
                }

                if (enemy.getY() < 0) {
                    enemy.setY(1);
                }

                if (enemy.getY() > gameData.getDisplayHeight()) {
                    enemy.setY(gameData.getDisplayHeight()-1);
                }


            }
        }




    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
