package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData) {

        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-10,0,-5,3,-3,6,3,6,5,3,10,0,3,-3,-3,-3);
        enemyShip.setWidth(20);
        enemyShip.setHeight(20);

        setStartPoint(gameData,enemyShip);

        return enemyShip;
    }




    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

    private void setStartPoint(GameData gameData, Entity enemySpaceship) {
        Random random = new Random();
        int randomizer = random.nextInt(4) + 1;

        double x = 0, y = 0, rotation = 0;

        switch (randomizer) {
            case 1:
                // Set enemy at the top
                y = 0;
                rotation = Math.random() * 180 + 0.1;
                break;
            case 2:
                // Set enemy at the bottom
                y = gameData.getDisplayHeight();
                rotation = Math.random() * -180 - 0.1;
                break;
            case 3:
                // Set enemy to the left
                x = 0;
                rotation = random.nextDouble() * 180 - 90;
                break;
            case 4:
                // Set enemy to the right
                x = gameData.getDisplayWidth();
                rotation = random.nextDouble() * -180 + 90;
                break;
        }

        enemySpaceship.setX(x != 0 ? x : Math.random() * gameData.getDisplayWidth());
        enemySpaceship.setY(y != 0 ? y : Math.random() * gameData.getDisplayHeight());
        enemySpaceship.setRotation(rotation);
    }



}
