package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;


public interface IPostEntityProcessingService {
    /**
     * Indicates that entity processing has been completed.
     *
     * @param gameData The game data containing information about the game state.
     * @param world    The game world containing entities and other game elements.
     * @precondition Entity processing is completed.
     * @postcondition Post-entity processing is completed.
     */

    void process(GameData gameData, World world);

    void postProcess(GameData gameData, World world);
}
