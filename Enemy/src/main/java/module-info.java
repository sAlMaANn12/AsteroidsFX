import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.enemy.EnemyControlSystem;
import dk.sdu.mmmi.enemy.EnemyPlugin;

module Enemy {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

    provides IGamePluginService with dk.sdu.mmmi.enemy.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.enemy.EnemyControlSystem;
}