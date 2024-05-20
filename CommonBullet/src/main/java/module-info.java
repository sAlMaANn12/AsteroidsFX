import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module CommonBullet {
    requires Common;
    exports dk.sdu.mmmi.cbse.common.bullet;


    provides IEntityProcessingService with SplitPackage.SplitPackage;
}