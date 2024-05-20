import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.enemy.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemy.EnemyPlugin;

module Enemy {
    exports dk.sdu.mmmi.cbse.enemy;
    requires Common;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

    provides IGamePluginService with EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.enemy.EnemyControlSystem, SplitPackage.SplitPackage;
}