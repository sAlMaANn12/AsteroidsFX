package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class EnemyPluginTest {

    public static void main(String[] args) {
        // Create instances of GameData and World
        GameData gameData = new GameData();
        World world = new World();

        // Create an instance of EnemyPlugin
        EnemyPlugin enemyPlugin = new EnemyPlugin();


        enemyPlugin.start(gameData, world);

        // Output some information to verify the creation of the enemy entity
        System.out.println("Enemy entity created: " + enemyPlugin.getEnemy());


        enemyPlugin.stop(gameData, world);

        // Output some information to verify the removal of the enemy entity
        System.out.println("Enemy entity removed.");
    }
}