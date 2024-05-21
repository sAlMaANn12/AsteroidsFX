package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * Defines a service provider interface (SPI) for creating bullet entities.
 * Implementing classes are responsible for creating new bullet entities based on
 * a source entity and the current game data. The behavior of bullet creation,
 * including its speed, direction, and appearance, will be determined by the
 * implementing class.
 *
 * @param e the source entity from which the bullet is created
 * @param gameData the current game data used to configure the bullet
 * @return a new bullet entity
 */
public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}
