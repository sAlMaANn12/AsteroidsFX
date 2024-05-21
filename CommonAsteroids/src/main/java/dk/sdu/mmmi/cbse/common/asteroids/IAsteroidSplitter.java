package dk.sdu.mmmi.cbse.common.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Defines an interface for splitting asteroid entities into smaller entities.
 * Implementing classes are responsible for splitting a given asteroid entity
 * into smaller entities and adding them to the provided game world. The specific
 * behavior of the splitting process, including the number of smaller asteroids,
 * their sizes, positions, and rotations, should be defined by the implementing class.
 *
 * @param e the asteroid entity to be split
 * @param w the game world where the new smaller asteroids will be added
 */
public interface IAsteroidSplitter {
    void createSplitAsteroid(Entity e, World w);
}
