package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * Processes entities in the game world before updating.
     *
     * @param gameData The game data containing information about the game state.
     * @param world    The game world containing entities and other game elements.
     * @precondition All processes are completed and the application is running.
     * @postcondition The required processing for the entities is completed.
     */
    void process(GameData gameData, World world);
}
