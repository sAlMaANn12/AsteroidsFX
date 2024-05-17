package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.enemy.Enemy;
import dk.sdu.mmmi.cbse.playersystem.Player;





public class CollisionDetector implements IPostEntityProcessingService {



    @Override
    public void process(GameData gameData, World world) {

        // Iterate through all entities in the world
        for (Entity entity : world.getEntities()) {
            for (Entity collideEntity : world.getEntities()) {

                // Skip if entity is colliding with itself or both entities are asteroids
                if (shouldSkipCollision(entity, collideEntity)) {
                    continue;
                }

                // Skip if the entity is a player or enemy and the collideEntity is their own bullet
                if (isOwnBullet(entity, collideEntity)) {
                    continue;
                }

                // Check for collision
                if (isColliding(entity, collideEntity)) {
                    handleCollision(entity, gameData, world, collideEntity);
                }
            }
        }
    }

    private boolean shouldSkipCollision(Entity entity, Entity collideEntity) {
        return entity == collideEntity || (entity instanceof Asteroid && collideEntity instanceof Asteroid);
    }

    private boolean isOwnBullet(Entity entity, Entity collideEntity) {
        if (entity instanceof Player) {
            return ((Player) entity).getBullets().contains(collideEntity);
        }
        if (entity instanceof Enemy) {
            return ((Enemy) entity).getBullets().contains(collideEntity);
        }
        return false;
    }

    private boolean isColliding(Entity entity, Entity collideEntity) {
        double centerXEntity = getCenterX(entity);
        double centerXCollideEntity = getCenterX(collideEntity);
        double centerYEntity = getCenterY(entity);
        double centerYCollideEntity = getCenterY(collideEntity);

        double distanceX = Math.abs(centerXEntity - centerXCollideEntity);
        double distanceY = Math.abs(centerYEntity - centerYCollideEntity);

        double minDistanceX = getMinDistance(entity.getWidth(), collideEntity.getWidth());
        double minDistanceY = getMinDistance(entity.getHeight(), collideEntity.getHeight());

        return distanceX < minDistanceX && distanceY < minDistanceY;
    }

    private double getCenterX(Entity entity) {
        return entity.getX() + entity.getWidth() / 2;
    }

    private double getCenterY(Entity entity) {
        return entity.getY() + entity.getHeight() / 2;
    }

    private double getMinDistance(double dimension1, double dimension2) {
        return dimension1 / 2 + dimension2 / 2;
    }

    private void handleCollision(Entity entity, GameData gameData, World world, Entity collideEntity) {
        if (entity instanceof Asteroid || entity instanceof Player || entity instanceof Enemy) {
            entity.handleCollision(gameData, world, collideEntity);
        }
    }

    @Override
    public void postProcess(GameData gameData, World world) {
        // Additional post-processing logic can be added here if needed
    }



}
